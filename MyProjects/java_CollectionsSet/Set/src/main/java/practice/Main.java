package practice;

import java.util.Scanner;

public class Main {
    private static final String LIST = "LIST";
    private static final String ADD = "ADD";
    private static final String QUIT = "0";
    private static final String INFO_BEFORE_BEGIN = "Введите одну из команд (для выхода нажмите - " + QUIT + "):\n" +
            "LIST - выводит список электронных адресов.\n" +
            "ADD - добавляет электронный адрес.";
    private static final String WRONG_EMAIL_ANSWER = "Неверный формат email или такой адрес уже существует!";
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        EmailList emailList = new EmailList();

        while (true) {
            System.out.println(INFO_BEFORE_BEGIN);
            String input = scanner.nextLine();
            if (input.equals(QUIT)) {
                break;
            }
            if (input.equals(LIST)) {
                for (String email:emailList.getSortedEmails()) {
                    System.out.println(email);
                }
            }
            if (input.equals(ADD)) {
                int sizeList = emailList.getSortedEmails().size();
                String email = scanner.nextLine();
                emailList.add(email);
                if (emailList.getSortedEmails().size() == sizeList) {
                    System.out.println(WRONG_EMAIL_ANSWER);
                }
            }
        }
    }
}
