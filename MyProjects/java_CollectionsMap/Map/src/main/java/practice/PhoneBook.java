package practice;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class PhoneBook {
    public static final String ERROR = "Неверный формат ввода!";
    private static final String NUMBER_EXISTS = "Такой номер существует!";
    private static final String NUMBER_NO_EXISTS = "Такого номера не существует!";
    private static final String NAME_NO_EXISTS = "Такого номера имени не существует!";
    private static final String CONTACT_ADD = "Контакт добавлен!";

    private TreeMap<String, String> phoneBook = new TreeMap<>();
    public String addContact(String phone, String name) {
        phone = isCorrectPhone(phone);
        name = isCorrectName(name);
        if (phone.equals(ERROR) || name.length() == 0) {return ERROR;}
        if (phoneBook.containsKey(name) && !phoneBook.get(name).equals(phone)) {
            phoneBook.put(name, phoneBook.get(name).concat(", ").concat(phone));
        } else if (phoneBook.containsValue(phone)) {
            for (Map.Entry entry : phoneBook.entrySet()) {
                if (entry.getValue().equals(phone)) {
                    phoneBook.remove(entry.getKey());
                }
            }
            phoneBook.put(name, phone);
            return NUMBER_EXISTS;
        } else {phoneBook.put(name, phone);}
        return CONTACT_ADD;
    }
    public String getContactByPhone(String phone) {
        boolean hasPhone = false;
        for (Map.Entry<String, String> entry : phoneBook.entrySet()) {
            String currentPhone = entry.getValue();
            currentPhone = currentPhone.replace(",", "");
            String[] allPhones = currentPhone.split(" ");
            if (allPhones.length > 1) {
                for (String isPhone : allPhones) {
                    if (isPhone.equals(phone)) hasPhone = true;
                }
            }
            if (currentPhone.equals(phone) || hasPhone) {
                return entry.getKey().concat(" - ").concat(phone);
            }
        }
        return NUMBER_NO_EXISTS;
    }
    public Set<String> getContactByName(String name) {
        Set<String> contacts = new TreeSet<>();
        if (!phoneBook.isEmpty()) {
            for (Map.Entry<String, String> entry : phoneBook.entrySet()) {
                if (entry.getKey().equals(name)) {
                    contacts.add(entry.getKey().concat(" - ").concat(entry.getValue()));
                }
            }
        }
        if (contacts.isEmpty()) System.out.println(NAME_NO_EXISTS);
        return contacts;
    }
    public Set<String> getAllContacts() {
        Set<String> contacts = new TreeSet<>();
        if (!phoneBook.isEmpty()) {
            for (Map.Entry<String, String> entry : phoneBook.entrySet()) {
                contacts.add(entry.getKey().concat(" - ").concat(entry.getValue()));
            }
        }
        return contacts;
    }
    private String isCorrectPhone(String phoneNumber) {
        String phone = phoneNumber.replaceAll("[^0-9]", "");
        String rezultPhone = "";
        if (phone.isEmpty() || phone.length() > 11 || phone.length() < 10) {
            return ERROR;
        }
        if (phone.charAt(0) != '7' && phone.charAt(0) != '8' && phone.length() == 11) {
            rezultPhone = ERROR;
        } else if (phone.charAt(0) == '8' && phone.length() == 11) {
            String subPhone = "";
            for (int i = 1; i < phone.length(); i++) {
                subPhone = subPhone.concat(String.valueOf(phone.charAt(i)));
            }
            rezultPhone = rezultPhone.concat("7").concat(subPhone);
        } else if (phone.length() == 10) {
            rezultPhone = rezultPhone.concat("7").concat(phone);
        } else rezultPhone = phone;
        return rezultPhone;
    }
    private String isCorrectName(String name) {
        return name.replaceAll("[^А-Яа-я]", "");
    }
}