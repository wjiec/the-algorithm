package problem.p1297maximumnumberofoccurrencesofasubstring;

import java.util.HashMap;
import java.util.Map;

/**
 * 1297. Maximum Number of Occurrences of a Substring
 *
 * https://leetcode.cn/problems/maximum-number-of-occurrences-of-a-substring/
 *
 * Given a string s, return the maximum number of ocurrences of any substring under the following rules:
 *
 * The number of unique characters in the substring must be less than or equal to maxLetters.
 * The substring size must be between minSize and maxSize inclusive.
 */

public class Solution {

    public int maxFreq(String s, int maxLetters, int minSize, int maxSize) {
        int ans = 0;
        char[] chars = s.toCharArray();
        for (int i = minSize; i <= maxSize; i++) {
            StringBuilder sb = new StringBuilder();
            Map<Character, Integer> map = new HashMap<>();
            Map<String, Integer> count = new HashMap<>();
            for (int l = 0, r = 0; r < chars.length; r++) {
                sb.append(chars[r]);
                map.merge(chars[r], 1, Integer::sum);
                while (map.size() > maxLetters || sb.length() > i) {
                    sb.deleteCharAt(0);
                    map.merge(chars[l++], 1, (old, v) -> old == 1 ? null : old - v);
                }
                if (sb.length() >= minSize) count.merge(sb.toString(), 1, Integer::sum);
            }
            for (var cnt : count.values()) ans = Math.max(ans, cnt);
        }
        return ans;
    }

    @SuppressWarnings("DuplicatedCode")
    private static class Optimization {
        public int maxFreq(String s, int maxLetters, int minSize, int maxSize) {
            int ans = 0;
            char[] chars = s.toCharArray();
            StringBuilder sb = new StringBuilder();
            Map<Character, Integer> map = new HashMap<>();
            Map<String, Integer> count = new HashMap<>();
            for (int l = 0, r = 0; r < chars.length; r++) {
                sb.append(chars[r]);
                map.merge(chars[r], 1, Integer::sum);
                while (map.size() > maxLetters || sb.length() > minSize) {
                    sb.deleteCharAt(0);
                    map.merge(chars[l++], 1, (old, v) -> old == 1 ? null : old - v);
                }
                if (sb.length() >= minSize) count.merge(sb.toString(), 1, Integer::sum);
            }
            for (var cnt : count.values()) ans = Math.max(ans, cnt);
            return ans;
        }
    }

    public static void main(String[] args) {
        assert new Solution().maxFreq("aababcaab", 2, 3, 4) == 2;
        assert new Solution().maxFreq("aaaa", 1, 3, 3) == 2;
        assert new Solution().maxFreq("aabcabcab", 2, 2, 3) == 3;
        assert new Solution().maxFreq("abcde", 2, 3, 3) == 0;

        assert new Optimization().maxFreq("aababcaab", 2, 3, 4) == 2;
        assert new Optimization().maxFreq("aaaa", 1, 3, 3) == 2;
        assert new Optimization().maxFreq("aabcabcab", 2, 2, 3) == 3;
        assert new Optimization().maxFreq("abcde", 2, 3, 3) == 0;
    }

}
