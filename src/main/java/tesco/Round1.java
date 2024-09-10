package tesco;

//[9:07 AM] Chhabra, Maninder
//Tesco has around 3200 stores and more than 10% of the stores have around 800 colleagues each.
//
//In a store, a colleague can work for multiple departments. Here are shifts of a colleague in various departments:
//
//In Bakery department: From 8 to 10
//
//In Checkout department: From 10 to 12
//
//In Diary department: From 14 to 19
//
//Given the above split of shifts, provide an API/method to return the different shifts of a colleague for the day after merging contiguous shifts. This will be exposed to the colleague in different UI and help them plan their day accordingly.
//
//His shift timings in this case are 8 to 12 and 14 to 19.
//
//
//Cases to test-
//
//With overlap - 8 to 10, 10 to 12, 14 to 19
//
//With overlap - 8 to 10, 9 to 12, 14 to 19
//
//With overlap 2 = 5 to 10, 8 to 9, 14 to 19


// 8 10
// 9 11

//0  1 2 3 4 5 6 7 8 9 10  11   12  ... 22 24
//                 1  1 1   1    0       1  1
//start = null;
//end =null;
//
//// start =null
//        // int i;
//while(i< arr.length;) {
//    if(arr[i]==1) {
//        start = arr[1]
//        break;
//        }
//    i++
//        }
//
//while (i< arr.length) {
//        if(arr[i]==1) {
//          end = arr[i]
//         }
//        } else {
//        result.add(new int[] {start, end})
//            while (i < arr.length){
//                // initiaize start
//                i++
//            }
//
//         }
//        }
//
//        }


//

//9, 11  e1[]
// 8, 10 e2[]
/// if (e2[1])

// 24 hours

import java.util.ArrayList;
import java.util.List;

public class Round1 {
    public static void main(String[] args) {
        Schedule schedule1 = new Schedule(8, 10);
        Schedule schedule2 = new Schedule(9, 11);
        List<Schedule> schedules = new ArrayList<>();
        schedules.add(schedule1);
        schedules.add(schedule2);
        Round1 round1 = new Round1();
        System.out.println(round1.getShiftTimings(schedules));
    }

    public List<Schedule> getShiftTimings(List<Schedule> schedules) {
        List<Schedule> result = new ArrayList<>();
        int[] hours = new int[24];
        for(Schedule s: schedules) {
            for(int i = s.start; i <= s.end; i++) {
                hours[i] = 1;
            }
        }
        int start = getNextShiftHour(0, hours);
        int  i = start +1;
        int end =0;
        while (i < hours.length) {
            if(hours[i] ==1) {
                end = i;
                i++;
            } else {
                result.add(new Schedule(start, end));
                i = getNextShiftHour(i+1, hours);
                if(i< 25) {
                    start= i;
                }
                i++;
            }
        }
        if(end > start && i < 25) {
            result.add(new Schedule(start, end));
        }
        return result;
    }

    private int getNextShiftHour(int i, int[] hours) {
        while (i < hours.length) {
            if(hours[i]==1) {
                return i;
            }
            i++;
        }
        return 25;
    }

}

class Schedule {
    int start;
    int end;

    public Schedule(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public int getStart() {
        return start;
    }

    public Schedule setStart(int start) {
        this.start = start;
        return this;
    }

    public int getEnd() {
        return end;
    }

    public Schedule setEnd(int end) {
        this.end = end;
        return this;
    }

    @Override
    public String toString() {
        return "Schedule{" +
                "start=" + start +
                ", end=" + end +
                '}';
    }
}