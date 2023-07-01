package problem.p1745palindromepartitioningiv;

import java.util.HashSet;
import java.util.Set;

/**
 * 1745. Palindrome Partitioning IV
 *
 * https://leetcode.cn/problems/palindrome-partitioning-iv/
 *
 * Given a string s, return true if it is possible to split the string s into three
 * non-empty palindromic substrings. Otherwise, return false.
 *
 * A string is said to be palindrome if it the same string when reversed.
 */

@SuppressWarnings("unchecked")
public class Solution {

    public boolean checkPartitioning(String s) {
        char[] chars = s.toCharArray();
        Set<Integer>[] ranges = new Set[chars.length];
        for (int i = 0; i < ranges.length; i++) {
            ranges[i] = new HashSet<>();
            ranges[i].add(i);
        }

        for (int i = 0; i < chars.length; i++) {
            int[] curr = palindromic(chars, i, i);
            if (curr != null) ranges[curr[1]].add(curr[0]);

            curr = palindromic(chars, i, i + 1);
            if (curr != null) ranges[curr[1]].add(curr[0]);
        }

        if (ranges[chars.length - 1] == null) return false;
        for (var prev : ranges[chars.length - 1]) {
            if (prev - 1 >= 0 && ranges[prev - 1] != null) {
                for (var first : ranges[prev - 1]) {
                    if (first - 1 >= 0 && ranges[first - 1] != null && ranges[first - 1].contains(0)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private int[] palindromic(char[] chars, int l, int r) {
        if (l >= 0 && r < chars.length && chars[l] == chars[r]) {
            while (l - 1 >= 0 && r + 1 < chars.length && chars[l - 1] == chars[r + 1]) {
                l--; r++;
            }
            return new int[]{l, r};
        }
        return null;
    }

    public static void main(String[] args) {
        assert new Solution().checkPartitioning("aaa");
        assert new Solution().checkPartitioning("aba");
        assert new Solution().checkPartitioning("aaaa");
        assert new Solution().checkPartitioning("abcbdd");
        assert !new Solution().checkPartitioning("bcbddxy");

        assert new Solution().checkPartitioning("a".repeat(2000));
    }

}
