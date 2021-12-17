package offer.p57heweisdelianggeshuzilcof;

import java.util.Arrays;

/**
 * 剑指 Offer 57. 和为s的两个数字
 *
 * https://leetcode-cn.com/problems/he-wei-sde-liang-ge-shu-zi-lcof/
 *
 * 输入一个递增排序的数组和一个数字s，在数组中查找两个数，使得它们的和正好是s。如果有多对数字的和等于s，则输出任意一对即可。
 */

public class Solution {

    public int[] twoSum(int[] nums, int target) {
        int l = 0, r = nums.length - 1;
        while (l < r) {
            if (nums[l] + nums[r] > target) r--;
            else if (nums[l] + nums[r] < target) l++;
            else return new int[]{nums[l], nums[r]};
        }
        return new int[]{0, 0};
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Solution().twoSum(new int[]{2, 7, 11, 15}, 9)));
        System.out.println(Arrays.toString(new Solution().twoSum(new int[]{10,26,30,31,47,60}, 40)));
    }

}
