package weekly.w445.C;

import java.math.BigInteger;

/**
 * 3518. Smallest Palindromic Rearrangement II
 *
 * https://leetcode.cn/contest/weekly-contest-445/problems/smallest-palindromic-rearrangement-ii/
 *
 * You are given a palindromic string s and an integer k.
 *
 * Return the k-th lexicographically smallest palindromic permutation of s.
 *
 * If there are fewer than k distinct palindromic permutations, return an empty string.
 *
 * Note: Different rearrangements that yield the same palindromic
 * string are considered identical and are counted once.
 */

public class Solution {

    public String smallestPalindrome(String s, int k) {
        int[] cnt = new int[128];
        for (var c : s.toCharArray()) cnt[c]++;

        // 我们要找到第 k 小的回文排列
        //  - 如果长度为奇数, 中间的字母是不可变的, 剩下的情况和偶数长度的一致
        //  - 如果长度是偶数, 由于前一半和后一半必须一样, 实际上就是找前一半的最小第 k 个排列
        //      - 已知条件是我们有 n1 个 a, n2 个 b, ..., n26 个 z
        //
        // 如果第一个位置选择填 a, 那么剩下的 n - 1 个位置有多少种填法?
        //  - 每个位置可以填一个 char, 但是不能填重复的, 也就是 n! / a! * b! * ... * z!

        // 中间位
        char middle = '\0';
        if (s.length() % 2 != 0) {
            for (var c = 'a'; c <= 'z'; c++) {
                if (cnt[c] % 2 == 1) middle = c;
                cnt[c] /= 2;
            }
        }

        // 递归计算每个位置应该填什么
        char[] ans = new char[s.length()];
        dfs(ans, cnt, 0, s.length() / 2, k);
        if (middle != '\0') ans[s.length() / 2] = middle;
        for (int i = 0, j = s.length() - 1; i < j; i++, j--) ans[j] = ans[i];

        return new String(ans);
    }

    // 枚举第 i 位应该填什么
    private boolean dfs(char[] ans, int[] cnt, int i, int len, int k) {
        if (i == len) return true;

        // 后面还剩下多少种填的可能
        for (char c = 'a'; c <= 'z'; c++) {
            if (cnt[c] == 0) continue;

            long remain = countPossible(cnt, c, len - i - 1);
            if (remain >= k) {
                ans[i] = c;
                cnt[i]--;
                return dfs(ans, cnt, i + 1, len, k);
            } else k -= remain;
        }

        return false;
    }

    private long countPossible(int[] cnt, char c, int len) {
        BigInteger bi = fac(len);
        for (var i = 'a'; i <= 'z'; i++) {
            int x = cnt[i] - (i == c ? 1 : 0);
            if (x != 0) bi = bi.divide(fac(x));
        }

        return bi.compareTo(BigInteger.valueOf(Integer.MAX_VALUE)) >= 0 ? Integer.MAX_VALUE : bi.longValue();
    }

    private BigInteger fac(int n) {
        BigInteger bi = BigInteger.ONE;
        for (int i = 2; i <= n; i++) bi = bi.multiply(BigInteger.valueOf(i));
        return bi;
    }

    public static void main(String[] args) {
        assert new Solution().smallestPalindrome("abba", 2).equals("baab");
        assert new Solution().smallestPalindrome("aa", 2).equals("");
        assert new Solution().smallestPalindrome("bacab", 1).equals("abcba");
    }

}
