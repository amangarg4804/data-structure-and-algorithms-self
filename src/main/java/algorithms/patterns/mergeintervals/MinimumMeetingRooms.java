package algorithms.patterns.mergeintervals;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

//Given a list of intervals representing the start and end time of ‘N’ meetings, find the minimum number of rooms required to hold all the meetings.
public class MinimumMeetingRooms {

  public static void main(String[] args) {
    List<Interval> input1 = new ArrayList<>();
    input1.add(new Interval(4, 5));
    input1.add(new Interval(2, 3));
    input1.add(new Interval(2, 4));
    input1.add(new Interval(3, 5));
    System.out.println("Minimum rooms required: " + findMinimumMeetingRooms(input1));

    List<Interval> input2 = new ArrayList<>();
    input2.add(new Interval(1, 4));
    input2.add(new Interval(2, 5));
    input2.add(new Interval(7, 9));
    System.out.println("Minimum rooms required: " + findMinimumMeetingRooms(input2));

    List<Interval> input3 = new ArrayList<>();
    input3.add(new Interval(6, 7));
    input3.add(new Interval(2, 4));
    input3.add(new Interval(8, 12));
    System.out.println("Minimum rooms required: " + findMinimumMeetingRooms(input3));

    List<Interval> input4 = new ArrayList<>();
    input4.add(new Interval(1, 4));
    input4.add(new Interval(2, 3));
    input4.add(new Interval(3, 6));
    System.out.println("Minimum rooms required: " + findMinimumMeetingRooms(input4));
  }

  public static int findMinimumMeetingRooms(List<Interval> meetings) {
    meetings.sort(Comparator.comparing(interval -> interval.start));

    PriorityQueue<Interval> minHeap = new PriorityQueue<>(
        Comparator.comparing(interval -> interval.end));
    int minRooms = 0;
    for (Interval meeting : meetings) {
      while (!minHeap.isEmpty() && minHeap.peek().end <= meeting.start) {
        minHeap.poll();
      }
      minHeap.offer(meeting);
      minRooms = Math.max(minRooms, minHeap.size());
    }
    return minRooms;
  }
}
