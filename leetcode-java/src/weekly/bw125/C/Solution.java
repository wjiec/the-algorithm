package weekly.bw125.C;

import common.Checker;

import java.util.*;

/**
 * 3067. Count Pairs of Connectable Servers in a Weighted Tree Network
 *
 * https://leetcode.cn/contest/biweekly-contest-125/problems/count-pairs-of-connectable-servers-in-a-weighted-tree-network/
 *
 * You are given an unrooted weighted tree with n vertices representing servers numbered
 * from 0 to n - 1, an array edges where edges[i] = [ai, bi, weighti] represents a
 * bidirectional edge between vertices ai and bi of weight weighti. You are also
 * given an integer signalSpeed.
 *
 * Two servers a and b are connectable through a server c if:
 *
 * a < b, a != c and b != c.
 * The distance from c to a is divisible by signalSpeed.
 * The distance from c to b is divisible by signalSpeed.
 * The path from c to b and the path from c to a do not share any edges.
 *
 * Return an integer array count of length n where count[i] is the number of server
 * pairs that are connectable through the server i.
 */

public class Solution {

    private final int[][] weights = new int[1001][1001];
    private final List<Integer>[] g = new List[1001];
    { Arrays.setAll(g, v -> new ArrayList<>()); }

    public int[] countPairsOfConnectableServers(int[][] edges, int signalSpeed) {
        for (var edge : edges) {
            weights[edge[0]][edge[1]] = edge[2];
            weights[edge[1]][edge[0]] = edge[2];

            g[edge[0]].add(edge[1]);
            g[edge[1]].add(edge[0]);
        }

        int[] ans = new int[edges.length + 1];
        for (int i = 0; i <= ans.length; i++) {
            int sum = 0;
            for (var next : g[i]) {
                var count = walk(next, i, weights[i][next], signalSpeed);
                ans[i] += count * sum;
                sum += count;
            }
        }
        return ans;
    }

    private int walk(int node, int parent, int init, int signalSpeed) {
        // [curr, parent, sum]
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{node, parent, init});

        int ans = 0;
        while (!queue.isEmpty()) {
            var curr = queue.remove();
            if (curr[2] % signalSpeed == 0) ans++;

            for (var next : g[curr[0]]) {
                if (next != curr[1]) {
                    queue.add(new int[]{next, curr[0], curr[2] + weights[curr[0]][next]});
                }
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().countPairsOfConnectableServers(new int[][]{
            {0,1,1},{1,2,5},{2,3,13},{3,4,9},{4,5,2}
        }, 1), new int[]{0,4,6,6,4,0});

        assert Checker.check(new Solution().countPairsOfConnectableServers(new int[][]{
            {0,6,3},{6,5,3},{0,3,1},{3,2,7},{3,1,6},{3,4,2}
        }, 3), new int[]{2,0,0,0,0,0,2});
    }

}
