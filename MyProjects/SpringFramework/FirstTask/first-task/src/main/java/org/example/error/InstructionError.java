package org.example.error;

import org.springframework.stereotype.Component;

@Component
public class InstructionError extends RuntimeException{
    private final String COMMAND_ERROR = "Wrong command! To learn the commands type - \"HELP\"";
    public void infoError() {
        System.out.println(COMMAND_ERROR);
    }
}
