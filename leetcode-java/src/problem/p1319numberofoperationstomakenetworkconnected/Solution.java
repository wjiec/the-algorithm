package problem.p1319numberofoperationstomakenetworkconnected;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 1319. Number of Operations to Make Network Connected
 *
 * https://leetcode.cn/problems/number-of-operations-to-make-network-connected/
 *
 * There are n computers numbered from 0 to n - 1 connected by ethernet cables connections
 * forming a network where connections[i] = [ai, bi] represents a connection between computers ai and bi.
 * Any computer can reach any other computer directly or indirectly through the network.
 *
 * You are given an initial computer network connections. You can extract certain cables
 * between two directly connected computers, and place them between any pair of disconnected
 * computers to make them directly connected.
 *
 * Return the minimum number of times you need to do this in order to make all the computers connected.
 * If it is not possible, return -1.
 */

@SuppressWarnings("DuplicatedCode")
public class Solution {

    private final Map<Integer, Set<Integer>> map = new HashMap<>();

    public int makeConnected(int n, int[][] connections) {
        if (connections.length < n - 1) return -1;
        for (var conn : connections) {
            map.computeIfAbsent(conn[0], v -> new HashSet<>()).add(conn[1]);
            map.computeIfAbsent(conn[1], v -> new HashSet<>()).add(conn[0]);
        }

        int ans = 0;
        boolean[] visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                dfs(visited, i);
                ans++;
            }
        }

        return ans - 1;
    }

    private void dfs(boolean[] visited, int curr) {
        visited[curr] = true;
        if (map.containsKey(curr)) {
            for (var next : map.get(curr)) {
                if (!visited[next]) {
                    dfs(visited, next);
                }
            }
        }
    }

    public static void main(String[] args) {
        assert new Solution().makeConnected(4, new int[][]{{0,1},{0,2},{1,2}}) == 1;
        assert new Solution().makeConnected(6, new int[][]{{0,1},{0,2},{0,3},{1,2},{1,3}}) == 2;
        assert new Solution().makeConnected(6, new int[][]{{0,1},{0,2},{0,3},{1,2}}) == -1;
        assert new Solution().makeConnected(5, new int[][]{{0,1},{0,2},{3,4},{2,3}}) == 0;
    }

}
