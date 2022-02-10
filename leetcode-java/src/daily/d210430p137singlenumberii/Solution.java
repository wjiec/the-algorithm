package daily.d210430p137singlenumberii;

import java.util.Arrays;

/**
 * 137. Single Number II
 *
 * https://leetcode-cn.com/problems/single-number-ii/
 *
 * Given an integer array nums where every element appears three times except for one,
 * which appears exactly once. Find the single element and return it.
 */

public class Solution {

    public int singleNumber(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }

        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            if (i != 0 && nums[i] != nums[i - 1] && i != nums.length - 1 && nums[i] != nums[i + 1]) {
                return nums[i];
            } else if (i == 0 && nums[0] != nums[1]) {
                return nums[0];
            }
        }
        return nums[nums.length - 1];
    }

    public static void main(String[] args) {
        assert new Solution().singleNumber(new int[]{2,2,3,2}) == 3;
        assert new Solution().singleNumber(new int[]{1,2,2,2}) == 1;
        assert new Solution().singleNumber(new int[]{0,1,0,1,0,1,99}) == 99;
        assert new Solution().singleNumber(new int[]{1,2,3,1,2,3,4,1,2,3}) == 4;
    }

}
