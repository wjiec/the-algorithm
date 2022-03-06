package problem.p189rotatearray;

import java.util.Arrays;

/**
 * 189. Rotate Array
 *
 * https://leetcode-cn.com/problems/rotate-array/
 *
 * Given an array, rotate the array to the right by k steps, where k is non-negative.
 */

public class Solution {

    public void rotate(int[] nums, int k) {
        k %= nums.length;
        if (k != 0) {
            reverse(nums, 0, nums.length - 1);
            reverse(nums, 0, k - 1);
            reverse(nums, k, nums.length - 1);
        }
    }

    private void reverse(int[] nums, int l, int r) {
        for (; l < r; l++, r--) {
            int t = nums[l];
            nums[l] = nums[r];
            nums[r] = t;
        }
    }

    public static void main(String[] args) {
        int[] l0 = new int[]{1,2,3,4,5,6};
        new Solution().rotate(l0, 4);
        System.out.println(Arrays.toString(l0));

        int[] l1 = new int[]{1,2,3,4,5,6,7};
        for (int i = 0; i < l1.length; i++) {
            new Solution().rotate(l1, 1);
            System.out.println(Arrays.toString(l1));
        }

        int[] l2 = new int[]{-1,-100,3,99};
        new Solution().rotate(l2, 2);
        System.out.println(Arrays.toString(l2));
    }

}
