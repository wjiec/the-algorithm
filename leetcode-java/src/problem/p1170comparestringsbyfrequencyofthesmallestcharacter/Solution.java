package problem.p1170comparestringsbyfrequencyofthesmallestcharacter;

import common.Checker;

/**
 * 1170. Compare Strings by Frequency of the Smallest Character
 *
 * https://leetcode.cn/problems/compare-strings-by-frequency-of-the-smallest-character/
 *
 * Let the function f(s) be the frequency of the lexicographically smallest character in a non-empty string s.
 * For example, if s = "dcce" then f(s) = 2 because the lexicographically
 * smallest character is 'c', which has a frequency of 2.
 *
 * You are given an array of strings words and another array of query strings queries.
 * For each query queries[i], count the number of words in words such
 * that f(queries[i]) < f(W) for each W in words.
 *
 * Return an integer array answer, where each answer[i] is the answer to the ith query.
 */

public class Solution {

    public int[] numSmallerByFrequency(String[] queries, String[] words) {
        int[] freq = new int[2001];
        for (var word : words) {
            freq[getFreq(word.toCharArray())]++;
        }

        for (int i = freq.length - 1, sum = 0; i >= 0; i--) {
            int curr = freq[i];
            freq[i] = sum;
            sum += curr;
        }

        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            ans[i] = freq[getFreq(queries[i].toCharArray())];
        }
        return ans;
    }

    private int getFreq(char[] chars) {
        char small = 'z'; int count = 0;
        for (char curr : chars) {
            if (curr <= small) {
                if (curr != small) {
                    small = curr;
                    count = 0;
                }
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().numSmallerByFrequency(
            new String[]{"cbd"}, new String[]{"zaaaz"}), new int[]{1});

        assert Checker.check(new Solution().numSmallerByFrequency(
            new String[]{"bbb","cc"}, new String[]{"a","aa","aaa","aaaa"}), new int[]{1, 2});
    }

}
