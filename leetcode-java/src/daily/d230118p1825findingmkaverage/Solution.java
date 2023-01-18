package daily.d230118p1825findingmkaverage;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.TreeMap;

/**
 * 1825. Finding MK Average
 *
 * https://leetcode.cn/problems/finding-mk-average/
 *
 * You are given two integers, m and k, and a stream of integers.
 * You are tasked to implement a data structure that calculates the MKAverage for the stream.
 *
 * The MKAverage can be calculated using these steps:
 *
 * If the number of the elements in the stream is less than m you should consider
 * the MKAverage to be -1. Otherwise, copy the last m elements of the stream
 * to a separate container.
 *
 * Remove the smallest k elements and the largest k elements from the container.
 * Calculate the average value for the rest of the elements rounded down to the nearest integer.
 * Implement the MKAverage class:
 *
 * MKAverage(int m, int k) Initializes the MKAverage object
 * with an empty stream and the two integers m and k.
 *
 * void addElement(int num) Inserts a new element num into the stream.
 *
 * int calculateMKAverage() Calculates and returns the MKAverage for the
 * current stream rounded down to the nearest integer.
 */

public class Solution {

    @SuppressWarnings({"SameParameterValue", "FieldCanBeLocal", "unchecked"})
    private static class MKAverage {
        private final int m, k;
        private final int BKT_MIN = 0, BKT_MID = 1, BKT_MAX = 2;
        private final int[] sums = new int[3], counts = new int[3];
        private final TreeMap<Integer, Integer>[] maps = new TreeMap[3];
        private final Queue<Integer> queue = new ArrayDeque<>();
        public MKAverage(int m, int k) {
            this.m = m; this.k = k;
            for (int i = 0; i < maps.length; i++) {
                maps[i] = new TreeMap<>();
            }
        }

        public void addElement(int num) {
            queue.add(num);

            if (queue.size() <= m) {
                addTo(BKT_MID, num);
                if (queue.size() == m) {
                    while (count(BKT_MIN) != k) addTo(BKT_MIN, popFirst(BKT_MID));
                    while (count(BKT_MAX) != k) addTo(BKT_MAX, popLast(BKT_MID));
                }
            } else {
                if (num < last(BKT_MIN)) {
                    addTo(BKT_MIN, num);
                    addTo(BKT_MID, popLast(BKT_MIN));
                } else if (num > first(BKT_MAX)) {
                    addTo(BKT_MAX, num);
                    addTo(BKT_MID, popFirst(BKT_MAX));
                } else addTo(BKT_MID, num);

                int removed = queue.remove();
                if (contains(BKT_MIN, removed)) {
                    pop(BKT_MIN, removed);
                    addTo(BKT_MIN, popFirst(BKT_MID));
                } else if (contains(BKT_MAX, removed)) {
                    pop(BKT_MAX, removed);
                    addTo(BKT_MAX, popLast(BKT_MID));
                } else pop(BKT_MID, removed);
            }
        }

        public int calculateMKAverage() {
            if (queue.size() != m) return -1;
            return sum(BKT_MID) / count(BKT_MID);
        }

        private void addTo(int bkt, int num) {
            sums[bkt] += num; counts[bkt]++;
            maps[bkt].merge(num, 1, MKAverage::sum);
        }

        private int pop(int bkt, int num) {
            maps[bkt].merge(num, -1, MKAverage::sum);
            sums[bkt] -= num; counts[bkt]--;
            return num;
        }

        private int popFirst(int bkt) { return pop(bkt, maps[bkt].firstKey()); }
        private int popLast(int bkt) { return pop(bkt, maps[bkt].lastKey()); }
        private int first(int bkt) { return maps[bkt].firstKey(); }
        private int last(int bkt) { return maps[bkt].lastKey(); }
        private int count(int bkt) { return counts[bkt]; }
        private int sum(int bkt) { return sums[bkt]; }
        private boolean contains(int bkt, int num) { return maps[bkt].containsKey(num); }

        private static Integer sum(Integer a, Integer b) {
            return a + b == 0 ? null : a + b;
        }
    }

    public static void main(String[] args) {
        MKAverage obj = new MKAverage(3, 1);
        obj.addElement(3);
        obj.addElement(1);
        assert obj.calculateMKAverage() == -1;
        obj.addElement(10);
        assert obj.calculateMKAverage() == 3;
        obj.addElement(5);
        obj.addElement(5);
        obj.addElement(5);
        assert obj.calculateMKAverage() == 5;
    }

}
