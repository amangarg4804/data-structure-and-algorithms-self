package util;

import java.time.Instant;
import java.time.LocalDateTime;

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


    String time = Instant.now().toString();
    time = time.replaceAll("[-:.TZ]*","");
    System.out.println(time);
  }

}
