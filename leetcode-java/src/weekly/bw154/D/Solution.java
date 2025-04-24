package weekly.bw154.D;

import common.Checker;
import common.Tag;

import java.util.*;

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

    private final Map<Integer, Set<Integer>> g = new HashMap<>();
    { g.put(1, new HashSet<>()); }

    @Tag({"树上时间戳", "树上修改权重"})
    public int[] treeQueries(int n, int[][] edges, int[][] queries) {
        // 求树上的最短路径, 也就是求树上路径的权重和
        for (var edge : edges) {
            g.computeIfAbsent(edge[0], k -> new HashSet<>()).add(edge[1]);
            g.computeIfAbsent(edge[1], k -> new HashSet<>()).add(edge[0]);
        }

        // 前序遍历生成的数组, 每一个区间实际上就表示一个子树
        //  - g: 1 -> {2, 3}, 2 -> {4}, 3 -> {5, 6}
        //  - 前序遍历: 1, 2, 4, 3, 5, 6
        //      - 子树 2: 2 4
        //      - 子树 3: 3, 5, 6
        //
        // 使用时间戳来记录每个节点进入和离开的时间, 也就是可以找到每个子树在前序遍历上的范围
        int[] enter = new int[n + 1], leave = new int[n + 1];
        dfs(1, 0, enter, leave);

        // 此时我们就可以按照以上顺序将权重按照差分填入到数组中
        //  - 我们将边 1 -> 2 的权重放在节点 2 上 (也就是子节点上)
        //  - 对于边权 1 -> 3 的权重被放在节点 3 上, 这个权重会影响 3 4 5 节点的权重和
        //  - 也就是影响前序遍历的一个区间内的所有数
        int[] weight = new int[n + 1];
        // 同时我们还需要使用树状数组来计算在一个区间内的前缀和, 用来回答查询
        int[] tree = new int[n + 1];
        for (var edge : edges) update(edge[0], edge[1], edge[2], weight, enter, leave, tree);

        List<Integer> ans = new ArrayList<>();
        for (var query : queries) {
            // 这里需要将查询的节点转换为在前序遍历中的位置
            if (query[0] == 2) ans.add(treeQuery(tree, enter[query[1]]));
            else update(query[1], query[2], query[3], weight, enter, leave, tree);
        }

        return ans.stream().mapToInt(v -> v).toArray();
    }

    private void update(int u, int v, int w, int[] weight, int[] enter, int[] leave, int[] tree) {
        // 我们让所有的边都是都是 u -> v 的, 根据进入节点的时间可以判断谁是子节点
        if (enter[u] > enter[v]) v = u;

        // 计算边权的增量, 初始情况 weight[v] = 0
        //  - 当后续发生修改时就需要计算修改实际变化了多少
        int diff = w - weight[v];

        // 当前边 u -> v 的权重是 w, 我们需要将这条边的权重保存在 v 中
        weight[v] = w;

        // 将增量放到树状数组中, 子树区间为 [enter[v], leave[v] + 1]
        treeUpdate(tree, enter[v], diff);
        treeUpdate(tree, leave[v] + 1, -diff);
    }

    private void treeUpdate(int[] tree, int i, int v) {
        while (i < tree.length) {
            tree[i] += v;
            i += lowbit(i);
        }
    }

    private int treeQuery(int[] tree, int i) {
        int ans = 0;
        while (i > 0) {
            ans += tree[i];
            i -= lowbit(i);
        }
        return ans;
    }

    private int lowbit(int v) { return v & -v; }

    private int clock = 0; // 树上递增时间戳

    private void dfs(int curr, int parent, int[] enter, int[] leave) {
        enter[curr] = ++clock;
        for (var next : g.get(curr)) {
            if (next == parent) continue;
            dfs(next, curr, enter, leave);
        }
        leave[curr] = clock;
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().treeQueries(2, new int[][]{{1,2,7}}, new int[][]{{2,2},{1,1,2,4},{2,2}}), new int[]{7,4});
        assert Checker.check(new Solution().treeQueries(3, new int[][]{{1,2,2},{1,3,4}}, new int[][]{{2,1},{2,3},{1,1,3,7},{2,2},{2,3}}), new int[]{0,4,2,7});
        assert Checker.check(new Solution().treeQueries(4, new int[][]{{1,2,2},{2,3,1},{3,4,5}}, new int[][]{{2,4},{2,3},{1,2,3,3},{2,2},{2,3}}), new int[]{8,3,2,5});
    }

}
