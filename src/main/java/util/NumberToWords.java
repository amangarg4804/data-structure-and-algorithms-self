package util;

import com.ibm.icu.text.NumberFormat;
import com.ibm.icu.text.RuleBasedNumberFormat;
import com.ibm.icu.util.ULocale;
import java.text.ParseException;

public class NumberToWords {

  public static String toWords(Long number) {
    NumberFormat formatter = new RuleBasedNumberFormat(new ULocale("en"), RuleBasedNumberFormat.SPELLOUT);
    return formatter.format(number);
  }

  public static Number toNumber(String words) {
    NumberFormat formatter = new RuleBasedNumberFormat(new ULocale("en"), RuleBasedNumberFormat.SPELLOUT);
    try {
      return formatter.parse(words);
    } catch (ParseException e) {
      e.printStackTrace();
    }
    return null;
  }

  public static void main(String[] args) {
    System.out.println(toWords(1000000L));
    System.out.println(toNumber("one million"));
    System.out.println(toNumber("six hundred million"));
  }

}
