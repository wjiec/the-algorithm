package weekly.w449.C;

import java.util.*;

/**
 * Q3. Maximum Sum of Edge Values in a Graph
 *
 * https://leetcode.cn/contest/weekly-contest-449/problems/maximum-sum-of-edge-values-in-a-graph/
 *
 * You are given an undirected connected graph of n nodes, numbered from 0 to n - 1.
 *
 * Each node is connected to at most 2 other nodes.
 *
 * The graph consists of m edges, represented by a 2D array edges, where
 * edges[i] = [ai, bi] indicates that there is an edge between nodes ai and bi.
 *
 * You have to assign a unique value from 1 to n to each node. The value of an edge
 * will be the product of the values assigned to the two nodes it connects.
 *
 * Your score is the sum of the values of all edges in the graph.
 *
 * Return the maximum score you can achieve.
 */

public class Solution {

    private List<Integer>[] g = null;

    // 图是连通的
    // 每个节点最多与其他两个节点相连
    //  - 节点数最多为 5e4
    //  - 为每个节点分配一个从 [1, n] 的唯一值
    //  - 边的值为两端节点的乘积
    //
    // 求所有边的和的最大值
    /** @noinspection unchecked*/
    public long maxScore(int n, int[][] edges) {
        // 由于一个节点最多只有两条边, 且图是连通的, 也就是意味着图
        // 要么是一条链, 要么就是一个环
        //  - 如果是一个环, 那么就找到随便一个节点, 分配 n, 然后为其左右节点分配 n-1 和 n-2, 然后递归这个过程
        //  - 如果是一条链, 那么就找到这条链的中间节点, 分配 n, 两边递归分配, 实际上和环是一样的, 就是少了最后成环的一条边
        //      - 环的一条边一定是 1 * 2 也就是两种情况相差 2
        g = new List[n];
        Arrays.setAll(g, i -> new ArrayList<>());
        for (var edge : edges) {
            g[edge[0]].add(edge[1]);
            g[edge[1]].add(edge[0]);
        }

        // 可以直接制造一棵树, 树的跟节点是 n, 子节点是 n-1, n-2, 然后递归分配直到 1
        long ans = 0; int x = n;
        Deque<Integer> dq = new ArrayDeque<>(); dq.add(x); dq.add(x--);
        while (x > 0 && !dq.isEmpty()) {
            long curr = dq.remove();

            int l = x--;
            ans += l * curr;
            dq.add(l);
        }

        return ans + (isCircle(0, -1, new boolean[n]) ? 2 : 0);
    }

    private boolean isCircle(int curr, int parent, boolean[] seen) {
        if (seen[curr]) return true;

        seen[curr] = true;
        for (var next : g[curr]) {
            if (next == parent) continue;
            if (isCircle(next, curr, seen)) return true;
        }
        return false;
    }

    public static void main(String[] args) {
        assert new Solution().maxScore(4, new int[][]{{0,1},{1,2},{2,3}}) == 23;
        assert new Solution().maxScore(6, new int[][]{{0,3},{4,5},{2,0},{1,3},{2,4},{1,5}}) == 82;
    }

}
