package algorithms.patterns.mergeintervals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class IntervalIntersection {

  public static void main(String[] args) {
    Interval[] input1 = new Interval[] {new Interval(1, 3), new Interval(5, 6), new Interval(7, 9)};
    Interval[] input2 = new Interval[] {new Interval(2, 3), new Interval(5, 7)};
    System.out.println(Arrays.toString(intersection(input1, input2)));
    Interval[] input3 = new Interval[] {new Interval(1, 3), new Interval(5, 7), new Interval(9, 12)};
    Interval[] input4 = new Interval[] {new Interval(5, 10)};
    System.out.println(Arrays.toString(intersection(input3, input4)));
  }

  public static Interval[] intersection(Interval[] arr1, Interval[] arr2) {
    List<Interval> intervals = new ArrayList<>();
    int arr1Index = 0;
    while (arr1Index < arr1.length) {
      int arr2Index = 0;
      while (arr2Index < arr2.length) {
        Interval interval1 = arr1[arr1Index];
        Interval interval2 = arr2[arr2Index];
        if((interval1.start <= interval2.start && interval2.start <=interval1.end) ||(interval2.start <=interval1.start && interval1.start <= interval2.end) ) {
          Interval interval = new Interval(Math.max(interval1.start, interval2.start), Math.min(interval1.end, interval2.end));
          intervals.add(interval);
        }
        arr2Index++;
      }
      arr1Index++;
    }
    return intervals.toArray(new Interval[0]);
  }
}
