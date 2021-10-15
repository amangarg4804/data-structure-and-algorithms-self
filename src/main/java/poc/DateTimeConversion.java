package poc;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Locale;

public class DateTimeConversion {

  public static void main(String[] args) {
//    String dateTime = "2020-10-10T10:10:11";

//    LocalDateTime date = LocalDateTime.parse("2020-10-10T10:10:11");

    String transformedDate =  "2020-10-10T10:10:11.000000Z";
    DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern( "yyyy-MM-dd'T'HH:mm:ss.SSSSSS'Z'");
 ;

//    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss", Locale.ENGLISH);

    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss.SSS");


    System.out.println(dtf.format( inputFormatter.parse(transformedDate))); // 2010-01-02

  }

}
