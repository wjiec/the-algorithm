package problem.p291wordpatternii;

import java.util.HashMap;
import java.util.Map;

/**
 * 291. Word Pattern II
 *
 * https://leetcode.cn/problems/word-pattern-ii/
 *
 * Given a pattern and a string s, return true if s matches the pattern.
 *
 * A string s matches a pattern if there is some bijective mapping of single
 * characters to strings such that if each character in pattern is replaced
 * by the string it maps to, then the resulting string is s. A bijective mapping
 * means that no two characters map to the same string, and no character maps
 * to two different strings.
 */

public class Solution {

    private final Map<Character, String> map = new HashMap<>();

    public boolean wordPatternMatch(String pattern, String s) {
        return dfs(pattern.toCharArray(), 0, s.toCharArray(), 0);
    }

    private boolean dfs(char[] pattern, int idx, char[] text, int start) {
        if (idx == pattern.length || start == text.length) {
            return idx == pattern.length && start == text.length;
        }

        if (map.containsKey(pattern[idx])) {
            String target = map.get(pattern[idx]);
            for (int i = start, j = 0; j < target.length() && i < text.length; i++, j++) {
                if (target.charAt(j) != text[i]) return false;
            }
            return dfs(pattern, idx + 1, text, start + target.length());
        }

        StringBuilder sb = new StringBuilder();
        for (int i = start; i < text.length; i++) {
            sb.append(text[i]);
            if (map.containsValue(sb.toString())) continue;

            map.put(pattern[idx], sb.toString());
            if (dfs(pattern, idx + 1, text, i + 1)) {
                return true;
            }
            map.remove(pattern[idx]);
        }
        return false;
    }

    public static void main(String[] args) {
        assert new Solution().wordPatternMatch("abab", "redblueredblue");
        assert new Solution().wordPatternMatch("aaaa", "asdasdasdasd");
        assert !new Solution().wordPatternMatch("aabb", "xyzabcxzyabc");
    }

}
