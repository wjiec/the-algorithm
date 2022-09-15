package problem.p1887reductionoperationstomakethearrayelementsequal;

import java.util.Arrays;

/**
 * 1887. Reduction Operations to Make the Array Elements Equal
 *
 * https://leetcode.cn/problems/reduction-operations-to-make-the-array-elements-equal/
 *
 * Given an integer array nums, your goal is to make all elements in nums equal.
 * To complete one operation, follow these steps:
 *
 * Find the largest value in nums. Let its index be i (0-indexed) and its value be largest.
 * If there are multiple elements with the largest value, pick the smallest i.
 * Find the next largest value in nums strictly smaller than largest. Let its value be nextLargest.
 * Reduce nums[i] to nextLargest.
 * Return the number of operations to make all elements in nums equal.
 */

public class Solution {

    public int reductionOperations(int[] nums) {
        Arrays.sort(nums);
        int ans = 0, curr = nums[nums.length - 1], count = 0;
        for (int i = nums.length - 1; i >= 0; i--) {
            if (nums[i] != curr) { ans += count; curr = nums[i]; }
            count++;
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().reductionOperations(new int[]{5,1,3}) == 3;
        assert new Solution().reductionOperations(new int[]{1,1,1}) == 0;
        assert new Solution().reductionOperations(new int[]{1,1,2,2,3}) == 4;
    }

}
