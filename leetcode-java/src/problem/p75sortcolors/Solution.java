package problem.p75sortcolors;

import common.Checker;

/**
 * 75. Sort Colors
 *
 * https://leetcode-cn.com/problems/sort-colors/
 *
 * Given an array nums with n objects colored red, white, or blue, sort them in-place so that objects of the
 * same color are adjacent, with the colors in the order red, white, and blue.
 *
 * We will use the integers 0, 1, and 2 to represent the color red, white, and blue, respectively.
 *
 * You must solve this problem without using the library's sort function.
 */

public class Solution {

    public void sortColors(int[] nums) {
        int l = 0, r = nums.length - 1;
        for (int i = 0; i <= r; i++) {
            while (i <= r && nums[i] == 2) {
                int temp = nums[i];
                nums[i] = nums[r];
                nums[r--] = temp;
            }

            if (nums[i] == 0) {
                int temp = nums[i];
                nums[i] = nums[l];
                nums[l++] = temp;
            }
        }
    }

    public static void main(String[] args) {
        int[] l1 = new int[]{2,0,2,1,1,0};
        new Solution().sortColors(l1);
        assert Checker.check(l1, new int[]{0,0,1,1,2,2});

        int[] l2 = new int[]{2,0,1};
        new Solution().sortColors(l2);
        assert Checker.check(l2, new int[]{0,1,2});
    }

}
