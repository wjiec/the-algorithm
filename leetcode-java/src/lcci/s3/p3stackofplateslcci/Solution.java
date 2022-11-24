package lcci.s3.p3stackofplateslcci;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * 面试题 03.03. 堆盘子
 *
 * https://leetcode.cn/problems/stack-of-plates-lcci/
 *
 * 堆盘子。设想有一堆盘子，堆太高可能会倒下来。因此，在现实生活中，盘子堆到一定高度时，我们就会另外堆一堆盘子。
 * 请实现数据结构SetOfStacks，模拟这种行为。SetOfStacks应该由多个栈组成，并且在前一个栈填满时新建一个栈。
 * 此外，SetOfStacks.push()和SetOfStacks.pop()应该与普通栈的操作方法相同（也就是说，pop()返回的值，
 * 应该跟只有一个栈时的情况一样）。
 *
 * 进阶：实现一个popAt(int index)方法，根据指定的子栈，执行pop操作。
 *
 * 当某个栈为空时，应当删除该栈。当栈中没有元素或不存在该栈时，pop，popAt 应返回 -1.
 */

public class Solution {

    private static class StackOfPlates {
        private final int capacity;
        private final List<Deque<Integer>> plates = new ArrayList<>();
        public StackOfPlates(int cap) { capacity = cap; }

        public void push(int val) {
            if (plates.isEmpty() || plates.get(plates.size() - 1).size() == capacity) {
                plates.add(new ArrayDeque<>());
            }
            Deque<Integer> last = plates.get(plates.size() - 1);
            if (last.size() < capacity) last.push(val);
        }

        public int pop() {
            while (!plates.isEmpty() && plates.get(plates.size() - 1).isEmpty()) {
                plates.remove(plates.size() - 1);
            }
            if (!plates.isEmpty()) {
                int val = plates.get(plates.size() - 1).pop();
                while (!plates.isEmpty() && plates.get(plates.size() - 1).isEmpty()) {
                    plates.remove(plates.size() - 1);
                }
                return val;
            }
            return -1;
        }

        public int popAt(int index) {
            while (index < plates.size()) {
                if (plates.get(index).isEmpty()) {
                    plates.remove(index);
                } else break;
            }

            if (index < plates.size()) {
                int val = plates.get(index).pop();
                if (plates.get(index).isEmpty()) {
                    plates.remove(index);
                }
                return val;
            }
            return -1;
        }
    }

    public static void main(String[] args) {
        StackOfPlates stack = new StackOfPlates(1);
        stack.push(1);
        stack.push(2);
        assert stack.popAt(1) == 2;
        assert stack.pop() == 1;
        assert stack.pop() == -1;
    }

}
