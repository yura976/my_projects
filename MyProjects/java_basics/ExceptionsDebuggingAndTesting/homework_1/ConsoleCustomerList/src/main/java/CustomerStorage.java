import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Scanner;

public class CustomerStorage {
    private HashMap<String, Customer> storage;
    private final int COUNT_COMPONENTS_ADD = 4;
    private final int COUNT_COMPONENTS_REMOVE = 2;
    private static final Logger LOG = LoggerFactory.getLogger(CustomerStorage.class);
    public CustomerStorage() {
        storage = new HashMap<>();
    }

    public void addCustomer(String data) {
        LOG.info("Выполнен запрос команды - ADD.");
        Scanner scanner = new Scanner(System.in);
        String[] components = data.split("\\s+");
        if (components.length != COUNT_COMPONENTS_ADD) {
            throw new IncorrectNumberComponents(components.length, Main.INSTRUCTION_ADD, COUNT_COMPONENTS_ADD);
        }
        String name = components[0] + " " + components[1];
        String phoneNumber = "+" + phoneNumberCheck(components[3]);
        String email = emailCheck(components[2]);
        Customer customer = new Customer(name, phoneNumber, email);
        if (!storage.containsKey(name)) storage.put(name, customer);
        else {
            System.out.println("The client already exists. Replace this client(yes/no)?");
            if (scanner.nextLine().equalsIgnoreCase("yes")) storage.replace(name, customer);
        }
    }

    public void listCustomers() {
        LOG.info("Выполнен запрос команды - LIST.");
        if (storage.isEmpty()) {
            System.out.println("List is empty!");
        }
        storage.values().forEach(System.out::println);
    }

    public void removeCustomer(String name) {
        LOG.info("Выполнен запрос команды - REMOVE.");
        String[] tokens = name.split("\\s+");
        if (tokens.length != COUNT_COMPONENTS_REMOVE) {
            throw new IncorrectNumberComponents(tokens.length, Main.INSTRUCTION_REMOVE, COUNT_COMPONENTS_REMOVE);
        }
        if (!storage.containsKey(name)) {
            System.out.println("There is no such client.\nPlease enter correct details.");
            return;
        }
        storage.remove(name);
    }

    private String phoneNumberCheck(String phoneNumber) {
        String rezultPhone = "";
        String phone = phoneNumber.replaceAll("[^0-9]", "");
        if (phone.isEmpty()) throw new PhoneNumberError(phoneNumber);
        if (phone.charAt(0) != '7' && phone.charAt(0) != '8') {
            throw new PhoneNumberError(phoneNumber);
        } else if (phone.charAt(0) == '8' && phone.length() == 11) {
            String subPhone = "";
            for (int i = 1; i < phone.length(); i++) {
                subPhone = subPhone.concat(String.valueOf(phone.charAt(i)));
            }
            rezultPhone = rezultPhone.concat("7").concat(subPhone);
        } else if (phone.length() < 11) {
            if (phone.length() == 10) {
                rezultPhone = rezultPhone.concat("7").concat(phone);
            } else {
                throw new PhoneNumberError(phoneNumber);
            }
        } else {rezultPhone = phone;}
        return rezultPhone;
    }

    public String emailCheck(String email) {
        String regex = "[^<>()\\[\\]@,;:\"*\\\\/]+@[A-Za-z0-9-]+\\.[A-Za-z0-9-]+[A-Za-z]";
        if (!email.matches(regex)) throw new EmailError(email);
        return email;
    }
    public int getCount() {
        LOG.info("Выполнен запрос команды - COUNT.");
        return storage.size();
    }

    public Customer getCustomer(String name) {
        return storage.get(name);
    }
}