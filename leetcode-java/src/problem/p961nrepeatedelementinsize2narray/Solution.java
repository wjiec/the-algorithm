package problem.p961nrepeatedelementinsize2narray;

import java.util.Arrays;

/**
 * 961. N-Repeated Element in Size 2N Array
 *
 * https://leetcode-cn.com/problems/n-repeated-element-in-size-2n-array/
 *
 * In a array nums of size 2 * n, there are n + 1 unique elements,
 * and exactly one of these elements is repeated n times.
 *
 * Return the element repeated n times.
 */

public class Solution {

    public int repeatedNTimes(int[] nums) {
        for (int k = 1; k <= 3; k++) {
            for (int i = 0; i < nums.length - k; i++) {
                if (nums[i] == nums[i + k]) return nums[i];
            }
        }
        return 0; // unreached
    }

    public int repeatedNTimes1(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length / 2];
    }

    public static void main(String[] args) {
        assert new Solution().repeatedNTimes(new int[]{1,2,3,3}) == 3;
        assert new Solution().repeatedNTimes(new int[]{2,1,2,5,3,2}) == 2;
        assert new Solution().repeatedNTimes(new int[]{5,1,5,2,5,3,5,4}) == 5;

        assert new Solution().repeatedNTimes1(new int[]{1,2,3,3}) == 3;
        assert new Solution().repeatedNTimes1(new int[]{2,1,2,5,3,2}) == 2;
        assert new Solution().repeatedNTimes1(new int[]{5,1,5,2,5,3,5,4}) == 5;
    }

}
