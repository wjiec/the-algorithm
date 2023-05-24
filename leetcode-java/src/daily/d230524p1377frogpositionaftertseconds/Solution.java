package daily.d230524p1377frogpositionaftertseconds;

import common.Checker;
import common.PrettyPrinter;

import java.util.HashSet;
import java.util.Set;

/**
 * 1377. Frog Position After T Seconds
 *
 * https://leetcode.cn/problems/frog-position-after-t-seconds/
 *
 * Given an undirected tree consisting of n vertices numbered from 1 to n.
 * A frog starts jumping from vertex 1. In one second, the frog jumps from
 * its current vertex to another unvisited vertex if they are directly connected.
 *
 * The frog can not jump back to a visited vertex. In case the frog can jump to several
 * vertices, it jumps randomly to one of them with the same probability. Otherwise, when
 * the frog can not jump to any unvisited vertex, it jumps forever on the same vertex.
 *
 * The edges of the undirected tree are given in the array edges, where
 * edges[i] = [ai, bi] means that exists an edge connecting the vertices ai and bi.
 *
 * Return the probability that after t seconds the frog is on the vertex target.
 * Answers within 10-5 of the actual answer will be accepted.
 */

/*
给你一棵由 n 个顶点组成的无向树，顶点编号从 1 到 n。青蛙从 顶点 1 开始起跳。规则如下：

在一秒内，青蛙从它所在的当前顶点跳到另一个 未访问 过的顶点（如果它们直接相连）。
青蛙无法跳回已经访问过的顶点。
如果青蛙可以跳到多个不同顶点，那么它跳到其中任意一个顶点上的机率都相同。
如果青蛙不能跳到任何未访问过的顶点上，那么它每次跳跃都会停留在原地。
无向树的边用数组 edges 描述，其中 edges[i] = [ai, bi] 意味着存在一条直接连通 ai 和 bi 两个顶点的边。

返回青蛙在 t 秒后位于目标顶点 target 上的概率。与实际答案相差不超过 10-5 的结果将被视为正确答案。
 */

@SuppressWarnings({"DuplicatedCode", "unchecked"})
public class Solution {

    private final Set<Integer>[] paths = new Set[102];

    public double frogPosition(int n, int[][] edges, int t, int target) {
        for (int i = 0; i < paths.length; i++) paths[i] = new HashSet<>();
        for (var edge : edges) {
            paths[edge[0]].add(edge[1]);
            paths[edge[1]].add(edge[0]);
        }

        memo = new double[t + 1][101];
        boolean[] seen = new boolean[n + 1];
        memo[0][1] = 1; seen[1] = true;
        dfs(1, 0, 1, seen);
        return memo[t][target];
    }

    private double[][] memo;

    private void dfs(int curr, int seconds, double probability, boolean[] seen) {
        if (seconds == memo.length - 1) return;

        double count = 0;
        for (var next : paths[curr]) {
            if (!seen[next]) count++;
        }
        if (count == 0) {
            memo[seconds + 1][curr] = probability;
            dfs(curr, seconds + 1, probability, seen);
            return;
        }

        double currProbability = probability / count;
        for (var next : paths[curr]) {
            if (!seen[next]) {
                seen[next] = true;
                memo[seconds + 1][next] += currProbability;
                dfs(next, seconds + 1, currProbability, seen);
                seen[next] = false;
            }
        }
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().frogPosition(7, new int[][]{{1,2},{1,3},{1,7},{2,4},{2,6},{3,5}}, 20, 6), 0.1666666666666667);

        assert Checker.check(new Solution().frogPosition(7, new int[][]{{1,2},{1,3},{1,7},{2,4},{2,6},{3,5}}, 2, 4), 0.16666666666666666);
        assert Checker.check(new Solution().frogPosition(7, new int[][]{{1,2},{1,3},{1,7},{2,4},{2,6},{3,5}}, 1, 7), 0.3333333333333333);
    }

}
