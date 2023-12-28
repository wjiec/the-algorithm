package daily.d231228p2735collectingchocolates;

/**
 * 2735. Collecting Chocolates
 *
 * https://leetcode.cn/problems/collecting-chocolates
 *
 * You are given a 0-indexed integer array nums of size n representing
 * the cost of collecting different chocolates.
 *
 * The cost of collecting the chocolate at the index i is nums[i].
 * Each chocolate is of a different type, and initially, the chocolate at the index i is of ith type.
 *
 * In one operation, you can do the following with an incurred cost of x:
 *
 * Simultaneously change the chocolate of ith type to ((i + 1) mod n)th type for all chocolates.
 *
 * Return the minimum cost to collect chocolates of all types, given that you can
 * perform as many operations as you would like.
 */

public class Solution {

    public long minCost(int[] nums, int x) {
        int n = nums.length;
        int[] dp = new int[n];
        System.arraycopy(nums, 0, dp, 0, n);

        long ans = sum(dp);
        for (int c = 1; c < n; c++) {
            for (int i = 0; i < n; i++) {
                dp[i] = Math.min(dp[i], nums[(c + i) % n]);
            }
            ans = Math.min(ans, (long) c * x + sum(dp));
        }
        return ans;
    }

    private long sum(int[] nums) {
        long s = 0;
        for (var v : nums) s += v;
        return s;
    }

    public static void main(String[] args) {
    }

}
