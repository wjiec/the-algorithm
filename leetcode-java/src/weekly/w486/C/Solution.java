package weekly.w486.C;

import java.util.*;

/**
 * Q3. Pythagorean Distance Nodes in a Tree
 *
 * https://leetcode.cn/contest/weekly-contest-486/problems/pythagorean-distance-nodes-in-a-tree/
 *
 * You are given an integer n and an undirected tree with n nodes numbered from 0 to n - 1.
 *
 * The tree is represented by a 2D array edges of length n - 1, where edges[i] = [ui, vi]
 * indicates an undirected edge between ui and vi.
 *
 * You are also given three distinct target nodes x, y, and z.
 *
 * For any node u in the tree:
 *
 * Let dx be the distance from u to node x
 * Let dy be the distance from u to node y
 * Let dz be the distance from u to node z
 * The node u is called special if the three distances form a Pythagorean Triplet.
 *
 * Return an integer denoting the number of special nodes in the tree.
 *
 * A Pythagorean triplet consists of three integers a, b, and c which,
 * when sorted in ascending order, satisfy a2 + b2 = c2.
 *
 * The distance between two nodes in a tree is the number of edges on the unique path between them.
 */

@SuppressWarnings({"unchecked", "DuplicatedCode"})
public class Solution {

    public int specialNodes(int n, int[][] edges, int x, int y, int z) {
        int logN = 32 - Integer.numberOfLeadingZeros(n);

        List<Integer>[] g = new List[n];
        Arrays.setAll(g, i -> new ArrayList<>());
        for (var edge : edges) {
            g[edge[0]].add(edge[1]);
            g[edge[1]].add(edge[0]);
        }

        int[] depth = new int[n];
        int[][] table = new int[n][logN];
        dfs(g, 0, -1, table, depth);
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

        int ans = 0;
        Queue<int[]> q = new ArrayDeque<>();
        for (q.add(new int[]{0, -1}); !q.isEmpty(); ) {
            var curr = q.remove();
            for (var next : g[curr[0]]) {
                if (next == curr[1]) continue;

                q.add(new int[]{next, curr[0]});
            }

            int dx = distance(table, depth, curr[0], x);
            int dy = distance(table, depth, curr[0], y);
            int dz = distance(table, depth, curr[0], z);
            if (check(dx, dy, dz)) ans++;
        }
        return ans;
    }

    private boolean check(int dx, int dy, int dz) {
        long a = Math.min(dx, Math.min(dy, dz));
        long c = Math.max(dx, Math.max(dy, dz));
        long b = dx + dy + dz - a - c;
        return a * a + b * b == c * c;
    }

    // 在树上计算 u -> v 节点的距离
    public int distance(int[][] table, int[] depth, int u, int v) {
        // 类似与计算二维前缀和, d(root, u) + d(root, v) - 2 * d(root, LCA)
        return depth[u] + depth[v] - 2 * depth[query(table, depth, u, v)];
    }

    private int query(int[][] table, int[] depth, int u, int v) {
        // 我们需要保证 u 的深度小于 v 的深度, 然后让 v 向上到与 u 同一深度
        if (depth[u] > depth[v]) { int t = u; u = v; v = t; }
        v = kthAncestor(table, v, depth[v] - depth[u]);

        // 如果 u == v, 说明 u 就是最近公共祖先
        if (u == v) return u;

        // 否则我们不断尝试走最大的步数尝试找到最近的公共祖先
        for (int j = table[u].length - 1; j >= 0; j--) {
            // u 和 v 向上走 2 ^ j 次方之后的父节点是 px 和 py
            int px = table[u][j], py = table[v][j];
            // 如果两者不同, 则说明最近公共祖先还在更上面
            //  - 但是不足以到达 2 ^ (j + 1) 个祖先
            //  - 也就是 2 ^ j < LCA < 2 ^ (j + 1)
            if (px != py) { u = px; v = py; }
        }

        // 最后我们使得 u 和 v 的父节点相同, 则这就是我们要找的最近的公共祖先了
        return table[u][0];
    }

    private int kthAncestor(int[][] table, int node, int k) {
        // 我们使用 k & (k - 1) 找到最低置位的位置
        //  - 然后我们向上跳跃这个长度, 最终使得向上跳跃 k 个位置
        for (; k != 0; k &= k - 1) {
            node = table[node][Integer.numberOfTrailingZeros(k)];
        }
        return node;
    }

    private void dfs(List<Integer>[] g, int curr, int parent, int[][] table, int[] depth) {
        table[curr][0] = parent;
        for (var next : g[curr]) {
            if (next != parent) {
                depth[next] = depth[curr] + 1;
                dfs(g, next, curr, table, depth);
            }
        }
    }

    private static class Optimization {
        public int specialNodes(int n, int[][] edges, int x, int y, int z) {
            // 分别以 x, y, z 为根遍历, 记录每个点到三个根的距离
            List<Integer>[] g = new List[n]; Arrays.setAll(g, i -> new ArrayList<>());
            for (var edge : edges) {
                g[edge[0]].add(edge[1]);
                g[edge[1]].add(edge[0]);
            }

            int[] dx = distanceOf(g, x);
            int[] dy = distanceOf(g, y);
            int[] dz = distanceOf(g, z);

            // 枚举每个节点并检查是否满足要求
            int ans = 0;
            for (int i = 0; i < n; i++) {
                if (check(dx[i], dy[i], dz[i])) ans++;
            }

            return ans;
        }

        private boolean check(int dx, int dy, int dz) {
            long a = Math.min(dx, Math.min(dy, dz));
            long c = Math.max(dx, Math.max(dy, dz));
            long b = dx + dy + dz - a - c;
            return a * a + b * b == c * c;
        }

        private int[] distanceOf(List<Integer>[] g, int root) {
            int[] depth = new int[g.length];
            dfs(g, root, -1, depth);
            return depth;
        }

        private void dfs(List<Integer>[] g, int curr, int parent, int[] depth) {
            for (var next : g[curr]) {
                if (next == parent) continue;

                depth[next] = depth[curr] + 1;
                dfs(g, next, curr, depth);
            }
        }
    }

    public static void main(String[] args) {
    }

}
