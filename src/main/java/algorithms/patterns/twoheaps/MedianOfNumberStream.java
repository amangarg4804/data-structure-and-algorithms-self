package algorithms.patterns.twoheaps;

import java.util.Comparator;
import java.util.PriorityQueue;

public class MedianOfNumberStream {

  private PriorityQueue<Integer> minHeap; // containing large numbers than median
  private PriorityQueue<Integer> maxHeap; //containing smaller numbers than median

  MedianOfNumberStream () {
    minHeap = new PriorityQueue<>();
    maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
  }

  public static void main(String[] args) {
    MedianOfNumberStream medianOfNumberStream = new MedianOfNumberStream();
    medianOfNumberStream.insertNum(3);
    medianOfNumberStream.insertNum(1);
    System.out.println(medianOfNumberStream.findMedian());
    medianOfNumberStream.insertNum(5);
    System.out.println(medianOfNumberStream.findMedian());
    medianOfNumberStream.insertNum(4);
    System.out.println(medianOfNumberStream.findMedian());
  }

  public void insertNum(int num) {
    if(minHeap.isEmpty() || num >=minHeap.peek() ) {
      minHeap.offer(num);
    } else {
      maxHeap.offer(num);
    }

    if (minHeap.size() -maxHeap.size() > 1) {
      maxHeap.offer(minHeap.poll());
    } else if(maxHeap.size() > minHeap.size()) {
      minHeap.offer(maxHeap.poll());
    }
  }

  public double findMedian() {
    int totalSize = minHeap.size() + maxHeap.size();
    if(totalSize %2 ==0) {
      return ((double) (minHeap.peek() + maxHeap.peek()))/2;
    }
    return minHeap.peek();
  }
}
