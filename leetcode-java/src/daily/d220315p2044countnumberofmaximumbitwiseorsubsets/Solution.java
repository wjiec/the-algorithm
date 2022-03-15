package daily.d220315p2044countnumberofmaximumbitwiseorsubsets;

/**
 * 2044. Count Number of Maximum Bitwise-OR Subsets
 *
 * https://leetcode-cn.com/problems/count-number-of-maximum-bitwise-or-subsets/
 *
 * Given an integer array nums, find the maximum possible bitwise OR of a subset of nums and
 * return the number of different non-empty subsets with the maximum bitwise OR.
 *
 * An array a is a subset of an array b if a can be obtained from b by deleting some (possibly zero) elements of b.
 * Two subsets are considered different if the indices of the elements chosen are different.
 *
 * The bitwise OR of an array a is equal to a[0] OR a[1] OR ... OR a[a.length - 1] (0-indexed).
 */

public class Solution {

    private int ans = 0;

    public int countMaxOrSubsets(int[] nums) {
        int max = 0;
        for (var n : nums) max |= n;
        dfs(nums, 0, 0, max);
        return ans;
    }

    private void dfs(int[] nums, int i, int curr, int target) {
        if (i == nums.length) {
            if (curr == target) ans++;
            return;
        }

        dfs(nums, i + 1, curr, target); // skip
        dfs(nums, i + 1, curr | nums[i], target); // select
    }

    public static void main(String[] args) {
        assert new Solution().countMaxOrSubsets(new int[]{3,1}) == 2;
        assert new Solution().countMaxOrSubsets(new int[]{2,2,2}) == 7;
        assert new Solution().countMaxOrSubsets(new int[]{3,2,1,5}) == 6;
    }

}
