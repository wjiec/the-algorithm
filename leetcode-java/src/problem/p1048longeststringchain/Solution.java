package problem.p1048longeststringchain;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 * 1048. Longest String Chain
 *
 * https://leetcode.cn/problems/longest-string-chain/
 *
 * You are given an array of words where each word consists of lowercase English letters.
 *
 * wordA is a predecessor of wordB if and only if we can insert exactly one letter anywhere in wordA
 * without changing the order of the other characters to make it equal to wordB.
 *
 * For example, "abc" is a predecessor of "abac", while "cba" is not a predecessor of "bcad".
 * A word chain is a sequence of words [word1, word2, ..., wordk] with k >= 1, where word1 is a
 * predecessor of word2, word2 is a predecessor of word3, and so on.
 *
 * A single word is trivially a word chain with k == 1.
 *
 * Return the length of the longest possible word chain with words chosen from the given list of words.
 */

public class Solution {

    public int longestStrChain(String[] words) {
        int ans = 0;
        Map<String, Integer> map = new HashMap<>();
        Arrays.sort(words, Comparator.comparingInt(String::length));
        for (var word : words) {
            int best = 0, n = word.length();
            for (int i = 0; i < n; i++) {
                String prev = word.substring(0, i) + word.substring(i + 1);
                best = Math.max(best, map.getOrDefault(prev, 0) + 1);
            }
            map.put(word, best);
            if (best > ans) ans = best;
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().longestStrChain(new String[]{"a","b","ba","bca","bda","bdca"}) == 4;
        assert new Solution().longestStrChain(new String[]{"xbc","pcxbcf","xb","cxbc","pcxbc"}) == 5;
        assert new Solution().longestStrChain(new String[]{"abcd","dbqca"}) == 1;
    }

}
