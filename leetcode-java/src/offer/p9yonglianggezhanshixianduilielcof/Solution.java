package offer.p9yonglianggezhanshixianduilielcof;

import java.util.Stack;

/**
 * 剑指 Offer 09. 用两个栈实现队列
 *
 * https://leetcode-cn.com/problems/yong-liang-ge-zhan-shi-xian-dui-lie-lcof/
 *
 * 用两个栈实现一个队列。队列的声明如下，请实现它的两个函数 appendTail 和 deleteHead
 * 分别完成在队列尾部插入整数和在队列头部删除整数的功能。(若队列中没有元素，deleteHead 操作返回 -1 )
 */

public class Solution {

    private static class CQueue {
        private final Stack<Integer> in = new Stack<>();
        private final Stack<Integer> out = new Stack<>();
        public CQueue() {}

        public void appendTail(int value) {
            in.push(value);
        }

        public int deleteHead() {
            if (out.empty()) {
                if (in.empty()) return -1;
                while (!in.isEmpty()) {
                    out.push(in.pop());
                }
            }
            return out.pop();
        }
    }

    public static void main(String[] args) {
        CQueue queue = new CQueue();
        assert queue.deleteHead() == -1;

        queue.appendTail(3);
        assert queue.deleteHead() == 3;
        assert queue.deleteHead() == -1;

        queue.appendTail(5);
        queue.appendTail(2);
        assert queue.deleteHead() == 5;
        assert queue.deleteHead() == 2;
        assert queue.deleteHead() == -1;
    }

}
