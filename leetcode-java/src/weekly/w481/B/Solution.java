package weekly.w481.B;

/**
 * Q2. Minimum Deletion Cost to Make All Characters Equal
 *
 * https://leetcode.cn/contest/weekly-contest-481/problems/minimum-deletion-cost-to-make-all-characters-equal/
 *
 * You are given a string s of length n and an integer array cost of the same length,
 * where cost[i] is the cost to delete the ith character of s.
 *
 * You may delete any number of characters from s (possibly none), such that the resulting
 * string is non-empty and consists of equal characters.
 *
 * Return an integer denoting the minimum total deletion cost required.
 */

public class Solution {

    public long minCost(String s, int[] cost) {
        long[] cs = new long[26]; long tot = 0;
        for (int i = 0; i < cost.length; i++) {
            cs[s.charAt(i) - 'a'] += cost[i]; tot += cost[i];
        }

        long ans = Long.MAX_VALUE;
        for (var v : cs) ans = Math.min(ans, tot - v);
        return ans;
    }

    public static void main(String[] args) {
    }

}
