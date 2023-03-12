package problem.p336palindromepairs;

import common.PrettyPrinter;

import java.util.ArrayList;
import java.util.List;

public class Solution {

    private static class Trie {
        private int index = -1;
        private final Trie[] children = new Trie[26];
        public Trie get(char c) { return children[c - 'a']; }
        public Trie set(char c) {
            if (children[c - 'a'] == null) {
                children[c - 'a'] = new Trie();
            }
            return children[c - 'a'];
        }
    }

    public List<List<Integer>> palindromePairs(String[] words) {
        Trie trie = new Trie();
        for (int i = 0; i < words.length; i++) {
            Trie curr = trie;
            for (var c : words[i].toCharArray()) {
                curr = curr.set(c);
            }
            curr.index = i;
        }

        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < words.length; i++) {
            int len = words[i].length();
            char[] chars = words[i].toCharArray();
            for (int r = 0; r <= len; r++) {
                if (isPalindrome(chars, r, len - 1)) {
                    int j = backFind(trie, chars, 0, r - 1);
                    if (j != -1 && j != i) ans.add(List.of(i, j));
                }
                if (r != 0 && isPalindrome(chars, 0, r - 1)) {
                    int j = backFind(trie, chars, r, len - 1);
                    if (j != -1 && j != i) ans.add(List.of(j, i));
                }
            }
        }
        return ans;
    }

    // l <= i <= r
    private int backFind(Trie trie, char[] chars, int l, int r) {
        while (trie != null && l <= r) trie = trie.get(chars[r--]);
        return trie == null ? -1 : trie.index;
    }

    // l <= i <= r
    private boolean isPalindrome(char[] chars, int l, int r) {
        while (l < r && chars[l] == chars[r]) { l++; r--; }
        return l >= r;
    }

    public static void main(String[] args) {
        PrettyPrinter.println(new Solution().palindromePairs(new String[]{
            "abcd","dcba","lls","s","sssll"
        }));

        PrettyPrinter.println(new Solution().palindromePairs(new String[]{
            "bat","tab","cat"
        }));

        PrettyPrinter.println(new Solution().palindromePairs(new String[]{
            "a",""
        }));
    }

}
