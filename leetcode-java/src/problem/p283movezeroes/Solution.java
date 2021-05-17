package problem.p283movezeroes;

import java.util.Arrays;

/**
 * 283. Move Zeroes
 *
 * https://leetcode-cn.com/problems/move-zeroes/
 *
 * Given an integer array nums, move all 0's to the end of it while
 * maintaining the relative order of the non-zero elements.
 *
 * Note that you must do this in-place without making a copy of the array.
 */

public class Solution {

    public void moveZeroes(int[] nums) {
        int l = 0, r = 0, sz = nums.length;
        while (r < sz) {
            if (nums[r] != 0) {
                nums[l++]= nums[r];
            }
            r++;
        }

        while (l < sz) {
            nums[l++] = 0;
        }
    }

    public static void main(String[] args) {
        int[] nums0 = new int[]{0,1,0,3,12};
        new Solution().moveZeroes(nums0);
        System.out.println(Arrays.toString(nums0));

        int[] nums1 = new int[]{0};
        new Solution().moveZeroes(nums1);
        System.out.println(Arrays.toString(nums1));
    }

}
