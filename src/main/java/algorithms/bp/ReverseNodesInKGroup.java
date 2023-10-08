package algorithms.bp;

import algorithms.patterns.reversallinkedlist.ListNode;

//https://www.youtube.com/watch?v=fi2vh0nQLi0 - There is a slight variation in this video from the leetcode problem
// In the video, the creator also reverses the remaining nodes if the count of remaining nodes is less than k
public class ReverseNodesInKGroup {
    public static ListNode reverseKGroup(ListNode head, int k) {

       int length = calculateLength(head);
       return reverseKGroup(head, k , length);

    }


    public static ListNode reverseKGroup(ListNode head, int k, int length) {
        if (k <= 1 || head == null || k > length) { // if number of nodes to reverse (k) are greater than the length of linked list, we can't reverse, hence return head
            return head;
        }
        // using recursion, we reverse first group
        // After reversing the first group, the last node of current group should point to the  head of the remaining group
        ListNode previous = null;
        int count = 0;
        ListNode next = null; // we require next outside while loop, because at the end of reversing one group, next will point to the head of the next group to be reversed.
        ListNode current = head;
        // And this next has to be passed to recursive call
        // compilation error outside while loop if next is not initialized to null
        while (current!=null && count < k) {
            // same code used for reversing a linked list
            // just that we use a count variable
            next = current.next;
            current.next = previous;
            previous = current;
            current = next;
            count++;
        }
        // either current is null or count became equal to k
        // if current is null, then there are no remaining nodes,
        // if count became equal to K, it means current is pointing to the first node of remaining nodes
        // head node should point to remaining nodes reversed head
        // 1-> 2-> 3-> 4-> 5-> 6-> 7 -> 8, k= 3
        // 3(previous)-> 2-> 1(head) 4(current, next)-> 5-> 6-> 7 -> 8
        //meaning 1 should point to reverse of 4-> 5-> 6-> 7 -> 8
        // Which node is holding 1 , it is head?
        //current and next , both are pointing to 4
        if(next != null) {
            head.next = reverseKGroup(next, k, length-k);
        }
        return previous;
        // Time complexity: we are visiting each node once. O(n)
        // space complexity: every call using O(k) space. Each call taking O(n/k) space. Total: O(n)
    }
    private static int calculateLength(ListNode head) {
        int length = 0;
        while (head!=null) {
            length++;
            head = head.next;
        }
        return length;
    }


    private class ListNode {
        int val;
        ListNode next;
        ListNode() {
        }
        ListNode(int val) {
            this.val = val;
        }
        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}
