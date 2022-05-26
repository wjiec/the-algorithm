package problem.p890findandreplacepattern;

import common.Checker;

import java.util.*;

/**
 * 890. Find and Replace Pattern
 *
 * https://leetcode.cn/problems/find-and-replace-pattern/
 *
 * Given a list of strings words and a string pattern, return a list of words[i] that match pattern.
 * You may return the answer in any order.
 *
 * A word matches the pattern if there exists a permutation of letters p so that after
 * replacing every letter x in the pattern with p(x), we get the desired word.
 *
 * Recall that a permutation of letters is a bijection from letters to letters:
 * every letter maps to another letter, and no two letters map to the same letter.
 */

public class Solution {

    public List<String> findAndReplacePattern(String[] words, String pattern) {
        List<String> ans = new ArrayList<>();
        for (var word : words) {
            if (match(pattern, word)) {
                ans.add(word);
            }
        }
        return ans;
    }

    private boolean match(String pattern, String word) {
        if (word.length() != pattern.length()) return false;

        Set<Character> mapped = new HashSet<>();
        Map<Character, Character> map = new HashMap<>();
        for (int i = 0, n = pattern.length(); i < n; i++) {
            char l = pattern.charAt(i), r = word.charAt(i);
            if (!map.containsKey(l)) {
                if (mapped.contains(r)) return false;

                mapped.add(r);
                map.put(l, r);
            } else if (map.get(l) != r) return false;
        }

        return true;
    }

    public static void main(String[] args) {
        assert Checker.anyOrder(new Solution().findAndReplacePattern(new String[]{
            "abc","deq","mee","aqq","dkd","ccc"
        }, "abb"), List.of("mee","aqq"));

        assert Checker.anyOrder(new Solution().findAndReplacePattern(new String[]{
            "a","b","c"
        }, "a"), List.of("a","b","c"));
    }

}
