package util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class AddDays {

    public static void main(String[] args) {
        // ADHO- Adhoc
        //INDA- Intraday
        //DAIL- Daily
        //WEEK- Weekly
        //MNTH- Monthly
        //QURT- Quarterly
        //BIMN- Bi-monthly
        //YEAR- Yearly
        //MIAN- Half-yearly

        LocalDate date = LocalDate.parse("03/31/2022", DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        System.out.println(date.plusMonths(1));

        // TODO: update next_execution date in DB

    }
}
