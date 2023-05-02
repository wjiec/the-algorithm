package problem.p487maxconsecutiveonesii;

/**
 * 487. Max Consecutive Ones II
 *
 * https://leetcode.cn/problems/max-consecutive-ones-ii/
 *
 * Given a binary array nums, return the maximum number of consecutive 1's in
 * the array if you can flip at most one 0.
 */

public class Solution {

    public int findMaxConsecutiveOnes(int[] nums) {
        int ans = 0, n = nums.length, c0 = 0;
        for (int l = 0, r = 0; r < n; r++) {
            c0 += nums[r] == 0 ? 1 : 0;
            while (c0 > 1) c0 -= nums[l++] == 0 ? 1 : 0;
            ans = Math.max(ans, r - l + 1);
        }
        return ans;
    }

    public static void main(String[] args) {
    }

}
