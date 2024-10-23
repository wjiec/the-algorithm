package weekly.w420.D;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

/**
 * 3327. Check if DFS Strings Are Palindromes
 *
 * https://leetcode.cn/contest/weekly-contest-420/problems/check-if-dfs-strings-are-palindromes/
 *
 * You are given a tree rooted at node 0, consisting of n nodes numbered from 0 to n - 1.
 * The tree is represented by an array parent of size n, where parent[i] is the parent of node i.
 * Since node 0 is the root, parent[0] == -1.
 *
 * You are also given a string s of length n, where s[i] is the character assigned to node i.
 *
 * Consider an empty string dfsStr, and define a recursive function dfs(int x) that takes a
 * node x as a parameter and performs the following steps in order:
 *
 * Iterate over each child y of x in increasing order of their numbers, and call dfs(y).
 * Add the character s[x] to the end of the string dfsStr.
 * Note that dfsStr is shared across all recursive calls of dfs.
 *
 * You need to find a boolean array answer of size n, where for each index i from 0 to n - 1, you do the following:
 *
 * Empty the string dfsStr and call dfs(i).
 * If the resulting string dfsStr is a palindrome, then set answer[i] to true. Otherwise, set answer[i] to false.
 *
 * Return the array answer.
 */

public class Solution {

    private char[] chars = null;
    private final Map<Integer, TreeSet<Integer>> g = new HashMap<>();

    public boolean[] findAnswer(int[] parent, String s) {
        chars = s.toCharArray();
        for (int i = 0; i < parent.length; i++) {
            g.computeIfAbsent(parent[i], k -> new TreeSet<>()).add(i);
        }

        boolean[] ans = new boolean[s.length()];
        dfs(0, ans);

        return ans;
    }

    private String dfs(int node, boolean[] ans) {
        if (!g.containsKey(node)) {
            ans[node] = true;
            return String.valueOf(chars[node]);
        }

        var curr = new StringBuilder();
        for (var sub : g.get(node)) {
            curr.append(dfs(sub, ans));
        }
        curr.append(chars[node]);

        ans[node] = isPalindrome(curr.toString().toCharArray());
        return curr.toString();
    }

    private boolean isPalindrome(char[] chars) {
        for (int l = 0, r = chars.length - 1; l < r; l++, r--) {
            if (chars[l] != chars[r]) return false;
        }
        return true;
    }

    public static void main(String[] args) {
    }

}
