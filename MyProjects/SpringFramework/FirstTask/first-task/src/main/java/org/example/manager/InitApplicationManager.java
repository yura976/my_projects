package org.example.manager;

import org.example.Person;
import org.example.worker.FileWorker;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

public class InitApplicationManager implements ApplicationManager {
    @Autowired
    private FileWorker fileWorker;
    @Override
    public Map<String, Person> creatingContactList() {
        return fileWorker.readFile();
    }
}
