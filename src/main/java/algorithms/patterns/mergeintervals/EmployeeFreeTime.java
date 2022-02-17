package algorithms.patterns.mergeintervals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
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
    System.out.println(findEmployeeFreeTimeHeap(input1));
    List<List<Interval>> input2 = new ArrayList<>();
    input2.add(new ArrayList<>(Arrays.asList(new Interval(1, 3), new Interval(9, 12))));
    input2.add(new ArrayList<>(Arrays.asList(new Interval(2, 4))));
    input2.add(new ArrayList<>(Arrays.asList(new Interval(6, 8))));
    System.out.println(findEmployeeFreeTime(input2));
    System.out.println(findEmployeeFreeTimeHeap(input2));

    List<List<Interval>> input3 = new ArrayList<>();
    input3.add(new ArrayList<>(Arrays.asList(new Interval(1, 3))));
    input3.add(new ArrayList<>(Arrays.asList(new Interval(2, 4))));
    input3.add(new ArrayList<>(Arrays.asList(new Interval(3, 5), new Interval(7, 9))));
    System.out.println(findEmployeeFreeTime(input3));
    System.out.println(findEmployeeFreeTimeHeap(input3));


    List<List<Interval>> input4 = new ArrayList<>();
    input4.add(new ArrayList<>(Arrays.asList(new Interval(1, 2))));
    input4.add(new ArrayList<>(Arrays.asList(new Interval(1, 3))));
    input4.add(new ArrayList<>(Arrays.asList(new Interval(4, 10))));
    input4.add(new ArrayList<>(Arrays.asList(new Interval(5, 6))));
    input4.add(new ArrayList<>(Arrays.asList(new Interval(8, 9))));
    System.out.println(findEmployeeFreeTime(input4));
    System.out.println(findEmployeeFreeTimeHeap(input4));

  }

  public static List<Interval> findEmployeeFreeTime(List<List<Interval>> schedule) {
    // time O(N*logN), space O(n)
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

  public static List<Interval> findEmployeeFreeTimeHeap(List<List<Interval>> schedules) {
    // O(N*logK), K number of employees, Space O(K)
    List<Interval> result = new ArrayList<>();

    PriorityQueue<EmployeeInterval> minHeap = new PriorityQueue<>(Comparator.comparing(schedule -> schedule.interval.start));

    for(int i=0; i< schedules.size(); i++) {
      minHeap.offer(new EmployeeInterval(schedules.get(i).get(0), i, 0));
    }
    Interval previousInterval = minHeap.peek().interval;
    while (!minHeap.isEmpty()) {
      EmployeeInterval queueTop = minHeap.poll();
      if(previousInterval.end <queueTop.interval.start) {
        result.add(new Interval(previousInterval.end, queueTop.interval.start));
        previousInterval = queueTop.interval;
      } else {
        if(previousInterval.end < queueTop.interval.end) {
          previousInterval = queueTop.interval;
        }
      }

      List<Interval> employeeSchedule = schedules.get(queueTop.employeeIndex);
      if(queueTop.intervalIndex < employeeSchedule.size()-1) {
        minHeap.offer(new EmployeeInterval(employeeSchedule.get(queueTop.intervalIndex +1), queueTop.employeeIndex, queueTop.intervalIndex+1));
      }
    }

    return result;
  }
}

class EmployeeInterval {
  Interval interval;
  int employeeIndex;
  int intervalIndex;

  public EmployeeInterval(Interval interval, int employeeIndex, int intervalIndex) {
    this.interval = interval;
    this.employeeIndex = employeeIndex;
    this.intervalIndex = intervalIndex;
  }

  public Interval getInterval() {
    return interval;
  }

  public void setInterval(Interval interval) {
    this.interval = interval;
  }

  public int getEmployeeIndex() {
    return employeeIndex;
  }

  public void setEmployeeIndex(int employeeIndex) {
    this.employeeIndex = employeeIndex;
  }

  public int getIntervalIndex() {
    return intervalIndex;
  }

  public void setIntervalIndex(int intervalIndex) {
    this.intervalIndex = intervalIndex;
  }
}
