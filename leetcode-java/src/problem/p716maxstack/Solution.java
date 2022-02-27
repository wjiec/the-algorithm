package problem.p716maxstack;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 716. Max Stack
 *
 * https://leetcode-cn.com/problems/max-stack/
 *
 * Design a max stack data structure that supports the stack operations
 * and supports finding the stack's maximum element.
 *
 * Implement the MaxStack class:
 *
 * MaxStack() Initializes the stack object.
 *
 * void push(int x) Pushes element x onto the stack.
 *
 * int pop() Removes the element on top of the stack and returns it.
 *
 * int top() Gets the element on the top of the stack without removing it.
 *
 * int peekMax() Retrieves the maximum element in the stack without removing it.
 *
 * int popMax() Retrieves the maximum element in the stack and removes it.
 * If there is more than one maximum element, only remove the top-most one.
 */

public class Solution {

    private static class MaxStack {
        private final Deque<Integer> stack = new ArrayDeque<>();
        private final Deque<Integer> maxStack = new ArrayDeque<>();
        public MaxStack() {}

        public void push(int x) {
            stack.push(x);
            maxStack.push(Math.max(x, maxStack.isEmpty() ? x : maxStack.peek()));
        }

        public int pop() { maxStack.pop(); return stack.pop(); }
        public int top() { return stack.getFirst(); }
        public int peekMax() { return maxStack.getFirst(); }

        public int popMax() {
            int max = peekMax();
            Deque<Integer> buf = new ArrayDeque<>();
            while (top() != max) buf.push(pop());
            pop();
            while (!buf.isEmpty()) push(buf.pop());
            return max;
        }
    }

    public static void main(String[] args) {
        MaxStack stack = new MaxStack();
        stack.push(5);
        stack.push(1);
        stack.push(5);
        assert stack.top() == 5;
        assert stack.popMax() == 5;
        assert stack.top() == 1;
        assert stack.peekMax() == 5;
        assert stack.pop() == 1;
        assert stack.top() == 5;
    }

}
