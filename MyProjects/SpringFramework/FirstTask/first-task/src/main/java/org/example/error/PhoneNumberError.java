package org.example.error;

import org.springframework.stereotype.Component;

@Component
public class PhoneNumberError extends RuntimeException{

    public void infoError(String phoneNumber) {
        System.out.printf("%s - invalid number format.\nRe-enter.\n", phoneNumber);
    }
}
