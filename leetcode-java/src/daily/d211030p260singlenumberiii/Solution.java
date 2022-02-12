package daily.d211030p260singlenumberiii;

import java.util.Arrays;

/**
 * 260. Single Number III
 *
 * https://leetcode-cn.com/problems/single-number-iii/
 *
 * Given an integer array nums, in which exactly two elements appear only once and
 * all the other elements appear exactly twice.
 *
 * Find the two elements that appear only once. You can return the answer in any order.
 *
 * You must write an algorithm that runs in linear runtime complexity and uses only constant extra space.
 */

public class Solution {

    public int[] singleNumber(int[] nums) {
        int xor = 0;
        for (var n : nums) xor ^= n;

        int lsb = xor == Integer.MIN_VALUE ? xor : (xor & -xor), v1 = 0, v2 = 0;
        for (var n : nums) {
            if ((n & lsb) != 0) v1 ^= n;
            else v2 ^= n;
        }
        return new int[]{v1, v2};
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Solution().singleNumber(new int[]{1, 2, 1, 3, 2, 5})));
        System.out.println(Arrays.toString(new Solution().singleNumber(new int[]{-1, 0})));
        System.out.println(Arrays.toString(new Solution().singleNumber(new int[]{0, 1})));
    }

}
