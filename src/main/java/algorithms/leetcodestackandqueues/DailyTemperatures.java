package algorithms.leetcodestackandqueues;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

public class DailyTemperatures {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(dailyTemperatures2(new int[]{73,74,75,71,69,72,76,73})));
    }

    public static int[] dailyTemperatures(int[] temperatures) {
        //time : O(n^2)
        int[] answer = new int[temperatures.length];
        for (int i=0; i< temperatures.length; i++) {
            int current = temperatures[i];
            for(int j=i+1; j< temperatures.length; j++) {
                if(temperatures[j] >current) {
                    answer[i] = j-i;
                    break;
                }
            }
        }
        return answer;
    }

    public static int[] dailyTemperatures2(int[] temperatures) {
        //time : O(n)
        // monotonic stack
        int[] answer = new int[temperatures.length];
        Deque<Integer> stack = new LinkedList<>();
        for (int i=temperatures.length-1; i>=0; i--) {
            while(!stack.isEmpty() && temperatures[i] >= temperatures[stack.peekFirst()]) { //Deque peekFirst is top of the stack
                stack.pop();
            }
            if(!stack.isEmpty() && temperatures[stack.peekFirst()] > temperatures[i]) {
                answer[i] = stack.peekFirst() -i;
            }
            stack.push(i);
        }
        return answer;
    }

    public static int[] dailyTemperatures3(int[] temperatures) {
        //time : O(n)
        //monotonic stack
        int[] answer = new int[temperatures.length];
        Deque<Integer> stack = new LinkedList<>();
        for (int i=0; i<temperatures.length; i++) {
            while (!stack.isEmpty() && temperatures[stack.peekFirst()] < temperatures[i]) {
                answer[stack.peekFirst()] = i-stack.peekFirst();
                stack.pop();
            }
            stack.push(i);
        }
        return answer;
    }

}
