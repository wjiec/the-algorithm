package problem.p641designcirculardeque;

/**
 * 641. Design Circular Deque
 *
 * https://leetcode-cn.com/problems/design-circular-deque/
 *
 * Design your implementation of the circular double-ended queue (deque).
 *
 * Implement the MyCircularDeque class:
 *
 * MyCircularDeque(int k) Initializes the deque with a maximum size of k.
 * boolean insertFront() Adds an item at the front of Deque. Returns true if the operation is successful, or false otherwise.
 * boolean insertLast() Adds an item at the rear of Deque. Returns true if the operation is successful, or false otherwise.
 * boolean deleteFront() Deletes an item from the front of Deque. Returns true if the operation is successful, or false otherwise.
 * boolean deleteLast() Deletes an item from the rear of Deque. Returns true if the operation is successful, or false otherwise.
 * int getFront() Returns the front item from the Deque. Returns -1 if the deque is empty.
 * int getRear() Returns the last item from Deque. Returns -1 if the deque is empty.
 * boolean isEmpty() Returns true if the deque is empty, or false otherwise.
 * boolean isFull() Returns true if the deque is full, or false otherwise.
 */

public class Solution {

    private static class MyCircularDeque {
        private static class Node {
            private final int value;
            private Node next = null;
            private Node prev = null;
            public Node(int v) { value = v; }
            public Node(int v, Node n) { value = v; next = n; }
            public Node(int v, Node p, Node n) { value = v; prev = p; next = n; }
        }

        private final int capacity;
        private int size = 0;
        private Node head = null, tail = null;
        public MyCircularDeque(int k) { capacity = k; }

        public boolean insertFront(int value) {
            if (isFull()) return false;

            size++;
            if (head != null) {
                head = new Node(value, head);
                head.next.prev = head;
            } else head = tail = new Node(value);

            return true;
        }

        public boolean insertLast(int value) {
            if (isFull()) return false;
            if (tail == null) return insertFront(value);

            size++;
            tail.next = new Node(value, tail, null);
            tail = tail.next;
            return true;
        }

        public boolean deleteFront() {
            if (isEmpty()) return false;

            size--;
            head = head.next;
            if (head != null) head.prev = null;
            else tail = null;
            return true;
        }

        public boolean deleteLast() {
            if (isEmpty()) return false;

            size--;
            tail = tail.prev;
            if (tail != null) tail.next = null;
            else head = null;
            return true;
        }

        public int getFront() { return isEmpty() ? -1 : head.value; }
        public int getRear() { return isEmpty() ? -1 : tail.value; }
        public boolean isEmpty() { return size == 0; }
        public boolean isFull() { return size == capacity; }
    }

    public static void main(String[] args) {
        MyCircularDeque circularDeque = new MyCircularDeque(3);
        assert circularDeque.insertLast(1);
        assert circularDeque.insertLast(2);
        assert circularDeque.insertFront(3);
        assert !circularDeque.insertFront(4);
        assert circularDeque.getRear() == 2;
        assert circularDeque.isFull();
        assert circularDeque.deleteLast();
        assert circularDeque.insertFront(4);
        assert circularDeque.getFront() == 4;

        // ["MyCircularDeque","insertFront","insertLast","insertLast",
        //      "getFront","deleteLast","getRear",
        //      "insertFront","deleteFront","getRear","insertLast","isFull"]
        // [[3],[8],[8],[2],[],[],[],[9],[],[],[2],[]]
        circularDeque = new MyCircularDeque(3);
        assert circularDeque.insertFront(8);
        assert circularDeque.insertLast(8);
        assert circularDeque.insertLast(2);
        assert circularDeque.getFront() == 8;
        assert circularDeque.deleteLast();
        assert circularDeque.getRear() == 8;
        assert circularDeque.insertFront(9);
        assert circularDeque.deleteFront();
        assert circularDeque.getRear() == 8;
        assert circularDeque.insertLast(2);
        assert circularDeque.isFull();
    }

}
