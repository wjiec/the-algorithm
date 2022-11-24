package lcci.s3.p5sortofstackslcci;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 面试题 03.05. 栈排序
 *
 * https://leetcode.cn/problems/sort-of-stacks-lcci/
 *
 * 栈排序。 编写程序，对栈进行排序使最小元素位于栈顶。最多只能使用一个其他的临时栈存放数据，
 * 但不得将元素复制到别的数据结构（如数组）中。
 *
 * 该栈支持如下操作：push、pop、peek 和 isEmpty。当栈为空时，peek 返回 -1。
 */

public class Solution {

    private static class SortedStack {
        private final Deque<Integer> minStack = new ArrayDeque<>();
        public SortedStack() {}
        public void pop() { if (!minStack.isEmpty()) minStack.pop(); }
        public int peek() { return minStack.isEmpty() ? -1 : minStack.peek(); }
        public boolean isEmpty() { return minStack.isEmpty(); }

        public void push(int val) {
            Deque<Integer> stash = new ArrayDeque<>();
            while (!minStack.isEmpty() && minStack.peek() < val) {
                stash.push(minStack.pop());
            }

            minStack.push(val);
            while (!stash.isEmpty()) minStack.push(stash.pop());
        }
    }

    public static void main(String[] args) {
        SortedStack stack = new SortedStack();
        stack.push(1);
        stack.push(2);
        assert stack.peek() == 1;
        stack.pop();
        assert stack.peek() == 2;
    }

}
