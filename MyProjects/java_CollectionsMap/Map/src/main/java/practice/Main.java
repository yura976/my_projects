package practice;

import java.util.Scanner;
import java.util.Set;

public class Main {
    private static final String LIST = "LIST";
    private static final String CONTACT_BY_PHONE = "CONTACT BY PHONE";
    private static final String CONTACT_BY_NAME = "CONTACT BY NAME";
    private static final String ADD = "ADD";
    private static final String QUIT = "0";
    private static final String INFO_BEFORE_BEGIN = "Введите одну из команд (для выхода нажмите - " + QUIT + "):\n" +
            LIST + " - выводит список всех контактов,\n" +
            CONTACT_BY_PHONE + " - выводит контакт по номеру телефона,\n" +
            CONTACT_BY_NAME + " - выводит контакт по имени,\n" +
            ADD + " - добавляет контакт.";
    private static final String ENTERING_CONTACT = "Введите имя и через пробел номер телефона: ";
    private static final String WRONG_COMMAND = "Неверная команда!";
    private static final String WRONG_PHONE_ANSWER = "Неверный формат контакта!";
    private static final String LIST_EMPTY = "Список пустой!";
    private static final String ENTERING_PHONE = "Введите номер телефона: ";
    private static final String ENTERING_NAME = "Введите имя: ";
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PhoneBook phoneBook = new PhoneBook();
        System.out.println(INFO_BEFORE_BEGIN);
        while (true) {
            String input = scanner.nextLine();
            if (input.equals(QUIT)) {
                break;
            }
            switch (input) {
                case LIST:
                    Set<String> allContacts = phoneBook.getAllContacts();
                    if (allContacts.isEmpty()) System.out.println(LIST_EMPTY);
                    else phoneBook.getAllContacts().forEach(System.out::println);
                    break;
                case CONTACT_BY_PHONE:
                    System.out.println(ENTERING_PHONE);
                    String phone = scanner.nextLine();
                    phone = phone.replaceAll("[^0-9]", "");
                    System.out.println(phoneBook.getContactByPhone(phone));
                    break;
                case CONTACT_BY_NAME:
                    System.out.println(ENTERING_NAME);
                    String name = scanner.nextLine();
                    phoneBook.getContactByName(name).forEach(System.out::println);
                    break;
                case ADD:
                    System.out.println(ENTERING_CONTACT);
                    String infoContact = scanner.nextLine();
                    String[] contact = infoContact.split(" ");
                    if (contact.length != 2) {
                        System.out.println(WRONG_PHONE_ANSWER);
                    } else {
                        System.out.println(phoneBook.addContact(contact[1], contact[0]));
                    }
                    break;
                default:
                    System.out.println(WRONG_COMMAND);
                    break;
            }
        }
    }
}
