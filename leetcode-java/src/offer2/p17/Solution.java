package offer2.p17;

import common.Template;

/**
 * 剑指 Offer II 017. 含有所有字符的最短字符串
 *
 * https://leetcode.cn/problems/M1oyTv
 *
 * 给定两个字符串 s 和 t 。返回 s 中包含 t 的所有字符的最短子字符串。如果 s 中不存在符合条件的子字符串，则返回空字符串 "" 。
 *
 * 如果 s 中存在多个符合条件的子字符串，返回任意一个。
 *
 * 注意： 对于 t 中重复字符，我们寻找的子字符串中该字符数量必须不少于 t 中该字符数量。
 */

public class Solution {

    @Template("子字符串匹配滑动窗口")
    public String minWindow(String s, String t) {
        int sl = s.length(), tl = t.length();
        if (sl < tl) return "";

        int[] map = new int[128];
        for (var c : t.toCharArray()) map[c]++;

        String ans = ""; int rest = tl;
        char[] chars = s.toCharArray();
        for (int l = 0, r = 0; r < sl; r++) {
            if (--map[chars[r]] >= 0) rest--;
            while (l <= r && map[chars[l]] + 1 <= 0) map[chars[l++]]++;
            if (rest == 0 && (ans.isEmpty() || r - l + 1 < ans.length())) {
                ans = s.substring(l, r + 1);
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().minWindow("a", "b").equals("");

        assert new Solution().minWindow("ADOBECODEBANC", "ABC").equals("BANC");
        assert new Solution().minWindow("a", "a").equals("a");
        assert new Solution().minWindow("a", "aa").equals("");
    }

}
