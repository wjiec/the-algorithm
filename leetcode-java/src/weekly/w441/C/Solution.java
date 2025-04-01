package weekly.w441.C;

/**
 * 3489. Zero Array Transformation IV
 *
 * https://leetcode.cn/contest/weekly-contest-441/problems/zero-array-transformation-iv/
 *
 * You are given an integer array nums of length n and a 2D array queries, where queries[i] = [li, ri, vali].
 *
 * Each queries[i] represents the following action on nums:
 *
 * Select a subset of indices in the range [li, ri] from nums.
 * Decrement the value at each selected index by exactly vali.
 * A Zero Array is an array with all its elements equal to 0.
 *
 * Return the minimum possible non-negative value of k, such that after processing the
 * first k queries in sequence, nums becomes a Zero Array. If no such k exists, return -1.
 */

/** @noinspection DuplicatedCode*/
public class Solution {

    // 按顺序处理 queries, 每次可以在 [l, r] 中选择一些元素将其减少 v
    public int minZeroArray(int[] nums, int[][] queries) {
        // 对于所有包含位置 i 的位置, 需要恰好 k 个处理使得 v_1 + v_2 + ... + v_k = nums[i]
        //  - 对于每个位置, 找到最小的 k 使得恰好所有元素都能满足以上条件
        //  - 最终的答案就是以上找到的最小的 k 的最大值

        int ans = 0;
        // 枚举每个位置所需的进行的处理
        for (int i = 0; i < nums.length; i++) {
            int v = nums[i];
            if (v == 0) continue;

            // 检查最少需要多少个 query 可以恰好将 nums[i] 变为 0
            //  - 也就是完全背包问题

            // dp[i + 1][j] 表示使用 [0, i) 个 query 使得相加的和为 j 的最小个数是多少
            boolean[] dp = new boolean[v + 1]; dp[0] = true; // 表示是否能得到
            for (int j = 0; j < queries.length; j++) {
                int l = queries[j][0], r = queries[j][1], x = queries[j][2];
                if (i < l || i > r) continue;

                // 枚举从哪个数开始叠加上当前数
                for (int k = v; k >= x; k--) dp[k] = dp[k] || dp[k - x];

                // 检查是否可以实现形成数字 v, 如果是就直接退出
                if (dp[v]) { ans = Math.max(ans, j + 1); break; }
            }
            if (!dp[v]) return -1;
        }

        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().minZeroArray(new int[]{2,0,2}, new int[][]{{0,2,1},{0,2,1},{1,1,3}}) == 2;
        assert new Solution().minZeroArray(new int[]{4,3,2,1}, new int[][]{{1,3,2},{0,2,1}}) == -1;
        assert new Solution().minZeroArray(new int[]{1,2,3,2,1}, new int[][]{{0,1,1},{1,2,1},{2,3,2},{3,4,1},{4,4,1}}) == 4;
        assert new Solution().minZeroArray(new int[]{1,2,3,2,6}, new int[][]{{0,1,1},{0,2,1},{1,4,2},{4,4,4},{3,4,1},{4,4,5}}) == 4;
    }

}
