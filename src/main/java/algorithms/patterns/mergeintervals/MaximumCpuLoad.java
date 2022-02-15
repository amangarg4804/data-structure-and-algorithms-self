package algorithms.patterns.mergeintervals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;
//We are given a list of Jobs. Each job has a Start time, an End time, and a CPU load when it is running. Our goal is to find the maximum CPU load at any time if all the jobs are running on the same machine.
public class MaximumCpuLoad {

  public static void main(String[] args) {
    List<Job> input1 = new ArrayList<>(
        Arrays.asList(new Job(1, 4, 3), new Job(2, 5, 4), new Job(7, 9, 6)));
    System.out.println(findMaxCpuLoad(input1));
    System.out.println(findMaxCpuLoadUsingMinHeap(input1));
    List<Job> input2 = new ArrayList<>(
        Arrays.asList(new Job(6, 7, 10), new Job(2, 4, 11), new Job(8, 12, 15)));
    System.out.println(findMaxCpuLoad(input2));
    System.out.println(findMaxCpuLoadUsingMinHeap(input2));
    List<Job> input3 = new ArrayList<>(
        Arrays.asList(new Job(1, 4, 2), new Job(2, 4, 1), new Job(3, 6, 5)));
    System.out.println(findMaxCpuLoad(input3));
    System.out.println(findMaxCpuLoadUsingMinHeap(input3));
  }

  public static int findMaxCpuLoad(List<Job> jobs) {
    jobs.sort(Comparator.comparing(job -> job.start));
    Iterator<Job> iterator = jobs.iterator();
    Job job = iterator.next();
    int end = job.end;
    int cpuLoad = job.cpuLoad;
    int maxLoad = cpuLoad;
    while (iterator.hasNext()) {
      Job next = iterator.next();
      if (next.start < end) {
        end = Math.max(end, next.end);
        cpuLoad += next.cpuLoad;
      } else {
        maxLoad = Math.max(cpuLoad, maxLoad);
        end = next.end;
        cpuLoad = next.cpuLoad;
      }
    }
    return Math.max(maxLoad, cpuLoad);
  }

  public static int findMaxCpuLoadUsingMinHeap(List<Job> jobs) {
    jobs.sort(Comparator.comparing(job -> job.start));
    PriorityQueue<Job> pq = new PriorityQueue<>(Comparator.comparing(job -> job.end));
    int currentLoad = 0;
    int maxLoad =0;
    for(Job job : jobs) {
      while (!pq.isEmpty() && pq.peek().end<= job.start) {
        currentLoad -=pq.poll().cpuLoad;
      }
      pq.offer(job);
      currentLoad+= job.cpuLoad;
      maxLoad = Math.max(currentLoad, maxLoad);
    }
    return maxLoad;
  }

}
