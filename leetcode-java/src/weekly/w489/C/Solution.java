package weekly.w489.C;

import ability.Benchmark;

/**
 * Q3. Longest Almost-Palindromic Substring
 *
 * https://leetcode.cn/contest/weekly-contest-489/problems/longest-almost-palindromic-substring/
 *
 * You are given a string s consisting of lowercase English letters.
 *
 * A substring is almost-palindromic if it becomes a palindrome after removing exactly one character from it.
 *
 * Return an integer denoting the length of the longest almost-palindromic substring in s.
 */

public class Solution {

    public int almostPalindromic(String s) {
        // 长度只有 2500, n^2 = 6.25e5 次
        //  - 必须删除一个字符后变为回文串, 且不改变顺序
        int n = s.length(), ans = 0;
        char[] chars = s.toCharArray();
        for (int l = 0; l < n - ans; l++) {
            int contains = 1 << (chars[l] - 'a');
            int mask = 1 << (chars[l] - 'a');
            for (int r = l + 1; r < n; r++) {
                mask ^= 1 << (chars[r] - 'a');
                contains |= 1 << (chars[r] - 'a');

                // 必须删除一个字符后变为回文串
                int m = r - l + 1, w = Integer.bitCount(mask);
                // 如果原始长度是奇数, 那么删除一个字符之后长度是偶数, 也就是 mask 的 w == 1
                if ((m & 1) == 1 && w == 1) {
                    // 此时我们需要删掉多余的一个字符, 检查是否能满足
                    char deleted = (char) ('a' + Integer.numberOfTrailingZeros(mask));
                    if (check(chars, l, r, deleted)) ans = Math.max(ans, m);
                }
                // 如果原始长度是偶数, 那么删除一个字符之后长度是奇数, 也就是
                if ((m & 1) == 0) {
                    //  - mask 的 w == 0: 删除一个字符之后 w -> 1
                    int target = 0;
                    if (w == 0) target = contains;
                    //  - mask 的 w == 2: 删除一个字符之后 w -> 1
                    if (w == 2) target = mask;
                    for (var v = target; v != 0; v -= v & -v) {
                        char deleted = (char) ('a' + Integer.numberOfTrailingZeros(v & -v));
                        if (check(chars, l, r, deleted)) {
                            ans = Math.max(ans, m); break;
                        }
                    }
                }
            }
        }
        return ans;
    }

    private boolean check(char[] chars, int l, int r, char deleted) {
        if (r - l + 1 == 2) return true;
        while (l < r) {
            if (chars[l] != chars[r]) {
                if (chars[l] == deleted) { deleted = ' '; l++; continue; }
                else if (chars[r] == deleted) { deleted = ' '; r--; continue; }
                else break;
            }
            l++; r--;
        }
        return l >= r;
    }

    private static class Optimization {
        public int almostPalindromic(String s) {
            // 枚举中间位置检查是否是回文串(可删除一个字母)
            int n = s.length(), ans = 0;
            char[] chars = s.toCharArray();
            for (int i = 0; i < 2 * n - 1; i++) {
                int l = i / 2, r = (i + 1) / 2;
                // 先扩展, 碰到不同的地方再进行移除
                while (l >= 0 && r < n && chars[l] == chars[r]) { l--; r++; }
                // 删除 l 或者 r 继续扩展
                ans = Math.max(ans, extend(chars, l - 1, r));
                ans = Math.max(ans, extend(chars, l, r + 1));
            }
            return Math.min(ans, n);
        }

        private int extend(char[] chars, int l, int r) {
            while (l >= 0 && r < chars.length && chars[l] == chars[r]) { l--; r++; }
            // 此时 (l, r) 是一个回文串, 长度为 r - l - 1
            return r - l - 1;
        }
    }

    public static void main(String[] args) {
        Benchmark.benchmark("Optimization", () -> {
            assert new Optimization().almostPalindromic("abca") == 4;
            assert new Optimization().almostPalindromic("abba") == 4;
            assert new Optimization().almostPalindromic("zzabba") == 5;
        });

        assert new Solution().almostPalindromic("abca") == 4;
        assert new Solution().almostPalindromic("abba") == 4;
        assert new Solution().almostPalindromic("zzabba") == 5;
    }

}
