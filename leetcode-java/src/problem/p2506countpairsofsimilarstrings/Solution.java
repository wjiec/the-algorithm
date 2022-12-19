package problem.p2506countpairsofsimilarstrings;

import java.util.HashMap;
import java.util.Map;

/**
 * 2506. Count Pairs Of Similar Strings
 *
 * https://leetcode.cn/problems/count-pairs-of-similar-strings/
 *
 * You are given a 0-indexed string array words.
 *
 * Two strings are similar if they consist of the same characters.
 *
 * For example, "abca" and "cba" are similar since both consist of characters 'a', 'b', and 'c'.
 * However, "abacba" and "bcfd" are not similar since they do not consist of the same characters.
 *
 * Return the number of pairs (i, j) such that 0 <= i < j <= word.length - 1 and
 * the two strings words[i] and words[j] are similar.
 */

public class Solution {

    public int similarPairs(String[] words) {
        int ans = 0;
        Map<Integer, Integer> count = new HashMap<>();
        for (var word : words) {
            int hash = 0;
            for (var c : word.toCharArray()) {
                hash |= 1 << (c - 'a');
            }

            int v = count.merge(hash, 1, Integer::sum);
            ans += v - 1;
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().similarPairs(new String[]{"aba","aabb","abcd","bac","aabc"}) == 2;
        assert new Solution().similarPairs(new String[]{"aabb","ab","ba"}) == 3;
        assert new Solution().similarPairs(new String[]{"nba","cba","dba"}) == 0;
    }

}
