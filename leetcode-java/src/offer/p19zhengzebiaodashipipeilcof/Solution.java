package offer.p19zhengzebiaodashipipeilcof;

import java.util.ArrayList;
import java.util.List;

/**
 * 剑指 Offer 19. 正则表达式匹配
 *
 * https://leetcode-cn.com/problems/zheng-ze-biao-da-shi-pi-pei-lcof/
 *
 * 请实现一个函数用来匹配包含'. '和'*'的正则表达式。模式中的字符'.'表示任意一个字符，而'*'表示它前面的字符可以出现任意次（含0次）。
 * 在本题中，匹配是指字符串的所有字符匹配整个模式。例如，字符串"aaa"与模式"a.a"和"ab*ac*a"匹配，但与"aa.a"和"ab*a"均不匹配。
 */

public class Solution {

    public boolean isMatch(String s, String p) {
        int m = s.length(), n = p.length();
        char[] ss = s.toCharArray(), ps = p.toCharArray();

        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;

        for (int i = 0; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (ps[j - 1] == '*') {
                    dp[i][j] = dp[i][j - 2];
                    if (isMatch(ss, ps, i, j - 1)) {
                        dp[i][j] = dp[i][j] || dp[i - 1][j];
                    }
                } else {
                    if (isMatch(ss, ps, i, j)) {
                        dp[i][j] = dp[i - 1][j - 1];
                    }
                }
            }
        }
        return dp[m][n];
    }

    private boolean isMatch(char[] s, char[] p, int i, int j) {
        return i != 0 && (p[j - 1] == '.' || s[i - 1] == p[j - 1]);
    }

    private interface Tokenizer {
        int match(String value);
    }

    private static class StringToken implements Tokenizer {
        private final String value;
        public StringToken(String value) { this.value = value; }
        public int match(String value) { return value.indexOf(this.value) == 0 ? this.value.length() : -1; }
        public String toString() { return "StringToken{value=" + value + "}"; }
    }

    private static class SingleCharacterToken implements Tokenizer {
        public int match(String value) { return 1; }
        public String toString() { return "SingleCharacterToken{}"; }
    }

    private static class AnyCharacterToken implements Tokenizer {
        private final char value;
        public AnyCharacterToken(char c) { this.value = c; }
        public int match(String value) {
            if (this.value != '.') {
                for (int i = 0; i < value.length(); i++) {
                    if (value.charAt(i) != this.value) return i;
                }
            }
            return value.length();
        }
        public String toString() { return "AnyCharacterToken{value=" + value + "}"; }
    }

    private final List<Tokenizer> tokens = new ArrayList<>();

    public boolean isMatch1(String s, String p) {
        int n = p.length(), l = 0;
        for (int r = 0; r < n; r++) {
            char c = p.charAt(r);
            if (c == '.') {
                if (l < r) tokens.add(new StringToken(p.substring(l, r)));
                if (r + 1 == n || p.charAt(r + 1) != '*') {
                    tokens.add(new SingleCharacterToken());
                }
                l = r + 1;
            } else if (c == '*') {
                if (l < r - 1) tokens.add(new StringToken(p.substring(l, r - 1)));
                tokens.add(new AnyCharacterToken(p.charAt(r - 1)));
                l = r + 1;
            }
        }
        if (l < n) tokens.add(new StringToken(p.substring(l, n)));

        return isMatch(s.toCharArray(), 0, s.length(), 0);
    }

    private boolean isMatch(char[] chars, int curr, int size, int token) {
        if (token == tokens.size()) return curr == size;
        if (curr == size) {
            for (; token < tokens.size(); token++) {
                if (!(tokens.get(token) instanceof AnyCharacterToken)) {
                    return false;
                }
            }
            return token == tokens.size();
        }

        Tokenizer tokenizer = tokens.get(token);
        int length = tokenizer.match(new String(chars, curr, size - curr));
        boolean matched = length != -1;
        if (matched && tokenizer instanceof AnyCharacterToken) {
            for (; length >= 0; length--) {
                if (isMatch(chars, curr + length, size, token + 1)) {
                    return true;
                }
            }
            return false;
        }
        return matched && isMatch(chars, curr + length, size, token + 1);
    }

    public static void main(String[] args) {
        assert !new Solution().isMatch("a", "b..");
        assert new Solution().isMatch("a", "ab*");
        assert new Solution().isMatch("aaa", "a*a");
        assert new Solution().isMatch("aaa", "a.a");
        assert !new Solution().isMatch("aa", "a");
        assert new Solution().isMatch("aa", "a*");
        assert new Solution().isMatch("ab", ".*");
        assert new Solution().isMatch("aab", "c*a*b");
        assert !new Solution().isMatch("mississippi", "mis*is*p*.");

        assert !new Solution().isMatch1("a", "b..");
        assert new Solution().isMatch1("a", "ab*");
        assert new Solution().isMatch1("aaa", "a*a");
        assert new Solution().isMatch1("aaa", "a.a");
        assert !new Solution().isMatch1("aa", "a");
        assert new Solution().isMatch1("aa", "a*");
        assert new Solution().isMatch1("ab", ".*");
        assert new Solution().isMatch1("aab", "c*a*b");
        assert !new Solution().isMatch1("mississippi", "mis*is*p*.");
    }

}
