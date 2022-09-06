package problem.p288uniquewordabbreviation;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 288. Unique Word Abbreviation
 *
 * https://leetcode.cn/problems/unique-word-abbreviation/
 *
 * The abbreviation of a word is a concatenation of its first letter, the number of characters between the
 * first and last letter, and its last letter. If a word has only two characters, then it is an abbreviation
 * of itself.
 *
 * For example:
 *
 * dog --> d1g because there is one letter between the first letter 'd' and the last letter 'g'.
 * internationalization --> i18n because there are 18 letters between the first letter 'i' and the last letter 'n'.
 * it --> it because any word with only two characters is an abbreviation of itself.
 * Implement the ValidWordAbbr class:
 *
 * ValidWordAbbr(String[] dictionary) Initializes the object with a dictionary of words.
 * boolean isUnique(string word) Returns true if either of the following conditions are met (otherwise returns false):
 * There is no word in dictionary whose abbreviation is equal to word's abbreviation.
 * For any word in dictionary whose abbreviation is equal to word's abbreviation, that word and word are the same.
 */

public class Solution {

    private static class ValidWordAbbr {
        private final Map<String, Set<String>> map = new HashMap<>();
        public ValidWordAbbr(String[] dictionary) {
            for (var word : dictionary) {
                map.computeIfAbsent(abbr(word), v -> new HashSet<>()).add(word);
            }
        }

        public boolean isUnique(String word) {
            Set<String> ws = map.get(abbr(word));
            return ws == null || (ws.size() == 1 && ws.contains(word));
        }

        private String abbr(String word) {
            if (word.length() <= 2) return word;
            return String.valueOf(word.charAt(0))
                + (word.length() - 2)
                + word.charAt(word.length() - 1);
        }
    }

    public static void main(String[] args) {
        ValidWordAbbr validWordAbbr = new ValidWordAbbr(new String[]{"deer", "door", "cake", "card"});
        assert !validWordAbbr.isUnique("dear");
        assert validWordAbbr.isUnique("cart");
        assert !validWordAbbr.isUnique("cane");
        assert validWordAbbr.isUnique("make");
        assert validWordAbbr.isUnique("cake");
    }

}
