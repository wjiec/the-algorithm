package weekly.w463.D;

import java.util.Arrays;

import static ability.Ability.Math.pow;

/**
 * Q4. XOR After Range Multiplication Queries II
 *
 * https://leetcode.cn/contest/weekly-contest-463/problems/xor-after-range-multiplication-queries-ii/
 *
 * You are given an integer array nums of length n and a 2D integer
 * array queries of size q, where queries[i] = [li, ri, ki, vi].
 *
 * For each query, you must apply the following operations in order:
 *
 * Set idx = li.
 * While idx <= ri:
 * Update: nums[idx] = (nums[idx] * vi) % (109 + 7).
 * Set idx += ki.
 *
 * Return the bitwise XOR of all elements in nums after processing all queries.
 */

public class Solution {

    public int xorAfterQueries(int[] nums, int[][] queries) {
        final int n = nums.length, MOD = 1_000_000_007;
        // 我们取 B = sqrt(queries.length) 作为阈值
        //  - 如果 v < B, 则我们使用差分数组算法来处理
        //  - 如果 v >= B, 则我们使用暴力算法来处理
        //
        // 差分数组的适用原理, 对于所有相同的 l % k, 实际上表示是同一批元素, 例如:
        //  - 对于 l = 1, k = 2, 取值有: 1, 3, 5, 7, 9, ...
        //  - 对于 l = 2, k = 2, 取值有: 2, 4, 6, 8, 10, ...
        //  - 对于 l = 3, k = 2, 取值有: 3, 5, 7, 9, 11, ...
        //
        // 在只考虑取值的情况下我们可以使用差分数组来计算
        int B = (int) Math.sqrt(queries.length);

        // 我们记录所有的 diff 数组在不同 k 情况下的差分情况
        int[][] diff = new int[B][]; // 懒初始化
        for (var query : queries) {
            int l = query[0], r = query[1], k = query[2]; long v = query[3];
            // 根据 k 与 B 的关系选择使用不同的算法
            if (k < B) {
                // 懒初始化
                if (diff[k] == null) {
                    diff[k] = new int[n + k];
                    Arrays.fill(diff[k], 1);
                }

                // 左端点直接乘以 v
                diff[k][l] = (int) ((diff[k][l] * v) % MOD);
                // 能到达的最远位置为 R = l + n * k <= r, 也就是 (R - l) % k == 0
                //  - 我们现在最大能取的区间是 [l, r], 将 l 位置转移到位置 0 开始计算, 也就是 [0, r - l]
                //  - 此时我们可以取到的 R' % k == 0, 也就是 R' = r - ((r - l) % k)
                //  - 同时我们需要 + k, 保证差分数组正确
                r = r - ((r - l) % k) + k;
                diff[k][r] = (int) ((diff[k][r] * pow(v, MOD - 2, MOD)) % MOD);
            } else {
                // 直接暴力计算
                for (int i = l; i <= r && i < n; i += k) {
                    nums[i] = (int) (((long) nums[i] * v) % MOD);
                }
            }
        }

        // 差分计算
        for (int k = 1; k < B; k++) {
            if (diff[k] == null) continue;

            // 枚举所有的可能起点, % k => [0, k)
            for (int l = 0; l < k; l++) {
                long delta = 1;
                for (int i = l; i < n; i += k) {
                    delta = (delta * diff[k][i]) % MOD;
                    nums[i] = (int) ((nums[i] * delta) % MOD);
                }
            }
        }

        int ans = 0;
        for (var v : nums) ans ^= v;
        return ans;
    }

    public static void main(String[] args) {
    }

}
