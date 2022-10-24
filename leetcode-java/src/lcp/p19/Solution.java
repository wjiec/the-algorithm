package lcp.p19;

/**
 * LCP 19. 秋叶收藏集
 *
 * https://leetcode.cn/problems/UlBDOe/
 *
 * 小扣出去秋游，途中收集了一些红叶和黄叶，他利用这些叶子初步整理了一份秋叶收藏集 leaves，
 * 字符串 leaves 仅包含小写字符 r 和 y， 其中字符 r 表示一片红叶，字符 y 表示一片黄叶。
 * 出于美观整齐的考虑，小扣想要将收藏集中树叶的排列调整成「红、黄、红」三部分。每部分树叶数量可以不相等，
 * 但均需大于等于 1。每次调整操作，小扣可以将一片红叶替换成黄叶或者将一片黄叶替换成红叶。
 *
 * 请问小扣最少需要多少次调整操作才能将秋叶收藏集调整完毕。
 */

public class Solution {

    public int minimumOperations(String leaves) {
        int n = leaves.length();
        char[] chars = leaves.toCharArray();

        int[] r1 = new int[n];
        for (int i = 0; i < n; i++) {
            if (i == 0) r1[i] = chars[i] == 'r' ? 0 : 1;
            else r1[i] = r1[i - 1] + (chars[i] == 'r' ? 0 : 1);
        }

        int[] y2 = new int[n];
        for (int i = 1; i < n; i++) {
            y2[i] = r1[i - 1] + (chars[i] == 'y' ? 0 : 1);
            if (i > 1) y2[i] = Math.min(y2[i], y2[i - 1] + (chars[i] == 'y' ? 0 : 1));
        }

        int[] r3 = new int[n];
        for (int i = n - 1; i >= 0; i--) {
            if (i == n - 1) r3[i] = chars[i] == 'r' ? 0 : 1;
            else r3[i] = r3[i + 1] + (chars[i] == 'r' ? 0 : 1);
        }

        int ans = n;
        for (int i = 1; i < n - 1; i++) {
            ans = Math.min(ans, y2[i] + r3[i + 1]);
        }

        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().minimumOperations("yry") == 3;

        assert new Solution().minimumOperations("rrryyyrryyyrr") == 2;
        assert new Solution().minimumOperations("ryr") == 0;
    }

}
