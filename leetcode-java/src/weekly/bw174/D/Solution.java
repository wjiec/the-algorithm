package weekly.bw174.D;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

@SuppressWarnings("unchecked")
public class Solution {

    public List<Integer> minimumFlips(int n, int[][] edges, String start, String target) {
        // 一次翻转会修改两个节点的值, 如果错误是奇数的话, 则无法修复
        int wrongs = 0; char[] s = start.toCharArray(), t = target.toCharArray();
        for (int i = 0; i < n; i++) if (s[i] != t[i]) wrongs++;
        if ((wrongs & 1) == 1) return List.of(-1);

        // 如果叶子节点需要修复的话, 那么一定需要翻转叶子节点, 同时也会导致连接叶子节点的父节点也会被翻转
        List<int[]>[] g = new List[n]; Arrays.setAll(g, i -> new ArrayList<>());
        for (int i = 0; i < edges.length; i++) {
            int a = edges[i][0], b = edges[i][1];
            g[a].add(new int[]{b, i});
            g[b].add(new int[]{a, i});
        }

        // 从叶子节点倒序枚举即可
        List<Integer> ans = new ArrayList<>();
        dfs(g, 0, -1, s, t, ans);
        ans.sort(Integer::compare);
        return ans;
    }

    // 当前在 curr 节点, 检查 parent -> curr 这条边是否需要翻转
    private boolean dfs(List<int[]>[] g, int curr, int parent, char[] s, char[] t, List<Integer> ans) {
        boolean reverse = s[curr] != t[curr];
        for (var pair : g[curr]) {
            int next = pair[0], i = pair[1];
            if (next == parent) continue;

            // 如果子节点需要翻转, 那么当前节点也会被翻转
            if (dfs(g, next, curr, s, t, ans)) {
                ans.add(i); reverse = !reverse;
            }
        }
        return reverse;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().minimumFlips(7, new int[][]{{3,0},{0,5},{4,2},{2,6},{5,1},{1,6}}, "1000101", "0110100"));

        System.out.println(new Solution().minimumFlips(3, new int[][]{{0,1},{1,2}}, "010", "100"));
        System.out.println(new Solution().minimumFlips(7, new int[][]{{0,1},{1,2},{2,3},{3,4},{3,5},{1,6}}, "0011000", "0010001"));
        System.out.println(new Solution().minimumFlips(2, new int[][]{{0,1}}, "00", "01"));
    }

}
