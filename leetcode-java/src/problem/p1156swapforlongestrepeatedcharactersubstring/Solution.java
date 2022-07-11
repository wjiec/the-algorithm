package problem.p1156swapforlongestrepeatedcharactersubstring;

/**
 * 1156. Swap For Longest Repeated Character Substring
 *
 * https://leetcode.cn/problems/swap-for-longest-repeated-character-substring/
 *
 * You are given a string text. You can swap two of the characters in the text.
 *
 * Return the length of the longest substring with repeated characters.
 */

public class Solution {

    public int maxRepOpt1(String text) {
        char[] chars = text.toCharArray();

        int[] map = new int[128];
        for (var c : chars) map[c]++;

        int ans = 0, n = chars.length;
        for (int l = 0, r = 0; l < n; ) {
            char curr = chars[l];
            while (r < n && chars[r] == curr) r++;
            // 此时 r 为第一个不等于 curr 的字符
            if (r < n - 1 && chars[r + 1] == curr) {
                int rr = r + 1;
                while (rr < n && chars[rr] == curr) rr++;
                ans = Math.max(ans, Math.min(map[curr], rr - l));
                l = r; continue;
            }

            int count = r - l;
            if (map[curr] > count) count++;
            ans = Math.max(ans, count);
            l = r;
        }

        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().maxRepOpt1("aabaaabaaaba") == 7;

        assert new Solution().maxRepOpt1("ababa") == 3;
        assert new Solution().maxRepOpt1("aaabaaa") == 6;
        assert new Solution().maxRepOpt1("aaabbaaa") == 4;
        assert new Solution().maxRepOpt1("aaaaa") == 5;
        assert new Solution().maxRepOpt1("abcdef") == 1;
    }

}
