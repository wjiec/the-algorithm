package weekly.w468.D;

import java.util.PriorityQueue;

/**
 * Q4. Maximum Total Subarray Value II
 *
 * https://leetcode.cn/contest/weekly-contest-468/problems/maximum-total-subarray-value-ii/
 *
 * You are given an integer array nums of length n and an integer k.
 *
 * You must select exactly k distinct non-empty subarrays nums[l..r] of nums.
 * Subarrays may overlap, but the exact same subarray (same l and r) cannot be chosen more than once.
 *
 * The value of a subarray nums[l..r] is defined as: max(nums[l..r]) - min(nums[l..r]).
 *
 * The total value is the sum of the values of all chosen subarrays.
 *
 * Return the maximum possible total value you can achieve.
 */

public class Solution {

    private static class SparseTable {

        private final int[][] minTable, maxTable;

        public SparseTable(int[] nums) {
            int n = nums.length, logN = 32 - Integer.numberOfLeadingZeros(n);
            minTable = new int[n][logN]; maxTable = new int[n][logN];

            for (int i = 0; i < n; i++) minTable[i][0] = maxTable[i][0] = nums[i];
            // 处理所有转移
            for (int j = 1; j < logN; j++) {
                for (int i = 0; i + (1 << j) <= n; i++) {
                    minTable[i][j] = Math.min(minTable[i][j - 1], minTable[i + (1 << (j - 1))][j - 1]);
                    maxTable[i][j] = Math.max(maxTable[i][j - 1], maxTable[i + (1 << (j - 1))][j - 1]);
                }
            }
        }

        // 求 [l, r) 的极值差
        public int query(int l, int r) {
            if (l >= r) return 0;

            // 分成两段 [l, l + 2 ^ j) [r - 2 ^ j, r)
            int j = 32 - Integer.numberOfLeadingZeros(r - l) - 1;
            return Math.max(maxTable[l][j], maxTable[r - (1 << j)][j]) - Math.min(minTable[l][j], minTable[r - (1 << j)][j]);
        }
    }

    public long maxTotalValue(int[] nums, int k) {
        // 我们如果枚举所有的子数组, 那么如果子数组范围越大, 产生的极值差也就越大
        //  - 也就是对于 [l, r) 这个子数组, l 固定的情况下 r 越大产生的极值差也就越大
        //  - 我们可以枚举所有以 0, 1, ..., n - 1 作为 l, 以 n - 1 作为 r 的子数组
        // 在这些子数组的基础上求最大堆, 经过 k 次处理即可得到选择恰好 k 个最大极值差
        //
        // 对于所有的 [l, r) 求极值差, 我们可以使用 ST 表来实现
        int n = nums.length;
        SparseTable st = new SparseTable(nums);
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> st.query(b[0], b[1]) - st.query(a[0], a[1]));
        for (int i = 0; i < n; i++) pq.add(new int[]{i, n});

        long ans = 0;
        while (k-- > 0) {
            var curr = pq.remove();
            ans += st.query(curr[0], curr[1]--);
            pq.add(curr);
        }

        return ans;
    }

    public static void main(String[] args) {
    }

}
