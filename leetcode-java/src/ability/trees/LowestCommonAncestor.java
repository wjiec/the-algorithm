package ability.trees;

import common.Tag;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Tag({"最近公共祖先", "LCA"})
@SuppressWarnings({"DuplicatedCode", "ReassignedVariable"})
public class LowestCommonAncestor {

    //
    // LCA 适合用于解决树上路径问题
    //

    // 记录每个节点的深度信息
    private final int[] depth;

    // 记录每个节点到达跟节点的距离
    private final long[] distance;

    // table[i][j] 表示节点 i 向上跳跃 2 ^ j 到达的节点位置
    private final int[][] table;

    // 通过 [u, v, w] 建树, 也就是在图中有一条 u -> v 且边权为 w 的边
    @SuppressWarnings("unchecked")
    public LowestCommonAncestor(int[][] edges) {
        // 图中节点的数量
        int n = edges.length + 1;
        // 我们至少需要使用多少位二进制才能表示所有的 n
        //  - 在后续倍增时, 我们需要最多需要跳跃 logN 次才能到达根节点
        int logN = 32 - Integer.numberOfLeadingZeros(n);

        depth = new int[n];
        distance = new long[n];
        table = new int[n][logN];

        // 开始遍历树上的所有节点
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

    // 找到节点 node 的第 k 个祖先节点
    public int kthAncestor(int node, int k) {
        // 我们使用 k & (k - 1) 找到最低置位的位置
        //  - 然后我们向上跳跃这个长度, 最终使得向上跳跃 k 个位置
        for (; k != 0; k &= k - 1) {
            node = table[node][Integer.numberOfTrailingZeros(k)];
        }
        return node;
    }

    // 找到 u 和 v 的最近公共祖先
    public int query(int u, int v) {
        // 我们需要保证 u 的深度小于 v 的深度, 然后让 v 向上到与 u 同一深度
        if (depth[u] > depth[v]) { int t = u; u = v; v = t; }
        v = kthAncestor(v, depth[v] - depth[u]);

        // 如果 u == v, 说明 u 就是最近公共祖先
        if (u == v) return u;

        // 否则我们不断尝试走最大的步数尝试找到最近的公共祖先
        for (int j = table[u].length - 1; j >= 0; j--) {
            // u 和 v 向上走 2 ^ j 次方之后的父节点是 px 和 py
            int px = table[u][j], py = table[v][j];
            // 如果两者不同, 则说明最近公共祖先还在更上面
            //  - 但是不足以到达 2 ^ (j + 1) 个祖先
            //  - 也就是 2 ^ j < LCA < 2 ^ (j + 1)
            if (px != py) {u = px; v = py; }
        }

        // 最后我们使得 u 和 v 的父节点相同, 则这就是我们要找的最近的公共祖先了
        return table[u][0];
    }

    // 在树上计算 u -> v 节点的距离
    public long distance(int u, int v) {
        // 类似与计算二维前缀和, d(root, u) + d(root, v) - 2 * d(root, LCA)
        return distance[u] + distance[v] - 2 * distance[query(u, v)];
    }

    // 在树上计算从根节点到 node 节点的距离
    public long distance(int node) {
        return distance[node];
    }

    // 返回从 node 向上跳跃至多 d 距离能到达的节点
    public int jumpTo(int node, long d) {
        long farther = distance[node];
        // 每次向上尝试跳跃最长的距离
        for (int j = table[node].length - 1; j >= 0; j--) {
            int ancestor = table[node][j];
            // 从根节点到 ancestor 的距离是 d(root, ancestor)
            //  - 也就是从 node 到 ancestor 的距离是 farther - d(root, ancestor)
            //      - 要满足 farther - d(root, ancestor) <= d
            //
            // 我们需要尽可能扩大 farther - d(root, ancestor) 的值且不超过 d
            //  - 所有我们只要找到一个满足条件的 ancestor 就跳过去, 并逐渐逼近
            if (ancestor != -1 && farther - distance[ancestor] <= d) {
                node = ancestor;
            }
        }
        return node;
    }

    // 计算每个节点的深度同时记录倍增中 j = 0 的节点
    private void dfs(List<int[]>[] g, int curr, int parent) {
        // 2 ^ 0 也就是向上跳跃一次可以到达父节点
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
