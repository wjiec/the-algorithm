package problem.p472concatenatedwords;

import ability.Ability.AlphaTrie;
import common.PrettyPrinter;

import java.util.*;

/**
 * 472. Concatenated Words
 *
 * https://leetcode.cn/problems/concatenated-words/
 *
 * Given an array of strings words (without duplicates), return
 * all the concatenated words in the given list of words.
 *
 * A concatenated word is defined as a string that is comprised
 * entirely of at least two shorter words in the given array.
 */

public class Solution {

    private final Set<String> visited = new HashSet<>();

    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        Arrays.sort(words, Comparator.comparingInt(String::length));

        List<String> ans = new ArrayList<>();
        for (var word : words) {
            visited.add(word);
            if (check(word) > 1) ans.add(word);
        }
        return ans;
    }

    private final Map<String, Integer> memo = new HashMap<>();

    private int check(String word) {
        int n = word.length();
        if (!memo.containsKey(word)) {
            int ans = visited.contains(word) ? 1 : 0;
            for (int i = 1; i <= n - 1; i++) {
                int l = check(word.substring(0, i));
                int r = check(word.substring(i));
                if (l != 0 && r != 0) ans = Math.max(ans, l + r);
            }

            memo.put(word, ans);
        }
        return memo.get(word);
    }

    private static class ByTrie {
        private final AlphaTrie root = new AlphaTrie();

        public List<String> findAllConcatenatedWordsInADict(String[] words) {
            Arrays.sort(words, Comparator.comparingInt(String::length));


            List<String> ans = new ArrayList<>();
            for (var word : words) {
                if (word.length() == 0) continue;

                boolean[] visited = new boolean[word.length()];
                if (dfs(word.toCharArray(), 0, visited)) ans.add(word);
                else root.set(word).asLeaf();
            }
            return ans;
        }

        private boolean dfs(char[] chars, int i, boolean[] visited) {
            if (i == chars.length) return true;
            if (visited[i]) return false;

            visited[i] = true;
            AlphaTrie trie = root;
            for (; i < chars.length; i++) {
                trie = trie.get(chars[i]);
                if (trie == null) return false;
                if (trie.isLeaf() && dfs(chars, i + 1, visited)) return true;
            }
            return false;
        }
    }

    public static void main(String[] args) {
        PrettyPrinter.println(new Solution().findAllConcatenatedWordsInADict(new String[]{
            "a","b","ab", "abc"
        }));

        PrettyPrinter.println(new Solution().findAllConcatenatedWordsInADict(new String[]{
            "cat","cats","catsdogcats","dog","dogcatsdog","hippopotamuses","rat","ratcatdogcat"
        }));

        PrettyPrinter.println(new Solution().findAllConcatenatedWordsInADict(new String[]{
            "cat","dog","catdog"
        }));


        PrettyPrinter.println(new ByTrie().findAllConcatenatedWordsInADict(new String[]{
            "a","b","ab", "abc"
        }));
        PrettyPrinter.println(new ByTrie().findAllConcatenatedWordsInADict(new String[]{
            "cat","cats","catsdogcats","dog","dogcatsdog","hippopotamuses","rat","ratcatdogcat"
        }));
        PrettyPrinter.println(new ByTrie().findAllConcatenatedWordsInADict(new String[]{
            "cat","dog","catdog"
        }));
    }

}
