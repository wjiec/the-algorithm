package problem.p139wordbreak;

import java.util.*;

/**
 * 139. Word Break
 *
 * https://leetcode-cn.com/problems/word-break/
 *
 * Given a string s and a dictionary of strings wordDict, return true if s can be segmented into a space-separated
 * sequence of one or more dictionary words.
 *
 * Note that the same word in the dictionary may be reused multiple times in the segmentation.
 */

public class Solution {

    private static class DictTree {
        private final char c;
        private boolean finished;
        private final Map<Character, DictTree> children;

        public DictTree(char c) { this.c = c; children = new HashMap<>(); }
        public void insert(String s) {
            DictTree curr = this;
            for (var c : s.toCharArray()) {
                curr.children.putIfAbsent(c, new DictTree(c));
                curr = curr.children.get(c);
            }
            curr.finished = true;
        }
        public boolean contains(char[] chars, int l, int r) {
            DictTree curr = this;
            for (int i = l; i <= r; i++) {
                if (!curr.children.containsKey(chars[i])) {
                    return false;
                }
                curr = curr.children.get(chars[i]);
            }
            return curr.finished;
        }
    }

    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> set = new HashSet<>(wordDict);
        boolean[] dp = new boolean[s.length() + 1]; dp[0] = true;
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && set.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }

    public boolean wordBreakByDictTree(String s, List<String> wordDict) {
        DictTree tree = new DictTree(' ');
        for (var word : wordDict) tree.insert(word);
        return dfs(s.toCharArray(), tree, 0);
    }

    private boolean dfs(char[] chars, DictTree tree, int i) {
        if (i == chars.length) return true;
        for (int j = i; j < chars.length; j++) {
            if (tree.contains(chars, i, j)) {
                if (dfs(chars, tree, j + 1)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        assert new Solution().wordBreak("leetcode", List.of("leet", "code"));
        assert new Solution().wordBreak("applepenapple", List.of("apple", "pen"));
        assert !new Solution().wordBreak("catsandog", List.of("cats", "dog", "sand", "and", "cat"));

        assert new Solution().wordBreakByDictTree("leetcode", List.of("leet", "code"));
        assert new Solution().wordBreakByDictTree("applepenapple", List.of("apple", "pen"));
        assert !new Solution().wordBreakByDictTree("catsandog", List.of("cats", "dog", "sand", "and", "cat"));
    }

}
