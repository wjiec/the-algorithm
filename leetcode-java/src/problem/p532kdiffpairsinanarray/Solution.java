package problem.p532kdiffpairsinanarray;

import java.util.Arrays;

/**
 * 532. K-diff Pairs in an Array
 *
 * https://leetcode-cn.com/problems/k-diff-pairs-in-an-array/
 *
 * Given an array of integers nums and an integer k, return the number of unique k-diff pairs in the array.
 *
 * A k-diff pair is an integer pair (nums[i], nums[j]), where the following are true:
 *
 * 0 <= i, j < nums.length
 * i != j
 * nums[i] - nums[j] == k
 * Notice that |val| denotes the absolute value of val.
 */

public class Solution {

    public int findPairs(int[] nums, int k) {
        Arrays.sort(nums);
        int l = 0, r = 1, n = nums.length, ans = 0;
        while (l < n && r < n) {
            if (l != r && nums[r] - nums[l] == k) {
                ans++;
                for (int v = nums[l]; l < n && nums[l] == v; ) l++;
                for (int v = nums[r]; r < n && nums[r] == v; ) r++;
                continue;
            }

            if (nums[r] - nums[l] < k) r++;
            else l++;
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().findPairs(new int[]{6,7,3,6,4,6,3,5,6,9}, 4) == 2;
        assert new Solution().findPairs(new int[]{1,1,1,1,1}, 0) == 1;

        assert new Solution().findPairs(new int[]{3,1,4,1,5}, 2) == 2;
        assert new Solution().findPairs(new int[]{1,2,3,4,5}, 1) == 4;
        assert new Solution().findPairs(new int[]{1,3,1,5,4}, 0) == 1;
    }

}
