package weekly.bw178.C;

import java.util.HashMap;
import java.util.Map;

/**
 * Q3. Minimum Cost to Equalize Arrays Using Swaps
 *
 * https://leetcode.cn/contest/biweekly-contest-178/problems/minimum-cost-to-equalize-arrays-using-swaps/
 *
 * You are given two integer arrays nums1 and nums2 of size n.
 *
 * You can perform the following two operations any number of times on these two arrays:
 *
 * Swap within the same array: Choose two indices i and j. Then, choose either to
 * swap nums1[i] and nums1[j], or nums2[i] and nums2[j]. This operation is free of charge.
 *
 * Swap between two arrays: Choose an index i. Then, swap nums1[i] and nums2[i].
 * This operation incurs a cost of 1.
 *
 * Return an integer denoting the minimum cost to make nums1 and nums2 identical.
 * If this is not possible, return -1.
 */

public class Solution {

    private static Integer sum(Integer a, Integer b) {
        return a + b == 0 ? null : (a + b);
    }

    public int minCost(int[] nums1, int[] nums2) {
        // 如果想要两个数组相同, 那么必须所有数都要成对
        //  - 最好是相同数组内交换, 这样可以不消耗代价
        //  - 如果两个数组的元素相同, 则可以通过交换直接消除
        Map<Integer, Integer> freq = new HashMap<>();

        record Pair(int a, int b) {}
        Map<Pair, Integer> diff = new HashMap<>();
        for (int i = 0; i < nums1.length; i++) {
            int a = nums1[i], b = nums2[i];
            freq.merge(a, 1, Solution::sum);
            freq.merge(b, -1, Solution::sum);
        }
        if (freq.isEmpty()) return 0;
        // 如果存在某个数是奇数的话, 无法得到相同的数组, 直接返回
        int pos = 0, neg = 0;
        for (var v : freq.values()) {
            if ((v & 1) == 1) return -1;
            if (v > 0) pos += v; else neg += v;
        }
        // 否则对于任意的偶数, 我们都可以通过一次 2 操作加上一次 1 操作使得其可以匹配
        return pos / 2 - (neg + pos) / 2;
    }

    public static void main(String[] args) {
        assert new Solution().minCost(new int[]{1,2,3}, new int[]{2,3,1}) == 0;
        assert new Solution().minCost(new int[]{10,10}, new int[]{20,20}) == 1;

        assert new Solution().minCost(new int[]{10,20}, new int[]{20,10}) == 0;
        assert new Solution().minCost(new int[]{10,20}, new int[]{30,40}) == -1;
    }

}
