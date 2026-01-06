package weekly.w471.C;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

/**
 * Q3. Longest Balanced Substring II
 *
 * https://leetcode.cn/contest/weekly-contest-471/problems/longest-balanced-substring-ii/
 *
 * You are given a string s consisting only of the characters 'a', 'b', and 'c'.
 *
 * A substring of s is called balanced if all distinct characters in the substring appear the same number of times.
 *
 * Return the length of the longest balanced substring of s.
 */

public class Solution {

    public int longestBalanced(String s) {
        int n = s.length();
        char[] chars = s.toCharArray();

        int ans = 0;
        // 如果子串里只有一个字符, 那么答案就是这个字符的长度
        for (int r = 1, l = 0; r <= n; r++) {
            if (r == n || chars[r] != chars[l]) {
                ans = Math.max(ans, r - l); l = r;
            }
        }

        // 如果子串里有两个字符
        ans = Math.max(ans, group2(chars, 'a', 'b'));
        ans = Math.max(ans, group2(chars, 'a', 'c'));
        ans = Math.max(ans, group2(chars, 'b', 'c'));

        // 如果子串里三个字符, 把 (a, b) 压缩一下
        //  - (a + n) << 32 | (b + n)
        BiFunction<Integer, Integer, Long> comp = (a, b) -> (long) ((long) (a + n) << 32 | (b + n));
        Map<Long, Integer> p = new HashMap<>(); p.put(comp.apply(0, 0), -1);
        for (int i = 0, a = 0, b = 0, c = 0; i < n; i++) {
            switch (chars[i]) { case 'a' -> a++; case 'b' -> b++; case 'c' -> c++; }

            long k = comp.apply(a - b, b - c);
            if (p.containsKey(k)) ans = Math.max(ans, i - p.get(k));
            else p.put(k, i);
        }

        return ans;
    }

    private int group2(char[] chars, char a, char b) {
        int n = chars.length, ans = 0;
        for (int i = 0; i < chars.length; i++) {
            // 每一个只包含 a 或 b 的组里循环处理
            Map<Integer, Integer> p = new HashMap<>(); p.put(0, i - 1);
            for (int d = 0; i < n && (chars[i] == a || chars[i] == b); i++) {
                d += chars[i] == a ? 1 : -1;
                if (p.containsKey(d)) ans = Math.max(ans, i - p.get(d));
                else p.put(d, i);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().longestBalanced("accc") == 3;
        assert new Solution().longestBalanced("abbac") == 4;
    }

}
