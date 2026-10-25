package algorithms.interviews.goldman.practice;

// Alice and Bob are traveling to Rome for separate business meetings.
//
//You are given 4 strings arriveAlice, leaveAlice, arriveBob, and leaveBob. Alice will be in the city from the dates arriveAlice to leaveAlice (inclusive), while Bob will be in the city from the dates arriveBob to leaveBob (inclusive). Each will be a 5-character string in the format "MM-DD", corresponding to the month and day of the date.
//
//Return the total number of days that Alice and Bob are in Rome together.
//
//You can assume that all dates occur in the same calendar year, which is not a leap year. Note that the number of days per month can be represented as: [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31].
//
//
//
//Example 1:
//Input: arriveAlice = "08-15", leaveAlice = "08-18", arriveBob = "08-16", leaveBob = "08-19"
//Output: 3
//Explanation: Alice will be in Rome from August 15 to August 18. Bob will be in Rome from August 16 to August 19. They are both in Rome together on August 16th, 17th, and 18th, so the answer is 3.

//Example 2:
//Input: arriveAlice = "10-01", leaveAlice = "10-31", arriveBob = "11-01", leaveBob = "12-31"
//Output: 0
//Explanation: There is no day when Alice and Bob are in Rome together, so we return 0.
//
//
//Constraints:
//
//All dates are provided in the format "MM-DD".
//Alice and Bob's arrival dates are earlier than or equal to their leaving dates.
//The given dates are valid dates of a non-leap year.
public class CountDaysSpentTogether {
 //Understanding how to compare dates can be tricky when they're written as strings like "08-15".
 // This problem is much easier once we convert everything into a simple number representing **the day of the year** (from 1 to 365).
 //
 //Here is the step-by-step breakdown of the intuition and what a **prefix sum** is.
 //
 //---
 //
 //### 1. What is a "Prefix Sum"?
 //
 //A **prefix sum** is a running total of an array. Instead of recalculating things over and over, we precalculate cumulative sums so we can look up answers instantly.
 //
 //Imagine keeping track of how many total days have passed *before* a given month starts in a non-leap year:
 //
 //* **January** has 31 days. By the end of January, **31** days have passed.
 //* **February** has 28 days. By the end of February, 31 + 28 = **59** days have passed.
 //* **March** has 31 days. By the end of March, 59 + 31 = **90** days have passed.
 //
 //If we put this into an array (`prefixSum`), it looks like this:
 //
 //* `prefixSum[0] = 0` (Start of the year)
 //* `prefixSum[1] = 31` (End of Jan)
 //* `prefixSum[2] = 59` (End of Feb)
 //* `prefixSum[3] = 90` (End of Mar)
 //* ...and so on.
 //
 //#### How we use it to convert a date:
 //
 //If we want to find the day number for **March 5th** (`"03-05"`):
 //
 //1. Month is `3`. The prefix sum right before March is `prefixSum[2]`, which is `59`.
 // This tells us that 59 days passed in January and February combined.
 //2. The day is `5`.
 //3. Total day of the year = `59 + 5 = 64`.
 //
 //---
 //
 //### 2. Finding the Overlap Between Alice and Bob
 //
 //Once we convert all four dates (Alice's arrival/leave and Bob's arrival/leave) into day numbers,
 // we need to figure out how many days they spend in Rome together.
 //
 //Think of it like two line segments on a number line:
 //
 //* **Alice's trip:** from `arriveAliceDay` to `leaveAliceDay`
 //* **Bob's trip:** from `arriveBobDay` to `leaveBobDay`
 //
 //#### The Rule of Overlaps:
 //
 //1. **When does the overlap start?**
 //They can only meet *after both have arrived*. Therefore, the overlap starts on the **later** of their two arrival days:
 //
 //   Start = max(arriveAliceDay, arriveBobDay)
 //
 //2. **When does the overlap end?**
 //The meeting ends as soon as *the first person leaves*. Therefore, the overlap ends on the **earlier** of their two departure days:
 //
 //   End = min(leaveAliceDay, leaveBobDay)
 //
 //3. **How many days is that?**
 //If the `End` date is greater than or equal to the `Start` date, they overlap.
 // Because the dates are **inclusive** (meaning staying on just August 16th counts as 1 day, not 16 - 16 = 0), the formula for total days is:
 //
 //   Total Days = End - Start + 1
 //
 //If the `Start` day comes *after* the `End` day, it means their trips never crossed paths.
 // In that case, we return `0` (which is why `Math.max(0, ...)` is used).
 //
 //---
    public int countDaysTogether(String arriveAlice, String leaveAlice, String arriveBob, String leaveBob) {
        int[] prefixSum = new int[13];
        int[] daysPerMonth ={31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        prefixSum[0] = 0;
        for(int i = 0; i< daysPerMonth.length; i++) {
            prefixSum[i+1] = prefixSum[i] + daysPerMonth[i];
        }
        int aliceArrivalDay = getDayOfTheYear(arriveAlice,prefixSum);
        int aliceLeaveDay = getDayOfTheYear(leaveAlice,prefixSum);
        int bobArrivalDay = getDayOfTheYear(arriveBob,prefixSum);
        int bobLeaveDay = getDayOfTheYear(leaveBob,prefixSum);

        int maxArrivalDay = Math.max(aliceArrivalDay, bobArrivalDay); // keep in mind this is the one of the 365 days of the year
        int minLeaveDay = Math.min(aliceLeaveDay, bobLeaveDay);

        return (minLeaveDay < maxArrivalDay) ? -1 : minLeaveDay-maxArrivalDay+1;

    }

    private int getDayOfTheYear(String dayAndMonth, int[] prefixSum) {
        // NOTE: a string "08" can be converted to int 8 without issues using int.parseInt
        int month = Integer.parseInt(dayAndMonth.substring(0, 2));
        int day = Integer.parseInt(dayAndMonth.substring(3));
        return prefixSum[month-1] + day;
    }
}