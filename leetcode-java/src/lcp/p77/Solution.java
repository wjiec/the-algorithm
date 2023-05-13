package lcp.p77;

import java.util.Arrays;

/**
 * LCP 77. 符文储备
 *
 * https://leetcode.cn/problems/W2ZX4X/
 *
 * 远征队在出发前需要携带一些「符文」，作为后续的冒险储备。runes[i] 表示第 i 枚符文的魔力值。
 *
 * 他们将从中选取若干符文进行携带，并对这些符文进行重新排列，以确保任意相邻的两块符文之间的魔力值相差不超过 1。
 *
 * 请返回他们能够携带的符文 最大数量。
 */

public class Solution {

    public int runeReserve(int[] runes) {
        Arrays.sort(runes);

        int ans = 1, curr = 1;
        for (int i = 1; i < runes.length; i++) {
            if (runes[i] - runes[i - 1] <= 1) {
                ans = Math.max(ans, ++curr);
            } else curr = 1;
        }
        return ans;
    }

}
