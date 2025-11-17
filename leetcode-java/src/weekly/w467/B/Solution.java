package weekly.w467.B;

import java.util.TreeSet;

/**
 * Q2. Maximize Sum of At Most K Distinct Elements
 *
 * https://leetcode.cn/contest/weekly-contest-467/problems/maximize-sum-of-at-most-k-distinct-elements/
 *
 * You are given a positive integer array nums and an integer k.
 *
 * Choose at most k elements from nums so that their sum is maximized. However, the chosen numbers must be distinct.
 *
 * Return an array containing the chosen numbers in strictly descending order.
 */

public class Solution {

    public int[] maxKDistinct(int[] nums, int k) {
        TreeSet<Integer> s = new TreeSet<>();
        for (var v : nums) s.add(v);

        k = Math.min(k, s.size());
        int[] ans = new int[k];
        for (int i = 0; i < k; i++) ans[i] = s.pollLast();
        return ans;
    }

    public static void main(String[] args) {
    }

}
