package weekly.bw104.C;

/**
 * 6369. Maximum OR
 *
 * https://leetcode.cn/contest/biweekly-contest-104/problems/maximum-or/
 *
 * You are given a 0-indexed integer array nums of length n and an integer k.
 *
 * In an operation, you can choose an element and multiply it by 2.
 *
 * Return the maximum possible value of nums[0] | nums[1] | ... | nums[n - 1] that can be
 * obtained after applying the operation on nums at most k times.
 *
 * Note that a | b denotes the bitwise or between two integers a and b.
 */

public class Solution {

    public long maximumOr(int[] nums, int k) {
        int[] bits = new int[64];
        for (var v : nums) {
            System.out.println(Integer.toBinaryString(v));
            for (int i = 0; v >= (1 << i); i++) {
                if ((v & (1 << i)) != 0) bits[i]++;
            }
        }

        long ans = 0;
        for (var v : nums) {
            long curr = (long) v << k;
            for (int i = 0; v >= (1 << i); i++) {
                if (bits[i] > 1 || ((v & (1 << i)) == 0 && bits[i] == 1)) {
                    curr |= (1L << i);
                }
            }
            ans = Math.max(ans, curr);
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().maximumOr(new int[]{12, 9}, 1) == 30;
        assert new Solution().maximumOr(new int[]{8, 1, 2}, 2) == 35;
    }

}
