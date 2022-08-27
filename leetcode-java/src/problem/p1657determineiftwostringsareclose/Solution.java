package problem.p1657determineiftwostringsareclose;

import java.util.Arrays;

/**
 * 1657. Determine if Two Strings Are Close
 *
 * https://leetcode.cn/problems/determine-if-two-strings-are-close/
 *
 * Two strings are considered close if you can attain one from the other using the following operations:
 *
 * Operation 1: Swap any two existing characters.
 * For example, abcde -> aecdb
 *
 * Operation 2: Transform every occurrence of one existing character into another
 * existing character, and do the same with the other character.
 * For example, aacabb -> bbcbaa (all a's turn into b's, and all b's turn into a's)
 *
 * You can use the operations on either string as many times as necessary.
 *
 * Given two strings, word1 and word2, return true if word1 and word2 are close, and false otherwise.
 */

public class Solution {

    public boolean closeStrings(String word1, String word2) {
        if (word1.length() != word2.length()) return false;

        int hash1 = 0, hash2 = 0;
        int[] s1 = new int[128], s2 = new int[128];
        for (var c : word1.toCharArray()) { s1[c]++; hash1 |= 1 << (c - 'a'); }
        for (var c : word2.toCharArray()) { s2[c]++; hash2 |= 1 << (c - 'a'); }

        // 连个字符串所拥有的字符应该是相同的
        if (hash1 != hash2) return false;

        // 数量应该都是相同的
        Arrays.sort(s1); Arrays.sort(s2);
        for (int i = 0; i < s1.length; i++) {
            if (s1[i] != s2[i]) return false;
        }

        return true;
    }

    public static void main(String[] args) {
        assert new Solution().closeStrings("abc", "bca");
        assert !new Solution().closeStrings("a", "aa");
        assert new Solution().closeStrings("cabbba", "abbccc");
        assert !new Solution().closeStrings("cabbba", "aabbss");
    }

}
