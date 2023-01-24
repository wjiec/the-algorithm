package lcci.s8.p13pileboxlcci;

import java.util.Arrays;

/**
 * 面试题 08.13. 堆箱子
 *
 * https://leetcode.cn/problems/pile-box-lcci/
 *
 * 堆箱子。给你一堆n个箱子，箱子宽 wi、深 di、高 hi。
 * 箱子不能翻转，将箱子堆起来时，下面箱子的宽度、高度和深度必须大于上面的箱子。
 * 实现一种方法，搭出最高的一堆箱子。箱堆的高度为每个箱子高度的总和。
 *
 * 输入使用数组[wi, di, hi]表示每个箱子。
 */

public class Solution {

    // [width, depth, height]
    public int pileBox(int[][] box) {
        Arrays.sort(box, (a, b) -> {
            if (a[0] != b[0]) return a[0] - b[0];
            if (a[1] != b[1]) return a[1] - b[1];
            return a[2] - b[2];
        });

        int ans = 0;
        // dp[i] 表示使用第 i 的箱子的最大高度
        int[] dp = new int[box.length];
        for (int i = 0; i < box.length; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (box[i][0] > box[j][0] && box[i][1] > box[j][1] && box[i][2] > box[j][2]) {
                    dp[i] = Math.max(dp[i], dp[j]);
                }
            }
            ans = Math.max(ans, dp[i] += box[i][2]);
        }

        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().pileBox(new int[][]{{1, 1, 1}, {2, 2, 2}, {3, 3, 3}}) == 6;
        assert new Solution().pileBox(new int[][]{{1, 1, 1}, {2, 3, 4}, {2, 6, 7}, {3, 4, 5}}) == 10;
    }

}
