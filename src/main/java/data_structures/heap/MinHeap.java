package data_structures.heap;

import java.util.Arrays;

public class MinHeap {

    int[] arr;
    int length;
    int size;

    public MinHeap(int length) {
        this.length = length;
        arr = new int[length];
        size = 0;
    }

    public int getParentIndex(int childIndex) {
        return (childIndex - 1) / 2;
    }

    public int getLeftIndex(int parentIndex) {
        return parentIndex * 2 + 1;
    }

    public int getRightIndex(int parentIndex) {
        return parentIndex * 2 + 2;
    }

    public boolean hasParent(int childIndex) {
        return getParentIndex(childIndex) >= 0;
    }

    public boolean hasLeftChild(int parentIndex) {
        return getLeftIndex(parentIndex) > 0 && getLeftIndex(parentIndex) < size;
    }

    public boolean hasRightChild(int parentIndex) {
        return getRightIndex(parentIndex) > 0 && getRightIndex(parentIndex) < size;
    }

    public int getParent(int childIndex) {
        return arr[getParentIndex(childIndex)];
    }

    public int getLeftChild(int parentIndex) {
        return arr[getLeftIndex(parentIndex)];
    }
    public int getRightChild(int parentIndex) {
        return arr[getRightIndex(parentIndex)];
    }

    public void swap(int index1, int index2) {
        int temp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = temp;
    }

    public void ensureCapacity() {
        if(length ==size) {
            arr = Arrays.copyOf(arr, length*2);
            length = length*2;
        }
    }

    public int getSize() {
        return size;
    }

    public void insert(int newElement) {
        ensureCapacity();
        arr[size++] = newElement;
        heapifyUp();
    }

    public void insertForKthLargest(int newElement) {
        // the client code will check if the incoming number is larger than the head of this heap
        // If yes, it will call this method
        arr[0] = newElement;
        heapifyDown();
    }

    private void heapifyUp() {
        int childIndex = size-1;
        while (hasParent(childIndex) && getParent(childIndex) > arr[childIndex]) {
            swap(getParentIndex(childIndex), childIndex);
            childIndex = getParentIndex(childIndex);
        }
    }

    public int peek() {
        return arr[0];
    }

    public int poll() {
        // will return root element = element at 0th index
        int element = arr[0];
        arr[0] = arr[size-1];
        size--;
        heapifyDown();
        return element;
    }

    private void heapifyDown() {
        int index = 0;
        while(hasLeftChild(index)) {
            int smallerChildIndex = getLeftIndex(index);
            if(hasRightChild(index) && getRightChild(index) < getLeftChild(index)) {
                smallerChildIndex = getRightIndex(index);
            }
            if(arr[index] < arr[smallerChildIndex]) {
                break;
            } else {
                swap(index, smallerChildIndex);
                index = smallerChildIndex;
            }
        }
    }
}
