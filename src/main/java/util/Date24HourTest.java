package util;

import org.apache.commons.lang3.time.DateUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Date24HourTest {

    public static void main(String[] args) throws ParseException {
        String DF1_DATE_PATTERN = "yyyy-MM-dd HH:mm:ss";
        DateFormat df1 = new SimpleDateFormat(DF1_DATE_PATTERN);
        Date result1 = df1.parse("2023-02-03 11:17:12.919+0530");
        Date payNewDate = DateUtils.addHours(result1, 24);
        if(new Date().after(payNewDate)) {
            System.out.println("YES");
        }
    }
}
