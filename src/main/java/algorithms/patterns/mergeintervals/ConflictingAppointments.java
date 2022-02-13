package algorithms.patterns.mergeintervals;

import java.util.Arrays;
import java.util.Comparator;

public class ConflictingAppointments {

  public static void main(String[] args) {
    Interval[] intervals1 = {new Interval(1, 4), new Interval(2, 5), new Interval(7, 9)};
    System.out.println(canAttendAllAppointments(intervals1));
    Interval[] intervals2 = {new Interval(6, 7), new Interval(2, 4), new Interval(8, 12)};
    System.out.println(canAttendAllAppointments(intervals2));
    Interval[] intervals3 = {new Interval(4, 5), new Interval(2, 3), new Interval(3, 6)};
    System.out.println(canAttendAllAppointments(intervals3));
  }

  public static boolean canAttendAllAppointments(Interval[] intervals) {
    Arrays.sort(intervals, Comparator.comparing(interval -> interval.start));
    for(int i = 1; i< intervals.length ; i++) {
      if(intervals[i-1].end > intervals[i].start) {
        return false;
      }
    }
    return true;
  }
}
