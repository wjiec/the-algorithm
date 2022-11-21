package problem.p2568minimumfuelcosttoreporttothecapital;

import java.util.*;

/**
 * 6243. Minimum Fuel Cost to Report to the Capital
 *
 * https://leetcode.cn/problems/minimum-fuel-cost-to-report-to-the-capital/
 *
 * There is a tree (i.e., a connected, undirected graph with no cycles) structure country network consisting of n cities numbered from 0 to n - 1 and exactly n - 1 roads. The capital city is city 0. You are given a 2D integer array roads where roads[i] = [ai, bi] denotes that there exists a bidirectional road connecting cities ai and bi.
 *
 * There is a meeting for the representatives of each city. The meeting is in the capital city.
 *
 * There is a car in each city. You are given an integer seats that indicates the number of seats in each car.
 *
 * A representative can use the car in their city to travel or change the car and ride with another representative. The cost of traveling between two cities is one liter of fuel.
 *
 * Return the minimum number of liters of fuel to reach the capital city.
 */

@SuppressWarnings("DuplicatedCode")
public class Solution {

    private final int CARS = 1, SEAT = 2, FUEL = 3;

    public long minimumFuelCost(int[][] roads, int seats) {
        Map<Integer, Integer> degree = new HashMap<>();
        Map<Integer, Set<Integer>> edges = new HashMap<>();
        for (var road : roads) {
            degree.merge(road[0], 1, Integer::sum);
            degree.merge(road[1], 1, Integer::sum);
            edges.computeIfAbsent(road[0], v -> new HashSet<>()).add(road[1]);
            edges.computeIfAbsent(road[1], v -> new HashSet<>()).add(road[0]);
        }

        degree.merge(0, 1, Integer::sum);
        edges.computeIfAbsent(0, v -> new HashSet<>());

        // [node, cars, seat, fuel]
        Queue<long[]> scanQueue = new ArrayDeque<>();
        for (var node : degree.entrySet()) {
            if (node.getValue() == 1) {
                scanQueue.add(new long[]{node.getKey(), 1, 1, 0});
            }
        }

        int[] index = new int[roads.length + 1];
        Queue<Integer> indexQueue = new ArrayDeque<>();
        index[0] = 1; indexQueue.add(0);
        while (!indexQueue.isEmpty()) {
            int node = indexQueue.remove();
            for (var next : edges.get(node)) {
                if (index[next] == 0) {
                    index[next] = index[node] + 1;
                    indexQueue.add(next);
                }
            }
        }

        long ans = 0;
        Map<Integer, long[]> memo = new HashMap<>();
        while (!scanQueue.isEmpty()) {
            long[] curr = scanQueue.remove();

            int node = (int) curr[0];
            if (node == 0) { ans += curr[FUEL]; continue; }

            for (var next : edges.get(node)) {
                if (index[node] < index[next]) continue;

                long[] state = new long[]{next, curr[CARS], curr[SEAT], curr[FUEL] + curr[CARS]};

                long[] prevState = memo.get(next);
                if (prevState != null) {
                    state[CARS] += prevState[CARS];
                    state[SEAT] += prevState[SEAT];
                    state[FUEL] += prevState[FUEL];
                }

                memo.put(next, state);
                if (degree.merge(next, -1, Integer::sum) == 1) {
                    // 需要加上当前节点的人
                    ++state[SEAT];
                    // 直接计算需要几辆车(向上取整)
                    state[CARS] = (state[SEAT] + seats - 1) / seats;

                    scanQueue.add(state);
                }
            }
        }

        return ans;
    }

    private static class Optimization {

        private long ans = 0, seats = 0;
        private final Map<Integer, Set<Integer>> edges = new HashMap<>();

        public long minimumFuelCost(int[][] roads, int seats) {
            if (roads.length == 0) return 0;
            for (var road : roads) {
                edges.computeIfAbsent(road[0], v -> new HashSet<>()).add(road[1]);
                edges.computeIfAbsent(road[1], v -> new HashSet<>()).add(road[0]);
            }

            this.seats = seats;
            dfs(0, -1);
            return ans;
        }

        private int dfs(int curr, int parent) {
            int people = 1;
            for (var next : edges.get(curr)) {
                if (next != parent) {
                    people += dfs(next, curr);
                }
            }

            if (curr != 0) ans += (people + seats - 1) / seats;
            return people;
        }
    }

    public static void main(String[] args) {
        assert new Solution().minimumFuelCost(new int[][]{{1,0},{0,2},{3,1},{1,4},{5,0}}, 1) == 7;
        assert new Solution().minimumFuelCost(new int[][]{{0,1},{0,2},{1,3},{1,4}}, 5) == 4;

        assert new Solution().minimumFuelCost(new int[][]{{0,1},{0,2},{0,3}}, 5) == 3;
        assert new Solution().minimumFuelCost(new int[][]{{3,1},{3,2},{1,0},{0,4},{0,5},{4,6}}, 2) == 7;
        assert new Solution().minimumFuelCost(new int[][]{}, 1) == 0;

        assert new Optimization().minimumFuelCost(new int[][]{{1,0},{0,2},{3,1},{1,4},{5,0}}, 1) == 7;
        assert new Optimization().minimumFuelCost(new int[][]{{0,1},{0,2},{1,3},{1,4}}, 5) == 4;

        assert new Optimization().minimumFuelCost(new int[][]{{0,1},{0,2},{0,3}}, 5) == 3;
        assert new Optimization().minimumFuelCost(new int[][]{{3,1},{3,2},{1,0},{0,4},{0,5},{4,6}}, 2) == 7;
        assert new Optimization().minimumFuelCost(new int[][]{}, 1) == 0;
    }

}
