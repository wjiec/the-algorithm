package weekly.bw157.C;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static ability.Ability.Math.pow;

/**
 * Q3. Number of Ways to Assign Edge Weights I
 *
 * https://leetcode.cn/contest/biweekly-contest-157/problems/number-of-ways-to-assign-edge-weights-i
 *
 * There is an undirected tree with n nodes labeled from 1 to n, rooted at node 1.
 * The tree is represented by a 2D integer array edges of length n - 1, where
 * edges[i] = [ui, vi] indicates that there is an edge between nodes ui and vi.
 *
 * Initially, all edges have a weight of 0. You must assign each edge a weight of either 1 or 2.
 *
 * The cost of a path between any two nodes u and v is the total weight of all edges in the path connecting them.
 *
 * Select any one node x at the maximum depth. Return the number of ways to assign edge weights
 * in the path from node 1 to x such that its total cost is odd.
 *
 * Since the answer may be large, return it modulo 109 + 7.
 *
 * Note: Ignore all edges not in the path from node 1 to x.
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

    /** @noinspection unchecked*/
    public int assignEdgeWeights(int[][] edges) {
        // 因为只能赋值为 1 或者 2, 赋值 2 不影响奇偶性, 所以在一条路径中赋值为 1 的数量为
        // 奇数时, 那边的权重之和就是奇数, 统计所有从 1 走到最大深度节点的赋值方案数量
        //
        // 其实也就是从 1 到最大深度的路径长度为 k, 求在 k 中赋值 x 个 1 的方案数量(x <= k, x % 2 == 1)
        //  - 组合数学: 有 k 个位置, 需要填其中的 x 个位置, 也就是 k! / (x! * (k - x)!)
        List<Integer>[] g = new List[edges.length + 2];
        Arrays.setAll(g, i -> new ArrayList<>());
        for (var edge : edges) {
            g[edge[0]].add(edge[1]);
            g[edge[1]].add(edge[0]);
        }

        long ans = 0;
        int k = dfs(g, 1, 0, 0);
        for (int x = 1; x <= k; x += 2) {
            ans = (ans + ((((fac[k] * inv[x]) % MOD) * inv[k - x]) % MOD)) % MOD;
        }
        return (int) ans;
    }

    private int dfs(List<Integer>[] g, int curr, int parent, int depth) {
        int ans = depth;
        for (var next : g[curr]) {
            if (next == parent) continue;
            ans = Math.max(ans, dfs(g, next, curr, depth + 1));
        }
        return ans;
    }

    public static void main(String[] args) {
    }

}
