package problem.p295findmedianfromdatastream;

import java.util.PriorityQueue;

/**
 * 295. Find Median from Data Stream
 *
 * https://leetcode-cn.com/problems/find-median-from-data-stream/
 *
 * The median is the middle value in an ordered integer list. If the size of the list is even,
 * there is no middle value and the median is the mean of the two middle values.
 *
 * For example, for arr = [2,3,4], the median is 3.
 * For example, for arr = [2,3], the median is (2 + 3) / 2 = 2.5.
 * Implement the MedianFinder class:
 *
 * MedianFinder() initializes the MedianFinder object.
 * void addNum(int num) adds the integer num from the data stream to the data structure.
 * double findMedian() returns the median of all elements so far.
 *
 * Answers within 10-5 of the actual answer will be accepted.
 */

public class Solution {

    private static class MedianFinder {
        private final PriorityQueue<Integer> a = new PriorityQueue<>();
        private final PriorityQueue<Integer> b = new PriorityQueue<>((l, r) -> r - l);

        public MedianFinder() {}

        public void addNum(int num) {
            if (a.size() != b.size()) { // size % 2 == 1
                a.add(num);
                b.add(a.remove());
            } else {
                b.add(num);
                a.add(b.remove());
            }
        }

        public double findMedian() {
            return a.size() != b.size() ? a.peek() : (a.peek() + b.peek()) / 2.0;
        }
    }

    public static void main(String[] args) {
        MedianFinder finder = new MedianFinder();
        finder.addNum(1);
        assert finder.findMedian() == 1;
        finder.addNum(2);
        assert finder.findMedian() == 1.5;
        finder.addNum(3);
        assert finder.findMedian() == 2;
        finder.addNum(4);
        assert finder.findMedian() == 2.5;
        finder.addNum(5);
        assert finder.findMedian() == 3;
    }

}
