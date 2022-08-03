package problem.p1381designastackwithincrementoperation;

/**
 * 1381. Design a Stack With Increment Operation
 *
 * https://leetcode.cn/problems/design-a-stack-with-increment-operation/
 *
 * Design a stack which supports the following operations.
 *
 * Implement the CustomStack class:
 *
 * CustomStack(int maxSize) Initializes the object with maxSize which is the maximum number of elements
 * in the stack or do nothing if the stack reached the maxSize.
 *
 * void push(int x) Adds x to the top of the stack if the stack hasn't reached the maxSize.
 *
 * int pop() Pops and returns the top of stack or -1 if the stack is empty.
 *
 * void inc(int k, int val) Increments the bottom k elements of the stack by val. If there are less
 * than k elements in the stack, just increment all the elements in the stack.
 */

public class Solution {

    private static class CustomStack {
        private int top = -1;
        private final int[] stack, adds;
        public CustomStack(int maxSize) { stack = new int[maxSize]; adds = new int[maxSize]; }
        public void push(int x) { if (top < stack.length - 1) stack[++top] = x; }

        public int pop() {
            if (top == -1) return -1;

            int v = stack[top] + adds[top];
            if (top != 0) adds[top - 1] += adds[top];

            adds[top] = 0; top--;
            return v;
        }

        public void increment(int k, int val) {
            k = Math.min(k - 1, top);
            if (k >= 0) adds[k] += val;
        }
    }

    public static void main(String[] args) {
        CustomStack customStack = new CustomStack(3);
        customStack.push(1);
        customStack.push(2);
        assert customStack.pop() == 2;
        customStack.push(2);
        customStack.push(3);
        customStack.push(4);
        customStack.increment(5, 100);
        customStack.increment(2, 100);
        assert customStack.pop() == 103;
        assert customStack.pop() == 202;
        assert customStack.pop() == 201;
        assert customStack.pop() == -1;
    }

}
