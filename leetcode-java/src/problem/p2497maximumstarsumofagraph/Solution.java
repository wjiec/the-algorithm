package problem.p2497maximumstarsumofagraph;

import java.util.PriorityQueue;

/**
 * 2497. Maximum Star Sum of a Graph
 *
 * https://leetcode.cn/problems/maximum-star-sum-of-a-graph/
 *
 * There is an undirected graph consisting of n nodes numbered from 0 to n - 1.
 * You are given a 0-indexed integer array vals of length n where vals[i] denotes
 * the value of the ith node.
 *
 * You are also given a 2D integer array edges where edges[i] = [ai, bi] denotes that
 * there exists an undirected edge connecting nodes ai and bi.
 *
 * A star graph is a subgraph of the given graph having a center node containing 0 or
 * more neighbors. In other words, it is a subset of edges of the given graph such that
 * there exists a common node for all edges.
 *
 * The image below shows star graphs with 3 and 4 neighbors respectively, centered at the blue node.
 */

public class Solution {

    public int maxStarSum(int[] vals, int[][] edges, int k) {
        PriorityQueue<Integer>[] pqs = new PriorityQueue[vals.length];
        for (int i = 0; i < vals.length; i++) {
            pqs[i] = new PriorityQueue<>((a, b) -> b - a);
        }

        for (var edge : edges) {
            pqs[edge[0]].add(vals[edge[1]]);
            pqs[edge[1]].add(vals[edge[0]]);
        }

        int ans = Integer.MIN_VALUE;
        for (int i = 0; i < vals.length; i++) {
            int curr = vals[i];
            for (int j = 0; j < k && !pqs[i].isEmpty(); j++) {
                if (pqs[i].peek() > 0) {
                    curr += pqs[i].remove();
                } else break;
            }
            ans = Math.max(ans, curr);
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().maxStarSum(new int[]{1,2,3,4,10,-10,-20}, new int[][]{{0,1},{1,2},{1,3},{3,4},{3,5},{3,6}}, 2) == 16;
        assert new Solution().maxStarSum(new int[]{-5}, new int[][]{}, 0) == -5;
    }

}
