package daily.d230428p1172dinnerplatestacks;

import java.util.*;

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
        private final int capacity;
        private final List<Deque<Integer>> stacks = new ArrayList<>();
        private final PriorityQueue<Integer> hungry = new PriorityQueue<>();
        public DinnerPlates(int capacity) { this.capacity = capacity; }

        public void push(int val) {
            if (!hungry.isEmpty() && hungry.peek() >= stacks.size()) hungry.clear();
            if (hungry.isEmpty()) {
                Deque<Integer> curr = new ArrayDeque<>();
                curr.push(val); stacks.add(curr);
                if (curr.size() < capacity) hungry.add(stacks.size() - 1);
            } else {
                Deque<Integer> curr = stacks.get(hungry.peek()); curr.push(val);
                if (curr.size() == capacity) hungry.remove();
            }
        }

        public int pop() { return popAtStack(stacks.size() - 1); }

        public int popAtStack(int index) {
            if (index < 0 || index >= stacks.size() || stacks.get(index).isEmpty()) {
                return -1;
            }

            Deque<Integer> curr = stacks.get(index);
            if (curr.size() == capacity) hungry.add(index);

            int ans = curr.pop();
            while (!stacks.isEmpty() && stacks.get(stacks.size() - 1).isEmpty()) {
                stacks.remove(stacks.size() - 1);
            }
            return ans;
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
