package problem.p225implementstackusingqueues;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 225. Implement Stack using Queues
 *
 * Implement a last in first out (LIFO) stack using only two queues.
 * The implemented stack should support all the functions of a normal queue (push, top, pop, and empty).
 *
 * Implement the MyStack class:
 *
 * void push(int x) Pushes element x to the top of the stack.
 * int pop() Removes the element on the top of the stack and returns it.
 * int top() Returns the element on the top of the stack.
 * boolean empty() Returns true if the stack is empty, false otherwise.
 *
 * Notes:
 * You must use only standard operations of a queue, which means only push to back,
 * peek/pop from front, size, and is empty operations are valid.
 *
 * Depending on your language, the queue may not be supported natively.
 * You may simulate a queue using a list or deque (double-ended queue),
 * as long as you use only a queue's standard operations.
 */

public class Solution {

    static class MyStack {
        private Queue<Integer> q0 = new ArrayDeque<>();
        private Queue<Integer> q1 = new ArrayDeque<>();
        /** Initialize your data structure here. */
        public MyStack() {}
        /** Push element x onto stack. */
        public void push(int x) {
            if (q0.isEmpty()) {
                doPush(x, q0, q1);
            } else {
                doPush(x, q1, q0);
            }
        }
        private void doPush(int x, Queue<Integer> empty, Queue<Integer> data) {
            empty.add(x);
            while (!data.isEmpty()) {
                empty.add(data.remove());
            }
        }
        /** Removes the element on top of the stack and returns that element. */
        public int pop() {
            if (q0.isEmpty()) {
                return q1.remove();
            }
            return q0.remove();
        }
        /** Get the top element. */
        public int top() {
            if (q0.isEmpty()) {
                return q1.element();
            }
            return q0.element();
        }
        /** Returns whether the stack is empty. */
        public boolean empty() {
            return q0.isEmpty() && q1.isEmpty();
        }
    }

    public static void main(String[] args) {
        MyStack stack = new MyStack();
        assert stack.empty();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        assert stack.top() == 3;
        assert stack.pop() == 3;
        assert stack.top() == 2;
        assert stack.pop() == 2;
        assert stack.top() == 1;
        assert stack.pop() == 1;
        assert stack.empty();
    }

}
