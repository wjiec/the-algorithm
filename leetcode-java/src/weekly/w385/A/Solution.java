package weekly.w385.A;

/**
 * 3042. Count Prefix and Suffix Pairs I
 *
 * https://leetcode.cn/contest/weekly-contest-385/problems/count-prefix-and-suffix-pairs-i/
 *
 * You are given a 0-indexed string array words.
 *
 * Let's define a boolean function isPrefixAndSuffix that takes two strings, str1 and str2:
 *
 * isPrefixAndSuffix(str1, str2) returns true if str1 is both a prefix
 * and a suffix of str2, and false otherwise.
 *
 * For example, isPrefixAndSuffix("aba", "ababa") is true because "aba" is a
 * prefix of "ababa" and also a suffix, but isPrefixAndSuffix("abc", "abcd") is false.
 *
 * Return an integer denoting the number of index pairs (i, j) such that i < j, and
 * isPrefixAndSuffix(words[i], words[j]) is true.
 */

public class Solution {

    public int countPrefixSuffixPairs(String[] words) {
        int ans = 0, n = words.length;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (isPrefixAndSuffix(words[i], words[j])) ans++;
            }
        }
        return ans;
    }

    private boolean isPrefixAndSuffix(String s1, String s2) {
        return s2.startsWith(s1) && s2.endsWith(s1);
    }

    public static void main(String[] args) {
    }

}
