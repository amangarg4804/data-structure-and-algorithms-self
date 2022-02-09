package algorithms.patterns.mergeintervals;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

//Given a list of intervals, merge all the overlapping intervals to produce a list that has only mutually exclusive intervals.
public class MergeIntervals {

  public static void main(String[] args) {
    List<Interval> intervals1 = new ArrayList<>();
    intervals1.add(new Interval(1, 4));
    intervals1.add(new Interval(2, 5));
    intervals1.add(new Interval(7,9));
    System.out.println(merge(intervals1));

    List<Interval> intervals2 = new ArrayList<>();
    intervals2.add(new Interval(6, 7));
    intervals2.add(new Interval(2, 4));
    intervals2.add(new Interval(5,9));
    System.out.println(merge(intervals2));

    List<Interval> intervals3 = new ArrayList<>();
    intervals3.add(new Interval(1, 4));
    intervals3.add(new Interval(2, 6));
    intervals3.add(new Interval(3, 5));
    System.out.println(merge(intervals3));
  }

  public static List<Interval> merge(List<Interval> intervals) {
    List<Interval> mergedIntervals = new ArrayList<>();
    if (intervals.size() < 2) {
      return intervals;
    }
    intervals.sort(Comparator.comparingInt(interval -> interval.start));
    Iterator<Interval> iterator = intervals.iterator();
    Interval interval = iterator.next();
    int start = interval.start;
    int end = interval.end;
    while (iterator.hasNext()) {
      Interval next = iterator.next();
      if (next.start <= end) {
        end = Math.max(end, next.end);
      } else {
        Interval mergedInterval = new Interval(start, end);
        mergedIntervals.add(mergedInterval);
        start = next.start;
        end = next.end;
      }
    }
    mergedIntervals.add(new Interval(start, end));
    return mergedIntervals;
  }
}


