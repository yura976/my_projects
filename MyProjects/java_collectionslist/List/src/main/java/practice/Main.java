package practice;

import java.util.Scanner;

public class Main {
    private static TodoList todoList = new TodoList();
    private static final String LIST = "LIST";
    private static final String ADD = "ADD";
    private static final String EDIT = "EDIT";
    private static final String DELETE = "DELETE";
    private static final String INFO_BEFORE_BEGIN = "Для работы со списком дел введите одну из команд:\n" +
            "-" + LIST + " - вывод дел;\n" +
            "-" + ADD + " - добавляет дело в конец или на определенное место;\n" +
            "-" + EDIT + " - заменяет дело с указанным номером;\n" +
            "-" + DELETE + " - удаляет дело по указанному номеру;\n" +
            "Для выхода из программы нажмите - \"Q\".";
    private static final String ENTERING_COMMAND = "Введите команду:";
    private static final String ENTERING_CASE_POSITION = "Введите позицию дела: ";
    private static final String ENTERING_CASE = "Введите дело: ";
    private static final String ADDING_LIST_TO_THE_END = "Добавить в конец списка (\"Y\" - да, \"N\" - нет):";
    public static void main(String[] args) {
        String text;
        String affair;
        int index;
        System.out.println(INFO_BEFORE_BEGIN);
        do  {
            System.out.println(ENTERING_COMMAND);
            text = new Scanner(System.in).nextLine();
            if (text.equals(LIST)) {
                System.out.println(todoList.getTodos());
            }
            if (text.equals(EDIT) || text.equals(DELETE)) {
                System.out.println(ENTERING_CASE_POSITION);
                index = new Scanner(System.in).nextInt();
                if (text.equals(EDIT)) {
                    System.out.println(ENTERING_CASE);
                    affair = new Scanner(System.in).nextLine();
                    todoList.edit(index, affair);
                }
                if (text.equals(DELETE)) {
                    todoList.delete(index);
                }
            }
            if (text.equals(ADD)) {
                System.out.println(ENTERING_CASE);
                affair = new Scanner(System.in).nextLine();
                System.out.println(ADDING_LIST_TO_THE_END);
                if (new Scanner(System.in).nextLine().equals("Y")) {
                    todoList.add(affair);
                } else {
                    System.out.println(ENTERING_CASE_POSITION);
                    index = new Scanner(System.in).nextInt();
                    todoList.add(index, affair);
                }
            }
        } while (!text.equals("Q"));
    }
}
