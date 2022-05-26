package algorithms.patterns.kwaymerge;

import java.util.PriorityQueue;

//Given an array of ‘K’ sorted LinkedLists, merge them into one sorted list.
public class MergeKSortedLinkedLists {

  public static void main(String[] args) {
    ListNode l1 = new ListNode(2);
    l1.next = new ListNode(6);
    l1.next.next = new ListNode(8);

    ListNode l2 = new ListNode(3);
    l2.next = new ListNode(6);
    l2.next.next = new ListNode(7);

    ListNode l3 = new ListNode(1);
    l3.next = new ListNode(3);
    l3.next.next = new ListNode(4);

   ListNode merged = merge(new ListNode[]{l1, l2, l3});
   while (merged!=null) {
     System.out.print(merged.value + " ");
     merged = merged.next;
   }
  }

  private static ListNode merge(ListNode[] sortedLists) {
    PriorityQueue<ListNode> minHeap = new PriorityQueue<>((node1, node2) -> node1.value - node2.value);
    for(ListNode root : sortedLists) {
      minHeap.offer(root);
    }
    ListNode resultHead = null;
    ListNode resultTail = null;
    while (!minHeap.isEmpty()) {
      ListNode currentMin = minHeap.poll();
      if(resultTail ==null) {
        resultHead = currentMin;
        resultTail = currentMin;
      } else {
        resultTail.next = currentMin;
        resultTail = resultTail.next;
      }
      if(currentMin.next !=null) {
        minHeap.offer(currentMin.next);
      }
    }
    return resultHead;
    // time N* LogK, where ‘N’ is the total number of elements in all the ‘K’ input arrays
  }
}
