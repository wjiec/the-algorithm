package problem.p820shortencodingofwords;

import java.util.HashMap;
import java.util.Map;

/**
 * 820. Short Encoding of Words
 *
 * https://leetcode.cn/problems/short-encoding-of-words/
 *
 * A valid encoding of an array of words is any reference string s and array of indices indices such that:
 *
 * words.length == indices.length
 *
 * The reference string s ends with the '#' character.
 *
 * For each index indices[i], the substring of s starting from indices[i]
 * and up to (but not including) the next '#' character is equal to words[i].
 *
 * Given an array of words, return the length of the shortest
 * reference string s possible of any valid encoding of words.
 */

public class Solution {

    private static class Trie {
        private int count = 0;
        private final Trie[] map = new Trie[26];
        private Trie get(char c) {
            if (map[c - 'a'] == null) {
                map[c - 'a'] = new Trie();
                count++;
            }
            return map[c - 'a'];
        }
    }

    public int minimumLengthEncoding(String[] words) {
        Trie root = new Trie();
        Map<Trie, Integer> map = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            Trie curr = root;
            String word = words[i];
            for (int j = word.length() - 1; j >= 0; j--) {
                curr = curr.get(word.charAt(j));
            }
            map.put(curr, i);
        }

        int ans = 0;
        for (var kv : map.entrySet()) {
            if (kv.getKey().count == 0) ans += words[kv.getValue()].length() + 1;
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().minimumLengthEncoding(new String[]{"time", "me", "bell"}) == 10;
        assert new Solution().minimumLengthEncoding(new String[]{"t"}) == 2;
    }

}
