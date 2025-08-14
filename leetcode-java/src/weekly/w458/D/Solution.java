package weekly.w458.D;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Q4. Longest Palindromic Path in Graph
 *
 * https://leetcode.cn/contest/weekly-contest-458/problems/longest-palindromic-path-in-graph/
 *
 * You are given an integer n and an undirected graph with n nodes labeled from 0 to n - 1
 * and a 2D array edges, where edges[i] = [ui, vi] indicates an edge between nodes ui and vi.
 *
 * You are also given a string label of length n, where label[i] is the character associated with node i.
 *
 * You may start at any node and move to any adjacent node, visiting each node at most once.
 *
 * Return the maximum possible length of a palindrome that can be formed by
 * visiting a set of unique nodes along a valid path.
 */

/** @noinspection unchecked, DuplicatedCode */
public class Solution {

    // 找到图中一条重复点的路径, 使得组成的回文串最长
    //  1 <= n <= 14
    public int maxLen(int n, int[][] edges, String label) {
        // 枚举回文中心计算找到可能的最大值
        char[] chars = label.toCharArray();
        int[] count = new int[26];
        for (var c : chars) count[c - 'a']++;

        // 计算奇数对的数量
        int oddPair = 0;
        for (var v : count) oddPair += v & 1;

        // 我们可以选择一个奇数对放在中心, 丢弃其他的奇数对, 然后将偶数对拼在两边
        int limited = n - Math.max(oddPair - 1 /* 丢弃掉的奇数对 */, 0);
        // 如果图中的节点两两相联的话, 那么可以得到理论最大数量
        if (edges.length == n * (n - 1) / 2) return limited;

        List<Integer>[] g = new List[n];
        Arrays.setAll(g, i -> new ArrayList<>());
        for (var edge : edges) {
            g[edge[0]].add(edge[1]);
            g[edge[1]].add(edge[0]);
        }

        int[][][] memo = new int[n][n][1 << n];
        for (var a : memo) for (var b : a) Arrays.fill(b, -1);

        int ans = 0;
        for (int l = 0; l < n; l++) {
            // 计算奇数长度的回文串
            ans = Math.max(ans, dfs(l, l, 1 << l, g, chars, memo) + 1);
            if (ans == limited) return ans;

            // 计算偶数长度的回文串, 保证 l < r, 因为 (l, r) 和 (r, l) 实际计算出来的结果是一样的
            for (var r : g[l]) {
                if (l < r && chars[l] == chars[r]) {
                    ans = Math.max(ans, dfs(l, r, 1 << l | 1 << r, g, chars, memo) + 2);
                    if (ans == limited) return ans;
                }
            }
        }
        return ans;
    }

    private int dfs(int l, int r, int mask, List<Integer>[] g, char[] chars, int[][][] memo) {
        if (memo[l][r][mask] != -1) return memo[l][r][mask];

        int ans = 0;
        for (int ll : g[l]) {
            // ll 这个点已存在在路径中
            if ((mask & (1 << ll)) != 0) continue;
            for (int rr : g[r]) {
                // rr 这个点已存在在路径中, 或者 rr == ll, 或者字符不同
                if ((mask & (1 << rr)) != 0 || ll == rr || chars[ll] != chars[rr]) continue;
                ans = Math.max(ans, dfs(Math.min(ll, rr), Math.max(ll, rr), mask | (1 << ll) | (1 << rr), g, chars, memo) + 2);
            }
        }
        return memo[l][r][mask] = ans;
    }

    public static void main(String[] args) {
    }

}
