package org.example.worker;

import org.example.Person;
import org.example.error.EmailError;
import org.example.error.IncorrectNumberComponents;
import org.example.error.InstructionError;
import org.example.error.PhoneNumberError;
import org.example.manager.ApplicationManager;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class ApplicationWorker {
    private final ApplicationManager applicationManager;
    private final ContactWorker contactWorker;
    private final FileWorker fileWorker;
    private final InstructionError instructionError;
    private final IncorrectNumberComponents incorrectNumberComponents;
    private final PhoneNumberError phoneNumberError;
    private final EmailError emailError;
    private static final String GREETINGS = "Hello! You have started working in the \"Contact\" console application." +
            "\nTo continue, enter one of the commands shown below.";
    private static final String addCommand = "add Василий Петров;+79215637722;vasily.petrov@gmail.com";
    private static final String commandExamples = "\t" + addCommand + "\n" +
            "\tlist\n\tremove vasily.petrov@gmail.com\n\twrite\n\texit\nEnter the following command";
    private static final String helpText = "Command examples:\n" + commandExamples;
    private final String THANKS = "THANK YOU FOR WORKING WITH OUR APPLICATION.";
    public static final String INSTRUCTION_ADD = "add";
    public static final String INSTRUCTION_LIST = "list";
    public static final String INSTRUCTION_REMOVE = "remove";
    public static final String WRITE_TO_FILE = "write";
    public static final String INSTRUCTION_HELP = "help";
    public static final String INSTRUCTION_EXIT = "exit";
    public static final int COMPONENT_COUNT_CONTACT = 3;

    public ApplicationWorker(ContactWorker contactWorker, FileWorker fileWorker, InstructionError instructionError,
                             IncorrectNumberComponents incorrectNumberComponents, PhoneNumberError phoneNumberError,
                             EmailError emailError, ApplicationManager applicationManager) {
        this.contactWorker = contactWorker;
        this.fileWorker = fileWorker;
        this.instructionError = instructionError;
        this.incorrectNumberComponents = incorrectNumberComponents;
        this.phoneNumberError = phoneNumberError;
        this.emailError = emailError;
        this.applicationManager = applicationManager;
    }
    public void applicationMenu() {
        System.out.println(GREETINGS + "\n" + helpText);
        contactWorker.setPersonMap(applicationManager.creatingContactList());
        while (true){
            String operation = new Scanner(System.in).nextLine();
            String[] tokens = operation.split("\\s+", 2);
            if (tokens[0].equalsIgnoreCase(INSTRUCTION_ADD)) {
                Person person = createContact(tokens[1], INSTRUCTION_ADD);
                if (person != null) contactWorker.addContact(person);
            } else if (tokens[0].equalsIgnoreCase(INSTRUCTION_LIST)) {
                contactWorker.printAllContacts();
            } else if (tokens[0].equalsIgnoreCase(INSTRUCTION_REMOVE)) {
                if (tokens.length != 2) {
                    incorrectNumberComponents.infoError(tokens.length, INSTRUCTION_REMOVE, 2);
                } else contactWorker.deleteContact(tokens[1]);
            } else if (tokens[0].equalsIgnoreCase(WRITE_TO_FILE)) {
                fileWorker.writeToFile(contactWorker.getPersonMap());
            }else if (tokens[0].equalsIgnoreCase(INSTRUCTION_HELP)) {
                System.out.println(helpText);
            } else if (tokens[0].equalsIgnoreCase(INSTRUCTION_EXIT)) {
                System.out.println(THANKS);
                break;
            } else instructionError.infoError();
        }
    }

    private Person createContact(String contact, String instruction) {
        String [] tokens = contact.split(";");
        Person person = null;
        String fullName = tokens[0].trim();
        String phone = "+".concat(phoneNumberCheck(tokens[1]));
        String email = emailCheck(tokens[2]);
        if (tokens.length != COMPONENT_COUNT_CONTACT) {
            incorrectNumberComponents.infoError(tokens.length, instruction, COMPONENT_COUNT_CONTACT);
        } else if (phone == null) {
            phoneNumberError.infoError(tokens[1]);
        } else if (email == null) {
            emailError.infoError(tokens[2]);
        } else person = new Person(fullName, phone, email);
        return person;
    }

    private String phoneNumberCheck(String phoneNumber) {
        String phone = phoneNumber.replaceAll("[^0-9]", "");
        String rezultPhone = "";
        if (phone.isEmpty() || phone.length() > 11 || phone.length() < 10) {
            return null;
        }
        if (phone.charAt(0) != '7' && phone.charAt(0) != '8' && phone.length() == 11) {
            rezultPhone = null;
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
    private String emailCheck(String email) {
        String resultEmail = email.trim();
        String regex = "[^<>()\\[\\]@,;:\"*\\\\/]+@[A-Za-z0-9-]+\\.[A-Za-z0-9-]+[A-Za-z]";
        if (!resultEmail.matches(regex)) return null;
        return resultEmail;
    }
}
