package offer.p59duiliedezuidazhilcof;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;

/**
 * 面试题59 - II. 队列的最大值
 *
 * https://leetcode.cn/problems/dui-lie-de-zui-da-zhi-lcof/
 *
 * 请定义一个队列并实现函数 max_value 得到队列里的最大值，要求
 * 函数max_value、push_back 和 pop_front 的均摊时间复杂度都是O(1)。
 *
 * 若队列为空，pop_front 和 max_value 需要返回 -1
 */

public class Solution {

    private static class MaxQueue {
        private final Queue<Integer> queue = new ArrayDeque<>();
        private final Deque<Integer> stack = new ArrayDeque<>();
        public MaxQueue() {}
        public int max_value() { return stack.isEmpty() ? -1 : stack.peekFirst(); }

        public void push_back(int value) {
            while (!stack.isEmpty() && stack.peekLast() < value) {
                stack.removeLast();
            }
            queue.add(value);
            stack.addLast(value);
        }

        public int pop_front() {
            if (queue.isEmpty()) return -1;
            if (stack.isEmpty()) return -1;

            int ans = queue.remove();
            if (ans == stack.peekFirst()) {
                stack.removeFirst();
            }
            return ans;
        }
    }

    public static void main(String[] args) {
        MaxQueue queue = new MaxQueue();
        queue.push_back(1);
        queue.push_back(2);
        assert queue.max_value() == 2;
        queue.pop_front();
        assert queue.max_value() == 2;
        queue.pop_front();

        queue.push_back(2);
        queue.push_back(1);
        assert queue.max_value() == 2;
        queue.pop_front();
        assert queue.max_value() == 1;
    }

}
