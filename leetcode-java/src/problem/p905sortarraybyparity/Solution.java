package problem.p905sortarraybyparity;

import java.util.Arrays;

/**
 * 905. Sort Array By Parity
 *
 * https://leetcode-cn.com/problems/sort-array-by-parity/
 *
 * Given an array nums of non-negative integers, return an array consisting of
 * all the even elements of nums, followed by all the odd elements of nums.
 *
 * You may return any answer array that satisfies this condition.
 */

public class Solution {

    public int[] sortArrayByParity(int[] nums) {
        int l = 0, r = nums.length - 1;
        while (l < r) {
            while (nums[l] % 2 == 0 && l < r) l++;
            while (nums[r] % 2 == 1 && l < r) r--;

            int t = nums[l]; nums[l] = nums[r]; nums[r] = t;
        }
        return nums;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Solution().sortArrayByParity(new int[]{3, 1, 2, 4})));
        System.out.println(Arrays.toString(new Solution().sortArrayByParity(new int[]{1, 3, 5, 7})));
        System.out.println(Arrays.toString(new Solution().sortArrayByParity(new int[]{2, 4, 6, 8})));
        System.out.println(Arrays.toString(new Solution().sortArrayByParity(new int[]{1, 2, 4, 6, 8})));
        System.out.println(Arrays.toString(new Solution().sortArrayByParity(new int[]{1, 2, 4, 6, 8, 3})));
    }

}
