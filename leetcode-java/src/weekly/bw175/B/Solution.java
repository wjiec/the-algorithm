package weekly.bw175.B;

/**
 * Q2. Minimum K to Reduce Array Within Limit
 *
 * https://leetcode.cn/contest/biweekly-contest-175/problems/minimum-k-to-reduce-array-within-limit/
 *
 * You are given a positive integer array nums.
 *
 * For a positive integer k, define nonPositive(nums, k) as the minimum number of operations
 * needed to make every element of nums non-positive. In one operation,
 * you can choose an index i and reduce nums[i] by k.
 *
 * Return an integer denoting the minimum value of k such that nonPositive(nums, k) <= k2.
 */

public class Solution {

    public int minimumK(int[] nums) {
        // k 值越大, 那么减少的速度越快, 越有可能满足 <= k^2
        int l = 0, r = Integer.MAX_VALUE; // (l, r)
        while (l + 1 < r) {
            int mid = l + (r - l) / 2;
            // 如果 mid 可以满足条件的话, 那么可以尝试减小值
            if (check(nums, mid)) r = mid;
            else l = mid;
        }
        return r;
    }

    private boolean check(int[] nums, long k) {
        long count = k * k;
        for (var v : nums) if ((count -= (v + k - 1) / k) < 0) break;
        return count >= 0;
    }

    public static void main(String[] args) {
        assert new Solution().minimumK(new int[]{1,1,1,1,1}) == 3;
    }

}
