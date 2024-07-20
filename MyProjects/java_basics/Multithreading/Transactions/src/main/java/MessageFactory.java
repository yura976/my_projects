import java.util.Random;

public class MessageFactory {
    private int nextMessageIndex;
    private static final String TEMPLATE_CREATED_MESSAGE_BALANCE = "Request %d: Account balance %s";
    private static final String TEMPLATE_EXECUTION_MESSAGE_BALANCE = "Report %d: Account balance %s is %d";
    private static final String TEMPLATE_ACCOUNT_BLOCKING_BALANCE = "Report %d: Account %s blocked";
    private static final String TEMPLATE_CREATED_MESSAGE_TRANSFER =
            "Request %d: Transfer from account %s to account %s - %d RUB";
    private static final String TEMPLATE_EXECUTION_MESSAGE_TRANSFER =
            "Report %d: Transfer from account %s to account %s for %d RUB - completed successfully";
    private static final String TEMPLATE_INSUFFICIENT_FUNDS =
            "Report %d: Transfer from account %s to account %s for %d RUB - insufficient funds";
    private static final String TEMPLATE_ACCOUNT_BLOCKING_TRANSFER =
            "Report %d: Transfer from account %s to account %s for %d RUB - fraud, accounts are blocked";
    private static final int INITIAL_MESSAGE_INDEX = 1;
    private static final long MAX_MONEY_VALUE = 53000;
    private static final long MIN_MONEY_VALUE = 100;

    public MessageFactory() {
        this.nextMessageIndex = INITIAL_MESSAGE_INDEX;
    }

    public Message creatingRequest() {
        if (new Random().nextInt(MessageType.values().length) == 0) {
            return createBalance(getRandomAccNumber());
        } else {
            String fromAccNumber = getRandomAccNumber();
            String toAccNumber = getRandomAccNumber();
            long money = new Random().nextLong(MAX_MONEY_VALUE) + MIN_MONEY_VALUE;
            while (fromAccNumber.equals(toAccNumber)) {
                toAccNumber = getRandomAccNumber();
            }
            return createTransfer(fromAccNumber, toAccNumber, money);
        }
    }
    private Message createBalance(String accNumber) {
        return new Message(String.format(TEMPLATE_CREATED_MESSAGE_BALANCE,
                findAndIncrementNextMessageIndex(), accNumber), MessageType.BALANCE_MESSAGE);
    }
    private Message createTransfer(String fromAccNumber, String toAccNumber, long money) {
        return new Message(String.format(TEMPLATE_CREATED_MESSAGE_TRANSFER,
                findAndIncrementNextMessageIndex(),fromAccNumber, toAccNumber, money),
                MessageType.TRANSFER_MESSAGE);
    }
    private String getRandomAccNumber() {
        String accountName =
                String.valueOf(new Random().nextInt(CreatingAccountsOfBank.AMOUNT_OF_ACCOUNTS) + 1);
        return CreatingAccountsOfBank.accounts.get(accountName).getAccNumber();
    }

    public Message creatingBalanceReport(int numberMessage, String accNumber, long money) {
        if (money < 0) return new Message(String.format(TEMPLATE_ACCOUNT_BLOCKING_BALANCE, numberMessage, accNumber),
                MessageType.BALANCE_MESSAGE);
        return new Message(String.format(TEMPLATE_EXECUTION_MESSAGE_BALANCE, numberMessage, accNumber, money),
                MessageType.BALANCE_MESSAGE);
    }
    public Message creatingTransferReport(int numberMessage, String fromAccNumber, String toAccNumber,
                                          long money, int hasTransfer) {
        if (hasTransfer > 0) {
            return new Message(String.format(TEMPLATE_EXECUTION_MESSAGE_TRANSFER,
                    numberMessage, fromAccNumber, toAccNumber, money), MessageType.TRANSFER_MESSAGE);
        } else if (hasTransfer == 0) {
            return new Message(String.format(TEMPLATE_ACCOUNT_BLOCKING_TRANSFER,
                    numberMessage,fromAccNumber, toAccNumber, money, fromAccNumber), MessageType.BALANCE_MESSAGE);
        } else {
            return new Message(String.format(TEMPLATE_INSUFFICIENT_FUNDS,
                    numberMessage, fromAccNumber, toAccNumber, money), MessageType.BALANCE_MESSAGE);
        }
    }
    private synchronized int findAndIncrementNextMessageIndex(){
        return nextMessageIndex++;
    }
}
