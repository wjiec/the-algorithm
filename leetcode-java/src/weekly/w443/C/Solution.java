package weekly.w443.C;

import ability.Benchmark;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 3504. Longest Palindrome After Substring Concatenation II
 *
 * https://leetcode.cn/contest/weekly-contest-443/problems/longest-palindrome-after-substring-concatenation-ii/
 *
 * You are given two strings, s and t.
 *
 * You can create a new string by selecting a substring from s (possibly empty)
 * and a substring from t (possibly empty), then concatenating them in order.
 *
 * Return the length of the longest palindrome that can be formed this way.
 */

public class Solution {

    /** @noinspection unchecked*/
    public int longestPalindrome(String s, String t) {
        // 从 s 取前缀拼接上从 t 取的后缀使得拼接后的字符串是个回文串
        //  1. 中心点坐标 i1, i2 都位于 s 内
        //  2. 中心点坐标 j1, j2 都位于 t 内
        //  3. 中心点坐标 i, j 位于 s 和 t 内
        //
        // 1. 在 s 中找所有的回文序列, 如果 s[i1] != s[i2] 则考虑从 t 中找到所有等于 s[i1] 的位置继续往后遍历
        // 2. 在 t 中找所有的回文序列, 如果 t[j1] != t[j2] 则考虑从 s 中找到所有等于 t[j2] 的位置继续往前遍历
        // 3. 在 s 和 t 中找到所有相同的位置, 枚举两边进行遍历
        //
        // 三种情况取最大值

        List<Integer>[] c1 = new List[26], c2 = new List[26];
        Arrays.setAll(c1, i -> new ArrayList<>());
        Arrays.setAll(c2, i -> new ArrayList<>());

        int ans = 1, sl = s.length(), tl = t.length();
        char[] ss = s.toCharArray(), tt = t.toCharArray();
        for (int i = 0; i < sl; i++) c1[ss[i] - 'a'].add(i);
        for (int j = 0; j < tl; j++) c2[tt[j] - 'a'].add(j);

        // 第一种情况
        for (int i = 1; i < sl; i++) {
            // 以 i, i 为中心扩展
            ans = Math.max(ans, extNext(ss, i, i, -1, tt, sl, tl, c2, ans));
            // 以 i - 1, i 为中心扩展
            ans = Math.max(ans, extNext(ss, i - 1, i, 0, tt, sl, tl, c2, ans));
        }

        // 第二种情况
        for (int j = 1; j < tl; j++) {
            // 以 j, j 为中心扩展
            ans = Math.max(ans, extPrev(tt, j, j, -1, ss, sl, tl, c1, ans));
            // 以 j - 1, j 为中心扩展
            ans = Math.max(ans, extPrev(tt, j - 1, j, 0, ss, sl, tl, c1, ans));
        }

        // 第三种情况
        for (int i = 0; i < sl; i++) {
            for (var j : c2[ss[i] - 'a']) {
                // 以 i, j 为中心点坐标往两边扩展, 两边的长度分别有 (i + 1) 和 tl - j
                if (i + 1 + tl - j <= ans) continue;

                // 开始尝试扩展并计算最终的长度
                int curr = 0, si = i, tj = j;
                while (si >= 0 && tj < tl && ss[si] == tt[tj]) { si--; tj++; curr += 2; }
                // 如果 i 后面还有一个, 或者 j 前面还有一个, 则可以再加 1
                boolean add = i + 1 < sl || j > 0;
                ans = Math.max(ans, curr + (add ? 1 : 0));
            }
        }

        return ans;
    }

    private int extNext(char[] ss, int i1, int i2, int curr, char[] tt, int sl, int tl, List<Integer>[] c, int max) {
        while (i1 >= 0 && i2 < sl && ss[i1] == ss[i2]) { i1--; i2++; curr += 2; }

        int ans = curr;
        // (i1, i2) 是合法的回文字符串, 如果 i1 >= 0 则还可以考虑从 tt 进行扩展
        if (i1 >= 0) {
            // 此时从 i1, j 开始是新扩展的长度
            for (var j : c[ss[i1] - 'a']) {
                // 前面还可以扩展 i1 + 1 个位置, 后面还可以扩展 tl - j
                if (i1 + 1 + tl - j <= max - curr) continue;

                int curr_ext = 0, si = i1;
                while (si >= 0 && j < tl && ss[si] == tt[j]) { si--; j++; curr_ext += 2; }
                ans = Math.max(ans, curr + curr_ext);
            }
        }
        return ans;
    }

    private int extPrev(char[] tt, int j1, int j2, int curr, char[] ss, int sl, int tl, List<Integer>[] c, int max) {
        while (j1 >= 0 && j2 < tl && tt[j1] == tt[j2]) { j1--; j2++; curr += 2; }

        int ans = curr;
        // (j1, j2) 是合法的回文字符串, 如果 j2 < tl 则还可以考虑从 ss 进行扩展
        if (j2 < tl) {
            for (var i : c[tt[j2] - 'a']) {
                // 前面还可以扩展 i + 1, 后面还可以扩展 tl - j2
                if (i + 1 + tl - j2 <= max - curr) continue;

                // 此时从 i, tj 开始是新扩展的长度
                int curr_ext = 0, tj = j2;
                while (tj < tl && i >= 0 && tt[tj] == ss[i]) { tj++; i--; curr_ext += 2; }
                ans = Math.max(ans, curr + curr_ext);
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        var s = "a".repeat(1000);

        Benchmark.benchmark("", () -> {
            assert new Solution().longestPalindrome(s, s) == 2000;
            assert new Solution().longestPalindrome("rtk", "hrgpgt") == 5;
            assert new Solution().longestPalindrome("b", "ajbbx") == 3;

            assert new Solution().longestPalindrome("a", "a") == 2;
            assert new Solution().longestPalindrome("abc", "def") == 1;
            assert new Solution().longestPalindrome("b", "aaaa") == 4;
            assert new Solution().longestPalindrome("abcde", "ecdba") == 5;
        });
    }

}
