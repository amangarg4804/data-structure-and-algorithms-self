package algorithms.patterns.kwaymerge;

import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class MergeKSortedArrays {

  public static void main(String[] args) {
    System.out.println(Arrays.toString(merge(
        Arrays.asList(new Integer[]{2, 6, 8}, new Integer[]{3, 6, 7}, new Integer[]{1, 3, 4}))));
  }

  private static int[] merge(List<Integer[]> sortedArrays) {

    PriorityQueue<Element> minHeap = new PriorityQueue<>((e1,e2) -> sortedArrays.get(e1.listIndex)[e1.elementIndex] - sortedArrays.get(e2.listIndex)[e2.elementIndex]);
    int resultArraySize = 0;
    for(int i = 0; i< sortedArrays.size(); i++) {
      if(sortedArrays.get(i)!=null) {
        resultArraySize+= sortedArrays.get(i).length;
        minHeap.offer(new Element(i, 0));
      }
    }
    int[] result = new int[resultArraySize];
    int currentIndex = 0;
    while (!minHeap.isEmpty()) {
      Element currentElement = minHeap.poll();
      result[currentIndex++] = sortedArrays.get(currentElement.listIndex)[currentElement.elementIndex];
      currentElement.elementIndex++;
      if(currentElement.elementIndex < sortedArrays.get(currentElement.listIndex).length) {
        minHeap.offer(currentElement);
      }
    }
    return result;
  }
}
