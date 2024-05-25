package util;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class MilisecondsToDate {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    public static void main(String[] args) {
        long milliseconds = 1711905774000L; // Example milliseconds representing a date
        Instant instant = Instant.ofEpochMilli(milliseconds);
        LocalDateTime dateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        String formattedDate = dateTime.format(FORMATTER);
        System.out.println(formattedDate);
    }
}
