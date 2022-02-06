package daily.d210914plongestwordindictionarythroughdeleting;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 524. Longest Word in Dictionary through Deleting
 *
 * https://leetcode-cn.com/problems/longest-word-in-dictionary-through-deleting/
 *
 * Given a string s and a string array dictionary, return the longest string in the dictionary
 * that can be formed by deleting some of the given string characters.
 *
 * If there is more than one possible result, return the longest word with the smallest lexicographical order.
 * If there is no possible result, return the empty string.
 */

public class Solution {

    public String findLongestWord(String s, List<String> dictionary) {
        var sorted = dictionary.stream().filter((dict) -> dict.length() <= s.length())
            .sorted(Comparator.comparingInt(String::length).reversed().thenComparing(Function.identity()))
            .collect(Collectors.toList());

        for (var dict : sorted) {
            int dictIdx = 0;
            for (int i = 0; i < s.length() && dictIdx < dict.length(); i++) {
                if (s.charAt(i) == dict.charAt(dictIdx)) {
                    dictIdx++;
                }
            }

            if (dictIdx == dict.length()) return dict;
        }
        return "";
    }

    public static void main(String[] args) {
        assert new Solution().findLongestWord("abpcplea", new ArrayList<>(List.of("ale","apple","monkey","plea"))).equals("apple");
        assert new Solution().findLongestWord("abpcplea", new ArrayList<>(List.of("a", "b", "c"))).equals("a");
    }

}
