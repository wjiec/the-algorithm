package weekly.bw153.B;

import java.util.ArrayList;
import java.util.List;

/**
 * 3499. Maximize Active Section with Trade I
 *
 * https://leetcode.cn/contest/biweekly-contest-153/problems/maximize-active-section-with-trade-i/
 *
 * You are given a binary string s of length n, where:
 *
 * '1' represents an active section.
 * '0' represents an inactive section.
 * You can perform at most one trade to maximize the number of active sections in s. In a trade, you:
 *
 * Convert a contiguous block of '1's that is surrounded by '0's to all '0's.
 * Afterward, convert a contiguous block of '0's that is surrounded by '1's to all '1's.
 * Return the maximum number of active sections in s after making the optimal trade.
 *
 * Note: Treat s as if it is augmented with a '1' at both ends, forming t = '1' + s + '1'.
 * The augmented '1's do not contribute to the final count.
 */

public class Solution {

    /** @noinspection DuplicatedCode*/ // 一次操作执行两个步骤:
    //  1. 将一个被 0 包围的连续 1 区块转换为全 0
    //  2. 将一个被 1 包围的连续 0 区块转换为全 1
    public int maxActiveSectionsAfterTrade(String s) {
        s = "1" + s + "1";
        // 将 0111...1110 转换为 0000...0000
        // 将 1000...0001 转换为 1111...1111

        // 将这些 1 和 0 分组, 得到每组的大小, 1 为整数, 0 为负数
        List<Integer> groups = new ArrayList<>();
        for (int i = 0, p = 9, c = 0; i <= s.length(); i++) {
            int curr = i == s.length() ? 1 : (s.charAt(i) - '0');
            if (i == s.length() || curr != p) {
                if (c != 0) groups.add(c * (p == 1 ? 1 : -1));
                p = curr; c = 1;
            } else c++;
        }

        // 现在问题变成要执行以下步骤
        //  1. 将一个被负数包围的正数全转换成负数
        //  2. 将一个被正数包围的负数全转换为正数
        // 求和得到最大的正数和

        // 字符串整体是 P N P N ... N P N P 交替的形式
        //  - 也就是我们可以改 N P N 这一段, 然后变成 N N N
        //  - 然后找到两侧的 P 也就是 P N N N P 最终得到 P P P P P
        // 实际上就是将一个 P 左右两侧的 N 变成 P

        int nc = 0, total = 0;
        for (var v : groups) {
            if (v > 0) total += v;
            else nc++;
        }

        // 如果只有一个负数, 那我们无法进行操作
        if (nc < 2) return total - 2;

        // 否则可以将一个 P 两边的 N 变成 P
        int ans = total;
        for (int i = 1; i < groups.size() - 1; i++) {
            if (groups.get(i) > 0) {
                ans = Math.max(ans, total + -groups.get(i - 1) + -groups.get(i + 1));
            }
        }

        return ans - 2;
    }

    public static void main(String[] args) {
        assert new Solution().maxActiveSectionsAfterTrade("01") == 1;
        assert new Solution().maxActiveSectionsAfterTrade("0100") == 4;
        assert new Solution().maxActiveSectionsAfterTrade("1000100") == 7;
        assert new Solution().maxActiveSectionsAfterTrade("01010") == 4;
    }

}
