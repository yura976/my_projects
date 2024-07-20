public class PhoneNumberError extends RuntimeException{
    public PhoneNumberError(String phoneNumber) {
        System.out.printf("%s - invalid number format.\nRe-enter.\n", phoneNumber);
    }
}
