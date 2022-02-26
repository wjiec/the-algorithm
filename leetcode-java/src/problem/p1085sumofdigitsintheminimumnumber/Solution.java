package problem.p1085sumofdigitsintheminimumnumber;

import java.util.Arrays;

/**
 * 1085. Sum of Digits in the Minimum Number
 *
 * https://leetcode-cn.com/problems/sum-of-digits-in-the-minimum-number/
 *
 * Given an integer array nums, return 0 if the sum of the digits of
 * the minimum integer in nums is odd, or 1 otherwise.
 */

public class Solution {

    public int sumOfDigits(int[] nums) {
        Arrays.sort(nums);
        return sumOfDigits(nums[0]) % 2 == 0 ? 1 : 0;
    }

    private int sumOfDigits(int num) {
        int sum = 0;
        for (; num != 0; num /= 10) sum += num % 10;
        return sum;
    }

    public static void main(String[] args) {
        assert new Solution().sumOfDigits(new int[]{34,23,1,24,75,33,54,8}) == 0;
        assert new Solution().sumOfDigits(new int[]{99,77,33,66,55}) == 1;
    }

}
