package problem.p1316distinctechosubstrings;

import ability.Benchmark;

import java.util.HashSet;
import java.util.Set;

/**
 * 1316. Distinct Echo Substrings
 *
 * https://leetcode.cn/problems/distinct-echo-substrings
 *
 * Return the number of distinct non-empty substrings of text that can be written as the
 * concatenation of some string with itself (i.e. it can be written as a + a where a is some string).
 */

public class Solution {

    // 找到不同的非空子字符串，这个非空子字符串是 a + a 的形式
    //  1. 如果快速判断字串是 a + a 模式
    public int distinctEchoSubstrings(String text) {
        char[] chars = text.toCharArray();
        int[][] counter = new int[chars.length + 1][26];
        for (int i = 1; i <= chars.length; i++) {
            System.arraycopy(counter[i - 1], 0, counter[i], 0, 26);
            counter[i][chars[i - 1] - 'a']++;
        }

        Set<String> ans = new HashSet<>();
        for (int i = 0; i < chars.length; i++) {
            for (int j = i + 1; j < chars.length; j += 2) {
                if (isEchoString(chars, i, j + 1, counter[i], counter[j + 1])) {
                    ans.add(new String(chars, i, j - i + 1));
                }
            }
        }

        return ans.size();
    }

    private boolean isEchoString(char[] chars, int l, int r, int[] cl, int[] cr) {
        for (int i = 0; i < cl.length; i++) {
            if ((cr[i] - cl[i]) % 2 != 0) return false;
        }

        for (int i = l, j = l + (r - l) / 2; j < r; i++, j++) {
            if (chars[i] != chars[j]) return false;
        }

        return true;
    }

    private static class SlideWindow {
        public int distinctEchoSubstrings(String text) {
            int n = text.length();
            char[] chars = text.toCharArray();

            Set<String> ans = new HashSet<>();
            // 枚举窗口的大小
            for (int len = 2; len <= n; len += 2) {
                int fails = 0;
                int[] counter = new int[128];
                for (int l = 0, r = 0; r < n; r++) {
                    if (++counter[chars[r]] % 2 == 0) fails--;
                    else fails++;

                    if (r - l + 1 > len) {
                        if (--counter[chars[l++]] % 2 == 0) fails--;
                        else fails++;
                    }

                    if (fails == 0) {
                        boolean ok = true;
                        for (int i = l, j = i + len / 2; j <= r; i++, j++) {
                            if (chars[i] != chars[j]) {
                                ok = false;
                                break;
                            }
                        }

                        if (ok) ans.add(new String(chars, l, len));
                    }
                }
            }

            return ans.size();
        }
    }

    private static class BruteForce {
        public int distinctEchoSubstrings(String text) {
            int n = text.length();
            Set<String> ans = new HashSet<>();
            for (int i = 0; i < n; i++) {
                for (int j = i + 2; j <= n; j += 2) {
                    int len = (j - i) / 2;
                    if (text.substring(i, i + len).equals(text.substring(i + len, j))) {
                        ans.add(text.substring(i, j));
                    }
                }
            }
            return ans.size();
        }
    }

    private static class RollingHash {

        private static final int MOD = 1_000_000_007;

        public int distinctEchoSubstrings(String text) {
            int n = text.length(), base = 31;
            long[] pre = new long[n + 1];
            long[] mul = new long[n + 1]; mul[0] = 1;
            for (int i = 1; i <= n; i++) {
                pre[i] = (pre[i - 1] * base + text.charAt(i - 1)) % MOD;
                mul[i] = (mul[i - 1] * base) % MOD;
            }

            Set<Integer> ans = new HashSet<>();
            for (int i = 0; i < n; i++) {
                for (int j = i + 1; j < n; j++) {
                    int l = j - i;
                    if (j + l > n) break;
                    // 第一段为 [i, j), 第二段为 [j, j + l)

                    if (hash(pre, mul, i, j) == hash(pre, mul, j, j + l)) {
                        ans.add(hash(pre, mul, i, j + l));
                    }
                }
            }

            return ans.size();
        }

        // 计算得到 [l, r) 之间的哈希值
        //  我们使用如下公式计算前缀和
        //      pre[i] = pre[i - 1] * base + text[i]
        //  如果需要求 pre[i:j] 展开式子得
        //      pre[i] = text[0] * k ^ (i - 1) + text[1] * k ^ (i - 2) + ... + text[i - 1]
        //      pre[j] = text[0] * k ^ (j - 1) + text[1] * k ^ (j - 2) + ... + text[j - 1]
        //  将 k ^ (j - i) * pre[i] 得到
        //      k ^ (j - i) * pre[i] = text[0] * k ^ (i - 1 + j - i) + ... + text[i - 1] * k ^ (j - i)
        //                           = text[0] * k ^ (j - 1) + text[1] * k ^ (j - 2) + ... + text[i - 1] * k ^ (j - i)
        //  将两者相减得到
        //      pre[j] - k ^ (j - i) * pre[i] = text[i - 1] * k ^ (j - i - 1) + ... + text[j - 1]
        private int hash(long[] pre, long[] mul, int l, int r) {
            return (int) (((pre[r] - (mul[r - l] * pre[l]) % MOD) + MOD) % MOD);
        }
    }

    public static void main(String[] args) {
        Benchmark.benchmark("RollingHash", () -> {
            assert new RollingHash().distinctEchoSubstrings("abcabcabc") == 3;
            assert new RollingHash().distinctEchoSubstrings("leetcodeleetcode") == 2;
            assert new RollingHash().distinctEchoSubstrings("a".repeat(2000)) == 1000;
        });

        Benchmark.benchmark("SlideWindow", () -> {
            assert new SlideWindow().distinctEchoSubstrings("abcabcabc") == 3;
            assert new SlideWindow().distinctEchoSubstrings("leetcodeleetcode") == 2;
            assert new SlideWindow().distinctEchoSubstrings("a".repeat(2000)) == 1000;
        });

        Benchmark.benchmark("Main", () -> {
            assert new Solution().distinctEchoSubstrings("abcabcabc") == 3;
            assert new Solution().distinctEchoSubstrings("leetcodeleetcode") == 2;
            assert new Solution().distinctEchoSubstrings("a".repeat(2000)) == 1000;
        });

        Benchmark.benchmark("BruteForce", () -> {
            assert new BruteForce().distinctEchoSubstrings("abcabcabc") == 3;
            assert new BruteForce().distinctEchoSubstrings("leetcodeleetcode") == 2;
            assert new BruteForce().distinctEchoSubstrings("a".repeat(2000)) == 1000;
        });
    }

}
