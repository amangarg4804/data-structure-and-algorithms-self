package algorithms.leetcodestackandqueues;

class MyCircularQueue {

    int front;
    int rear;
    Integer [] arr;
    public MyCircularQueue(int k) {
        arr = new Integer[k];
        front =-1;
        rear =-1;
    }

    public boolean enQueue(int value) {
        if(isFull()) {
            return false;
        }
        if(front ==-1) {
            front =0;
        }
        rear = (rear +1) %arr.length;
        arr[rear] = value; // Remember - front and rear will always point to element to be dequeued and element to be enqueued respectively
        // (think of queue.peekFirst and queue.peekLast operation)
        return true;
    }

    public boolean deQueue() {
        if(isEmpty()) {
            return false;
        }
        arr[front] = null;
        if(front ==rear) {
            front =-1;
            rear=-1;
        } else {
            front = (front+1)%arr.length;
        }

        return true;
    }

    public int Front() {
        if(front==-1) {
            return -1;
        }
        return arr[front];
    }

    public int Rear() {
        if(rear ==-1) {
            return -1;
        }
        return arr[rear];
    }

    public boolean isEmpty() {
//        return front ==rear && (front ==-1 || arr[front] ==null);
        return front ==-1;
    }

    public boolean isFull() {
//        return front!=-1 && rear !=-1
//                &&  ((rear+1)%arr.length) == front && arr[front] !=null && arr[rear] !=null;
        return rear+1%arr.length ==front;
    }
}