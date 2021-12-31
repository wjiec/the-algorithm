package lcci3.p1threeinonelcci;

/**
 * 面试题 03.01. 三合一
 *
 * https://leetcode-cn.com/problems/three-in-one-lcci/
 *
 * 三合一。描述如何只用一个数组来实现三个栈。
 *
 * 你应该实现push(stackNum, value)、pop(stackNum)、isEmpty(stackNum)、peek(stackNum)方法。
 *
 * stackNum表示栈下标，value表示压入的值。
 *
 * 构造函数会传入一个stackSize参数，代表每个栈的大小。
 */

public class Solution {

    private static class TripleInOne {

        private final int size;
        private final int[] tops;
        private final int[] stack;

        public TripleInOne(int stackSize) {
            size = stackSize;
            stack = new int[size * 3];
            tops = new int[3];
        }

        public void push(int stackNum, int value) {
            if (tops[stackNum] < size) {
                stack[size * stackNum + tops[stackNum]] = value;
                tops[stackNum]++;
            }
        }

        public int pop(int stackNum) {
            if (tops[stackNum] > 0) {
                return stack[size * stackNum + (--tops[stackNum])];
            }
            return -1;
        }

        public int peek(int stackNum) {
            if (tops[stackNum] > 0) {
                return stack[size * stackNum + tops[stackNum] - 1];
            }
            return -1;
        }

        public boolean isEmpty(int stackNum) {
            return tops[stackNum] == 0;
        }
    }

    public static void main(String[] args) {
        TripleInOne stack = new TripleInOne(1);
        stack.push(0, 1);
        stack.push(0, 2);
        assert stack.pop(0) == 1;
        assert stack.pop(0) == -1;
        assert stack.pop(0) == -1;
        assert stack.isEmpty(0);
    }

}
