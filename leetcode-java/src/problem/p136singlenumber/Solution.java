package problem.p136singlenumber;

import java.util.Arrays;

/**
 * 136. Single Number
 *
 * https://leetcode-cn.com/problems/single-number/
 *
 * Given a non-empty array of integers nums, every element appears twice except for one. Find that single one.
 *
 * Follow up: Could you implement a solution with a linear runtime complexity and without using extra memory?
 */

public class Solution {

    public int singleNumber(int[] nums) {
        int sz = nums.length;
        if (sz == 1) {
            return nums[0];
        }

        Arrays.sort(nums);
        if (nums[0] != nums[1]) {
            return nums[0];
        }

        for (int i = 2; i < sz; i++) {
            if (nums[i] != nums[i - 1] && (i == sz - 1 || nums[i] != nums[i + 1])) {
                return nums[i];
            }
        }
        return -1; // unreachable
    }

    // a ^ a = 0
    // a ^ 0 = a
    public int xor(int[] nums) {
        int rs = 0;
        for (var n : nums) {
            rs ^= n;
        }
        return rs;
    }

    public static void main(String[] args) {
        assert new Solution().singleNumber(new int[]{1}) == 1;
        assert new Solution().singleNumber(new int[]{2,2,1}) == 1;
        assert new Solution().singleNumber(new int[]{4,1,2,1,2}) == 4;
        assert new Solution().singleNumber(new int[]{4,1,2,1,2,4,3}) == 3;

        assert new Solution().xor(new int[]{1}) == 1;
        assert new Solution().xor(new int[]{2,2,1}) == 1;
        assert new Solution().xor(new int[]{4,1,2,1,2}) == 4;
        assert new Solution().xor(new int[]{4,1,2,1,2,4,3}) == 3;
    }

}
