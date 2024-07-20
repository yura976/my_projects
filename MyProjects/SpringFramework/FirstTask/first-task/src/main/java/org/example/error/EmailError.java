package org.example.error;

import org.springframework.stereotype.Component;

@Component
public class EmailError extends RuntimeException{

    public void infoError(String email) {
        System.out.printf("%s - invalid email format.\nRe-enter.\n", email);
    }
}
