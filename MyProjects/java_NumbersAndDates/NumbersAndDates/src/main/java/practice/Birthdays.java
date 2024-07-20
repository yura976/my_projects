package practice;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Locale;

public class Birthdays {

    public static void main(String[] args) {

        int day = 31;
        int month = 12;
        int year = 1990;

        System.out.println(collectBirthdays(year, month, day));

    }

    public static String collectBirthdays(int year, int month, int day) {
        String result = "";
        LocalDate birthday = LocalDate.of(year, month, day);
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        Locale langEn = new Locale("English");
        for (int i = 0; today.isAfter(birthday.plusYears(i)) || today.isEqual(birthday.plusYears(i)); i++) {
            result = result.concat(i + " - "
                    + formatter.format(birthday.plusYears(i))
                    + " - " + birthday.plusYears(i).getDayOfWeek().getDisplayName(TextStyle.SHORT, langEn)
                    + System.lineSeparator());
        }

        return result;
    }
}