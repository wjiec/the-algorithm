package weekly.bw128.C;

import common.Checker;

import java.util.*;

/**
 * 3112. Minimum Time to Visit Disappearing Nodes
 *
 * https://leetcode.cn/contest/biweekly-contest-128/problems/minimum-time-to-visit-disappearing-nodes/
 *
 * There is an undirected graph of n nodes. You are given a 2D array edges,
 * where edges[i] = [ui, vi, lengthi] describes an edge between node ui and
 * node vi with a traversal time of lengthi units.
 *
 * Additionally, you are given an array disappear, where disappear[i] denotes the time
 * when the node i disappears from the graph and you won't be able to visit it.
 *
 * Notice that the graph might be disconnected and might contain multiple edges.
 *
 * Return the array answer, with answer[i] denoting the minimum units of time required
 * to reach node i from node 0. If node i is unreachable from node 0 then answer[i] is -1.
 */

public class Solution {

    private static final int INF = Integer.MAX_VALUE;

    @SuppressWarnings("unchecked")
    public int[] minimumTime(int n, int[][] edges, int[] disappear) {
        List<int[]>[] g = new List[n];
        Arrays.setAll(g, i -> new ArrayList<>());
        for (var edge : edges) {
            int a = edge[0], b = edge[1], w = edge[2];
            g[a].add(new int[]{b, w});
            g[b].add(new int[]{a, w});
        }

        int[] ans = new int[n]; Arrays.fill(ans, INF); ans[0] = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(v -> v[0]));
        pq.add(new int[]{0, 0});
        while (!pq.isEmpty()) {
            var curr = pq.remove();
            int node = curr[0], cost = curr[1];
            if (cost > ans[node]) continue;

            for (var e : g[node]) {
                int next = e[0], nextCost = cost + e[1];
                if (nextCost < disappear[next] && nextCost < ans[next]) {
                    ans[next] = nextCost;
                    pq.add(new int[]{next, nextCost});
                }
            }
        }

        for (int i = 0; i < n; i++) if (ans[i] == INF) ans[i] = -1;
        return ans;
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().minimumTime(3, new int[][]{
            {2,0,9},{1,0,5},{2,2,4},{0,1,10},{1,1,10},{1,1,10},{2,2,10},{1,1,10}
        }, new int[]{4, 13, 19}), new int[]{0, 5, 9});

        assert Checker.check(new Solution().minimumTime(3, new int[][]{
            {0,1,2},{1,2,1},{0,2,4}
        }, new int[]{1,1,5}), new int[]{0, -1, 4});

        assert Checker.check(new Solution().minimumTime(3, new int[][]{
            {0,1,2},{1,2,1},{0,2,4}
        }, new int[]{1,3,5}), new int[]{0, 2, 3});
    }

}
