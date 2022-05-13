package problem.p792numberofmatchingsubsequences;

import java.util.ArrayList;

/**
 * 792. Number of Matching Subsequences
 *
 * https://leetcode.cn/problems/number-of-matching-subsequences/
 *
 * Given a string s and an array of strings words, return the number of words[i] that is a subsequence of s.
 *
 * A subsequence of a string is a new string generated from the original string
 * with some characters (can be none) deleted without changing the
 * relative order of the remaining characters.
 *
 * For example, "ace" is a subsequence of "abcde".
 */

@SuppressWarnings("unchecked")
public class Solution {

    private static class Node {
        private int index = 0;
        private final String word;
        public Node(String word) { this.word = word; }
        public boolean hasNext() { return ++index != word.length(); }
        public char next() { return word.charAt(index); }
    }

    public int numMatchingSubseq(String s, String[] words) {
        ArrayList<Node>[] buckets = new ArrayList[26];
        for (int i = 0; i < buckets.length; i++) buckets[i] = new ArrayList<>();
        for (var word : words) buckets[word.charAt(0) - 'a'].add(new Node(word));

        int ans = 0;
        for (var c : s.toCharArray()) {
            ArrayList<Node> currBucket = buckets[c - 'a'];
            buckets[c - 'a'] = new ArrayList<>();

            for (var node : currBucket) {
                if (node.hasNext()) buckets[node.next() - 'a'].add(node);
                else ans++;
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().numMatchingSubseq("abcde", new String[]{
            "a","bb","acd","ace"
        }) == 3;

        assert new Solution().numMatchingSubseq("dsahjpjauf", new String[]{
            "ahjpjau","ja","ahbwzgqnuk","tnmlanowax"
        }) == 2;
    }

}
