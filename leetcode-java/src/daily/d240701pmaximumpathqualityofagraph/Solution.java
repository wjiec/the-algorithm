package daily.d240701pmaximumpathqualityofagraph;

import ability.Benchmark;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 2065. Maximum Path Quality of a Graph
 *
 * https://leetcode.cn/problems/maximum-path-quality-of-a-graph/
 *
 * There is an undirected graph with n nodes numbered from 0 to n - 1 (inclusive).
 * You are given a 0-indexed integer array values where values[i] is the value of the ith node.
 * You are also given a 0-indexed 2D integer array edges, where each edges[j] = [uj, vj, timej] indicates
 * that there is an undirected edge between the nodes uj and vj, and it takes timej seconds to travel
 * between the two nodes. Finally, you are given an integer maxTime.
 *
 * A valid path in the graph is any path that starts at node 0, ends at node 0, and takes at most
 * maxTime seconds to complete. You may visit the same node multiple times.
 * The quality of a valid path is the sum of the values of the unique nodes visited in
 * the path (each node's value is added at most once to the sum).
 *
 * Return the maximum quality of a valid path.
 *
 * Note: There are at most four edges connected to each node.
 */

public class Solution {

    private final List<int[]>[] g = new List[1001];
    { Arrays.setAll(g, i -> new ArrayList<>()); }

    public int maximalPathQuality(int[] values, int[][] edges, int maxTime) {
        for (var edge : edges) {
            if (edge[2] > maxTime) continue;

            g[edge[0]].add(new int[]{edge[1], edge[2]});
            g[edge[1]].add(new int[]{edge[0], edge[2]});
        }

        this.values = values;
        dfs(0, values[0], maxTime);

        return ans;
    }

    private int ans = 0;
    private int[] values = null;
    private final boolean[] seen = new boolean[1001];
    { seen[0] = true; }

    private void dfs(int curr, int sum, int remain) {
        if (curr == 0) ans = Math.max(ans, sum);
        for (var e : g[curr]) {
            if (e[1] > remain) continue;

            if (!seen[e[0]]) {
                seen[e[0]] = true;
                dfs(e[0], sum + values[e[0]], remain - e[1]);
                seen[e[0]] = false;
            } else {
                dfs(e[0], sum, remain - e[1]);
            }
        }
    }

    public static void main(String[] args) {
        Benchmark.benchmark("default", () -> {
            assert new Solution().maximalPathQuality(new int[]{0,32,10,43}, new int[][]{
                {0,1,10}, {1,2,15},{0,3,10}
            }, 49) == 75;

            assert new Solution().maximalPathQuality(new int[]{
                7604,904,47,2553,8851,1912,7361,3518,6802,8416,9276,2517,5710,
                4404,8018,353,6740,9918,8417,7677,3616,4177,3424,5820,8618,4059,
                1502,4653,7885,7424,9961,2827,3814,4882,2808
            }, new int[][]{
                {2,6,54},{4,30,18},{6,20,24},{10,16,94},{25,30,94},{12,25,71},
                {10,11,67},{8,24,93},{14,16,34},{4,14,48},{23,26,66},{10,29,70},
                {2,33,56},{15,25,96},{6,14,66},{5,10,61},{6,12,33},{29,34,10},
                {9,29,63},{17,19,81},{3,14,48},{9,21,73},{13,30,54},{19,27,65},
                {4,33,41},{29,31,35},{17,31,23},{23,28,52},{0,17,44},{15,17,30},
                {7,16,60},{2,3,90},{3,4,44},{3,5,51},{9,28,95},{7,23,23},{26,34,48},
                {5,33,47},{16,22,64},{1,18,93},{21,31,31},{26,32,49},{8,26,25},
                {18,24,73},{19,21,41},{0,31,20},{7,30,38},{23,27,85},{24,33,56},
                {11,25,90},{11,27,53},{12,18,73},{21,24,38},{13,34,76},{13,32,74},
                {7,27,36},{15,34,72},{12,13,11},{20,22,48},{1,19,43},{9,15,58},
                {11,32,18},{0,32,87},{0,28,20},{2,18,53},{20,28,82},{8,22,68},
                {5,22,27},{8,20,12}
            }, 69) == 15489;
        });
    }

}
