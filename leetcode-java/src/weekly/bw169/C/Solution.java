package weekly.bw169.C;

/**
 * Q3. Longest Non-Decreasing Subarray After Replacing at Most One Element
 *
 * https://leetcode.cn/contest/biweekly-contest-169/problems/longest-non-decreasing-subarray-after-replacing-at-most-one-element/
 *
 * You are given an integer array nums.
 *
 * You are allowed to replace at most one element in the array with any other integer value of your choice.
 *
 * Return the length of the longest non-decreasing subarray that can be obtained after performing at most one replacement.
 *
 * An array is said to be non-decreasing if each element is greater than or equal to its previous one (if it exists).
 */

public class Solution {

    public int longestSubarray(int[] nums) {
        int ans = 1;
        // 只要是非递减就可以满足要求, 那么就可以修改任意一个元素, 使得这个元素与前一个元素保持一致即可
        //  - 也就是找到原始数组中的非递减最长子数组, 然后将其长度加 1 即可
        //  - 如果整个原始数组已经满足非递减的话, 那么答案就是 len(nums)
        //  - 我们可以选择修改这个数组或者不修改这个数字
        //
        // 一个元素要么与前一个元素拼接形成子数组, 要么自身成为一个子数组
        int[] keep = new int[nums.length]; keep[0] = 1;
        // dp[i][j] 表示在 i 位置上对应未产生修改(j = 0)以及产生修改(j = 1)的最长非递减子数组的长度是多少
        int[][] dp = new int[nums.length][2];
        // 初始条件, 在只有一个字符的情况下, 不管修不修改都是满足条件的
        dp[0][0] = dp[0][1] = 1;
        for (int i = 1; i < nums.length; i++) {
            // 不进行任何修改操作, 要么与前一个元素拼接要么自己单独成为一个子数组
            keep[i] = (nums[i] >= nums[i - 1] ? keep[i - 1] : 0) + 1;

            // 否则我们可以考虑进行一次修改操作
            //  - 要么就是修改自身(j = 1), 使得自身与前一个数字相同
            dp[i][1] = keep[i - 1] + 1;
            //  - 要么之前就已经修改了, 然后我们从之前修改的位置(必须小于等于当前元素)进行转移
            //      - 如果当前位置大于前一个位置, 那么就可以从前一个的 dp[i - 1][0] 进行转移
            if (nums[i] >= nums[i - 1]) dp[i][0] = Math.max(dp[i][0], dp[i - 1][0] + 1);
            //      - 否则就是从前一个刚刚修改的位置进行转移
            //          - 要么前一个是跟再前面一个一样, 也就是当前元素需要大于等于前第 2 个数字
            if (i > 1 && nums[i] >= nums[i - 2]) dp[i][0] = Math.max(dp[i][0], dp[i - 1][1] + 1);
            //          - 要么就是考虑把前一个修改为小于等于当前值的数字, 也就是必定为 2
            dp[i][0] = Math.max(dp[i][0], 2);

            // 计算答案
            ans = Math.max(ans, Math.max(keep[i], Math.max(dp[i][0], dp[i][1])));
        }

        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().longestSubarray(new int[]{7, 4, -10, 2}) == 3;
        assert new Solution().longestSubarray(new int[]{6, -4, -1, -1}) == 4;
        assert new Solution().longestSubarray(new int[]{3, -4, -2}) == 3;
        assert new Solution().longestSubarray(new int[]{4}) == 1;
        assert new Solution().longestSubarray(new int[]{1, 2, 3, 1, 2}) == 4;
        assert new Solution().longestSubarray(new int[]{2, 2, 2, 2, 2}) == 5;
        assert new Solution().longestSubarray(new int[]{1, 5, -10, 5}) == 4;
    }

}
