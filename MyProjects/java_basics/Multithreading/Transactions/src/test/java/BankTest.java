import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.util.Map;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BankTest {
    private static Map<String, Account> accountMap;
    private static Bank bank;
    private static final long TRANSFER_AMOUNT = 300;
    private static long sumAllAccounts;
    @BeforeAll
    public static void start(){
        accountMap = CreatingAccountsOfBank.accounts;
        sumAllAccounts = CreatingAccountsOfBank.getSumAllAccounts();
        bank = new Bank();
        accountMap.forEach((s, account) -> System.out.println(s + ". " + account));
    }
    @Test
    void transfer() {
        long amountFromToTransfer = getBalanceUponTransfer("1");
        long amountToBeforeTransfer = getBalanceUponTransfer("2");
        bank.transfer(accountMap.get("1").getAccNumber(), accountMap.get("2").getAccNumber(), TRANSFER_AMOUNT);
        long amountFromAfterTransfer = getBalanceUponTransfer("1");
        long amountToAfterTransfer = getBalanceUponTransfer("2");
        long sumAllAccountsAfterTransfer = bank.getSumAllAccounts();
        assertEquals(amountFromToTransfer - TRANSFER_AMOUNT,
                amountFromAfterTransfer);
        assertEquals(amountToBeforeTransfer + TRANSFER_AMOUNT,
                amountToAfterTransfer);
        assertEquals(sumAllAccounts, sumAllAccountsAfterTransfer);
    }
    private static long getBalanceUponTransfer(String nameAccount){
        return bank.getBalance(accountMap.get(nameAccount).getAccNumber());
    }

    @Test
    void getBalance(){
        String nameAccount = String.valueOf(new Random ().nextInt(10) + 1);
        long money = bank.getBalance(accountMap.get(nameAccount).getAccNumber());
        assertEquals(accountMap.get(nameAccount).getMoney(), money);
    }
    @Test
    void getIsBlokedBalance() {
        accountMap.get("1").setBloked(true);
        assertEquals(bank.getBalance(accountMap.get("1").getAccNumber()), -1);
    }
}