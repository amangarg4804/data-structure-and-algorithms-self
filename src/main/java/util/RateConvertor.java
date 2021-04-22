package util;

public class RateConvertor {

  public static Long perYearToPerSecond(Long number) {
    return perMonthToPerSecond(number)/12;
  }

  public static Long perMonthToPerSecond(Long number) {
    return perDayToPerSecond(number) / 30;
  }

  public static Long perDayToPerSecond(Long number) {
    return perHourToPerSecond(number) / 24;
  }

  public static Long perHourToPerSecond(Long number) {
    return perMinuteToPerSecond(number) / 60;
  }

  public static Long perMinuteToPerSecond(Long number) {
    return number / 60;
  }

  public static void main(String[] args) {
    System.out.println(perMonthToPerSecond(1000000L));
    System.out.println(NumberToWords.toWords(perMonthToPerSecond(1000000L)));
  }

}
