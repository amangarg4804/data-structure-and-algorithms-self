package poc;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.TemporalAccessor;
import java.util.Locale;

public class DateTimeConversion {

  public static void main(String[] args) {
//    String dateTime = "2020-10-10T10:10:11";

//    LocalDateTime date = LocalDateTime.parse("2020-10-10T10:10:11");

    String inputDate =  "2020-10-10T10:10:11.000000Z";
    String ruleDate = "2020-10-09";


    DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern( "yyyy-MM-dd'T'HH:mm:ss.SSSSSS'Z'");
//    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss", Locale.ENGLISH);

//    DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss.SSS");


//    System.out.println(outputFormatter.format( inputFormatter.parse(inputDate))); // 2010-01-02
       DateTimeFormatter outputFormatter2 = DateTimeFormatter.ofPattern("YYYY-MM-dd");

      System.out.println(outputFormatter2.format( inputFormatter.parse(inputDate))); // 2010-01-02

      String formattedInputDate = outputFormatter2.format( inputFormatter.parse(inputDate));
      LocalDate localDate = LocalDate.parse(inputDate, DateTimeFormatter.ofPattern("YYYY-MM-dd"));
      System.out.println(localDate);
  }


}
