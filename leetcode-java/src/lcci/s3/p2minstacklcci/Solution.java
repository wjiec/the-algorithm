package lcci.s3.p2minstacklcci;

import java.util.Stack;

/**
 * 面试题 03.02. 栈的最小值
 *
 * https://leetcode-cn.com/problems/min-stack-lcci/
 *
 * 请设计一个栈，除了常规栈支持的pop与push函数以外，还支持min函数，该函数返回栈元素中的最小值。
 *
 * 执行push、pop和min操作的时间复杂度必须为O(1)。
 */

public class Solution {

    private static class MinStack {

        private int minValue = 0;
        private final Stack<Integer> min = new Stack<>();
        private final Stack<Integer> stack = new Stack<>();

        public MinStack() {}

        public void push(int x) {
            if (stack.isEmpty()) {
                minValue = x;
            } else {
                minValue = Math.min(minValue, x);
            }

            stack.push(x);
            min.push(minValue);
        }

        public void pop() {
            stack.pop();
            min.pop();
            if (!stack.isEmpty()) {
                minValue = min.peek();
            }
        }

        public int top() {
            return stack.peek();
        }

        public int getMin() {
            return min.peek();
        }
    }

    public static void main(String[] args) {
        MinStack stack = new MinStack();
        stack.push(-2);
        stack.push(0);
        stack.push(-3);
        assert stack.getMin() == -3;
        stack.pop();
        assert stack.top() == 0;
        assert stack.getMin() == -2;
    }

}
