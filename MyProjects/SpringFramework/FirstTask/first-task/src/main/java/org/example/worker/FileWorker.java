package org.example.worker;

import org.example.Person;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class FileWorker {
    private final Resource pathFileForSave;
    private final Resource pathContactsFile;
    private final String FILE_RECORDED = "File named contacts.txt was written successfully\nEnter the following command";

    public FileWorker(@Value("${app.file-for-save}") Resource pathFileForSave,
                      @Value("${app.default-contacts-path}") Resource pathContactsFile) {
        this.pathFileForSave = pathFileForSave;
        this.pathContactsFile = pathContactsFile;
    }
    public void writeToFile(Map<String, Person> personMap) {
        List<String> personList = new ArrayList<>();
        for (Person person : personMap.values()) {
            personList.add(person.toString());
        }
        try {
            Files.write(Paths.get(pathFileForSave.getFilename()), personList);
            System.out.println(FILE_RECORDED);
        } catch (IOException e) {
            System.out.println("File error");
        }
    }
    public Map<String, Person> readFile() {
        List<String> personList = new ArrayList<>();
        Map<String, Person> personMap = new HashMap<>();
        try {
            personList = Files.readAllLines(pathContactsFile.getFile().toPath());
        } catch (IOException e) {
            System.out.println("File error");
        }
        personList.forEach(person -> {
            String [] personInfo = person.split(";");
            personMap.put(personInfo[0], new Person(personInfo[0], personInfo[1], personInfo[2]));
        });
        return personMap;
    }
}
