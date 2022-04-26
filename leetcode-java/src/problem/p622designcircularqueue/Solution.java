package problem.p622designcircularqueue;

/**
 * 622. Design Circular Queue
 *
 * https://leetcode-cn.com/problems/design-circular-queue/
 *
 * Design your implementation of the circular queue. The circular queue is a linear data structure in which
 * the operations are performed based on FIFO (First In First Out) principle and the last position is connected
 * back to the first position to make a circle. It is also called "Ring Buffer".
 *
 * One of the benefits of the circular queue is that we can make use of the spaces in front of the queue.
 * In a normal queue, once the queue becomes full, we cannot insert the next element even if there is a space
 * in front of the queue. But using the circular queue, we can use the space to store new values.
 *
 * Implementation the MyCircularQueue class:
 *
 * MyCircularQueue(k) Initializes the object with the size of the queue to be k.
 * int Front() Gets the front item from the queue. If the queue is empty, return -1.
 * int Rear() Gets the last item from the queue. If the queue is empty, return -1.
 * boolean enQueue(int value) Inserts an element into the circular queue. Return true if the operation is successful.
 * boolean deQueue() Deletes an element from the circular queue. Return true if the operation is successful.
 * boolean isEmpty() Checks whether the circular queue is empty or not.
 * boolean isFull() Checks whether the circular queue is full or not.
 *
 * You must solve the problem without using the built-in queue data structure in your programming language.
 */

public class Solution {

    private static class MyCircularQueue {
        private final int[] queue;
        private int head = 0;
        private int tail = -1;
        private int size = 0;
        private final int capacity;
        public MyCircularQueue(int k) { queue = new int[k]; capacity = k; }

        public boolean enQueue(int value) {
            if (isFull()) return false;

            if (++tail == capacity) tail = 0;
            queue[tail] = value;
            size++;

            return true;
        }

        public boolean deQueue() {
            if (isEmpty()) return false;

            size--;
            if (++head == capacity) head = 0;

            return true;
        }

        public int Front() { return isEmpty() ? -1 : queue[head]; }
        public int Rear() { return isEmpty() ? -1 : queue[tail]; }
        public boolean isEmpty() { return size == 0; }
        public boolean isFull() { return size == capacity; }
    }

    public static void main(String[] args) {
        MyCircularQueue myCircularQueue = new MyCircularQueue(3);
        assert myCircularQueue.enQueue(1);
        assert myCircularQueue.enQueue(2);
        assert myCircularQueue.enQueue(3);
        assert !myCircularQueue.enQueue(4);
        assert myCircularQueue.Rear() == 3;
        assert myCircularQueue.isFull();
        assert myCircularQueue.deQueue();
        assert myCircularQueue.enQueue(4);
        assert myCircularQueue.Rear() == 4;
    }

}
