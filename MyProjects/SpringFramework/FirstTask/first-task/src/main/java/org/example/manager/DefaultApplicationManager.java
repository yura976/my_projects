package org.example.manager;

import org.example.Person;

import java.util.HashMap;
import java.util.Map;

public class DefaultApplicationManager implements ApplicationManager {
    @Override
    public Map<String, Person> creatingContactList() {
        return new HashMap<>();
    }
}
