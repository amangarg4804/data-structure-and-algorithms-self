package algorithms.patterns.mergeintervals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

//For ‘K’ employees, we are given a list of intervals representing the working hours of each employee.
// Our goal is to find out if there is a free interval that is common to all employees.
// You can assume that each list of employee working hours is sorted on the start time.
public class EmployeeFreeTime {

  public static void main(String[] args) {
    List<List<Interval>> input1 = new ArrayList<>();
    input1.add(new ArrayList<>(Arrays.asList(new Interval(1, 3), new Interval(5, 6))));
    input1.add(new ArrayList<>(Arrays.asList(new Interval(2, 3), new Interval(6, 8))));
    System.out.println(findEmployeeFreeTime(input1));

    List<List<Interval>> input2 = new ArrayList<>();
    input2.add(new ArrayList<>(Arrays.asList(new Interval(1, 3), new Interval(9, 12))));
    input2.add(new ArrayList<>(Arrays.asList(new Interval(2, 4))));
    input2.add(new ArrayList<>(Arrays.asList(new Interval(6, 8))));
    System.out.println(findEmployeeFreeTime(input2));

    List<List<Interval>> input3 = new ArrayList<>();
    input3.add(new ArrayList<>(Arrays.asList(new Interval(1, 3))));
    input3.add(new ArrayList<>(Arrays.asList(new Interval(2, 4))));
    input3.add(new ArrayList<>(Arrays.asList(new Interval(3, 5), new Interval(7, 9))));
    System.out.println(findEmployeeFreeTime(input3));


    List<List<Interval>> input4 = new ArrayList<>();
    input4.add(new ArrayList<>(Arrays.asList(new Interval(1, 2))));
    input4.add(new ArrayList<>(Arrays.asList(new Interval(1, 3))));
    input4.add(new ArrayList<>(Arrays.asList(new Interval(4, 10))));
    input4.add(new ArrayList<>(Arrays.asList(new Interval(5, 6))));
    input4.add(new ArrayList<>(Arrays.asList(new Interval(8, 9))));
    System.out.println(findEmployeeFreeTime(input4));
  }

  public static List<Interval> findEmployeeFreeTime(List<List<Interval>> schedule) {
    // O(N*logN)
    List<Interval> sortedIntervals = schedule.stream()
        .flatMap(List::stream)
        .sorted(Comparator.comparing(interval -> interval.start))
        .collect(Collectors.toList());
    List<Interval> result = new ArrayList<>();
    int maxEnd= 0;
    for(int i = 0; i < sortedIntervals.size() -1; i++) {
      maxEnd = Math.max(sortedIntervals.get(i).end , maxEnd);
      if(maxEnd < sortedIntervals.get(i+1).start) {
        result.add(new Interval(sortedIntervals.get(i).end, sortedIntervals.get(i+1).start));
      }
    }
    return result;
  }
}
