package data_structures.arrays;

import java.util.Arrays;

public class MyArrayList {

    String[] arr;

    int currentSize;

    public MyArrayList() {
        arr = new String[10];
        currentSize = 0;
    }

    public void add(String s) {
        if (arr.length == currentSize) {
            arr = Arrays.copyOf(arr, arr.length * 2);
        }
        arr[currentSize++] = s;
    }

    public String get(int index) {
        return arr[index];
    }

    public void remove(int index) {
        for (int i = index; i < currentSize - 1; i++) {
            arr[i] = arr[i + 1];
        }
        arr[currentSize - 1] = null;
    }

    public int size() {
        return currentSize;
    }

}


class MyArrayListGeneric<T> {

    Object[] arr;

    int currentSize;

    public MyArrayListGeneric() {
        arr = new Object[10];
        currentSize = 0;
    }

    public void add(T s) {
        if (arr.length == currentSize) {
            arr = Arrays.copyOf(arr, arr.length * 2);
        }
        arr[currentSize++] = s;
    }

    public T get(int index) {
        return (T)arr[index];
    }

    public void remove(int index) {
        for (int i = index; i < currentSize - 1; i++) {
            arr[i] = arr[i + 1];
        }
        arr[currentSize - 1] = null;
    }

    public int size() {
        return currentSize;
    }

}

class TestMyArrayList {

    public static void main(String[] args) {
        MyArrayList myArrayList = new MyArrayList();
        for (int i = 0; i < 15; i++) {
            myArrayList.add("abc" + i);
        }

        System.out.println(myArrayList.size());

        for (int i = 0; i < 15; i++) {
            System.out.println(myArrayList.get(i));
        }

        myArrayList.remove(5);

        for (int i = 0; i < 15; i++) {
            System.out.println(myArrayList.get(i));
        }
    }
}