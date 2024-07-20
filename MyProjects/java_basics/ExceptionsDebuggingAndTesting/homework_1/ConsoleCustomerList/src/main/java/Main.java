import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

public class Main {
    private static final String addCommand = "add Василий Петров " +
            "vasily.petrov@gmail.com +79215637722";
    private static final String commandExamples = "\t" + addCommand + "\n" +
            "\tlist\n\tcount\n\tremove Василий Петров\n\texit";
    private static final String helpText = "Command examples:\n" + commandExamples;
    private static final String countText = "There are %d customers";
    private static final String GREETINGS = "Hello! You have started working in the \"Contact\" console application." +
            "\nTo continue, enter one of the commands shown below.";
    public static final String INSTRUCTION_ADD = "add";
    public static final String INSTRUCTION_COUNT = "count";
    public static final String INSTRUCTION_LIST = "list";
    public static final String INSTRUCTION_REMOVE = "remove";
    public static final String INSTRUCTION_HELP = "help";
    public static final String INSTRUCTION_EXIT = "exit";
    private static final Logger LOG = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        System.out.println(GREETINGS + "\n" + helpText);
        LOG.info("INFO");
        Scanner scanner = new Scanner(System.in);
        CustomerStorage executor = new CustomerStorage();
        for (; ; ) {
            try {
                String command = scanner.nextLine();
                String[] tokens = command.split("\\s+", 2);
                if (tokens[0].equalsIgnoreCase(INSTRUCTION_ADD)) {
                    executor.addCustomer(tokens[1]);
                } else if (tokens[0].equalsIgnoreCase(INSTRUCTION_LIST)) {
                    executor.listCustomers();
                } else if (tokens[0].equalsIgnoreCase(INSTRUCTION_REMOVE)) {
                    executor.removeCustomer(tokens[1]);
                } else if (tokens[0].equalsIgnoreCase(INSTRUCTION_COUNT)) {
                    System.out.printf((countText) + "%n", executor.getCount());
                } else if (tokens[0].equalsIgnoreCase(INSTRUCTION_HELP)) {
                    System.out.println(helpText);
                } else if (tokens[0].equalsIgnoreCase(INSTRUCTION_EXIT)) {
                    break;
                } else {
                    throw new InstructionError();
                }
            } catch(InstructionError e){
                LOG.error("Ошибка ввода команды - " + e);
            } catch (IncorrectNumberComponents e) {
                LOG.error("Некорректное количество компонентов - " + e);
            } catch (PhoneNumberError e) {
                LOG.error("Неверный формат номера телефона - " + e);
            } catch (EmailError e) {
                LOG.error("Неправильный формат email - " + e);
            }
        }
    }
}