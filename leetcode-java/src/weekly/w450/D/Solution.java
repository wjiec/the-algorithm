package weekly.w450.D;

import java.util.HashMap;
import java.util.Map;

/**
 * Q4. Minimum Weighted Subgraph With the Required Paths II
 *
 * https://leetcode.cn/contest/weekly-contest-450/problems/minimum-weighted-subgraph-with-the-required-paths-ii
 *
 * You are given an undirected weighted tree with n nodes, numbered from 0 to n - 1.
 *
 * It is represented by a 2D integer array edges of length n - 1, where
 * edges[i] = [ui, vi, wi] indicates that there is an edge between nodes ui and vi with weight wi.
 *
 * Additionally, you are given a 2D integer array queries, where queries[j] = [src1j, src2j, destj].
 *
 * Return an array answer of length equal to queries.length, where answer[j] is the minimum total
 * weight of a subtree such that it is possible to reach destj from both src1j and src2j
 * using edges in this subtree.
 *
 * A subtree here is any connected subset of nodes and edges of the original tree forming a valid tree.
 */

public class Solution {

    private final Map<Integer, Map<Integer, Integer>> g = new HashMap<>();

    // 对每个查询 {src1, src2, dest} 需要找到一个最小的子树(任意节点和边组成的连通子集)权重和
    // 使得可以从 src1 和 src2 到达 dest
    //  - 节点数 3 <= n <= 1e5
    //  - 查询数 1 <= queries.length <= 1e5
    public int[] minimumWeight(int[][] edges, int[][] queries) {
        for (var edge : edges) {
            g.computeIfAbsent(edge[0], HashMap::new).merge(edge[1], edge[2], Integer::min);
            g.computeIfAbsent(edge[1], HashMap::new).merge(edge[0], edge[2], Integer::min);
        }

        // 从 src1 -> dest 加上从 src2 -> dest 一定是最优路线么?
        //  - 有没有可能绕一圈 src1 -> src2 -> dest 反而代价更低
        // 需要至多以 O(logN) 的时间复杂度处理每一个询问
        int[] ans = new int[queries.length];

        return ans;
    }

    public static void main(String[] args) {
    }

}
