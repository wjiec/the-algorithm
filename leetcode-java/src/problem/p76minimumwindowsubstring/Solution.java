package problem.p76minimumwindowsubstring;

/**
 * 76. Minimum Window Substring
 *
 * https://leetcode.cn/problems/minimum-window-substring/
 *
 * Given two strings s and t of lengths m and n respectively, return the minimum window
 * substring of s such that every character in t (including duplicates) is included in the window.
 *
 * If there is no such substring, return the empty string "".
 *
 * The testcases will be generated such that the answer is unique.
 */

public class Solution {

    public String minWindow(String s, String t) {
        int[] tc = new int[128];
        for (var c : t.toCharArray()) tc[c]++;

        int[] sc = new int[128];
        char[] chars = s.toCharArray();

        int idx = -1, len = Integer.MAX_VALUE;
        for (int l = 0, r = 0; r < chars.length; r++) {
            sc[chars[r]]++;

            while (l < r && sc[chars[l]] > tc[chars[l]]) {
                sc[chars[l++]]--;
            }

            if (check(sc, tc) && r - l + 1 < len) {
                len = r - l + 1;
                idx = l;
            }
        }

        return idx == -1 ? "" : s.substring(idx, idx + len);
    }

    private boolean check(int[] a, int[] b) {
        for (int i = 0; i < a.length; i++) {
            if (a[i] < b[i]) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        assert new Solution().minWindow("a", "b").equals("");

        assert new Solution().minWindow("ADOBECODEBANC", "ABC").equals("BANC");
        assert new Solution().minWindow("a", "a").equals("a");
        assert new Solution().minWindow("a", "aa").equals("");
    }

}
