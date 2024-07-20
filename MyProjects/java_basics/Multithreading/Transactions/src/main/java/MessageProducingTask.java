import java.util.concurrent.TimeUnit;

public class MessageProducingTask implements Runnable {
    private final MessageBroker messageBroker;
    private final MessageFactory messageFactory;
    private int stopThread = 0;
    private static final int MILLISECONDS_DURATION_TO_SLEEP_BEFORE_PRODUCING = 100;
    private final int MAX_NUMBER_OF_MESSAGES = 1000;
    public MessageProducingTask(MessageBroker messageBroker, MessageFactory messageFactory) {
        this.messageBroker = messageBroker;
        this.messageFactory = messageFactory;
    }

    @Override
    public void run() {
        try {
            while (stopThread++ < MAX_NUMBER_OF_MESSAGES) {
                TimeUnit.MILLISECONDS.sleep(MILLISECONDS_DURATION_TO_SLEEP_BEFORE_PRODUCING);
                Message produceMessage = messageFactory.creatingRequest();
                messageBroker.produceMessages(produceMessage);
            }
        } catch (final InterruptedException interruptedException) {
            Thread.currentThread().interrupt();
        }
    }
}
