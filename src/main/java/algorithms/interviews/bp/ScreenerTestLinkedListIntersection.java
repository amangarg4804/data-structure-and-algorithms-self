package algorithms.interviews.bp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ScreenerTestLinkedListIntersection {


    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in, StandardCharsets.UTF_8);
        BufferedReader in = new BufferedReader(reader);
        String line;
        Map<String, ListNode> nodeMap = new HashMap<>();
        while ((line = in.readLine()) != null) {
            // System.out.println(line);
            if(line.contains("->")) {
                String[] tokens = line.split("->");
                String from = tokens[0];
                String to = tokens[1];
                if (!nodeMap.containsKey(from)) {
                    nodeMap.put(from, new ListNode(from));
                }
                if (!nodeMap.containsKey(to)) {
                    nodeMap.put(to, new ListNode(to));
                }
                nodeMap.get(from).next = nodeMap.get(to);
            } else {
                detectCycleOrIntersection(line, nodeMap);
            }
        }
    }
    private static void detectCycleOrIntersection(String line, Map<String, ListNode> nodeMap) {
        //System.out.println("input line to check is *****" + line);
        String[] startNodes = line.split(",");
        List<ListNode> startNodesList = new ArrayList<>();
        for (String node : startNodes) {
            startNodesList.add(nodeMap.get(node));
        }

        boolean hasIntersection = false;
        boolean hasCycle = false;
        for (int i = 0; i < startNodesList.size(); i++) {
            for (int j = i + 1; j < startNodesList.size(); j++) {
                if(hasCycle(startNodesList.get(i)) || hasCycle(startNodesList.get(j))) {
                    hasCycle = true;
                    break;
                }
                if (detectIntersection(startNodesList.get(i), startNodesList.get(j))) {
                    hasIntersection = true;
                    break;
                }

            }
            if (hasCycle || hasIntersection) {
                break;
            }
        }
        if(hasCycle) {
            System.out.println("Error Thrown!");
        } else {
            System.out.println(hasIntersection? "True": "False");
        }


    }

    public static boolean detectIntersection(ListNode list1, ListNode list2) {
        return getIntersectionNode(list1, list2) != null;
    }
    public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }

        ListNode pointerA = headA;
        ListNode pointerB = headB;

        while (pointerA != pointerB) {
            pointerA = (pointerA == null) ? headB : pointerA.next;
            pointerB = (pointerB == null) ? headA : pointerB.next;
        }

        return pointerA;
    }
    public static boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }

        ListNode slow = head;
        ListNode fast = head.next;

        while (fast != null && fast.next != null) {
            if (slow == fast) {
                return true;
            }
            slow = slow.next;
            fast = fast.next.next;
        }

        return false;
    }







    static class ListNode {
        String val;
        ListNode next;

        ListNode(String val) {
            this.val = val;
            this.next = null;
        }
    }



}
