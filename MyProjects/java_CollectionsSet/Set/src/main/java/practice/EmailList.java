package practice;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class EmailList {
    private TreeSet<String> emailSet = new TreeSet<>();
    public void add(String email) {
        String regex = "[^<>()\\[\\]@,;:\"*\\\\/]+@[A-Za-z0-9-]+\\.[A-Za-z0-9-]+[A-Za-z]";
        if (email.matches(regex)) {
            emailSet.add(email.toLowerCase());
        }
    }
    public List<String> getSortedEmails() {
        return new ArrayList<>(emailSet);
    }

}
