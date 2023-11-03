package weekly.w369.A;

/**
 * 2917. Find the K-or of an Array
 *
 * https://leetcode.cn/contest/weekly-contest-369/problems/find-the-k-or-of-an-array/
 *
 * You are given a 0-indexed integer array nums, and an integer k.
 *
 * The K-or of nums is a non-negative integer that satisfies the following:
 *
 * The ith bit is set in the K-or if and only if there are at least k elements of nums in which bit i is set.
 * Return the K-or of nums.
 *
 * Note that a bit i is set in x if (2i AND x) == 2i, where AND is the bitwise AND operator.
 */

public class Solution {

    public int findKOr(int[] nums, int k) {
        int ans = 0;
        for (int i = 0; i < 31; i++) {
            int count = 0;
            for (var v : nums) {
                if ((v & (1 << i)) != 0) count++;
            }
            if (count >= k) ans |= 1 << i;
        }
        return ans;
    }

    public static void main(String[] args) {
    }

}
