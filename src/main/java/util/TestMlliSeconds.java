package util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TestMlliSeconds {

  public static void main(String[] args) {
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss.SSS");
    System.out.println(dtf.format(LocalDateTime.now()));
    System.out.println(dtf.format(LocalDateTime.now()));
  }

}
