package weekly.w301.B;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * 6113. Smallest Number in Infinite Set
 *
 * https://leetcode.cn/contest/weekly-contest-301/problems/smallest-number-in-infinite-set/
 *
 * You have a set which contains all positive integers [1, 2, 3, 4, 5, ...].
 *
 * Implement the SmallestInfiniteSet class:
 *
 * SmallestInfiniteSet() Initializes the SmallestInfiniteSet object to contain all positive integers.
 * int popSmallest() Removes and returns the smallest integer contained in the infinite set.
 * void addBack(int num) Adds a positive integer num back into the infinite set, if
 * it is not already in the infinite set.
 */

public class Solution {

    private static class SmallestInfiniteSet {
        private int last = 1;
        private final Set<Integer> added = new HashSet<>();
        private final PriorityQueue<Integer> q = new PriorityQueue<>();
        public SmallestInfiniteSet() {}

        public int popSmallest() {
            if (!q.isEmpty() && q.peek() < last) {
                added.remove(q.peek());
                return q.remove();
            }
            return last++;
        }

        public void addBack(int num) {
            if (num < last & !added.contains(num)) {
                q.add(num); added.add(num);
            }
        }
    }

    public static void main(String[] args) {
    }

}
