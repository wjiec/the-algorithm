package problem.p2452wordswithintwoeditsofdictionary;

import common.Checker;

import java.util.ArrayList;
import java.util.List;

/**
 * 2452. Words Within Two Edits of Dictionary
 *
 * https://leetcode.cn/problems/words-within-two-edits-of-dictionary/
 *
 * You are given two string arrays, queries and dictionary. All words in each array
 * comprise of lowercase English letters and have the same length.
 *
 * In one edit you can take a word from queries, and change any letter in it to any other letter.
 * Find all words from queries that, after a maximum of two edits, equal some word from dictionary.
 *
 * Return a list of all words from queries, that match with some word from dictionary
 * after a maximum of two edits. Return the words in the same order they appear in queries.
 */

public class Solution {

    public List<String> twoEditWords(String[] queries, String[] dictionary) {
        char[][] words = new char[dictionary.length][];
        for (int i = 0; i < words.length; i++) words[i] = dictionary[i].toCharArray();


        List<String> ans = new ArrayList<>();
        for (var query : queries) {
            char[] chars = query.toCharArray();
            for (var word : words) {
                int edits = 0;
                for (int i = 0; i < word.length; i++) {
                    if (word[i] != chars[i]) {
                        if (++edits > 2) break;
                    }
                }
                if (edits <= 2) { ans.add(query); break; }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().twoEditWords(
            new String[]{"word","note","ants","wood"},
            new String[]{"wood","joke","moat"}
        ), List.of("word","note","wood"));

        assert Checker.check(new Solution().twoEditWords(
            new String[]{"yes"},
            new String[]{"not"}
        ), List.of());
    }

}
