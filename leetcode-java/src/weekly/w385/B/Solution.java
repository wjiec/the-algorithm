package weekly.w385.B;

/**
 * 3043. Find the Length of the Longest Common Prefix
 *
 * https://leetcode.cn/contest/weekly-contest-385/problems/find-the-length-of-the-longest-common-prefix/
 *
 * You are given two arrays with positive integers arr1 and arr2.
 *
 * A prefix of a positive integer is an integer formed by one or more of its
 * digits, starting from its leftmost digit. For example, 123 is a prefix of
 * the integer 12345, while 234 is not.
 *
 * A common prefix of two integers a and b is an integer c, such that c is a
 * prefix of both a and b. For example, 5655359 and 56554 have a common
 * prefix 565 while 1223 and 43456 do not have a common prefix.
 *
 * You need to find the length of the longest common prefix between all
 * pairs of integers (x, y) such that x belongs to arr1 and y belongs to arr2.
 *
 * Return the length of the longest common prefix among all pairs.
 *
 * If no common prefix exists among them, return 0.
 */

public class Solution {

    private static class Trie {
        public final Trie[] children = new Trie[10];
        public Trie next(int b) { return children[b] == null ? (children[b] = new Trie()) : children[b]; }
        public Trie get(int b) { return children[b]; }
    }

    public int longestCommonPrefix(int[] arr1, int[] arr2) {
        Trie root = new Trie();
        for (var v : arr1) {
            Trie curr = root;
            for (var b : split(v)) {
                curr = curr.next(b);
            }
        }

        int ans = 0;
        for (var v : arr2) {
            Trie curr = root; int level = 0;
            for (var b : split(v)) {
                if (curr.get(b) != null) {
                    level++;
                    curr = curr.next(b);
                } else break;
            }
            ans = Math.max(ans, level);
        }
        return ans;
    }

    private int[] split(int v) {
        int[] ans = new int[String.valueOf(v).length()];
        for (int i = ans.length - 1; i >= 0; i--) {
            ans[i] = v % 10; v /= 10;
        }
        return ans;
    }

    public static void main(String[] args) {
    }

}
