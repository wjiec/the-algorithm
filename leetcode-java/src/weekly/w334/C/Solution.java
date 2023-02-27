package weekly.w334.C;

import java.util.Arrays;

/**
 * 2576. Find the Maximum Number of Marked Indices
 *
 * https://leetcode.cn/problems/find-the-maximum-number-of-marked-indices/
 *
 * You are given a 0-indexed integer array nums.
 *
 * Initially, all of the indices are unmarked. You are allowed to
 * make this operation any number of times:
 *
 * Pick two different unmarked indices i and j such
 * that 2 * nums[i] <= nums[j], then mark i and j.
 *
 * Return the maximum possible number of marked indices in nums
 * using the above operation any number of times.
 */

public class Solution {

    public int maxNumOfMarkedIndices(int[] nums) {
        Arrays.sort(nums);
        int i = 0, n = nums.length;
        for (int j = (n + 1) / 2; j < n; j++) {
            if (2 * nums[i] <= nums[j]) i++;
        }
        return i * 2;
    }

    public static void main(String[] args) {
        assert new Solution().maxNumOfMarkedIndices(new int[]{
            1,78,27,48,14,86,79,68,77,20,57,21,18,67,5,51,70,85,47,
            56,22,79,41,8,39,81,59,74,14,45,49,15,10,28,16,77,22,65,
            8,36,79,94,44,80,72,8,96,78,39,92,69,55,9,44,26,76,40,77,
            16,69,40,64,12,48,66,7,59,10
        }) == 64;

        assert new Solution().maxNumOfMarkedIndices(new int[]{3,5,2,4}) == 2;
        assert new Solution().maxNumOfMarkedIndices(new int[]{9,2,5,4}) == 4;
        assert new Solution().maxNumOfMarkedIndices(new int[]{7,6,8}) == 0;
    }

}
