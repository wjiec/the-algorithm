package weekly.bw154.D;

import java.util.HashMap;
import java.util.Map;

/**
 * 3515. Shortest Path in a Weighted Tree
 *
 * https://leetcode.cn/contest/biweekly-contest-154/problems/shortest-path-in-a-weighted-tree/
 *
 * You are given an integer n and an undirected, weighted tree rooted at node 1 with n nodes
 * numbered from 1 to n. This is represented by a 2D array edges of length n - 1, where
 * edges[i] = [ui, vi, wi] indicates an undirected edge from node ui to vi with weight wi.
 *
 * You are also given a 2D integer array queries of length q, where each queries[i] is either:
 *
 * [1, u, v, w'] – Update the weight of the edge between nodes u and v to w',
 * where (u, v) is guaranteed to be an edge present in edges.
 *
 * [2, x] – Compute the shortest path distance from the root node 1 to node x.
 *
 * Return an integer array answer, where answer[i] is the shortest path distance
 * from node 1 to x for the ith query of [2, x].
 */

public class Solution {

    public int[] treeQueries(int n, int[][] edges, int[][] queries) {
        Map<Integer, Map<Integer, Integer>> g = new HashMap<>();
        for (var edge : edges) {
            g.computeIfAbsent(edge[0], k -> new HashMap<>()).put(edge[1], edge[2]);
            g.computeIfAbsent(edge[1], k -> new HashMap<>()).put(edge[0], edge[2]);
        }

        // 在修改一条边之后, 会影响所有子树节点的最短路径, 有可能更长, 有可能更短
        //  - 最短路径可以使用 dijkstra 来计算
        //
        // 记录每次的修改, 根据修改的权重走不同的分支, 最终完成所有分支的遍历计算
        //  - 每次

        return null;
    }

    public static void main(String[] args) {
    }

}
