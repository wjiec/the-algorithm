package problem.p1638countsubstringsthatdifferbyonecharacter;

import java.util.HashMap;
import java.util.Map;

/**
 * 1638. Count Substrings That Differ by One Character
 *
 * https://leetcode.cn/problems/count-substrings-that-differ-by-one-character/
 *
 * Given two strings s and t, find the number of ways you can choose a non-empty substring of s and
 * replace a single character by a different character such that the resulting substring is a substring
 * of t. In other words, find the number of substrings in s that differ from some substring
 * in t by exactly one character.
 *
 * For example, the underlined substrings in "computer" and "computation" only differ by
 * the 'e'/'a', so this is a valid way.
 *
 * Return the number of substrings that satisfy the condition above.
 *
 * A substring is a contiguous sequence of characters within a string.
 */

public class Solution {

    public int countSubstrings(String s, String t) {
        int ans = 0;
        char[] s1 = s.toCharArray(), s2 = t.toCharArray();
        for (int i = 0; i < s1.length; i++) {
            for (int j = i; j < s1.length; j++) {
                ans += count(s1, i, j + 1, s2);
            }
        }
        return ans;
    }

    private final Map<String, Integer> memo = new HashMap<>();

    private int count(char[] s1, int l, int r, char[] s2) {
        String key = new String(s1, l, r - l);
        if (memo.containsKey(key)) return memo.get(key);

        int len = r - l, ans = 0;
        if (len == 1) {
            for (var c : s2) if (c != s1[l]) ans++;
        } else {
            for (int i = 0; i <= s2.length - len; i++) {
                if (oneDiff(s1, l, r, s2, i, i + len)) {
                    ans++;
                }
            }
        }

        memo.put(key, ans);
        return ans;
    }

    private boolean oneDiff(char[] s1, int l, int r, char[] s2, int a, int b) {
        int diff = 0;
        for (; l < r && a < b; l++, a++) {
            if (s1[l] != s2[a]) {
                if (++diff > 1) return false;
            }
        }
        return diff == 1;
    }

    public static void main(String[] args) {
        assert new Solution().countSubstrings("aba", "baba") == 6;
        assert new Solution().countSubstrings("ab", "bb") == 3;
        assert new Solution().countSubstrings("a", "a") == 0;
        assert new Solution().countSubstrings("abe", "bbc") == 10;
    }

}
