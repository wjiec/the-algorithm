package weekly.w418.A;

/**
 * 3309. Maximum Possible Number by Binary Concatenation
 *
 * https://leetcode.cn/contest/weekly-contest-418/problems/maximum-possible-number-by-binary-concatenation/
 *
 * You are given an array of integers nums of size 3.
 *
 * Return the maximum possible number whose binary representation can be formed by concatenating
 * the binary representation of all elements in nums in some order.
 *
 * Note that the binary representation of any number does not contain leading zeros.
 */

public class Solution {

    public int maxGoodNumber(int[] nums) {
        String[] bins = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            bins[i] = Integer.toBinaryString(nums[i]);
        }

        int ans = 0;
        ans = Math.max(ans, Integer.parseInt(bins[0] + bins[1] + bins[2], 2));
        ans = Math.max(ans, Integer.parseInt(bins[0] + bins[2] + bins[1], 2));
        ans = Math.max(ans, Integer.parseInt(bins[1] + bins[0] + bins[2], 2));
        ans = Math.max(ans, Integer.parseInt(bins[1] + bins[2] + bins[0], 2));
        ans = Math.max(ans, Integer.parseInt(bins[2] + bins[0] + bins[1], 2));
        ans = Math.max(ans, Integer.parseInt(bins[2] + bins[1] + bins[0], 2));

        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().maxGoodNumber(new int[]{1, 2, 3}) == 30;
    }

}
