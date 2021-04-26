package problem.p26removeduplicatesfromsortedarray;

import java.util.Arrays;

/**
 * 26. Remove Duplicates from Sorted Array
 *
 * https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array/
 *
 * 给你一个有序数组 nums ，请你 原地 删除重复出现的元素，使每个元素 只出现一次 ，返回删除后数组的新长度。
 *
 * 不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。
 */

public class Solution {

    public int removeDuplicates(int[] nums) {
        int rs = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[rs] != nums[i]) {
                nums[++rs] = nums[i];
            }
        }
        return rs + 1;
    }

    public static void main(String[] args) {
        var a0 = new int[]{1,1,2};
        assert new Solution().removeDuplicates(a0) == 2;
        System.out.println(Arrays.toString(a0));

        var a1 = new int[]{0,0,1,1,1,2,2,3,3,4};
        assert new Solution().removeDuplicates(a1) == 5;
        System.out.println(Arrays.toString(a1));
    }

}
