import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class CreatingAccountsOfBank {
    public static final int AMOUNT_OF_ACCOUNTS = 10;
    private static final long MAX_MONEY_VALUE = 50000;
    private static final long MIN_MONEY_VALUE = 100;
    private static final int FIRST_DIGIT_ACCOUNT = 405;
    private static final int FIRST_DIGIT_ACCOUNT_INTERVAL = 5;
    private static final int AMOUNT_LAST_DIGIT_ACCOUNT = 17;
    private static final int INTERVAL_DIGIT = 10;
    public static Map<String, Account> accounts = setAccounts();
    private static final long sumAllAccounts = setSumAllAccounts();
    private static Map<String, Account> setAccounts() {
        Map<String, Account> accountMap= new HashMap<>();
        for (int i = 0; i < AMOUNT_OF_ACCOUNTS; i++) {
            Account account = new Account();
            account.setAccNumber(generationAccNumber());
            account.setMoney(new Random().nextLong(MAX_MONEY_VALUE) + MIN_MONEY_VALUE);
            accountMap.put(String.valueOf(i + 1), account);
        }
        return accountMap;
    }
    private static String generationAccNumber() {
        StringBuilder stringBuilder = new StringBuilder();
        Random random = new Random();
        stringBuilder.append(random.nextInt(FIRST_DIGIT_ACCOUNT_INTERVAL) + FIRST_DIGIT_ACCOUNT);
        for (int i = 0; i < AMOUNT_LAST_DIGIT_ACCOUNT; i++) {
            stringBuilder.append(random.nextInt(INTERVAL_DIGIT));
        }
        return stringBuilder.toString();
    }
    private static long setSumAllAccounts() {
        long sumMoney = 0;
        for (Map.Entry<String, Account> account: accounts.entrySet()) {
            sumMoney += account.getValue().getMoney();
        }
        return sumMoney;
    }
    public static long getSumAllAccounts() {
        return sumAllAccounts;
    }
}
