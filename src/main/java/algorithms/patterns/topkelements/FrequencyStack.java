package algorithms.patterns.topkelements;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class FrequencyStack {

  private int sequence = 0;

  private final PriorityQueue<Element> maxHeap = new PriorityQueue<>(
      (n1, n2) -> {
        if (n1.frequency == n2.frequency) {
          return n2.getSequence() - n1.getSequence();
        }
        return n2.frequency - n1.frequency;
      });
  private final Map<Integer, Integer> map = new HashMap<>();

  public static void main(String[] args) {
    FrequencyStack frequencyStack = new FrequencyStack();
    frequencyStack.push(1);
    frequencyStack.push(2);
    frequencyStack.push(3);
    frequencyStack.push(2);
    frequencyStack.push(1);
    frequencyStack.push(2);
    frequencyStack.push(5);
    System.out.println(frequencyStack.pop());
    System.out.println(frequencyStack.pop());
    System.out.println(frequencyStack.pop());
  }


  public void push(int n) {
    map.put(n, map.getOrDefault(n, 0) + 1);
    maxHeap.offer(new Element(n, map.get(n), sequence++));
  }

  public int pop() {
    Element element = maxHeap.poll();
    if (element.getValue() > 1) {
      map.put(element.getValue(), element.getFrequency() - 1);
    } else {
      map.remove(element.getValue());
    }
    return element.getValue();
  }
}

class Element {

  int value;
  int frequency;
  int sequence;

  public Element(int value, int frequency, int sequence) {
    this.value = value;
    this.frequency = frequency;
    this.sequence = sequence;
  }

  public int getValue() {
    return value;
  }

  public void setValue(int value) {
    this.value = value;
  }

  public int getFrequency() {
    return frequency;
  }

  public void setFrequency(int frequency) {
    this.frequency = frequency;
  }

  public int getSequence() {
    return sequence;
  }

  public void setSequence(int sequence) {
    this.sequence = sequence;
  }
}