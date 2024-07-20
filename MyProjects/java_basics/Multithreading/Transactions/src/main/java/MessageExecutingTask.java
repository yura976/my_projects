import java.util.concurrent.TimeUnit;

public class MessageExecutingTask implements Runnable {
    private final MessageBroker messageBroker;
    private final MessageFactory messageFactory;
    private final Bank bank;
    private int stopThread = 0;
    private static final int MILLISECONDS_DURATION_TO_SLEEP_BEFORE_EXECUTING = 100;
    private static final int MAX_NUMBER_OF_MESSAGES = 1000;

    public MessageExecutingTask(MessageBroker messageBroker, Bank bank, MessageFactory messageFactory) {
        this.messageBroker = messageBroker;
        this.bank = bank;
        this.messageFactory = messageFactory;
    }

    @Override
    public void run() {
        try {
            while (stopThread++ < MAX_NUMBER_OF_MESSAGES) {
                TimeUnit.MILLISECONDS.sleep(MILLISECONDS_DURATION_TO_SLEEP_BEFORE_EXECUTING);
                messageBroker.executeMessages(bank, messageFactory);
            }
        } catch (final InterruptedException interruptedException) {
            Thread.currentThread().interrupt();
        }
    }
}