package problem.p1005maximizesumofarrayafterknegations;

import java.util.Arrays;

/**
 * 1005. Maximize Sum Of Array After K Negations
 *
 * https://leetcode-cn.com/problems/maximize-sum-of-array-after-k-negations/
 *
 * Given an array nums of integers, we must modify the array in the following way:
 * we choose an i and replace nums[i] with -nums[i], and we repeat this process k times in total.
 * (We may choose the same index i multiple times.)
 *
 * Return the largest possible sum of the array after modifying it in this way.
 */

public class Solution {

    public int largestSumAfterKNegations(int[] nums, int k) {
        Arrays.sort(nums);

        int sum = 0, idx = 0;
        for (; nums[idx] < 0 && idx < k; idx++) nums[idx] = -nums[idx];
        if ((k - idx) % 2 == 1) {
            if (idx != 0 && nums[idx - 1] < nums[idx]) {
                idx -= 1;
            }

            nums[idx] = -nums[idx];
        }

        for (var n : nums) sum += n;
        return sum;
    }

    public static void main(String[] args) {
        assert new Solution().largestSumAfterKNegations(new int[]{-8,3,-5,-3,-5,-2}, 6) == 22;
        assert new Solution().largestSumAfterKNegations(new int[]{4,2,3}, 1) == 5;
        assert new Solution().largestSumAfterKNegations(new int[]{3,-1,0,2}, 3) == 6;
        assert new Solution().largestSumAfterKNegations(new int[]{3,-1,0,2}, 2) == 6;
        assert new Solution().largestSumAfterKNegations(new int[]{3,-1,1,2}, 2) == 5;
        assert new Solution().largestSumAfterKNegations(new int[]{2,-3,-1,5,-4}, 2) == 13;
    }

}
