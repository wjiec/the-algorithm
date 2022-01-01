package lcci3.p4implementqueueusingstackslcci;

import java.util.Stack;

/**
 * 面试题 03.04. 化栈为队
 *
 * https://leetcode-cn.com/problems/implement-queue-using-stacks-lcci/
 *
 * 实现一个MyQueue类，该类用两个栈来实现一个队列。
 */

public class Solution {

    private static class MyQueue {
        private final Stack<Integer> a = new Stack<>();
        private final Stack<Integer> b = new Stack<>();

        public MyQueue() { }
        public void push(int x) { b.push(x); }

        public int pop() {
            if (a.isEmpty()) {
                while (!b.isEmpty()) {
                    a.push(b.pop());
                }
            }
            return a.pop();
        }

        public int peek() {
            if (a.isEmpty()) {
                while (!b.isEmpty()) {
                    a.push(b.pop());
                }
            }
            return a.peek();
        }

        public boolean empty() {
            return a.empty() && b.empty();
        }
    }

    public static void main(String[] args) {
        MyQueue queue = new MyQueue();
        queue.push(1);
        queue.push(2);
        assert queue.peek() == 1;
        assert queue.pop() == 1;
        assert queue.peek() == 2;
        assert !queue.empty();
    }

}
