package problem.p232implementqueueusingstacks;

import java.util.Stack;

/**
 * 232. Implement Queue using Stacks
 *
 * https://leetcode-cn.com/problems/implement-queue-using-stacks/
 *
 * Implement a first in first out (FIFO) queue using only two stacks.
 * The implemented queue should support all the functions of a normal queue (push, peek, pop, and empty).
 *
 * Implement the MyQueue class:
 *
 * void push(int x) Pushes element x to the back of the queue.
 * int pop() Removes the element from the front of the queue and returns it.
 * int peek() Returns the element at the front of the queue.
 * boolean empty() Returns true if the queue is empty, false otherwise.
 * Notes:
 *
 * You must use only standard operations of a stack, which means only push to top,
 * peek/pop from top, size, and is empty operations are valid.
 * Depending on your language, the stack may not be supported natively.
 * You may simulate a stack using a list or deque (double-ended queue)
 * as long as you use only a stack's standard operations.
 *
 * Follow-up: Can you implement the queue such that each operation is amortized O(1) time complexity?
 * In other words, performing n operations will take overall O(n) time
 * even if one of those operations may take longer.
 */

public class Solution {

    static class MyQueue {

        private Stack<Integer> s0 =new Stack<>();
        private Stack<Integer> s1 = new Stack<>();

        /** Initialize your data structure here. */
        public MyQueue() {}

        /** Push element x to the back of queue. */
        public void push(int x) {
            while (!s1.empty()) {
                s0.push(s1.pop());
            }
            s1.push(x);
            while (!s0.empty()) {
                s1.push(s0.pop());
            }
        }

        /** Removes the element from in front of queue and returns that element. */
        public int pop() {
            return s1.pop();
        }

        /** Get the front element. */
        public int peek() {
            return s1.peek();
        }

        /** Returns whether the queue is empty. */
        public boolean empty() {
            return s1.empty();
        }
    }

    public static void main(String[] args) {
        MyQueue queue = new MyQueue();
        assert queue.empty();
        queue.push(1);
        queue.push(2);
        assert queue.peek() == 1;
        queue.push(3);
        queue.push(4);
        assert queue.peek() == 1;
        assert queue.pop() == 1;
        assert queue.peek() == 2;
        assert queue.pop() == 2;
        assert queue.peek() == 3;
        assert queue.pop() == 3;
        assert queue.peek() == 4;
        assert queue.pop() == 4;
        assert queue.empty();
    }

}
