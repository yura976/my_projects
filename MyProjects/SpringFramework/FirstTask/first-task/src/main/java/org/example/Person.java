package org.example;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
public class Person {
    private String fullName;
    private String phoneNumber;
    private String email;
    @Override
    public String toString() {
        return fullName + "|" + phoneNumber + "|" + email;
    }
}
