package algorithms.patterns.mergeintervals;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

// Given a list of non-overlapping intervals sorted by their start time,
// insert a given interval at the correct position and merge all necessary intervals to produce a list that has only mutually exclusive intervals.
public class InsertInterval {

  public static void main(String[] args) {
    List<Interval> intervals1 = new ArrayList<>();
    intervals1.add(new Interval(1, 3));
    intervals1.add(new Interval(5, 7));
    intervals1.add(new Interval(8, 12));
    System.out.println(insert(intervals1, new Interval(4, 6)));

    List<Interval> intervals2 = new ArrayList<>();
    intervals2.add(new Interval(1, 3));
    intervals2.add(new Interval(5, 7));
    intervals2.add(new Interval(8,12));
    System.out.println(insert(intervals2, new Interval(4, 10)));

    List<Interval> intervals3 = new ArrayList<>();
    intervals3.add(new Interval(2, 3));
    intervals3.add(new Interval(5, 7));
    System.out.println(insert(intervals3, new Interval(1, 4)));
  }

  public static List<Interval> insert(List<Interval> intervals, Interval newInterval) {
    List<Interval> mergedIntervals = new ArrayList<>();
    int insertPosition = intervals.size() - 1;
    for (int i = 0; i < intervals.size(); i++) {
      if (intervals.get(i).start >= newInterval.start) {
        insertPosition = i;
        break;
      }
    }
    intervals.add(insertPosition, newInterval);

    Iterator<Interval> iterator = intervals.iterator();
    Interval interval = iterator.next();
    int start = interval.start;
    int end = interval.end;

    while (iterator.hasNext()) {
      Interval next = iterator.next();
      if (next.start <= end) {
        end = Math.max(next.end, end);
      } else {
        mergedIntervals.add(new Interval(start, end));
        start = next.start;
        end = next.end;
      }
    }
    mergedIntervals.add(new Interval(start, end));
    return mergedIntervals;
  }

  public static List<Interval> insertWithoutModifyingOriginalList(List<Interval> intervals, Interval newInterval) {
    List<Interval> mergedIntervals = new ArrayList<>();
    int i=0;
    while(i < intervals.size() && intervals.get(i).end < newInterval.start) {
      mergedIntervals.add(intervals.get(i++));
    }

    while(i < intervals.size() && intervals.get(i).start > newInterval.end ) {
      newInterval.start = Math.min(intervals.get(i).start, newInterval.start);
      newInterval.end = Math.min(intervals.get(i).end, newInterval.end);
      i++;
    }
    mergedIntervals.add(newInterval);
    while(i< intervals.size()) {
      mergedIntervals.add(intervals.get(i++));
    }
    return mergedIntervals;
  }
}
