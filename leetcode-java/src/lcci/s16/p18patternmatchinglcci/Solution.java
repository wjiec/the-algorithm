package lcci.s16.p18patternmatchinglcci;

/**
 * 面试题 16.18. 模式匹配
 *
 * https://leetcode.cn/problems/pattern-matching-lcci/
 *
 * 你有两个字符串，即pattern和value。 pattern字符串由字母"a"和"b"组成，用于描述字符串中的模式。
 * 例如，字符串"catcatgocatgo"匹配模式"aabab"（其中"cat"是"a"，"go"是"b"），该字符串也匹配像"a"、"ab"和"b"这样的模式。
 * 但需注意"a"和"b"不能同时表示相同的字符串。编写一个方法判断value字符串是否匹配pattern字符串。
 */

public class Solution {

    public boolean patternMatching(String pattern, String value) {
        char[] refs = pattern.toCharArray();
        char[] target = value.toCharArray();

        int a = 0, b = 0, n = target.length;
        for (var c : refs) { if (c == 'a') a++; else b++; }
        if (a == 0 || b == 0) {
            if (n % refs.length != 0) return false;
            return repeated(target, n / refs.length);
        }

        for (int i = 0; i <= n; i++) {
            int rest = n - a * i;
            if (rest % b != 0) continue;
            if (rest / b < 0) break;
            if (matches(target, refs, i, rest / b)) {
                return true;
            }
        }
        return false;
    }

    private boolean matches(char[] target, char[] patterns, int la, int lb) {
        int aStart = -1, bStart = -1, idx = 0;
        for (var pattern : patterns) {
            int len = 0, start = 0;
            switch (pattern) {
                case 'a' -> {
                    if (aStart == -1) {
                        aStart = idx;
                        idx += la;
                        continue;
                    }

                    len = la;
                    start = aStart;
                }
                case 'b' -> {
                    if (bStart == -1) {
                        bStart = idx;
                        idx += lb;
                        continue;
                    }

                    len = lb;
                    start = bStart;
                }
            }

            if (!equals(target, start, idx, len)) {
                return false;
            }
            idx += len;
        }

        if (idx != target.length) return false;
        return la != lb || !equals(target, aStart, bStart, la);
    }

    private boolean equals(char[] target, int s1, int s2, int l) {
        for (int i = 0; i < l; i++) {
            if (target[s1++] != target[s2++]) {
                return false;
            }
        }
        return true;
    }

    private boolean repeated(char[] target, int len) {
        for (int i = len; i < target.length; i++) {
            if (target[i] != target[i % len]) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        assert new Solution().patternMatching("bbbbbbbbbbbbbbabbbbb", "ppppppppppppppjsftcleifftfthiehjiheyqkhjfkyfckbtwbelfcgihlrfkrwireflijkjyppppg");
        assert !new Solution().patternMatching("bbbaa", "xxxxxxy");
        assert !new Solution().patternMatching("abbaa", "dogdogdogdogdog");
        assert new Solution().patternMatching("aaaaab", "xahnxdxyaahnxdxyaahnxdxyaahnxdxyaauxuhuo");
        assert new Solution().patternMatching("abba", "dogdogdogdog");

        assert new Solution().patternMatching("abba", "dogcatcatdog");
        assert !new Solution().patternMatching("abba", "dogcatcatfish");
        assert !new Solution().patternMatching("aaaa", "dogcatcatdog");
        assert new Solution().patternMatching("abba", "dogdogdogdog");
    }

}
