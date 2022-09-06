package problem.p281zigzagiterator;

import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;

/**
 * 281. Zigzag Iterator
 *
 * https://leetcode.cn/problems/zigzag-iterator/
 *
 * Given two vectors of integers v1 and v2, implement an iterator to return their elements alternately.
 *
 * Implement the ZigzagIterator class:
 *
 * ZigzagIterator(List<int> v1, List<int> v2) initializes the object with the two vectors v1 and v2.
 * boolean hasNext() returns true if the iterator still has elements, and false otherwise.
 * int next() returns the current element of the iterator and moves the iterator to the next element.
 */

public class Solution {

    public static class ZigzagIterator {
        private final Queue<Iterator<Integer>> queue = new ArrayDeque<>();
        public ZigzagIterator(List<Integer> v1, List<Integer> v2) {
            if (!v1.isEmpty()) queue.add(v1.iterator());
            if (!v2.isEmpty()) queue.add(v2.iterator());
        }
        public boolean hasNext() { return !queue.isEmpty(); }

        public int next() {
            Iterator<Integer> it = queue.remove();
            int val = it.next();
            if (it.hasNext()) queue.add(it);
            return val;
        }
    }

    public static void main(String[] args) {
    }

}
