package poc;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


public class RuleEngineDateTimeTest {
    public static void main(String[] args) {
        String inputDate = "2020-10-10T10:10:11.000000Z";
        String ruleDate = "2020-10-09";
        String inputDateFormatter = "yyyy-MM-dd'T'HH:mm:ss.SSSSSS'Z'";
        String ruleDateFormatter = "yyyy-MM-dd";

        LocalDate inputDateObj = convertToLocalDate(inputDate, inputDateFormatter);
        LocalDate ruleDateObj = convertToLocalDate(ruleDate, ruleDateFormatter);

        System.out.println("Input Date: " + inputDateObj);
        System.out.println("Rule Date: " + ruleDateObj);
        System.out.println("Is input date before rule date? " + inputDateObj.isBefore(ruleDateObj));

        LocalDate inputDateObj2 = convertToLocalDate2(inputDate, inputDateFormatter);
        LocalDate ruleDateObj2 = convertToLocalDate2(ruleDate, ruleDateFormatter);

        System.out.println("Input Date: " + inputDateObj2);
        System.out.println("Rule Date: " + ruleDateObj2);
        System.out.println("Is input date before rule date? " + inputDateObj2.isBefore(ruleDateObj2));

    }

    public static LocalDate convertToLocalDate(String inputDate, String formatter) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(formatter);

        try {
            if (formatter.contains("HH") || formatter.contains("mm") || formatter.contains("ss") || formatter.contains("Z")) {
                LocalDateTime localDateTime = LocalDateTime.parse(inputDate, dateTimeFormatter);
                return localDateTime.toLocalDate();
            } else {
                return LocalDate.parse(inputDate, dateTimeFormatter);
            }
        } catch (DateTimeParseException e) {
            throw new RuntimeException("Failed to parse date: " + inputDate, e);
        }
    }

    public static LocalDate convertToLocalDate2(String inputDate, String formatter) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(formatter);
        try {
            LocalDateTime localDateTime = LocalDateTime.parse(inputDate, dateTimeFormatter);
            return localDateTime.toLocalDate();
        } catch (DateTimeParseException e) {
            return LocalDate.parse(inputDate, dateTimeFormatter);
        }
    }
}

