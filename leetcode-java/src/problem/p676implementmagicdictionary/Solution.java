package problem.p676implementmagicdictionary;

import java.util.*;

/**
 * 676. Implement Magic Dictionary
 *
 * https://leetcode-cn.com/problems/implement-magic-dictionary/
 *
 * Design a data structure that is initialized with a list of different words. Provided a string,
 * you should determine if you can change exactly one character in this string to
 * match any word in the data structure.
 *
 * Implement the MagicDictionary class:
 *
 * MagicDictionary() Initializes the object.
 *
 * void buildDict(String[] dictionary) Sets the data structure with an array of distinct strings dictionary.
 *
 * bool search(String searchWord) Returns true if you can change exactly one character
 * in searchWord to match any string in the data structure, otherwise returns false.
 */

public class Solution {

    private static class MagicDictionary {
        private final Set<String> words = new HashSet<>();
        private final Set<Integer> lens = new HashSet<>();
        private final Map<String, Integer> map = new HashMap<>();
        public MagicDictionary() {}

        public void buildDict(String[] dictionary) {
            for (var word : dictionary) {
                words.add(word);
                lens.add(word.length());

                for (var neighbor : neighbors(word)) {
                    map.merge(neighbor, 1, Integer::sum);
                }
            }
        }

        public boolean search(String searchWord) {
            if (!lens.contains(searchWord.length())) return false;
            for (var neighbor : neighbors(searchWord)) {
                int count = map.getOrDefault(neighbor, 0);
                if (count > 1 || (count == 1 && !words.contains(searchWord))) {
                    return true;
                }
            }
            return false;
        }

        private List<String> neighbors(String s) {
            List<String> list = new ArrayList<>();
            StringBuilder sb = new StringBuilder(s);
            for (int i = 0, n = s.length(); i < n; i++) {
                char c = sb.charAt(i);
                sb.setCharAt(i, '*');
                list.add(sb.toString());
                sb.setCharAt(i, c);
            }
            return list;
        }
    }

    public static void main(String[] args) {
        MagicDictionary magicDictionary = new MagicDictionary();
        magicDictionary.buildDict(new String[]{"hello", "leetcode"});
        assert !magicDictionary.search("hello");
        assert magicDictionary.search("hhllo");
        assert !magicDictionary.search("hell");
        assert !magicDictionary.search("leetcoded");

        magicDictionary = new MagicDictionary();
        magicDictionary.buildDict(new String[]{"hello","hallo","leetcode"});
        assert magicDictionary.search("hello"); // hallo
        assert magicDictionary.search("hhllo");
        assert !magicDictionary.search("hell");
        assert !magicDictionary.search("leetcoded");
    }

}
