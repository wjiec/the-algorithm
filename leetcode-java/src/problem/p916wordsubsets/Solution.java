package problem.p916wordsubsets;

import common.Checker;

import java.util.ArrayList;
import java.util.List;

/**
 * 916. Word Subsets
 *
 * https://leetcode.cn/problems/word-subsets/
 *
 * You are given two string arrays words1 and words2.
 *
 * A string b is a subset of string a if every letter in b occurs in a including multiplicity.
 *
 * For example, "wrr" is a subset of "warrior" but is not a subset of "world".
 * A string a from words1 is universal if for every string b in words2, b is a subset of a.
 *
 * Return an array of all the universal strings in words1. You may return the answer in any order.
 */

public class Solution {

    public List<String> wordSubsets(String[] words1, String[] words2) {
        int[] req = new int[26];
        for (var word : words2) {
            int[] curr = cMap(word.toCharArray());
            for (int i = 0; i < curr.length; i++) {
                req[i] = Math.max(req[i], curr[i]);
            }
        }

        List<String> ans = new ArrayList<>();
        for (var word : words1) {
            boolean subset = true;
            int[] curr = cMap(word.toCharArray());
            for (int i = 0; i < curr.length; i++) {
                if (req[i] != 0 && req[i] > curr[i]) {
                    subset = false;
                    break;
                }
            }
            if (subset) ans.add(word);
        }
        return ans;
    }

    private int[] cMap(char[] chars) {
        int[] map = new int[26];
        for (var c : chars) map[c - 'a']++;
        return map;
    }

    public static void main(String[] args) {
        assert Checker.anyOrder(new Solution().wordSubsets(new String[]{
            "amazon","apple","facebook","google","leetcode"
        }, new String[]{"e","o"}), List.of("facebook","google","leetcode"));

        assert Checker.anyOrder(new Solution().wordSubsets(new String[]{
            "amazon","apple","facebook","google","leetcode"
        }, new String[]{"l","e"}), List.of("apple","google","leetcode"));

        assert Checker.anyOrder(new Solution().wordSubsets(new String[]{
            "amazon","apple","facebook","google","leetcode"
        }, new String[]{"e","oo"}), List.of("facebook","google"));

        assert Checker.anyOrder(new Solution().wordSubsets(new String[]{
            "amazon","apple","facebook","google","leetcode"
        }, new String[]{"lo","eo"}), List.of("google","leetcode"));

        assert Checker.anyOrder(new Solution().wordSubsets(new String[]{
            "amazon","apple","facebook","google","leetcode"
        }, new String[]{"ec","oc","ceo"}), List.of("facebook","leetcode"));
    }

}
