package problem.p2068checkwhethertwostringsarealmostequivalent;

/**
 * 2068. Check Whether Two Strings are Almost Equivalent
 *
 * https://leetcode-cn.com/problems/check-whether-two-strings-are-almost-equivalent/
 *
 * Two strings word1 and word2 are considered almost equivalent if the differences
 * between the frequencies of each letter from 'a' to 'z' between word1 and word2 is at most 3.
 *
 * Given two strings word1 and word2, each of length n, return true
 * word1 and word2 are almost equivalent, or false otherwise.
 *
 * The frequency of a letter x is the number of times it occurs in the string.
 */

public class Solution {

    public boolean checkAlmostEquivalent(String word1, String word2) {
        int[] map = new int[128];
        for (var c : word1.toCharArray()) map[c]++;
        for (var c : word2.toCharArray()) map[c]--;

        for (var n : map) if (n < -3 || n > 3) return false;
        return true;
    }

    public static void main(String[] args) {
        assert !new Solution().checkAlmostEquivalent("aaaa", "bccb");
        assert new Solution().checkAlmostEquivalent("abcdeef", "abaaacc");
        assert new Solution().checkAlmostEquivalent("cccddabba", "babababab");
    }

}
