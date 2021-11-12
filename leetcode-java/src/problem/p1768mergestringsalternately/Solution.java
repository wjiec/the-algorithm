package problem.p1768mergestringsalternately;

/**
 * 1768. Merge Strings Alternately
 *
 * https://leetcode-cn.com/problems/merge-strings-alternately/
 *
 * You are given two strings word1 and word2. Merge the strings by adding letters in alternating order,
 * starting with word1.
 *
 * If a string is longer than the other, append the additional letters onto the end of the merged string.
 *
 * Return the merged string.
 */

public class Solution {

    public String mergeAlternately(String word1, String word2) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0, l = Math.min(word1.length(), word2.length()); i < l; i++) {
            sb.append(word1.charAt(i));
            sb.append(word2.charAt(i));
        }

        if (word1.length() > (sb.length() / 2)) {
            sb.append(word1, sb.length() / 2, word1.length());
        } else if (word2.length() > (sb.length() / 2)) {
            sb.append(word2, sb.length() / 2, word2.length());
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        assert new Solution().mergeAlternately("abc", "pqr").equals("apbqcr");
        assert new Solution().mergeAlternately("ab", "pqrs").equals("apbqrs");
        assert new Solution().mergeAlternately("abcd", "pq").equals("apbqcd");
    }

}
