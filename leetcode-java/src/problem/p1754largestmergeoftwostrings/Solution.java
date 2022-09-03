package problem.p1754largestmergeoftwostrings;

/**
 * 1754. Largest Merge Of Two Strings
 *
 * https://leetcode.cn/problems/largest-merge-of-two-strings/
 *
 * You are given two strings word1 and word2. You want to construct a string merge in the following way:
 * while either word1 or word2 are non-empty, choose one of the following options:
 *
 * If word1 is non-empty, append the first character in word1 to merge and delete it from word1.
 * For example, if word1 = "abc" and merge = "dv", then after choosing this operation, word1 = "bc" and merge = "dva".
 *
 * If word2 is non-empty, append the first character in word2 to merge and delete it from word2.
 * For example, if word2 = "abc" and merge = "", then after choosing this operation, word2 = "bc" and merge = "a".
 *
 * Return the lexicographically largest merge you can construct.
 * A string a is lexicographically larger than a string b (of the same length) if in the first position
 * where a and b differ, a has a character strictly larger than the corresponding character in b.
 *
 * For example, "abcd" is lexicographically larger than "abcc" because the first position they differ is at
 * the fourth character, and d is greater than c.
 */

public class Solution {

    public String largestMerge(String word1, String word2) {
        StringBuilder sb = new StringBuilder();
        while (!word1.isEmpty() || !word2.isEmpty()) {
            if (word1.compareTo(word2) >= 0) {
                sb.append(word1.charAt(0));
                word1 = word1.substring(1);
            } else {
                sb.append(word2.charAt(0));
                word2 = word2.substring(1);
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        assert new Solution().largestMerge("cabaa", "bcaaa").equals("cbcabaaaaa");
        assert new Solution().largestMerge("abcabc", "abdcaba").equals("abdcabcabcaba");
    }

}
