package offer2.p110;

import common.Checker;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * 剑指 Offer II 110. 所有路径
 *
 * https://leetcode.cn/problems/bP4bmD/
 *
 * 给定一个有 n 个节点的有向无环图，用二维数组 graph 表示，请找到所有从 0 到 n-1 的路径并输出（不要求按顺序）。
 *
 * graph 的第 i 个数组中的单元都表示有向图中 i 号节点所能到达的下一些结点（译者注：有向图是有方向的，
 * 即规定了 a→b 你就不能从 b→a ），若为空，就是没有下一个节点了。
 */

public class Solution {

    private final List<List<Integer>> ans = new ArrayList<>();

    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        Deque<Integer> path = new ArrayDeque<>();
        path.add(0);

        dfs(graph, 0, path);
        return ans;
    }

    private void dfs(int[][] graph, int node, Deque<Integer> curr) {
        if (node == graph.length - 1) { ans.add(new ArrayList<>(curr)); return; }

        for (var next : graph[node]) {
            curr.addLast(next);
            dfs(graph, next, curr);
            curr.removeLast();
        }
    }

    public static void main(String[] args) {
        assert Checker.anyOrder(new Solution().allPathsSourceTarget(new int[][]{{1,2},{3},{3},{}}), new int[][]{
            {0,1,3},{0,2,3}
        });

        assert Checker.anyOrder(new Solution().allPathsSourceTarget(new int[][]{{4,3,1},{3,2,4},{3},{4},{}}), new int[][]{
            {0,4},{0,3,4},{0,1,3,4},{0,1,2,3,4},{0,1,4}
        });

        assert Checker.anyOrder(new Solution().allPathsSourceTarget(new int[][]{{1},{}}), new int[][]{
            {0,1}
        });

        assert Checker.anyOrder(new Solution().allPathsSourceTarget(new int[][]{{1,2,3},{2},{3},{}}), new int[][]{
            {0,1,2,3},{0,2,3},{0,3}
        });

        assert Checker.anyOrder(new Solution().allPathsSourceTarget(new int[][]{{1,3},{2},{3},{}}), new int[][]{
            {0,1,2,3},{0,3}
        });
    }

}
