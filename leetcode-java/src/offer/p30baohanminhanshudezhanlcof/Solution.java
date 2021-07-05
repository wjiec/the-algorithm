package offer.p30baohanminhanshudezhanlcof;

import java.util.Stack;

/**
 * 剑指 Offer 30. 包含min函数的栈
 *
 * https://leetcode-cn.com/problems/bao-han-minhan-shu-de-zhan-lcof/
 *
 * 定义栈的数据结构，请在该类型中实现一个能够得到栈的最小元素的 min 函数在该栈中，调用 min、push 及 pop 的时间复杂度都是 O(1)。
 */

public class Solution {

    private static class MinStack {
        private final Stack<Integer> stack = new Stack<>();
        private final Stack<Integer> minimum = new Stack<>();

        public MinStack() {}

        public void push(int x) {
            stack.push(x);
            if (minimum.isEmpty()) minimum.push(x);
            else minimum.push(Math.min(minimum.peek(), x));
        }

        public void pop() { stack.pop(); minimum.pop(); }
        public int top() { return stack.peek(); }
        public int min() { return minimum.peek(); }
    }

    public static void main(String[] args) {
        MinStack stack = new MinStack();
        stack.push(-2);
        stack.push(0);
        stack.push(-3);

        assert stack.min() == -3;
        stack.pop();
        assert stack.top() == 0;
        assert stack.min() == -2;
    }

}
