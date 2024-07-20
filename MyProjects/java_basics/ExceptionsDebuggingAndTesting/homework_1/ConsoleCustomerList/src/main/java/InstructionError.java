public class InstructionError extends RuntimeException{
    private final String commandError = "Wrong command! To learn the commands type - \"HELP\"";

    public InstructionError() {
        System.out.println(commandError);
    }
}
