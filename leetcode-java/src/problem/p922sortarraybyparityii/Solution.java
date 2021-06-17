package problem.p922sortarraybyparityii;

import java.util.Arrays;

/**
 * 922. Sort Array By Parity II
 *
 * https://leetcode-cn.com/problems/sort-array-by-parity-ii/
 *
 * Given an array of integers nums, half of the integers in nums are odd, and the other half are even.
 *
 * Sort the array so that whenever nums[i] is odd, i is odd, and whenever nums[i] is even, i is even.
 *
 * Return any answer array that satisfies this condition.
 */

public class Solution {

    public int[] sortArrayByParityII(int[] nums) {
        int l = nums.length;
        for (int i = 0, j = 1; i < l && j < l; i += 2, j += 2) {
            while (i < l && nums[i] % 2 == 0) i += 2;
            while (j < l && nums[j] % 2 == 1) j += 2;

            if (i < l && j < l) {
                int t = nums[i]; nums[i] = nums[j]; nums[j] = t;
            }
        }

        return nums;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Solution().sortArrayByParityII(new int[]{4, 2, 5, 7})));
        System.out.println(Arrays.toString(new Solution().sortArrayByParityII(new int[]{1, 2, 3, 4})));
        System.out.println(Arrays.toString(new Solution().sortArrayByParityII(new int[]{4, 3, 2, 1})));
        System.out.println(Arrays.toString(new Solution().sortArrayByParityII(new int[]{0, 1, 2, 3})));
        System.out.println(Arrays.toString(new Solution().sortArrayByParityII(new int[]{3, 1, 2, 0})));
    }

}
