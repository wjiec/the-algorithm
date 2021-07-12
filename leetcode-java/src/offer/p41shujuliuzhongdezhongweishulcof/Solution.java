package offer.p41shujuliuzhongdezhongweishulcof;

import java.util.PriorityQueue;

/**
 * 剑指 Offer 41. 数据流中的中位数
 *
 * https://leetcode-cn.com/problems/shu-ju-liu-zhong-de-zhong-wei-shu-lcof/
 *
 * 如何得到一个数据流中的中位数？如果从数据流中读出奇数个数值，那么中位数就是所有数值排序之后位于中间的数值。
 * 如果从数据流中读出偶数个数值，那么中位数就是所有数值排序之后中间两个数的平均值。
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
