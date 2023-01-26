package lcci.s17.p10continuousmedianlcci;

import java.util.TreeMap;

/**
 * 面试题 17.20. 连续中值
 *
 * https://leetcode.cn/problems/continuous-median-lcci/
 *
 * 随机产生数字并传递给一个方法。你能否完成这个方法，在每次产生新值时，寻找当前所有值的中间值（中位数）并保存。
 *
 * 中位数是有序列表中间的数。如果列表长度是偶数，中位数则是中间两个数的平均值。
 *
 * 例如，
 *
 * [2,3,4] 的中位数是 3
 *
 * [2,3] 的中位数是 (2 + 3) / 2 = 2.5
 *
 * 设计一个支持以下两种操作的数据结构：
 *
 * void addNum(int num) - 从数据流中添加一个整数到数据结构中。
 * double findMedian() - 返回目前所有元素的中位数。
 */

public class Solution {

    private static class MedianFinder {
        private int count = 0;
        private final int[] counts = new int[3], sums = new int[3];
        private final int BKT_LEFT = 0, BKT_MID = 1, BKT_RIGHT = 2;
        private TreeMap<Integer, Integer>[] buckets = new TreeMap[]{
            new TreeMap<>(), new TreeMap<>(), new TreeMap<>()
        };
        public MedianFinder() {}

        public void addNum(int num) {
            if (cnt(BKT_MID) == 0) {
                add(BKT_MID, num);
            } else {
                if (num < first(BKT_MID)) add(BKT_LEFT, num);
                else if (num > last(BKT_MID)) add(BKT_RIGHT, num);
                else add(BKT_MID, num);
            }

            int sideCount = count++ / 2;
            while (cnt(BKT_LEFT) != sideCount) {
                if (cnt(BKT_LEFT) < sideCount) {
                    if (cnt(BKT_RIGHT) != 0) add(BKT_MID, popFirst(BKT_RIGHT));
                    add(BKT_LEFT, popFirst(BKT_MID));
                } else add(BKT_MID, popLast(BKT_LEFT));
            }
            while (cnt(BKT_RIGHT) != sideCount) {
                if (cnt(BKT_RIGHT) < sideCount) add(BKT_RIGHT, popLast(BKT_MID));
                else add(BKT_MID, popFirst(BKT_RIGHT));
            }
        }

        public double findMedian() {
            return sums[BKT_MID] / (((count - 1) % 2) + 1.0);
        }

        private int cnt(int bkt) { return counts[bkt]; }
        private int first(int bkt) { return buckets[bkt].firstKey(); }
        private int last(int bkt) { return buckets[bkt].lastKey(); }

        private int popFirst(int bkt) {
            int first = buckets[bkt].firstKey();
            counts[bkt]--; sums[bkt] -= first;
            buckets[bkt].merge(first, -1, MedianFinder::sum);
            return first;
        }

        private int popLast(int bkt) {
            int last = buckets[bkt].lastKey();
            counts[bkt]--; sums[bkt] -= last;
            buckets[bkt].merge(last, -1, MedianFinder::sum);
            return last;
        }

        private void add(int bkt, int num) {
            counts[bkt]++; sums[bkt] += num;
            buckets[bkt].merge(num, 1, MedianFinder::sum);
        }

        private static Integer sum(Integer a, Integer b) {
            return a + b == 0 ? null : (a + b);
        }
    }

    public static void main(String[] args) {
        MedianFinder finder = new MedianFinder();
        finder.addNum(1);
        assert finder.findMedian() == 1.0;
        finder.addNum(2);
        assert finder.findMedian() == 1.5;
        finder.addNum(3);
        assert finder.findMedian() == 2.0;
        finder.addNum(4);
        assert finder.findMedian() == 2.5;
        finder.addNum(5);
        assert finder.findMedian() == 3;
    }

}
