package util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatTest {

  public static void main(String[] args) {
    String df = "yyyyMMddHHmmss";
    SimpleDateFormat sinkQueryDF = new SimpleDateFormat(df);
    Date currentDate = new Date();
    System.out.println(sinkQueryDF.format(currentDate));

  }
}
