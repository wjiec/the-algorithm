package weekly.bw115.C;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 2901. Longest Unequal Adjacent Groups Subsequence II
 *
 * https://leetcode.cn/contest/biweekly-contest-115/problems/longest-unequal-adjacent-groups-subsequence-ii/
 *
 * You are given an integer n, a 0-indexed string array words, and a 0-indexed
 * array groups, both arrays having length n.
 *
 * The hamming distance between two strings of equal length is the number of positions
 * at which the corresponding characters are different.
 *
 * You need to select the longest subsequence from an array of indices [0, 1, ..., n - 1], such
 * that for the subsequence denoted as [i0, i1, ..., ik - 1] having length k, the following holds:
 *
 * For adjacent indices in the subsequence, their corresponding groups are unequal, i.e.,
 * groups[ij] != groups[ij + 1], for each j where 0 < j + 1 < k.
 * words[ij] and words[ij + 1] are equal in length, and the hamming distance between them is 1,
 * where 0 < j + 1 < k, for all indices in the subsequence.
 * Return a string array containing the words corresponding to the indices (in order) in the
 * selected subsequence. If there are multiple answers, return any of them.
 *
 * A subsequence of an array is a new array that is formed from the original array by
 * deleting some (possibly none) of the elements without disturbing the relative
 * positions of the remaining elements.
 *
 * Note: strings in words may be unequal in length.
 */

public class Solution {

    private String[] words = null;
    private boolean[][] checks = null;
    private Map<Integer, List<Integer>> g = new HashMap<>();

    public List<String> getWordsInLongestSubsequence(int n, String[] words, int[] groups) {
        this.words = words;
        checks = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            char[] s1 = words[i].toCharArray();
            for (int j = i + 1; j < n; j++) {
                char[] s2 = words[j].toCharArray();
                if (s1.length == s2.length) {
                    checks[i][j] = hamming(s1, s2) && groups[i] != groups[j];
                }
            }
            g.computeIfAbsent(words[i].length(), v -> new ArrayList<>()).add(i);
        }

        List<Integer> idx = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            List<Integer> curr = dfs(i);
            if (curr.size() > idx.size()) idx = curr;
        }

        List<String> ans = new ArrayList<>();
        for (var i : idx) ans.add(words[i]);

        return ans;
    }

    private final List<Integer>[] memo = new List[1001];

    private List<Integer> dfs(int node) {
        if (memo[node] == null) {
            List<Integer> ans = new ArrayList<>();
            for (var next : g.get(words[node].length())) {
                if (next > node && checks[node][next]) {
                    List<Integer> curr = dfs(next);
                    if (curr.size() > ans.size()) {
                        ans = curr;
                    }
                }
            }
            memo[node] = new ArrayList<>();
            memo[node].add(node);
            memo[node].addAll(ans);
        }
        return memo[node];
    }

    private boolean hamming(char[] a, char[] b) {
        int distance = 0;
        for (int i = 0; i < a.length; i++) {
            if (a[i] != b[i]) {
                if (++distance > 1) break;
            }
        }
        return distance == 1;
    }

    public static void main(String[] args) {
    }

}
