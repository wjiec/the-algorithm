package weekly.bw157.D;

import common.Checker;

import java.util.*;

import static ability.Ability.Math.pow;

/**
 * Q4. Number of Ways to Assign Edge Weights II
 *
 * https://leetcode.cn/contest/biweekly-contest-157/problems/number-of-ways-to-assign-edge-weights-ii
 *
 * There is an undirected tree with n nodes labeled from 1 to n, rooted at node 1.
 * The tree is represented by a 2D integer array edges of length n - 1,
 * where edges[i] = [ui, vi] indicates that there is an edge between nodes ui and vi.
 *
 * Initially, all edges have a weight of 0. You must assign each edge a weight of either 1 or 2.
 *
 * The cost of a path between any two nodes u and v is the total weight of all edges in the path connecting them.
 *
 * You are given a 2D integer array queries. For each queries[i] = [ui, vi], determine
 * the number of ways to assign weights to edges in the path such that the cost of the
 * path between ui and vi is odd.
 *
 * Return an array answer, where answer[i] is the number of valid assignments for queries[i].
 *
 * Since the answer may be large, apply modulo 109 + 7 to each answer[i].
 *
 * Note: For each query, disregard all edges not in the path between node ui and vi.
 */

public class Solution {

    private static final int MAX_N = 100_001;
    private static final int MOD = 1_000_000_007;

    private static final long[] fac = new long[MAX_N];
    static {
        fac[0] = fac[1] = 1;
        for (int i = 2; i < MAX_N; i++) fac[i] = (fac[i - 1] * i) % MOD;
    }

    private static final long[] inv = new long[MAX_N];
    static {
        inv[MAX_N - 1] = pow(fac[MAX_N - 1], MOD - 2, MOD);
        for (int i = MAX_N - 1; i > 0; i--) inv[i - 1] = (inv[i] * i) % MOD;
    }

    // LCA 可以通过树上倍增算法实现
    @SuppressWarnings({"unchecked", "DuplicatedCode"})
    private static class LowestCommonAncestor {
        private final int[] depth;
        private final int[] distance;
        private final int[][] table;

        public LowestCommonAncestor(int[][] edges) {
            int n = edges.length + 2;
            int logN = 32 - Integer.numberOfLeadingZeros(n);

            depth = new int[n];
            distance = new int[n];
            table = new int[n][logN];

            List<int[]>[] g = new List[n];
            Arrays.setAll(g, i -> new ArrayList<>());
            for (var edge : edges) {
                g[edge[0]].add(new int[]{edge[1], 1});
                g[edge[1]].add(new int[]{edge[0], 1});
            }
            dfs(g, 1, 0);

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

    public int[] assignEdgeWeights(int[][] edges, int[][] queries) {
        LowestCommonAncestor lca = new LowestCommonAncestor(edges);

        int[] ans = new int[queries.length];
        Map<Integer, Integer> memo = new HashMap<>();
        for (int i = 0; i < queries.length; i++) {
            int a = queries[i][0], b = queries[i][1];
            if (a == b) continue;

            int k = lca.distance(a, b);
            if (!memo.containsKey(k)) {
                int curr = 0;
                for (int x = 1; x <= k; x += 2) {
                    curr = (int) ((curr + ((((fac[k] * inv[x]) % MOD) * inv[k - x]) % MOD)) % MOD);
                }
                memo.put(k, curr);
            }
            ans[i] = memo.get(k);
        }

        return ans;
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().assignEdgeWeights(new int[][]{{1,2}}, new int[][]{{1,1},{1,2}}), new int[]{0,1});
    }

}
