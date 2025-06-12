package problem.p1898maximumnumberofremovablecharacters;

import java.util.Arrays;

/**
 * 1898. Maximum Number of Removable Characters
 *
 * https://leetcode.cn/problems/maximum-number-of-removable-characters/
 *
 * You are given two strings s and p where p is a subsequence of s. You are also given a distinct
 * 0-indexed integer array removable containing a subset of indices of s (s is also 0-indexed).
 *
 * You want to choose an integer k (0 <= k <= removable.length) such that, after removing k characters
 * from s using the first k indices in removable, p is still a subsequence of s.
 * More formally, you will mark the character at s[removable[i]] for each 0 <= i < k, then remove all
 * marked characters and check if p is still a subsequence.
 *
 * Return the maximum k you can choose such that p is still a subsequence of s after the removals.
 *
 * A subsequence of a string is a new string generated from the original string with some characters
 * (can be none) deleted without changing the relative order of the remaining characters.
 * @noinspection DuplicatedCode
 */

public class Solution {

    public int maximumRemovals(String s, String p, int[] removable) {
        boolean[] removes = new boolean[s.length()];
        char[] ss = s.toCharArray(), ps = p.toCharArray();

        int l = 0, r = removable.length + 1;
        while (l < r) {
            int mid = l + (r - l) / 2;
            Arrays.fill(removes, false);
            for (int i = 0; i < mid; i++) {
                removes[removable[i]] = true;
            }

            if (check(ss, ps, removes)) l = mid + 1;
            else r = mid;
        }
        return l - 1;
    }

    private boolean check(char[] s, char[] p, boolean[] remove) {
        int si = 0, pi = 0, sl = s.length, pl = p.length;
        for (; si < sl && pi < pl; si++) {
            if (!remove[si] && s[si] == p[pi]) pi++;
        }
        return pi == pl;
    }

    public static void main(String[] args) {
        assert new Solution().maximumRemovals("qlevcvgzfpryiqlwy", "qlecfqlw", new int[]{12, 5}) == 2;

        assert new Solution().maximumRemovals("abcacb", "ab", new int[]{3,1,0}) == 2;
        assert new Solution().maximumRemovals("abcbddddd", "abcd", new int[]{3,2,1,4,5,6}) == 1;
        assert new Solution().maximumRemovals("abcab", "abc", new int[]{0,1,2,3,4}) == 0;
    }

}
