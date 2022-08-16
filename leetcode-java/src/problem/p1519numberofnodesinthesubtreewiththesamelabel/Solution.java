package problem.p1519numberofnodesinthesubtreewiththesamelabel;

import common.Checker;

import java.util.*;

/**
 * 1519. Number of Nodes in the Sub-Tree With the Same Label
 *
 * https://leetcode.cn/problems/number-of-nodes-in-the-sub-tree-with-the-same-label/
 *
 * You are given a tree (i.e. a connected, undirected graph that has no cycles) consisting of n nodes
 * numbered from 0 to n - 1 and exactly n - 1 edges. The root of the tree is the node 0, and each
 * node of the tree has a label which is a lower-case character given in the string labels
 * (i.e. The node with the number i has the label labels[i]).
 *
 * The edges array is given on the form edges[i] = [ai, bi], which means there is an edge
 * between nodes ai and bi in the tree.
 *
 * Return an array of size n where ans[i] is the number of nodes in the subtree of the ith node
 * which have the same label as node i.
 *
 * A subtree of a tree T is the tree consisting of a node in T and all of its descendant nodes.
 */

public class Solution {

    private final Map<Integer, Set<Integer>> map = new HashMap<>();

    public int[] countSubTrees(int n, int[][] edges, String labels) {
        int[] ans = new int[n];
        Arrays.fill(ans, -1);

        for (var edge : edges) {
            map.computeIfAbsent(edge[0], v -> new HashSet<>()).add(edge[1]);
            map.computeIfAbsent(edge[1], v -> new HashSet<>()).add(edge[0]);
        }

        dfs(labels.toCharArray(), ans, 0);
        return ans;
    }

    private int[] dfs(char[] labels, int[] ans, int curr) {
        int[] count = new int[26];
        Set<Integer> children = map.get(curr);
        if (children != null) {
            for (var next : children) {
                if (ans[next] == -1) {
                    ans[curr] = Integer.MAX_VALUE;
                    int[] r = dfs(labels, ans, next);
                    for (int i = 0; i < 26; i++) count[i] += r[i];
                }
            }
        }

        ans[curr] = ++count[labels[curr] - 'a'];
        return count;
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().countSubTrees(7, new int[][]{
            {0,1},{0,2},{1,4},{1,5},{2,3},{2,6}
        }, "abaedcd"), new int[]{2,1,1,1,1,1,1});

        assert Checker.check(new Solution().countSubTrees(4, new int[][]{
            {0,1},{1,2},{0,3}
        }, "bbbb"), new int[]{4,2,1,1});

        Checker.check(new Solution().countSubTrees(5, new int[][]{
            {0,1},{0,2},{1,3},{0,4}
        }, "aabab"), new int[]{3,2,1,1,1});
    }

}
