package problem.p416partitionequalsubsetsum;

/**
 * 416. Partition Equal Subset Sum
 *
 * https://leetcode-cn.com/problems/partition-equal-subset-sum/
 *
 * Given a non-empty array nums containing only positive integers, find if the array
 * can be partitioned into two subsets such that the sum of elements in both subsets is equal.
 */

public class Solution {

    public boolean canPartition(int[] nums) {
        int sum = 0, max = 0;
        for (var n : nums) {
            sum += n;
            if (n > max) max = n;
        }

        int half = sum / 2;
        if (sum % 2 != 0 || max > half) return false;
        if (max == half) return true;

        boolean[] dp = new boolean[half + 1]; dp[0] = true;
        for (var n : nums) {
            if (dp[half - n]) return true;
            for (int i = half; i >= n; i--) {
                dp[i] |= dp[i - n];
            }
        }
        return dp[half];
    }

    public static void main(String[] args) {
        assert new Solution().canPartition(new int[]{1,5,11,5});
        assert !new Solution().canPartition(new int[]{1,2,3,5});

        assert new Solution().canPartition(new int[]{1,7,11,5});
        assert !new Solution().canPartition(new int[]{2,7,11,5});
        assert !new Solution().canPartition(new int[]{2,2,3,5});
        assert !new Solution().canPartition(new int[]{1,101});
        assert new Solution().canPartition(new int[]{1,7,7,13});
        assert new Solution().canPartition(new int[]{1,1,1,7,7,13});
    }

}
