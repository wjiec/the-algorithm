package problem.p290wordpattern;

import java.util.HashMap;
import java.util.Map;

/**
 * 290. Word Pattern
 *
 * https://leetcode-cn.com/problems/word-pattern/
 *
 * Given a pattern and a string s, find if s follows the same pattern.
 *
 * Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty word in s.
 */

public class Solution {

    public boolean wordPattern(String pattern, String s) {
        int index = 0, sz = pattern.length();
        String[] words = s.split("\\s+");
        if (words.length != sz) {
            return false;
        }

        Map<Character, String> mapping = new HashMap<>();
        Map<String, Character> backMapping = new HashMap<>();
        for (var word : words) {
            char c = pattern.charAt(index++);
            String val = mapping.get(c);
            if (val == null) {
                mapping.put(c, word);
                backMapping.put(word, c);
            } else if (!val.equals(word) || backMapping.get(word) != c) {
                return false;
            }

            if (index == sz) {
                index = 0;
            }
        }
        return mapping.size() == backMapping.size();
    }

    public static void main(String[] args) {
        assert !new Solution().wordPattern("abc", "dog cat dog");
        assert new Solution().wordPattern("abba", "dog cat cat dog");
        assert !new Solution().wordPattern("abba", "dog cat cat fish");
        assert !new Solution().wordPattern("aaaa", "dog cat cat dog");
        assert !new Solution().wordPattern("abba", "dog dog dog dog");
    }

}
