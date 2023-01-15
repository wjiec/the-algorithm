package weekly.w328.A;

/**
 * 6291. Difference Between Element Sum and Digit Sum of an Array
 *
 * https://leetcode.cn/problems/difference-between-element-sum-and-digit-sum-of-an-array/
 *
 * You are given a positive integer array nums.
 *
 * The element sum is the sum of all the elements in nums.
 * The digit sum is the sum of all the digits (not necessarily distinct) that appear in nums.
 * Return the absolute difference between the element sum and digit sum of nums.
 *
 * Note that the absolute difference between two integers x and y is defined as |x - y|.
 */

public class Solution {

    public int differenceOfSum(int[] nums) {
        int ans = 0;
        for (var v : nums) {
            ans += v;
            while (v != 0) {
                ans -= v % 10;
                v /= 10;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
    }

}
