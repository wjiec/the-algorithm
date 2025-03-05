package weekly.bw150.D;

import ability.Benchmark;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 3455. Shortest Matching Substring
 *
 * https://leetcode.cn/contest/biweekly-contest-150/problems/shortest-matching-substring/
 *
 * You are given a string s and a pattern string p, where p contains exactly two '*' characters.
 *
 * The '*' in p matches any sequence of zero or more characters.
 *
 * Return the length of the shortest substring in s that matches p. If there is no such substring, return -1.
 *
 * Note: The empty substring is considered valid.
 */

/** @noinspection DuplicatedCode*/
public class Solution {

    public int shortestMatchingSubstring(String s, String p) {
        if (p.length() == 2) return 0;
        if (p.length() > s.length() + 2) return -1;

        // 模式字符串可以用: a * b * c 的形式进行表达
        //  1. 在字符串 s 中找到所有 a, b, c 的出现位置 x, y, z
        //  2. 依次找到 a 后面最近的 b, 再找到 b 之后最近的 c, 计算 [a, c + z] 的长度取最小
        //      i. y 必须在 x + len(a) 之后, z 必须在 y + len(b) 之后
        int star1 = p.indexOf('*'), star2 = p.indexOf('*', star1 + 1);
        String a = p.substring(0, star1), b = p.substring(star1 + 1, star2), c = p.substring(star2 + 1);

        // 找到 a, b, c 的所有出现位置
        List<Integer> x = kmp(s.toCharArray(), a.toCharArray());
        List<Integer> y = kmp(s.toCharArray(), b.toCharArray());
        List<Integer> z = kmp(s.toCharArray(), c.toCharArray());

        // 为所有位置分组
        List<Integer> len = new ArrayList<>();
        List<List<Integer>> groups = new ArrayList<>();
        if (a.length() != 0) { groups.add(x); len.add(a.length()); }
        if (b.length() != 0) { groups.add(y); len.add(b.length()); }
        if (c.length() != 0) { groups.add(z); len.add(c.length()); }
        for (var g : groups) if (g.size() == 0) return -1;
        if (groups.size() == 1) return len.get(0);

        int ans = s.length() + 1;
        // 如果是由 2 个组成的话, 我们直接使用双指针找到最接近的两组坐标
        if (groups.size() == 2) {
            int l1 = len.get(0), l2 = len.get(1);
            List<Integer> g1 = groups.get(0), g2 = groups.get(1);
            for (int i = 0, j = 0; j < g2.size(); j++) {
                int rIdx = g2.get(j);
                while (i + 1 < g1.size() && g1.get(i + 1) + l1 <= rIdx) i++;
                if (g1.get(i) + l1 <= rIdx) ans = Math.min(ans, rIdx + l2 - g1.get(i));
            }
        } else if (groups.size() == 3) {
            // 如果由 3 个组成, 我们枚举中间的位置, 找到左右两边距离最近的位置
            int l1 = len.get(0), l2 = len.get(1), l3 = len.get(2);
            List<Integer> g1 = groups.get(0), g2 = groups.get(1), g3 = groups.get(2);
            for (int i = 0, j = 0, k = 0; j < g2.size(); j++) {
                int rIdx = g2.get(j);
                while (i + 1 < g1.size() && g1.get(i + 1) + l1 <= rIdx) i++;
                while (k < g3.size() && g3.get(k) < rIdx + l2) k++;
                if (g1.get(i) + l1 <= rIdx && k < g3.size()) ans = Math.min(ans, g3.get(k) + l3 - g1.get(i));
            }
        }

        return ans > s.length() ? -1 : ans;
    }

    private List<Integer> kmp(char[] target, char[] pattern) {
        if (pattern.length == 0) return Collections.emptyList();
        if (pattern.length > target.length) return Collections.emptyList();

        int[] next = lps(pattern);
        List<Integer> ans = new ArrayList<>();
        for (int i = 0, j = 0; i < target.length; ) {
            if (target[i] == pattern[j]) {
                i++; j++;
                if (j == pattern.length) {
                    ans.add(i - j);
                    j = next[j - 1];
                }
            } else {
                if (j != 0) j = next[j - 1];
                else i++;
            }
        }

        return ans;
    }

    private int[] lps(char[] pattern) {
        int[] next = new int[pattern.length];
        for (int i = 1, j = 0; i < pattern.length; ) {
            if (pattern[i] == pattern[j]) {
                next[i++] = ++j;
            } else {
                if (j != 0) j = next[j - 1];
                else next[i++] = 0;
            }
        }
        return next;
    }

    public static void main(String[] args) {
        String aa = "a".repeat(100_000);
        Benchmark.benchmark("", () -> {
            assert new Solution().shortestMatchingSubstring("abcabcabcabcabcabcabcabc", "abcabc*abc*abcabc") == 15;
            assert new Solution().shortestMatchingSubstring("otqqkeeycttc", "ot*qqkee*ycttc") == 12;
            assert new Solution().shortestMatchingSubstring("abaacbaecebce", "ba*c*ce") == 8;
            assert new Solution().shortestMatchingSubstring("baccbaadbc", "cc*baa*adb") == -1;
            assert new Solution().shortestMatchingSubstring("a", "**") == 0;
            assert new Solution().shortestMatchingSubstring("madlogic", "*adlogi*") == 6;

            assert new Solution().shortestMatchingSubstring(aa, "a*a*a") == 3;
            assert new Solution().shortestMatchingSubstring(aa, "a**a") == 2;
            assert new Solution().shortestMatchingSubstring(aa, "**a") == 1;
        });
    }

}
