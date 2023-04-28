package daily.d230428p1172dinnerplatestacks;

import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.Deque;
import java.util.PriorityQueue;

/**
 * 1172. Dinner Plate Stacks
 *
 * https://leetcode.cn/problems/dinner-plate-stacks/description/
 *
 * You have an infinite number of stacks arranged in a row and numbered (left to right)
 * from 0, each of the stacks has the same maximum capacity.
 *
 * Implement the DinnerPlates class:
 *
 * DinnerPlates(int capacity) Initializes the object with the maximum capacity of the stacks capacity.
 *
 * void push(int val) Pushes the given integer val into the leftmost stack with a size less than capacity.
 *
 * int pop() Returns the value at the top of the rightmost non-empty stack and removes it
 * from that stack, and returns -1 if all the stacks are empty.
 *
 * int popAtStack(int index) Returns the value at the top of the stack with the given index
 * and removes it from that stack or returns -1 if the stack with that given index is empty.
 */

public class Solution {

    private static class DinnerPlates {
        private record IndexedStack(int idx, Deque<Integer> stack) { }

        private int idx = 0;
        private final int capacity;
        private final PriorityQueue<IndexedStack> half = new PriorityQueue<>(Comparator.comparingInt(a -> a.idx));
        private final PriorityQueue<IndexedStack> full = new PriorityQueue<>(Comparator.comparingInt(a -> a.idx));
        public DinnerPlates(int capacity) { this.capacity = capacity; }

        public void push(int val) {
            if (half.isEmpty()) half.add(new IndexedStack(idx++, new ArrayDeque<>()));
            IndexedStack curr = half.remove();
            curr.stack.push(val);
            if (curr.stack.size() == capacity) full.add(curr);
            else half.add(curr);
        }

        public int pop() {
        }

        public int popAtStack(int index) {
        }
    }

    public static void main(String[] args) {
        DinnerPlates D = new DinnerPlates(2);
        D.push(1);
        D.push(2);
        D.push(3);
        D.push(4);
        D.push(5);
        assert D.popAtStack(0) == 2;
        D.push(20);
        D.push(21);
        assert D.popAtStack(0) == 20;
        assert D.popAtStack(2) == 21;
        assert D.pop() == 5;
        assert D.pop() == 4;
        assert D.pop() == 3;
        assert D.pop() == 1;
        assert D.pop() == -1;
    }

}
