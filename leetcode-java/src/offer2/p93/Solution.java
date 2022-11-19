package offer2.p93;

import java.util.HashMap;
import java.util.Map;

/**
 * 剑指 Offer II 093. 最长斐波那契数列
 *
 * https://leetcode.cn/problems/Q91FMA/
 *
 * 如果序列 X_1, X_2, ..., X_n 满足下列条件，就说它是 斐波那契式 的：
 *
 * n >= 3
 * 对于所有 i + 2 <= n，都有 X_i + X_{i+1} = X_{i+2}
 * 给定一个严格递增的正整数数组形成序列 arr ，找到 arr 中最长的斐波那契式的子序列的长度。如果一个不存在，返回  0 。
 *
 * （回想一下，子序列是从原序列  arr 中派生出来的，它从 arr 中删掉任意数量的元素（也可以不删），而不改变其余元素的顺序。
 * 例如， [3, 5, 8] 是 [3, 4, 5, 6, 7, 8] 的一个子序列）
 */

public class Solution {

    public int lenLongestFibSubseq(int[] arr) {
        int n = arr.length;
        Map<Integer, Integer> index = new HashMap<>();
        for (int i = 0; i < n; i++) index.put(arr[i], i);

        int ans = 0;
        int[][] dp = new int[n][n];
        for (int c = 0; c < n; c++) {
            for (int b = c - 1; b >= 0 && arr[b] * 2 > arr[c]; b--) {
                int a = index.getOrDefault(arr[c] - arr[b], -1);
                if (a >= 0) dp[b][c] = Math.max(dp[a][b] + 1, 3);
                ans = Math.max(ans, dp[b][c]);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        // 2 4 6 7 13 21 34
        assert new Solution().lenLongestFibSubseq(new int[]{2,4,5,6,7,8,11,13,14,15,21,22,34}) == 5;

        assert new Solution().lenLongestFibSubseq(new int[]{1,2,3,4,5,6,7,8}) == 5;
        assert new Solution().lenLongestFibSubseq(new int[]{1,3,7,11,12,14,18}) == 3;
    }

}
