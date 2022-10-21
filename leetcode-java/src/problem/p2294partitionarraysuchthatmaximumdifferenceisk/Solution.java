package problem.p2294partitionarraysuchthatmaximumdifferenceisk;

import java.util.Arrays;

/**
 * 2294. Partition Array Such That Maximum Difference Is K
 *
 * https://leetcode.cn/problems/partition-array-such-that-maximum-difference-is-k/
 *
 * You are given an integer array nums and an integer k. You may partition nums into one or more
 * subsequences such that each element in nums appears in exactly one of the subsequences.
 *
 * Return the minimum number of subsequences needed such that the difference between the maximum
 * and minimum values in each subsequence is at most k.
 *
 * A subsequence is a sequence that can be derived from another sequence by deleting some or no
 * elements without changing the order of the remaining elements.
 */

public class Solution {

    public int partitionArray(int[] nums, int k) {
        Arrays.sort(nums);

        int ans = 1, n = nums.length;
        for (int l = 0, r = 0; r < n; r++) {
            if (nums[r] - nums[l] > k) {
                ans++; l = r;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().partitionArray(new int[]{6,5,4,3,2,1}, 100) == 1;
        assert new Solution().partitionArray(new int[]{3,6,1,2,5}, 2) == 2;
        assert new Solution().partitionArray(new int[]{1,2,3}, 1) == 2;
        assert new Solution().partitionArray(new int[]{2,2,4,5}, 0) == 3;
    }

}
