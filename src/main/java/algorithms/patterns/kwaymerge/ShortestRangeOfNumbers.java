package algorithms.patterns.kwaymerge;

import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class ShortestRangeOfNumbers {

  public static void main(String[] args) {
    Integer[] arr1 = new Integer[] {1, 5, 8};
    Integer[] arr2 = new Integer[] {4, 12};
    Integer[] arr3 = new Integer[] {7, 8, 10};

    System.out.println(Arrays.toString(shortestRange(List.of(arr1, arr2, arr3))));
  }

  private static int[] shortestRange(List<Integer[]> input) {
    PriorityQueue<Element> minheap = new PriorityQueue<>((e1, e2) -> input.get(e1.listIndex)[e1.elementIndex] - input.get(e2.listIndex)[e2.elementIndex]);
    int max = Integer.MIN_VALUE;
    for(int i=0;i< input.size();i++) {
      minheap.offer(new Element(i, 0));
      max = Math.max(max, input.get(i)[0]);
    }
    int rangeStart=0;
    int rangeEnd = Integer.MAX_VALUE;
    while (minheap.size() ==input.size()) {
      Element currentElement = minheap.poll();
      if(rangeEnd-rangeStart > max - input.get(currentElement.listIndex)[currentElement.elementIndex]) {
        rangeStart = input.get(currentElement.listIndex)[currentElement.elementIndex];
        rangeEnd = max;
      }
      currentElement.elementIndex++;
      if(currentElement.elementIndex < input.get(currentElement.listIndex).length) {
        minheap.offer(currentElement);
        max = Math.max(max, input.get(currentElement.listIndex)[currentElement.elementIndex]);
      }
    }
    return new int[]{rangeStart, rangeEnd};
  }
}
