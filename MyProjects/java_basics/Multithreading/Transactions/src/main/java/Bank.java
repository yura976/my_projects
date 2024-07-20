import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Bank {
    private Map<String, Account> accounts;
    private final Map<String, Account> blockedAccounts;
    private final Random random = new Random();
    public Bank() {
        accounts = CreatingAccountsOfBank.accounts;
        blockedAccounts = new HashMap<>();
    }
    public synchronized boolean isFraud() {
        try {
            Thread.sleep(1000);
        } catch (final InterruptedException interruptedException) {
            Thread.currentThread().interrupt();
        }
        return random.nextBoolean();
    }

    public int transfer(String fromAccountName, String toAccountName,long amount) {
        boolean isFraud = false;
        boolean accountFromMoreAccountTo;
        String nameFromAccount = accountNameSearch(fromAccountName);
        String nameToAccount = accountNameSearch(toAccountName);
        Account fromAccount = accountSearch(nameFromAccount);
        Account toAccount = accountSearch(nameToAccount);
        if (amount > 50000) {
            isFraud = isFraud();
        }
        if (isFraud) {
            fromAccount.setBloked(true);
            toAccount.setBloked(true);
            blockedAccounts.put(nameFromAccount, fromAccount);
            blockedAccounts.put(nameToAccount, toAccount);
            return 0;
        } else if ((getBalance(fromAccountName) - amount) < 0){
            return -1;
        } else {
            accountFromMoreAccountTo = fromAccount.compareTo(toAccount) > 0;
            if (accountFromMoreAccountTo){
                doTransfer(nameFromAccount, nameToAccount, amount, accountFromMoreAccountTo);
                return 1;
            }
            doTransfer(nameToAccount, nameFromAccount, amount, accountFromMoreAccountTo);
            return 1;
        }
    }
    public long getBalance(String accountNum) {
        String nameAccount = accountNameSearch(accountNum);
        Account account = CreatingAccountsOfBank.accounts.get(nameAccount);
        if (account.isBloked()) return -1;
        synchronized (account) {
            return accounts.get(nameAccount).getMoney();
        }
    }
    private String accountNameSearch(String accNumber) {
        for (Map.Entry<String, Account> accountEntry : accounts.entrySet()) {
            if (accountEntry.getValue().getAccNumber().equals(accNumber))
                return accountEntry.getKey();
        }
        return null;
    }
    private Account accountSearch(String nameAccount){
        return CreatingAccountsOfBank.accounts.get(nameAccount);
    }
    private void doTransfer(String nameFromAccount, String nameToAccount, long amount, boolean accountFromMoreAccountTo) {
        Account externalAccount = accountSearch(nameFromAccount);
        Account internalAccount = accountSearch(nameToAccount);
        amount = accountFromMoreAccountTo ? amount : -amount;
        synchronized (externalAccount) {
            synchronized (internalAccount) {
                changeBalance(nameFromAccount, -amount);
                changeBalance(nameToAccount, amount);
            }
        }
    }
    private void changeBalance(String accName, long amount) {
        long money = accounts.get(accName).getMoney() + amount;
        CreatingAccountsOfBank.accounts.get(accName).setMoney(money);
    }
    public long getSumAllAccounts() {
        long sum = 0;
        for (Map.Entry<String, Account> account: CreatingAccountsOfBank.accounts.entrySet())
            sum += account.getValue().getMoney();
        return sum;
    }
}