package weekly.w458.D;

import ability.Ability.UnionFind;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

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

/** @noinspection unchecked*/
public class Solution {

    // 找到图中一条重复点的路径, 使得组成的回文串最长
    //  1 <= n <= 14
    public int maxLen(int n, int[][] edges, String label) {
        Set<Integer>[] g = new Set[n];
        Arrays.setAll(g, i -> new HashSet<>());
        for (var edge : edges) { g[edge[0]].add(edge[1]); g[edge[1]].add(edge[0]); }

        // 只有最多 14 个节点, 枚举所有可能性, 检查是否对应的节点是否能组成一条路径且是回文串
        int ans = 1;
        char[] chars = label.toCharArray();
        for (int i = 1; i < (1 << n); i++) {
            if (check(n, g, i, chars)) {
                ans = Math.max(ans, Integer.bitCount(i));
            }
        }
        return ans;
    }

    // 检查是否能构成回文串
    private boolean check(int n, Set<Integer>[] g, int mask, char[] chars) {
        int b = Integer.bitCount(mask);
        byte[] group = new byte[128];
        boolean[] bitset = new boolean[n];
        for (int i = 0; i < n; i++) {
            if ((mask & (1 << i)) != 0) {
                group[chars[i]]++;
                bitset[i] = true;
            }
        }

        // 检查字符是否能构成回文序列
        int odd = 0;
        for (var v : group) odd += v & 1;
        if (odd > 1 || (odd & 1) != (b & 1)) return false;

        // 连接所有 bitset 的节点
        UnionFind uf = new UnionFind(n);
        for (int i = 0; i < n; i++) {
            if (!bitset[i]) continue;
            for (var j : g[i]) {
                if (bitset[j]) uf.union(i, j);
            }
        }

        // 检查所有节点是否都连接了
        int root = -1;
        for (int i = 0; i < n; i++) {
            if (bitset[i]) {
                if (root < 0) root = uf.find(i);
                else if (uf.find(i) != root) return false;
            }
        }

        // 检查能否构成回文串, 枚举中心节点
        if (odd == 1) {
            for (int i = 0; i < n; i++) {
                if (bitset[i] && (group[chars[i]] & 1) == 1) {
                    return connectivity(g, chars, bitset, i, i);
                }
            }
            return false;
        }

        // 偶数长度需要枚举双中心
        for (int i = 0; i < n; i++) {
            if (!bitset[i]) continue;
            for (int j = i + 1; j < n; j++) {
                if (!bitset[j] || !g[i].contains(j) || chars[i] != chars[j]) continue;
                if (connectivity(g, chars, bitset, i, j)) return true;
            }
        }
        return false;
    }

    private boolean connectivity(Set<Integer>[] g, char[] chars, boolean[] bitset, int l, int r) {
        return true;
    }

    public static void main(String[] args) {
    }

}
