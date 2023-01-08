package algorithms.leetcodelinkedlist;

import java.util.HashSet;
import java.util.Set;

public class LinkedListsIntersection {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode current = headA;
        Set<ListNode> set = new HashSet<>();
        while (current!=null) {
            set.add(current);
            current = current.next;
        }
        current = headB;
        while (current!=null) {
            if(set.contains(current)) {
                return current;
            }
            current = current.next;
        }
        return null;
    }

    public ListNode getIntersectionNode1(ListNode headA, ListNode headB) {
        // without extra space
        // know length of both linked lists, move linked lists, so they have same starting points.
        // move to next nodes till we find an intersection point or till we reach null in one of the linked lists
        int lenA = getlength(headA);
        int lenB = getlength(headB);
        ListNode currentA = headA;
        ListNode currentB = headB;
        if(lenA>lenB) {
            // List A is larger, move its current pointer
            int diff = lenA -lenB;
            while (diff--!=0) {
                currentA = currentA.next;
            }
        }
        if(lenB >lenA) {
            int diff = lenB - lenA;
            while (diff--!=0) {
                currentB = currentB.next;
            }
        }

        while (currentA!=null && currentB!=null) {
            if(currentA ==currentB) {
                return currentA; // both nodes are same, this is intersection point;
            }
            currentA = currentA.next;
            currentB = currentB.next;
        }
        return null; // If we reach end of one of the lists, there was no intersection, hence returning null
    }

    public ListNode getIntersectionNode2(ListNode headA, ListNode headB) {
        // without extra space
        // know length of both linked lists, move linked lists, so they have same starting points.
        // move to next nodes till we find an intersection point or till we reach null in one of the linked lists
        int lenA = getlength(headA);
        int lenB = getlength(headB);
        ListNode currentA = headA;
        ListNode currentB = headB;

        while (lenA>lenB) {
            // List A is larger, move its current pointer until both lengths are same
                currentA = currentA.next;
                lenA--;
        }
        while (lenB >lenA) {
            currentB = currentB.next;
            lenB--;
        }

        while (currentA != currentB) { // since lengths are same now, this condition will be true either when currentA and current B are null or they are intersecting/same node
            currentA = currentA.next;
            currentB = currentB.next;
        }
        return currentA; // we can return either currentA or currentB, either they will be null or referencing same node
    }

    private static int getlength(ListNode current) {
        int length =0;
        while (current !=null) {
            current = current.next;
            length++;
        }
        return length;
    }

    public ListNode getIntersectionNode3(ListNode headA, ListNode headB) {
        // without extra space
        // without knowing the length of linked lists
        // We know the lengthA + lengthB = lengthB + lengthA
        // So we can iterate twice
//https://leetcode.com/explore/learn/card/linked-list/214/two-pointer-technique/1215/discuss/49785/Java-solution-without-knowing-the-difference-in-len!/165648
        ListNode currentA = headA;
        ListNode currentB = headB;
        while (currentA!=currentB) {
            currentA = currentA==null? headB: currentA.next;
            currentB = currentB==null?headA : currentB.next;
        }
        return currentA; // we can return either currentA or currentB, either they will be null or referencing same node
    }
// Case 1 (Have Intersection & Diff Len):
//            a
//A:          a1 → a2
//                   ↘
//                     c1 → c2 → c3 → null
//                   ↗
//B:     b1 → b2 → b3
//       b
//                 a
//A:          a1 → a2
//                   ↘
//                     c1 → c2 → c3 → null
//                   ↗
//B:     b1 → b2 → b3
//            b
//A:          a1 → a2
//                   ↘ a
//                     c1 → c2 → c3 → null
//                   ↗
//B:     b1 → b2 → b3
//                 b
//A:          a1 → a2
//                   ↘      a
//                     c1 → c2 → c3 → null
//                   ↗ b
//B:     b1 → b2 → b3
//A:          a1 → a2
//                   ↘           a
//                     c1 → c2 → c3 → null
//                   ↗      b
//B:     b1 → b2 → b3
//A:          a1 → a2
//                   ↘                a = null, then a = b1
//                     c1 → c2 → c3 → null
//                   ↗           b
//B:     b1 → b2 → b3
//A:          a1 → a2
//                   ↘
//                     c1 → c2 → c3 → null
//                   ↗                b = null, then b = a1
//B:     b1 → b2 → b3
//       a
//            b
//A:          a1 → a2
//                   ↘
//                     c1 → c2 → c3 → null
//                   ↗
//B:     b1 → b2 → b3
//            a
//                 b
//A:          a1 → a2
//                   ↘
//                     c1 → c2 → c3 → null
//                   ↗
//B:     b1 → b2 → b3
//                 a
//A:          a1 → a2
//                   ↘ b
//                     c1 → c2 → c3 → null
//                   ↗ a
//B:     b1 → b2 → b3

}
