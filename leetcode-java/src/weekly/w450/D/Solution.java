package weekly.w450.D;

import common.Checker;
import common.Tag;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    // LCA 可以通过树上倍增算法实现
    @SuppressWarnings("unchecked")
    private static class LowestCommonAncestor {
        private final int[] depth;
        private final int[] distance;
        private final int[][] table;

        @SuppressWarnings("DuplicatedCode")
        public LowestCommonAncestor(int[][] edges) {
            int n = edges.length + 1;
            int logN = 32 - Integer.numberOfLeadingZeros(n);

            depth = new int[n];
            distance = new int[n];
            table = new int[n][logN];

            List<int[]>[] g = new List[n];
            Arrays.setAll(g, i -> new ArrayList<>());
            for (var edge : edges) {
                g[edge[0]].add(new int[]{edge[1], edge[2]});
                g[edge[1]].add(new int[]{edge[0], edge[2]});
            }
            dfs(g, 0, -1);

            // 开始倍增向上 2 ^ j 次可以到达的节点
            for (int j = 1; j < logN; j++) {
                for (int i = 0; i < n; i++) {
                    // i 向上走 2 ^ (j - 1) 可以到达的地方是 p = table[i][j - 1]
                    // 再向上走 2 ^ (j - 1) 可以到达的地方是 table[p][j - 1]
                    int p = table[i][j - 1];
                    // 如果已经走到跟节点了, 那就不能继续往上走了
                    table[i][j] = p < 0 ? p : table[p][j - 1];
                }
            }
        }

        // 根据 LCA 计算 x 到 y 的距离
        public int distance(int x, int y) {
            return distance[x] + distance[y] - 2 * distance[query(x, y)];
        }

        // 找到 x 和 y 的最近公共祖先
        public int query(int x, int y) {
            // 我们需要保证 x 的深度小于 y 的深度, 然后让 y 向上到与 x 同一深度
            if (depth[x] > depth[y]) { int t = x; x = y; y = t; }
            y = kthAncestor(y, depth[y] - depth[x]);

            // 如果 x == y, 说明 x 就是最近公共祖先
            if (x == y) return x;

            // 否则我们不断尝试走最大的步数尝试找到最近的公共祖先
            for (int j = table[x].length - 1; j >= 0; j--) {
                // 想上走 2 ^ j 次方之后的父节点是 px 和 py, 如果两者不同
                // 说明最近公共祖先还在上面, 但是不够 2 ^ (j + 1)
                int px = table[x][j], py = table[y][j];
                if (px != py) { x = px; y = py; }
            }
            return table[x][0]; // 找到最近的公共祖先
        }

        // 找到节点 x 的第 k 个祖先
        private int kthAncestor(int node, int k) {
            for (; k != 0; k &= k - 1) {
                node = table[node][Integer.numberOfTrailingZeros(k)];
            }
            return node;
        }

        // 计算每个节点的深度同时记录倍增中 j = 0 的节点
        private void dfs(List<int[]>[] g, int curr, int parent) {
            table[curr][0] = parent;
            for (var next : g[curr]) {
                if (next[0] != parent) {
                    depth[next[0]] = depth[curr] + 1;
                    distance[next[0]] = distance[curr] + next[1];
                    dfs(g, next[0], curr);
                }
            }
        }
    }

    // 对每个查询 {src1, src2, dest} 需要找到一个最小的子树(任意节点和边组成的连通子集)权重和
    // 使得可以从 src1 和 src2 到达 dest
    //  - 节点数 3 <= n <= 1e5
    //  - 查询数 1 <= queries.length <= 1e5
    @Tag({"LCA", "最近公共祖先", "包含给定节点的最小子树"})
    public int[] minimumWeight(int[][] edges, int[][] queries) {
        // 对于三个节点, 通过求 |A -> B| + |B -> C| + |C -> A| 的路径和, 此时恰好
        // 每条路径走过两次, 将此路径和除以 2 就是答案.
        //
        // 求 |A -> B| 的路径和是通过求 |Root -> A| + |Root -> B| - 2 * |Root -> <LCA>| 的方式实现
        //  - LCA 即 A 和 B 的最近公共祖先
        //
        // 扩展: 如果有 k 个点, 则为了保证每条路径经过两次, 需要使用 DFN 来保证遍历顺序
        //  - 如果 Root -> A -> {B, C}, 在 B 子树中有 x 个点, C 子树中有 y 个点
        //      - 按照 B 子树取一个, C 子树取一个的走法, |A -> B| 和 |A -> C| 会走至少 max(x, y) 次
        //  - DFN: 也就是按照先序或者后序的方式遍历树时, 每个节点的遍历进入时间戳
        //
        // 三个节点的情况不需要应用 DFN, 因为三个节点是最小的两两关系的情况, 不管怎么走路径都是相同的
        LowestCommonAncestor lca = new LowestCommonAncestor(edges);

        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int a = queries[i][0], b = queries[i][1], c = queries[i][2];
            ans[i] = (lca.distance(a, b) + lca.distance(b, c) + lca.distance(c, a)) / 2;
        }
        return ans;
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().minimumWeight(new int[][]{
            {0, 3, 7}, {4, 1, 7}, {4, 2, 8}, {3, 4, 5}, {4, 5, 8}
        }, new int[][]{{3,5,1}}), new int[]{20});
    }

}
