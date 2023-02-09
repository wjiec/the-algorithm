package weekly.w331.D;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;
import java.util.TreeMap;

/**
 * 2561. Rearranging Fruits
 *
 * https://leetcode.cn/problems/rearranging-fruits/
 *
 * You have two fruit baskets containing n fruits each.
 * You are given two 0-indexed integer arrays basket1 and basket2
 * representing the cost of fruit in each basket.
 *
 * You want to make both baskets equal. To do so, you can use the
 * following operation as many times as you want:
 *
 * Chose two indices i and j, and swap the ith fruit of basket1 with the jth fruit of basket2.
 * The cost of the swap is min(basket1[i],basket2[j]).
 * Two baskets are considered equal if sorting them according to the fruit cost
 * makes them exactly the same baskets.
 *
 * Return the minimum cost to make both the baskets equal or -1 if impossible.
 */

public class Solution {

    public long minCost(int[] basket1, int[] basket2) {
        TreeMap<Integer, Integer> map1 = new TreeMap<>();
        TreeMap<Integer, Integer> map2 = new TreeMap<>();
        for (var v : basket1) map1.merge(v, 1, Integer::sum);
        for (var v : basket2) map2.merge(v, 1, Integer::sum);

        long min = Math.min(map1.firstKey(), map2.firstKey());
        Deque<long[]> deque = new ArrayDeque<>();
        while (!map1.isEmpty() || !map2.isEmpty()) {
            Map.Entry<Integer, Integer> e1 = map1.firstEntry();
            Map.Entry<Integer, Integer> e2 = map2.firstEntry();

            if (e1 == null) {
                if (e2.getValue() % 2 != 0) return -1;

                map2.remove(e2.getKey());
                deque.add(new long[]{e2.getKey(), e2.getValue() / 2});
                continue;
            } else if (e2 == null) {
                if (e1.getValue() % 2 != 0) return -1;

                map1.remove(e1.getKey());
                deque.add(new long[]{e1.getKey(), e1.getValue() / 2});
                continue;
            }

            if (e1.getKey().equals(e2.getKey())) {
                int swap = Math.min(e1.getValue(), e2.getValue());
                map1.merge(e1.getKey(), -swap, Solution::sum);
                map2.merge(e2.getKey(), -swap, Solution::sum);
                continue;
            }

            if (e1.getKey() < e2.getKey()) {
                if (e1.getValue() % 2 != 0) return -1;

                int avg = e1.getValue() / 2;
                map1.merge(e1.getKey(), -avg, Solution::sum);
                map2.merge(e1.getKey(), avg, Solution::sum);
                deque.add(new long[]{e1.getKey(), avg});
            } else {
                if (e2.getValue() % 2 != 0) return -1;

                int avg = e2.getValue() / 2;
                map1.merge(e2.getKey(), avg, Solution::sum);
                map2.merge(e2.getKey(), -avg, Solution::sum);
                deque.add(new long[]{e2.getKey(), avg});
            }
        }

        long ans = 0;
        while (!deque.isEmpty()) {
            long[] first = deque.pollFirst();
            long[] last = deque.pollLast();
            if (last == null) {
                if (first[1] % 2 != 0) return -1;
                ans += Math.min(first[0], min * 2) * first[1] / 2;
            } else {
                long swap = Math.min(first[1], last[1]);
                ans += Math.min(first[0], min * 2) * swap;
                if (first[1] - swap != 0) deque.addFirst(new long[]{first[0], first[1] - swap});
                if (last[1] - swap != 0) deque.addLast(new long[]{last[0], last[1] - swap});
            }
        }

        return ans;
    }

    private static Integer sum(Integer a, Integer b) {
        return a + b == 0 ? null : a + b;
    }

    public static void main(String[] args) {
        assert new Solution().minCost(
            new int[]{8, 14, 43, 43, 80, 80, 84, 88, 88, 100},
            new int[]{8, 14, 32, 32, 42, 42, 68, 68, 84, 100}
        ) == 48;
        assert new Solution().minCost(new int[]{4,4,4,4,3}, new int[]{5,5,5,5,3}) == 8;

        assert new Solution().minCost(new int[]{4,2,2,2}, new int[]{1,4,1,2}) == 1;
        assert new Solution().minCost(new int[]{2,3,4,1}, new int[]{3,2,5,1}) == -1;
    }

}
