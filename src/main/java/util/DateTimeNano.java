package util;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

public class DateTimeNano {

  public static void main(String[] args) {
//    LocalDateTime localDateTime = LocalDateTime.now();
//    System.out.println(localDateTime);
//    System.out.println(localDateTime.getNano());
//
//    Instant now = Instant.now();
//    System.out.println(now);
//    System.out.println(now.getNano());
//    System.out.println(System.nanoTime());
    String testDateString1 = "20211223051823";
    String testDateString2 = "20211223051824";
    Instant instant = Instant.now();
    String time = instant.toString();
    System.out.println(time);

    LocalDateTime.now(ZoneOffset.UTC);
    LocalDateTime datetime = LocalDateTime.ofInstant(instant, ZoneOffset.UTC);
    String formattedDate = DateTimeFormatter.ofPattern("yyyyMMddHHmmss").format(datetime);
    System.out.println(formattedDate);


    LocalDateTime localDateTime1 = LocalDateTime.parse(testDateString1,  DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
    LocalDateTime localDateTime2 = LocalDateTime.parse(testDateString2,  DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));

    System.out.println(localDateTime1.isBefore(localDateTime2));

//    time = time.replaceAll("[-:.TZ]*","");
//    System.out.println(time);

    String inputDate = "2021-12-18T05:46:30Z";

    DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern( "yyyy-MM-dd'T'HH:mm:ss'Z'");

    DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

    System.out.println(outputFormatter.format(inputFormat.parse(inputDate)));

    System.out.println(LocalDateTime.now(ZoneOffset.UTC));


//    ZoneId utc = ZoneId.of("Etc/UTC");
//    DateTimeFormatter targetFormatter = DateTimeFormatter.ofPattern(
//        "MM/dd/yyyy hh:mm:ss a zzz");
//
//    System.out.println(targetFormatter.format(LocalDateTime.now(ZoneOffset.UTC)));

//    String itsAlarmDttm = "2013-10-22T01:37:56";
//    ZonedDateTime utcDateTime = LocalDateTime.parse(itsAlarmDttm)
//        .atZone(ZoneId.systemDefault())
//        .withZoneSameInstant(utc);
//    String formatterUtcDateTime = utcDateTime.format(targetFormatter);
//    System.out.println(formatterUtcDateTime);

    ZoneId utc = ZoneId.of("Etc/UTC");
    DateTimeFormatter targetFormatter = DateTimeFormatter.ofPattern(
        "dd MMMM yyyy hh:mm zzz", Locale.ENGLISH);
    String itsAlarmDttm = "2013-10-22T01:37:56";
    ZonedDateTime utcDateTime = LocalDateTime.parse(itsAlarmDttm)
        .atZone(ZoneId.systemDefault())
        .withZoneSameInstant(utc);
    ZonedDateTime zonedDateTime = LocalDateTime.now().atZone(ZoneId.of("Etc/UTC"));
    String formatterUtcDateTime = zonedDateTime.format(targetFormatter);
    System.out.println(formatterUtcDateTime);

    // HH instead of hh, hh is hour of clock, HH is hour of day
    LocalDateTime localDateTime3 = LocalDateTime.parse("11/16/2021 12:14:33.786",DateTimeFormatter.ofPattern( "MM/dd/yyyy HH:mm:ss.SSS") );
    System.out.println(localDateTime3);


  }

}
