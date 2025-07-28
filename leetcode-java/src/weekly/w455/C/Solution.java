package weekly.w455.C;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Q3. Minimum Increments to Equalize Leaf Paths
 *
 * https://leetcode.cn/contest/weekly-contest-455/problems/minimum-increments-to-equalize-leaf-paths/
 *
 * You are given an integer n and an undirected tree rooted at node 0 with n nodes numbered
 * from 0 to n - 1. This is represented by a 2D array edges of length n - 1,
 * where edges[i] = [ui, vi] indicates an edge from node ui to vi.
 *
 * Each node i has an associated cost given by cost[i], representing the cost to traverse that node.
 *
 * The score of a path is defined as the sum of the costs of all nodes along the path.
 *
 * Your goal is to make the scores of all root-to-leaf paths equal by increasing
 * the cost of any number of nodes by any non-negative amount.
 *
 * Return the minimum number of nodes whose cost must be increased to make all root-to-leaf path scores equal.
 */

/** @noinspection unchecked*/
public class Solution {

    // 通过给任意数量的节点增加成本, 使得所有从根节点到叶子节点的路径得分相等
    public int minIncrease(int n, int[][] edges, int[] cost) {
        List<Integer>[] g = new List[n];
        Arrays.setAll(g, i -> new ArrayList<>());

        for (var edge : edges) {
            g[edge[0]].add(edge[1]);
            g[edge[1]].add(edge[0]);
        }

        // 找到最大的叶子节点的值, 然后其他的叶子节点增加到这个对应的值
        //  - 二次 dfs 的时候, 计算每个节点应该增加的值
        //  - 父节点可以增加 min(child1, child2, child3) 的值
        long maxSum = dfs(g, 0, -1, cost, 0);
        return (int) dfs1(g, 0, -1, cost, 0, maxSum)[2];
    }

    // {minNodes, minValue, totNodes}
    private long[] dfs1(List<Integer>[] g, int curr, int parent, int[] cost, long sum, long maxSum) {
        sum += cost[curr];
        if (g[curr].size() == 1 && g[curr].get(0) == parent) { // 叶子节点
            int nodes = maxSum - sum > 0 ? 1 : 0;
            return new long[]{nodes, maxSum - sum, nodes};
        }

        long minNodes = 0, minValue = Long.MAX_VALUE, totNodes = 0;
        for (var next : g[curr]) {
            if (next == parent) continue;

            var children = dfs1(g, next, curr, cost, sum, maxSum);
            if (children[1] < minValue) { minNodes = 1; minValue = children[1]; }
            else if (children[1] == minValue) minNodes++;

            totNodes += children[2];
        }

        // 如果子节点中存在 minNodes 个值为 minValue 的修改, 则可以把这 minNodes 变为修改当前节点从而减少修改次数
        //  - 减少 minNodes 个节点的修改
        //  - 增加对当前节点的修改
        //  - 当前节点修改的增量是 minValue
        if (minValue > 0) return new long[]{1, minValue, totNodes - Math.max(minNodes - 1, 0)};

        // 如果没有任何可以修改的, 那就只能返回 0, 0 了
        return new long[]{0, 0, totNodes};
    }

    private long dfs(List<Integer>[] g, int curr, int parent, int[] cost, long sum) {
        long ans = sum += cost[curr];
        for (var next : g[curr]) {
            if (next == parent) continue;
            ans = Math.max(ans, dfs(g, next, curr, cost, sum));
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().minIncrease(4, new int[][]{{0,1}, {0,2}, {0,3}}, new int[]{15,2,4,4}) == 1;
        assert new Solution().minIncrease(7, new int[][]{{0,1}, {1,2}, {1,3}, {2,4}, {3,5}, {3,6}}, new int[]{40,17,46,11,13,16,11}) == 2;

        assert new Solution().minIncrease(3, new int[][]{{0,1}, {0,2}}, new int[]{2,1,3}) == 1;
        assert new Solution().minIncrease(3, new int[][]{{0,1}, {1,2}}, new int[]{5,1,4}) == 0;
        assert new Solution().minIncrease(5, new int[][]{{0,4}, {0,1}, {1,2}, {1,3}}, new int[]{3,4,1,1,7}) == 1;
    }

}
