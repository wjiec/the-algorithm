package daily.d210802p743networkdelaytime;

import java.util.Arrays;

/**
 * 743. Network Delay Time
 *
 * https://leetcode-cn.com/problems/network-delay-time/
 *
 * You are given a network of n nodes, labeled from 1 to n. You are also given times,
 * a list of travel times as directed edges times[i] = (ui, vi, wi),
 * where ui is the source node, vi is the target node,
 * and wi is the time it takes for a signal to travel from source to target.
 *
 * We will send a signal from a given node k.
 * Return the time it takes for all the n nodes to receive the signal.
 * If it is impossible for all the n nodes to receive the signal, return -1.
 */

public class Solution {

    // @TODO
    public int networkDelayTime(int[][] times, int n, int k) {
        final int INF = Integer.MAX_VALUE / 2;
        int[][] g = new int[n][n];
        for (int i = 0; i < n; ++i) {
            Arrays.fill(g[i], INF);
        }
        for (int[] t : times) {
            int x = t[0] - 1, y = t[1] - 1;
            g[x][y] = t[2];
        }

        int[] dist = new int[n];
        Arrays.fill(dist, INF);
        dist[k - 1] = 0;
        boolean[] used = new boolean[n];
        for (int i = 0; i < n; ++i) {
            int x = -1;
            for (int y = 0; y < n; ++y) {
                if (!used[y] && (x == -1 || dist[y] < dist[x])) {
                    x = y;
                }
            }
            used[x] = true;
            for (int y = 0; y < n; ++y) {
                dist[y] = Math.min(dist[y], dist[x] + g[x][y]);
            }
        }

        int ans = Arrays.stream(dist).max().getAsInt();
        return ans == INF ? -1 : ans;
    }

    public static void main(String[] args) {
        assert new Solution().networkDelayTime(new int[][]{
            {1,2,1}, {2,3,2}, {1,3,2}
        }, 3, 1) == 2;

        assert new Solution().networkDelayTime(new int[][]{
            {1,2,1}, {2,1,3}
        }, 2, 2) == 3;

        assert new Solution().networkDelayTime(new int[][]{
            {2,1,1}, {2,3,1}, {3,4,1}
        }, 4, 2) == 2;

        assert new Solution().networkDelayTime(new int[][]{
            {1,2,1}
        }, 2, 1) == 1;

        assert new Solution().networkDelayTime(new int[][]{
            {1,2,1}
        }, 2, 2) == -1;
    }

}
