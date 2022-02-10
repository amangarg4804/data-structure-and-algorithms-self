package algorithms.patterns.mergeintervals;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

// Given a set of intervals, find out if any two intervals overlap.

public class IntervalsOverlap {

  public static void main(String[] args) {
    List<Interval> intervals1 = new ArrayList<>();
    intervals1.add(new Interval(1, 4));
    intervals1.add(new Interval(2, 5));
    intervals1.add(new Interval(7,9));
    System.out.println(isOverlapping(intervals1));

    List<Interval> intervals2 = new ArrayList<>();
    intervals2.add(new Interval(6, 7));
    intervals2.add(new Interval(2, 4));
    intervals2.add(new Interval(5,9));
    System.out.println(isOverlapping(intervals2));

    List<Interval> intervals3 = new ArrayList<>();
    intervals3.add(new Interval(1, 4));
    intervals3.add(new Interval(2, 6));
    intervals3.add(new Interval(3, 5));
    System.out.println(isOverlapping(intervals3));

    List<Interval> intervals4 = new ArrayList<>();
    intervals4.add(new Interval(1, 7));
    intervals4.add(new Interval(8, 9));
    intervals4.add(new Interval(10, 11));
    System.out.println(isOverlapping(intervals4));
  }

  public static boolean isOverlapping(List<Interval> intervals) {
    intervals.sort(Comparator.comparingInt(interval -> interval.start));
    Iterator<Interval> iterator = intervals.iterator();
    Interval interval = iterator.next();
    int end = interval.end;
    while (iterator.hasNext()) {
      Interval next = iterator.next();
      if (next.start <= end) {
        return true;
      }
      end = next.end;
    }
    return false;
  }
}
