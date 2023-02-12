package weekly.w332.A;

/**
 * 6354. Find the Array Concatenation Value
 *
 * https://leetcode.cn/contest/weekly-contest-332/problems/find-the-array-concatenation-value/
 *
 * You are given a 0-indexed integer array nums.
 *
 * The concatenation of two numbers is the number formed by concatenating their numerals.
 *
 * For example, the concatenation of 15, 49 is 1549.
 * The concatenation value of nums is initially equal to 0.
 *
 * Perform this operation until nums becomes empty:
 *
 * If there exists more than one number in nums, pick the first element and last element
 * in nums respectively and add the value of their concatenation to the concatenation
 * value of nums, then delete the first and last element from nums.
 *
 * If one element exists, add its value to the concatenation value of nums, then delete it.
 *
 * Return the concatenation value of the nums.
 */

public class Solution {

    public long findTheArrayConcVal(int[] nums) {
        long ans = 0;
        int l = 0, r = nums.length - 1;
        while (l <= r) {
            if (l == r) {
                ans += nums[l];
            } else {
                long val = Long.parseLong(String.valueOf(nums[l]) + String.valueOf(nums[r]));
                ans += val;
            }

            l++; r--;
        }
        return ans;
    }

    public static void main(String[] args) {
    }

}
