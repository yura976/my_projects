import org.redisson.Redisson;
import org.redisson.api.RList;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class RedisStorage {
    private RedissonClient redisson;
    private final static String KEY = "ONLINE_USERS";
    private static final int SLEEP = 500;
    private RList<String> datingSiteUsers;
    private static final Logger log = LoggerFactory.getLogger(RedisStorage.class);
    private static final String MESSAGE = "- На главной странице показываем пользователя";
    private static final String ERROR = "Не удалось подключиться к Redis";

    void init() {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");
        try {
            redisson = Redisson.create(config);
        } catch (Exception Exc) {
            log.error(ERROR);
        }
        datingSiteUsers = redisson.getList(KEY);
    }

    boolean addUserWithoutPayment(int position, int user) {
        if (!datingSiteUsers.contains(String.valueOf(user))) {
            datingSiteUsers.add(position, String.valueOf(user));
            log.info(MESSAGE + " " + user);
            try {
                Thread.sleep(SLEEP);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return true;
        }
        return false;
    }

    void addUserWithPayment(int position, int user) {
        datingSiteUsers.add(position, String.valueOf(user));
        log.info(String.format("> Пользователь %d оплатил платную услугу", user));
        log.info(MESSAGE + " " + user);
        try {
            Thread.sleep(SLEEP);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    void deleteAllUsers(){ datingSiteUsers.clear(); }

    boolean containsAllUsers() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.add(String.valueOf(i + 1));
        }
        return datingSiteUsers.containsAll(list);
    }
}
