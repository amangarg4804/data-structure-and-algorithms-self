package algorithms.patterns.kwaymerge;

public class ListNode {

  int value;
  ListNode next;

  public ListNode(int value) {
    this.value = value;
  }

  public int getValue() {
    return value;
  }

  public void setValue(int value) {
    this.value = value;
  }

  public ListNode getNext() {
    return next;
  }

  public void setNext(ListNode next) {
    this.next = next;
  }

  @Override
  public String toString() {
    return "ListNode{" +
        "value=" + value +
        ", next=" + next +
        '}';
  }
}
