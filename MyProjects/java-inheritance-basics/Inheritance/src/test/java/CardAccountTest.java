import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import practice.BankAccount;
import practice.CardAccount;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Тест класса CardAccount")
public class CardAccountTest {

    public static final double DELTA = 0.01;

    private static final String notExpectedSumMessage = "Сумма на счете не соответствует ожидаемой";
    private BankAccount cardAccount;

    @BeforeEach
    public void setUp() {
        cardAccount = new CardAccount();
    }

    @Test
    @DisplayName("Метод put")
    void put() {
        cardAccount.put(10.0);
        assertEquals(10.0, cardAccount.getAmount(), DELTA, notExpectedSumMessage);
    }

    @Test
    @DisplayName("Метод put, попытка вызвать метод с отрицательной суммой (баланс не должен измениться)")
    void putNegativeAmount() {
        cardAccount.put(-1.0);
        assertEquals(0.0, cardAccount.getAmount(), DELTA, notExpectedSumMessage);
    }

    @Test
    @DisplayName("Метод take, put=102.0, take=100.0")
    void put102take100() {
        cardAccount.put(102.0);
        cardAccount.take(100.0);
        assertEquals(1.0, cardAccount.getAmount(), DELTA, notExpectedSumMessage);
    }

    @Test
    @DisplayName("Метод take, put=2.0, take=1.0")
    void put2take1() {
        cardAccount.put(2.0);
        cardAccount.take(1.0);
        assertEquals(0.99, cardAccount.getAmount(), DELTA, notExpectedSumMessage);
    }

    @Test
    @DisplayName("Метод take, put=100.0, take=20.0")
    void put100take20() {
        cardAccount.put(100.0);
        cardAccount.take(20.0);
        assertEquals(79.8, cardAccount.getAmount(), DELTA, notExpectedSumMessage);
    }

    @Test
    @DisplayName("Метод take, попытка снять со счета денег больше, чем на счете имеется")
    void takeTooMuchMoney() {
        cardAccount.put(1.0);
        cardAccount.take(3.0);
        assertEquals(1.0, cardAccount.getAmount(), DELTA, notExpectedSumMessage);
    }
}