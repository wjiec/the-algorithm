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
        //  - 也就是在一个区间 [l, r) 里, len(a) == len(b)
        //      - 前缀和里 len(a) == aSum[r + 1] - aSum[l]
        //      - 前缀和里 len(b) == bSum[r + 1] - bSum[l]
        //  - 也就是 aSum[r + 1] - aSum[l] = bSum[r + 1] - bSum[l]
        //      - 也就是 bSum[l] - aSum[l] = bSum[r + 1] - aSum[r + 1]
        //  - 我们令 s[i] = a[i] - b[i], 并计算 s 的前缀和, 找到前缀和相同的项就构成了一个区间
        ans = Math.max(ans, group2(chars, 'a', 'b'));
        ans = Math.max(ans, group2(chars, 'a', 'c'));
        ans = Math.max(ans, group2(chars, 'b', 'c'));

        // 如果子串里三个字符
        //  - 也就是在一个区间 [l, r) 里, len(a) == len(b) == len(c)
        //      - 根据传递性, 我们有 len(a) == len(b) 且 len(b) == len(c)
        //  - 此时我们有：
        //      - bSum[l] - aSum[l] = bSum[r + 1] - aSum[r + 1]
        //      - cSum[l] - bSum[l] = cSum[r + 1] - bSum[r + 1]
        //  - 我们使用一个元组来表示 (bSum[l] - aSum[l], cSum[l] - bSum[l]) == (bSum[r + 1] - aSum[r + 1], cSum[r + 1] - bSum[r + 1])
        //  - 也就是令 s[i] = (b[i] - a[i], c[i] - b[i]), 找到前缀和 s 中相同的项构成一个区间
        BiFunction<Integer, Integer, Long> comp = (a, b) -> (long) ((long) (a + n) << 32 | (b + n));
        Map<Long, Integer> p = new HashMap<>(); p.put(comp.apply(0, 0), -1);
        for (int i = 0, a = 0, b = 0, c = 0; i < n; i++) {
            switch (chars[i]) { case 'a' -> a++; case 'b' -> b++; case 'c' -> c++; }

            long k = comp.apply(b - a, c - b);
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
