package algorithms.leetcodearraysandstrings;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

//The given dates are valid dates between the years 1971 and 2100.

public class DaysBetweenTwoDates {
    Map<Integer, Integer> monthToDays = new HashMap<>();
    {
        monthToDays.put(1, 31);
        monthToDays.put(2, 28);
        monthToDays.put(3, 31);
        monthToDays.put(4, 30);

        monthToDays.put(5, 31);
        monthToDays.put(6, 30);
        monthToDays.put(7, 31);
        monthToDays.put(8, 31);

        monthToDays.put(9, 30);
        monthToDays.put(10, 31);
        monthToDays.put(11, 30);
        monthToDays.put(12, 31);
    }

    public int daysBetweenDates(String date1, String date2) {
       //  "2020-01-15"
        // "2019-12-31"
        if(date1.compareTo(date2) < 0) {
            // we want to have date1 as older date for simplicity
            return daysBetweenDates(date2, date1);
        }
        if(date1.equals(date2)) {
            return 0;
        }
        // date 1 is older
        int date1DaysFrom1971 = calculateDaysFrom1971(date1);
        int date2DaysFrom1971 = calculateDaysFrom1971(date2); //17949:17885
        return date1DaysFrom1971-date2DaysFrom1971;
    }

    int calculateDaysFrom1971(String date) {
        // from 01-01-1971
        int[] parsedDate = Arrays.stream(date.split("-")).mapToInt(Integer::parseInt).toArray();
        int year = parsedDate[0];
        int month = parsedDate[1];
        int totalDays = parsedDate[2];// for day, we don't require a loop
        for(int i=1971; i< year; i++) { // if given year is 1972, loop runs once, if given year is 1971, loop doesn't run
            totalDays+= (isLeapYear(i)) ? 366: 365;
        }
        //notice that in above loop, we don't consider current year, so if current year is leap year and current month is > February, we should add 1 day
        for(int i=1; i< month; i++) {// if current month is 2 (Feb), we should add only Jan month days
            totalDays += monthToDays.get(i);
        }
        if(month > 2 && isLeapYear(year)) {
            totalDays++;
        }
        return totalDays;
    }

    boolean isLeapYear(int year) {
        return (year%4==0 && year%100!=0) || year%400==0; // for centuries, 1900, 2000, 2100, year has to be divisible by 400 to be a leap year
    }
}
