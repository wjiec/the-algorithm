package weekly.w420.D;

import ability.Palindrome.Manacher;
import common.Checker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    /** @noinspection unchecked*/
    public boolean[] findAnswer(int[] parent, String s) {
        int n = parent.length;
        chars = s.toCharArray();
        List<Integer>[] g = new List[n];
        Arrays.setAll(g, i -> new ArrayList<>());
        for (int i = 0; i < n; i++)
            if (parent[i] >= 0) g[parent[i]].add(i);

        int[][] ranges = new int[n][2];
        StringBuilder sb = new StringBuilder();
        dfs(g, 0, sb, ranges);

        Manacher m = new Manacher(sb.toString());

        boolean[] ans = new boolean[n];
        for (int i = 0; i < n; i++) {
            ans[i] = m.isPalindrome(ranges[i][0], ranges[i][1]);
        }

        return ans;
    }

    private int time = 0;

    private void dfs(List<Integer>[] g, int node, StringBuilder sb, int[][] ranges) {
        ranges[node][0] = time;
        for (var next : g[node]) {
            dfs(g, next, sb, ranges);
        }

        ranges[node][1] = time++;
        sb.append(chars[node]);
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().findAnswer(new int[]{-1,0,0,1,1,2}, "aababa"), new boolean[]{true,true,false,true,true,true});
    }

}
