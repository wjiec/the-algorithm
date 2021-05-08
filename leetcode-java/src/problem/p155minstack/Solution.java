package problem.p155minstack;

import java.util.Stack;

/**
 * 155. Min Stack
 *
 * https://leetcode-cn.com/problems/min-stack/
 *
 * Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.
 *
 * Implement the Solution class:
 *
 * Solution() initializes the stack object.
 * void push(val) pushes the element val onto the stack.
 * void pop() removes the element on the top of the stack.
 * int top() gets the top element of the stack.
 * int getMin() retrieves the minimum element in the stack.
 */

public class Solution {

//    private static class MinStack {
//        private Stack<Integer> stack = new Stack<>();
//        private Stack<Integer> mStack = new Stack<>();
//
//        public MinStack() { stack.push(Integer.MAX_VALUE); mStack.push(Integer.MAX_VALUE); }
//        public void push(int val) { stack.push(val); mStack.push(Math.min(val, mStack.peek())); }
//        public void pop() { stack.pop(); mStack.pop(); }
//        public int top() { return stack.peek(); }
//        public int getMin() { return mStack.peek(); }
//    }

    private static class MinStack {
        private int minValue = 0;
        private Stack<Integer> stack = new Stack<>();

        public MinStack() {}
        public void push(int val) {
            if (stack.empty()) {
                minValue = val;
                stack.push(0);
            } else {
                stack.push(val - minValue);
                if (val < minValue) {
                    minValue = val;
                }
            }
        }
        public void pop() {
            int top = stack.pop(); // top = val - min
            if (top < 0) { // val < min
                minValue = minValue - top;
            }
        }
        public int top() {
            if (stack.peek() < 0) {
                return minValue;
            }
            return stack.peek() + minValue;
        }
        public int getMin() { return minValue; }
    }

    // @TODO implement by different
    public static void main(String[] args) {
        MinStack ms0 = new MinStack();
        ms0.push(-2);
        ms0.push(0);
        ms0.push(-3);
        assert ms0.getMin() == -3;
        assert ms0.top() == -3;
        ms0.pop();
        assert ms0.top() == 0;
        ms0.pop();
        assert ms0.top() == -2;
        assert ms0.getMin() == -2;
    }

}
