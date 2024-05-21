package weekly.w398.C;

/**
 * 3153. Sum of Digit Differences of All Pairs
 *
 * https://leetcode.cn/contest/weekly-contest-398/problems/sum-of-digit-differences-of-all-pairs/
 *
 * You are given an array nums consisting of positive integers where
 * all integers have the same number of digits.
 *
 * The digit difference between two integers is the count of different digits
 * that are in the same position in the two integers.
 *
 * Return the sum of the digit differences between all pairs of integers in nums.
 */

public class Solution {

    public long sumDigitDifferences(int[] nums) {
        long ans = 0;
        int n = String.valueOf(nums[0]).length();
        int[][] digits = new int[n][10];
        for (int i = 0; i < nums.length; i++) {
            int v = nums[i];
            for (int j = 0; v != 0; v /= 10, j++) {
                ans += i - digits[j][v % 10]++;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().sumDigitDifferences(new int[]{13,23,12}) == 4;
        assert new Solution().sumDigitDifferences(new int[]{10,10,10}) == 0;
    }

}
