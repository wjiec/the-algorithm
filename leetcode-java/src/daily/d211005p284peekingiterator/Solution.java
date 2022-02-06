package daily.d211005p284peekingiterator;

import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;

/**
 * 284. Peeking Iterator
 *
 * https://leetcode-cn.com/problems/peeking-iterator/
 *
 * Design an iterator that supports the peek operation
 * on a list in addition to the hasNext and the next operations.
 *
 * Implement the PeekingIterator class:
 *
 * PeekingIterator(int[] nums) Initializes the object with the given integer array nums.
 * int next() Returns the next element in the array and moves the pointer to the next element.
 * bool hasNext() Returns true if there are still elements in the array.
 * int peek() Returns the next element in the array without moving the pointer.
 */

public class Solution {

    static class PeekingIterator implements Iterator<Integer> {

        private final Iterator<Integer> iter;
        private final Queue<Integer> deque = new ArrayDeque<>();

        public PeekingIterator(Iterator<Integer> iterator) {
            iter = iterator;
        }

        // Returns the next element in the iteration without advancing the iterator.
        public Integer peek() {
            if (iter.hasNext()) deque.add(iter.next());
            return deque.peek();
        }

        // hasNext() and next() should behave the same as in the Iterator interface.
        // Override them if needed.
        @Override
        public Integer next() {
            if (iter.hasNext()) deque.add(iter.next());
            return deque.remove();
        }

        @Override
        public boolean hasNext() {
            return !deque.isEmpty() || iter.hasNext();
        }
    }

    public static void main(String[] args) {
        var iter = new PeekingIterator(List.of(1, 2, 3).iterator());
        assert iter.hasNext();
        assert iter.next() == 1;
        assert iter.peek() == 2;
        assert iter.next() == 2;
        assert iter.peek() == 3;
        assert iter.next() == 3;
        assert !iter.hasNext();
    }

}
