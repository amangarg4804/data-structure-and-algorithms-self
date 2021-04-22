package basic;

import java.time.Instant;

public class EpochTime {

  public static void main(String[] args) {
    System.out.println(Instant.now().toEpochMilli());
  }

}
