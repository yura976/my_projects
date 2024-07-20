package practice.regex;

import java.util.Scanner;

public class PhoneCleanerRegex {
  public static final String ERROR = "Неверный формат номера";
  public static void main(String[] args) {

    Scanner scanner = new Scanner(System.in);
    while (true) {
      String input = scanner.nextLine();
      if (input.equals("0")) {
        scanner.close();
        break;
      }
      String phone = input.replaceAll("[^0-9]", "");
      String resultPhone = "";

      if (phone.isEmpty() || phone.length() > 11 || phone.length() < 10) {
        resultPhone = ERROR;
      }

      else if (phone.charAt(0) != '7' && phone.charAt(0) != '8' && phone.length() == 11) {
        resultPhone = ERROR;
      } else if (phone.charAt(0) == '8' && phone.length() == 11) {
        String subPhone = "";
        for (int i = 1; i < phone.length(); i++) {
          subPhone = subPhone.concat(String.valueOf(phone.charAt(i)));
        }
        resultPhone = resultPhone.concat("7").concat(subPhone);
      } else if (phone.length() == 10) {
        resultPhone = resultPhone.concat("7").concat(phone);
      } else resultPhone = phone;
      System.out.println(resultPhone);
    }
  }
}
