package problem.p648replacewords;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 648. Replace Words
 *
 * https://leetcode-cn.com/problems/replace-words/
 *
 * In English, we have a concept called root, which can be followed by some other word to form another longer word - let's call this word successor. For example, when the root "an" is followed by the successor word "other", we can form a new word "another".
 *
 * Given a dictionary consisting of many roots and a sentence consisting of words separated by spaces, replace all the successors in the sentence with the root forming it. If a successor can be replaced by more than one root, replace it with the root that has the shortest length.
 *
 * Return the sentence after the replacement.
 */

public class Solution {

    private static class Trie {
        private boolean leaf = false;
        private final Map<Character, Trie> children = new HashMap<>();
        public Trie get(char c) { return children.get(c); }
        public Trie set(char c) {
            if (!children.containsKey(c)) children.put(c, new Trie());
            return children.get(c);
        }
    }

    public String replaceWords(List<String> dictionary, String sentence) {
        Trie root = new Trie();
        for (var word : dictionary) {
            Trie curr = root;
            for (var c : word.toCharArray()) curr = curr.set(c);
            curr.leaf = true;
        }

        Trie curr = root;
        StringBuilder sb = new StringBuilder();
        for (int i = 0, n = sentence.length(); i < n; i++) {
            char c = sentence.charAt(i);
            if (c == ' ') curr = root;
            else if (curr != null) curr = curr.get(c);

            sb.append(c);
            if (curr != null && curr.leaf) {
                // 跳过剩下的字符
                while (i < n && sentence.charAt(i) != ' ') i++;
                if (i < n) i--; // skip space
            }
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        assert new Solution().replaceWords(
            List.of("cat","bat","rat"), "the cattle was rattled by the battery"
        ).equals("the cat was rat by the bat");

        assert new Solution().replaceWords(
            List.of("a","b","c"), "aadsfasf absbs bbab cadsfafs"
        ).equals("a a b c");
    }

}
