package org.example.error;

import org.springframework.stereotype.Component;

@Component
public class IncorrectNumberComponents extends RuntimeException{
    private final String TEXT_ERROR = "Please re-enter!\n" +
            "You entered %d component, after the \"%s\" command you must enter %d component.";

    public void infoError(int actual, String instruction, int expected) {
        System.out.println(String.format(TEXT_ERROR, actual, instruction, expected));
    }
}
