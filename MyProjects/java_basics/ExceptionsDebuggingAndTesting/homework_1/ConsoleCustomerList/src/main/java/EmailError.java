public class EmailError extends RuntimeException{
    public EmailError(String email) {
        System.out.printf("%s - invalid email format.\nRe-enter.\n", email);
    }
}