package algorithms.interviews.bp;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

//Design a stack-like data structure to push elements to the stack and pop the most frequent element from the stack.
//
//Implement the FreqStack class:
//
//FreqStack() constructs an empty frequency stack.
//void push(int val) pushes an integer val onto the top of the stack.
//int pop() removes and returns the most frequent element in the stack.
// If there is a tie for the most frequent element, the element closest to the stack's top is removed and returned.
//It is guaranteed that there will be at least one element in the stack before calling pop.
public class MaximumFrequencyStack {
    Map<Integer, Integer> valueToFrequency = new HashMap<>();
    Map<Integer, Stack<Integer>> frequencyToValues = new HashMap<>();
    int maxFrequency = 0;
    public MaximumFrequencyStack() {

    }

    public void push(int val) {
        Integer currFreq =valueToFrequency.getOrDefault(val, 0) +1;
        valueToFrequency.put(val, currFreq);
        if(currFreq > maxFrequency) {
            maxFrequency = currFreq;
        }
        Stack<Integer> stack = frequencyToValues.getOrDefault(currFreq, new Stack<>());
        stack.push(val);
        frequencyToValues.put(currFreq, stack);
        // we don't have to remove the element from frequencyToValues map, this will be used later in pop operation to maintain order

        //[[], [5], [7], [5],   [7], [4], [5], [], [], [], []]

        // valueToFrequency = 5-> 1, frequencyToValues = 1-> 5
        // valueToFrequency = {5->1}{7-> 1}, frequencyToValues = {1-> 5,7}
        // valueToFrequency = {5->2}{7-> 1}, frequencyToValues = {1-> 5,7} {2-> 5}
        // valueToFrequency = {5->2}{7-> 2}, frequencyToValues = {1-> 5,7} {2-> 5,7}
        // valueToFrequency =  {5->2}{7-> 2}{4-> 1}, frequencyToValues = {1-> 5,7} {2-> 5,7}
        // valueToFrequency = {5->3}{7-> 2}{4-> 1}, frequencyToValues = {1-> 5,7} {2-> 5,7}{3->5}

    }

    public int pop() {
        Stack<Integer> stack = frequencyToValues.get(maxFrequency);
        int val = stack.pop();
        valueToFrequency.put(val, valueToFrequency.get(val) - 1);
        if(stack.isEmpty()) {
            maxFrequency--;
        }
        return val;
    }

}
