package weekly.bw139.C;

/**
 * 3287. Find the Maximum Sequence Value of Array
 *
 * https://leetcode.cn/problems/find-the-maximum-sequence-value-of-array
 *
 * You are given an integer array nums and a positive integer k.
 *
 * The value of a sequence seq of size 2 * x is defined as:
 *
 * (seq[0] OR seq[1] OR ... OR seq[x - 1]) XOR (seq[x] OR seq[x + 1] OR ... OR seq[2 * x - 1]).
 *
 * Return the maximum value of any subsequence of nums having size 2 * k.
 */

public class Solution {

    public int maxValue(int[] nums, int k) {
        final int n = nums.length, INF = 1 << 7;
        // 枚举中间分割点, 例如：[0, i), [i, len), 枚举两边所有可能的值, 逐一进行 XOR 取最大值
        // 对于左右两边所有可能值的计算, 使用 0-1 背包思想，用 DP 解决

        // pre[a][b][c] 表示从 [0, a] 中取 b 个数是否能使用 OR 得到 c
        boolean[][][] pre = new boolean[n + 1][k + 1][INF];
        pre[0][0][0] = true;

        // 0-1 背包计算所有可能的值
        for (int i = 1; i <= nums.length; i++) {
            int curr = nums[i - 1];

            // 枚举所有可能的数字的数量
            for (int j = 0; j <= k; j++) {
                // 枚举从哪个值开始进行转移
                for (int v = 0; v < INF; v++) {
                    pre[i][j][v] = pre[i][j][v] || pre[i - 1][j][v];
                    if (j + 1 <= k && pre[i - 1][j][v]) pre[i][j + 1][curr | v] = true;
                }
            }
        }

        // suf[a][b][c] 表示从 [a, r) 中取 b 个数是否能使用 OR 得到 c
        boolean[][][] suf = new boolean[n + 1][k + 1][INF];
        suf[n][0][0] = true;

        int ans = 0;
        // 从后往前计算所有的可能
        for (int i = n - 1; i >= 0; i--) {
            int curr = nums[i];
            // 枚举所有可能的数字的数量
            for (int j = 0; j <= k; j++) {
                // 枚举从哪个值开始进行转移
                for (int v = 0; v < INF; v++) {
                    suf[i][j][v] = suf[i][j][v] || suf[i + 1][j][v];
                    if (j + 1 <= k && suf[i + 1][j][v]) suf[i][j + 1][curr | v] = true;
                }
            }

            // 枚举所有可能的配对
            for (int a = 0; a < INF; a++) {
                for (int b = 0; b < INF; b++) {
                    if (pre[i][k][a] && suf[i][k][b]) {
                        ans = Math.max(ans, a ^ b);
                    }
                }
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().maxValue(new int[]{2,6,7}, 1) == 5;
        assert new Solution().maxValue(new int[]{4,2,5,6,7}, 2) == 2;
    }

}
