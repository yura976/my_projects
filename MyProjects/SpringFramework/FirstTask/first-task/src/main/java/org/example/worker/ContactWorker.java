package org.example.worker;

import org.example.Person;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class ContactWorker {
    private Map<String, Person> personMap;
    private final String NEXT_COMMAND = "Enter the following command";
    private final String CONTACT_EXIST = "Such contact already exists";
    private final String CONTACT_ADD = "Contact added";
    private final String NOT_EXIST = "There is no contact with this email";
    private final String DELETE_CONTACT = "Contact deleted";
    private final String LIST_EMPTY = "The contact list is empty";
    public void addContact(Person person) {
        if (personMap.containsKey(person.getFullName())) {
            System.out.println(CONTACT_EXIST);
        } else {
            personMap.put(person.getFullName(), person);
            System.out.println(CONTACT_ADD);
        }
        System.out.println(NEXT_COMMAND);
    }

    public void deleteContact(String email) {
        String fullName = null;
        for (Map.Entry<String, Person> personEntry : personMap.entrySet()) {
            if (personEntry.getValue().getEmail().equals(email.trim())) {
                fullName = personEntry.getKey();
            }
        }
        if (fullName != null) {
            personMap.remove(fullName);
            System.out.println(DELETE_CONTACT);
        }
        else System.out.println(NOT_EXIST);
        System.out.println(NEXT_COMMAND);
    }

    public void printAllContacts() {
        if (personMap.isEmpty()) {
            System.out.println(LIST_EMPTY);
        } else {
            personMap.forEach((fullName, person) -> System.out.println(person));
        }
        System.out.println(NEXT_COMMAND);
    }

    public Map<String, Person> getPersonMap() {
        return new HashMap<>(personMap);
    }
    public void setPersonMap(Map<String, Person> personMap) {this.personMap = personMap;}
}
