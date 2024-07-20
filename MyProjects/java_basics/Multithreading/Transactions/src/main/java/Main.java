import java.util.ArrayList;
import java.util.List;

public class Main {
    private static final String STATE_OF_ACCOUNTS_BEFORE_TRANSFERS =
            "In the bank account before the transfer is - %d RUB\n";
    private static final String STATE_OF_ACCOUNTS_AFTER_TRANSFERS =
            "In the bank account after the transfer is - %d RUB";
    private static final int MAX_AMOUNT_THREADS = 5;
    private static final int MIN_AMOUNT_THREADS = 0;

    public static void main(String[] args) throws InterruptedException {
        System.out.printf(STATE_OF_ACCOUNTS_BEFORE_TRANSFERS, CreatingAccountsOfBank.getSumAllAccounts());
        MessageFactory messageFactory = new MessageFactory();
        MessageBroker messageBroker = new MessageBroker();
        Bank bank = new Bank();
        MessageProducingTask messageProducingTask = new MessageProducingTask(messageBroker, messageFactory);
        MessageExecutingTask messageExecutingTask = new MessageExecutingTask(messageBroker, bank, messageFactory);
        List<Thread> threadProducingList = new ArrayList<>();
        List<Thread> threadExecutingList = new ArrayList<>();
        for (int i = MIN_AMOUNT_THREADS; i < MAX_AMOUNT_THREADS; i++) {
            threadProducingList.add(new Thread(messageProducingTask));
            threadExecutingList.add(new Thread(messageExecutingTask));
        }
        threadProducingList.forEach(Thread::start);
        threadExecutingList.forEach(Thread::start);
        waitForTasksFinished(threadProducingList);
        waitForTasksFinished(threadExecutingList);
        System.out.printf(STATE_OF_ACCOUNTS_AFTER_TRANSFERS, bank.getSumAllAccounts());
    }
    private static void waitForTasksFinished(final  List<Thread> threads) throws InterruptedException {
        for (final Thread thread : threads){
            thread.join();
        }
    }
}
