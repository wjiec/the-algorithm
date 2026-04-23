package weekly.bw174.D;

import java.util.*;

/**
 * Q4. Minimum Edge Toggles on a Tree
 *
 * https://leetcode.cn/contest/biweekly-contest-174/problems/minimum-edge-toggles-on-a-tree/
 *
 * You are given an undirected tree with n nodes, numbered from 0 to n - 1.
 * It is represented by a 2D integer array edges of length n - 1, where edges[i] = [ai, bi]
 * indicates that there is an edge between nodes ai and bi in the tree.
 *
 * You are also given two binary strings start and target of length n. For each node x,
 * start[x] is its initial color and target[x] is its desired color.
 *
 * In one operation, you may pick an edge with index i and toggle both of its endpoints.
 * That is, if the edge is [u, v], then the colors of nodes u and v each flip from '0' to '1' or from '1' to '0'.
 *
 * Return an array of edge indices whose operations transform start into target.
 * Among all valid sequences with minimum possible length, return the edge indices in increasing order.
 *
 * If it is impossible to transform start into target, return an array containing a single element equal to -1.
 */

public class Solution {

    public List<Integer> minimumFlips(int n, int[][] edges, String start, String target) {
        // 一次翻转会修改两个节点的值, 如果错误是奇数的话, 则无法修复
        Set<Integer> wrongs = new HashSet<>();
        for (int i = 0; i < n; i++) if (start.charAt(i) != target.charAt(i)) wrongs.add(i);
        if ((wrongs.size() & 1) == 1) return List.of(-1);

        List<int[]>[] g = new List[n]; Arrays.setAll(g, i -> new ArrayList<>());
        for (int i = 0; i < edges.length; i++) {
            g[edges[i][0]].add(new int[]{edges[i][1], i});
            g[edges[i][1]].add(new int[]{edges[i][0], i});
        }

        boolean[] flips = new boolean[n];
        List<Integer> path = new ArrayList<>();
        if (wrongs.contains(0)) path.add(0);
        dfs(g, 0, -1, -1, wrongs, path, flips);

        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < flips.length; i++) {
            if (flips[i]) ans.add(i);
        }
        return ans;
    }

    private void dfs(List<int[]>[] g, int curr, int parent, int prev, Set<Integer> wrongs, List<Integer> p, boolean[] flips) {
        boolean currIsWrong = wrongs.contains(curr);
        if (prev != -1 && currIsWrong) {
            for (var v : p) flips[v] = !flips[v];
        }

        for (var next : g[curr]) {
            if (next[0] == parent) continue;
            p.add(next[1]);
            dfs(g, next[0], curr, currIsWrong ? curr : prev, wrongs, currIsWrong ? new ArrayList<>() : p, flips);
            p.remove(p.size() - 1);
        }
    }

    public static void main(String[] args) {
//        System.out.println(new Solution().minimumFlips(7, new int[][]{{3,0},{0,5},{4,2},{2,6},{5,1},{1,6}}, "1000101", "0110100"));

//        System.out.println(new Solution().minimumFlips(3, new int[][]{{0,1},{1,2}}, "010", "100"));
        System.out.println(new Solution().minimumFlips(7, new int[][]{{0,1},{1,2},{2,3},{3,4},{3,5},{1,6}}, "0011000", "0010001"));
        System.out.println(new Solution().minimumFlips(2, new int[][]{{0,1}}, "00", "01"));
    }

}
