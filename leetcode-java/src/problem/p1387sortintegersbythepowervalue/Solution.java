package problem.p1387sortintegersbythepowervalue;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * 1387. Sort Integers by The Power Value
 *
 * https://leetcode.cn/problems/sort-integers-by-the-power-value/
 *
 * The power of an integer x is defined as the number of steps needed to transform x into 1 using the following steps:
 *
 * if x is even then x = x / 2
 * if x is odd then x = 3 * x + 1
 * For example, the power of x = 3 is 7 because 3 needs 7 steps to
 * become 1 (3 --> 10 --> 5 --> 16 --> 8 --> 4 --> 2 --> 1).
 *
 * Given three integers lo, hi and k. The task is to sort all integers
 * in the interval [lo, hi] by the power value in ascending order, if two
 * or more integers have the same power value sort them by ascending order.
 *
 * Return the kth integer in the range [lo, hi] sorted by the power value.
 *
 * Notice that for any integer x (lo <= x <= hi) it is guaranteed that x
 * will transform into 1 using these steps and that the power of x is will
 * fit in a 32-bit signed integer.
 */

public class Solution {

    public int getKth(int lo, int hi, int k) {
        PriorityQueue<int[]> q = new PriorityQueue<>((a, b) -> {
            if (a[1] == b[1]) return a[0] - b[0];
            return a[1] - b[1];
        });

        for (int i = hi; i >= lo; i--) q.add(new int[]{i, count(i)});
        while (--k > 0) q.remove();
        return q.remove()[0];
    }

    private final Map<Integer, Integer> memo = new HashMap<>();

    private int count(int v) {
        if (v == 1) return 0;
        if (!memo.containsKey(v)) {
            if (v % 2 == 0) memo.put(v, count(v / 2) + 1);
            else memo.put(v, count(3 * v + 1) + 1);
        }
        return memo.get(v);
    }

    public static void main(String[] args) {
        assert new Solution().getKth(12, 15, 2) == 13;
        assert new Solution().getKth(7, 11, 4) == 7;
    }

}
