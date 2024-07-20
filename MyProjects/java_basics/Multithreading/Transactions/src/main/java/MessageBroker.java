import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MessageBroker {
    private Queue<Message> messageStore;
    private final int maxStoredMessage;
    private static final int QUEUE_SIZE_INDEX = 10;
    public MessageBroker() {
        messageStore = new ArrayDeque<>();
        maxStoredMessage = QUEUE_SIZE_INDEX;
    }
    public synchronized void produceMessages(Message message) {
        try {
            while (!isShouldProduce()) {
                wait();
            }
            messageStore.add(message);
            System.out.println(message);
            notify();
        } catch (final InterruptedException interruptedException) {
            Thread.currentThread().interrupt();
        }
    }
    public synchronized void executeMessages(Bank bank, MessageFactory messageFactory) {
        try {
            while (isShouldExecute()) {
                wait();
            }
            Message message = messageStore.poll();
            if (isBalance(message)) {
                System.out.println(executeBalanceMessage(message, bank, messageFactory));
            }
            else {
                System.out.println(executeTransferMessage(message, bank, messageFactory));
            }
            notify();
        } catch (final InterruptedException interruptedException) {
            Thread.currentThread().interrupt();
        }
    }
    private Message executeBalanceMessage(Message message, Bank bank, MessageFactory messageFactory) {
        List<String> dataList = getMessageData(message);
        int numberMessage = Integer.parseInt(dataList.get(0));
        String accountNum = dataList.get(1);
        long money = bank.getBalance(accountNum);
        return messageFactory.creatingBalanceReport(numberMessage, accountNum, money);
    }
    private Message executeTransferMessage(Message message, Bank bank, MessageFactory messageFactory) {
        List<String> dataList = getMessageData(message);
        int numberMessage = Integer.parseInt(dataList.get(0));
        String fromAccountName = dataList.get(1);
        String toAccountName = dataList.get(2);
        long amount = Long.parseLong(dataList.get(3));
        int hasTransfer = bank.transfer(fromAccountName, toAccountName, amount);
        return messageFactory.creatingTransferReport(
                numberMessage, fromAccountName, toAccountName, amount, hasTransfer);
    }
    private boolean isShouldProduce() {
        return messageStore.size() < maxStoredMessage;
    }
    private boolean isShouldExecute() {
        return messageStore.isEmpty();
    }
    private boolean isBalance(Message message) {
        return message.getMessageType() == MessageType.BALANCE_MESSAGE;
    }
    private List<String> getMessageData(Message message) {
        List<String> dataAboutTransfer = new ArrayList<>();
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(message.getData());
        while (matcher.find()) {
            dataAboutTransfer.add(matcher.group());
        }
        return dataAboutTransfer;
    }
}
