package weekly.w390.D;

import common.Checker;

/**
 * 3093.
 *
 * https://leetcode.cn/contest/weekly-contest-390/problems/longest-common-suffix-queries/
 *
 * You are given two arrays of strings wordsContainer and wordsQuery.
 *
 * For each wordsQuery[i], you need to find a string from wordsContainer that has the longest
 * common suffix with wordsQuery[i]. If there are two or more strings in wordsContainer that
 * share the longest common suffix, find the string that is the smallest in length.
 *
 * If there are two or more such strings that have the same smallest length, find the one that
 * occurred earlier in wordsContainer.
 *
 * Return an array of integers ans, where ans[i] is the index of the string in wordsContainer
 * that has the longest common suffix with wordsQuery[i].
 */

public class Solution {

    private static class Trie {
        private int minIndex = Integer.MAX_VALUE;
        private int minLength = Integer.MAX_VALUE;
        private final Trie[] children = new Trie[26];
        public Trie get(char c) { return children[c - 'a']; }
        public Trie set(char c) { return get(c) == null ? (children[c - 'a'] = new Trie()) : get(c); }
    }

    public int[] stringIndices(String[] wordsContainer, String[] wordsQuery) {
        Trie root = new Trie();
        int fallback = 0, cnt = Integer.MAX_VALUE;
        for (int i = wordsContainer.length - 1; i >= 0; i--) {
            char[] chars = wordsContainer[i].toCharArray();
            if (chars.length <= cnt) { cnt = chars.length; fallback = i; }

            Trie curr = root;
            for (int j = chars.length - 1; j >= 0; j--) {
                curr = curr.set(chars[j]);
                if (chars.length <= curr.minLength) {
                    curr.minLength = chars.length;
                    curr.minIndex = i;
                }
            }
        }

        int[] ans = new int[wordsQuery.length];
        next:
        for (int i = 0; i < wordsQuery.length; i++) {
            ans[i] = fallback;
            Trie curr = root;
            char[] chars = wordsQuery[i].toCharArray();

            for (int j = chars.length - 1; j >= 0; j--) {
                Trie next = curr.get(chars[j]);
                if (next == null) continue next;
                if (next.minIndex != Integer.MAX_VALUE) {
                    ans[i] = next.minIndex;
                }
                curr = next;
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().stringIndices(new String[]{"abcd","bcd","xbcd"}, new String[]{"cd","bcd","xyz"}), new int[]{1,1,1});
        assert Checker.check(new Solution().stringIndices(new String[]{"abcdefgh","poiuygh","ghghgh"}, new String[]{"gh","acbfgh","acbfegh"}), new int[]{2,0,2});
    }

}
