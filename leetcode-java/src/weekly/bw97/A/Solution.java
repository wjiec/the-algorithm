package weekly.bw97.A;

/**
 * 2553. Separate the Digits in an Array
 *
 * https://leetcode.cn/problems/separate-the-digits-in-an-array/
 *
 * Given an array of positive integers nums, return an array answer that
 * consists of the digits of each integer in nums after separating them
 * in the same order they appear in nums.
 *
 * To separate the digits of an integer is to get all the digits it has in the same order.
 *
 * For example, for the integer 10921, the separation of its digits is [1,0,9,2,1].
 */

public class Solution {

    public int[] separateDigits(int[] nums) {
        StringBuilder sb = new StringBuilder();
        for (var num : nums) sb.append(num);

        int idx = 0;
        int[] ans = new int[sb.length()];
        for (var c : sb.toString().toCharArray()) {
            ans[idx++] = c - '0';
        }
        return ans;
    }

    public static void main(String[] args) {
    }

}
