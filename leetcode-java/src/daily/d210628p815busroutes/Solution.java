package daily.d210628p815busroutes;

import java.util.*;

/**
 * 815. Bus Routes
 *
 * https://leetcode-cn.com/problems/bus-routes/
 *
 * You are given an array routes representing bus routes where routes[i]
 * is a bus route that the ith bus repeats forever.
 *
 * For example, if routes[0] = [1, 5, 7], this means that the 0th bus travels
 * in the sequence 1 -> 5 -> 7 -> 1 -> 5 -> 7 -> 1 -> ... forever.
 *
 * You will start at the bus stop source (You are not on any bus initially),
 * and you want to go to the bus stop target. You can travel between bus stops by buses only.
 *
 * Return the least number of buses you must take to travel from source to target. Return -1 if it is not possible.
 */

public class Solution {

    private static class Bus {
        private final int id, station;
        private Bus(int id, int station) { this.id = id; this.station = station; }
    }

    public int numBusesToDestination(int[][] routes, int source, int target) {
        if (source == target) return 0;

        Map<Integer, List<Bus>> stations = new HashMap<>(), buses = new HashMap<>();
        for (int i = 0, l = routes.length; i < l; i++) {
            buses.putIfAbsent(i + 1, new ArrayList<>());
            for (int station : routes[i]) {
                var bus = new Bus(i + 1, station);
                buses.get(bus.id).add(bus);

                stations.putIfAbsent(station, new ArrayList<>());
                stations.get(station).add(bus);
            }
        }
        if (!stations.containsKey(target)) return -1;

        int[] visited = new int[routes.length + 1];
        Arrays.fill(visited, -1);

        Queue<Bus> queue = new ArrayDeque<>();
        if (stations.containsKey(source)) {
            for (var start : stations.get(source)) {
                visited[start.id] = 1;
                queue.addAll(buses.get(start.id));
            }

            while (!queue.isEmpty()) {
                var bus = queue.remove();
                for (var next : stations.get(bus.station)) {
                    if (visited[next.id] == -1) {
                        visited[next.id] = visited[bus.id] + 1;
                        queue.addAll(buses.get(next.id));
                    }
                }
            }
        }

        int ans = Integer.MAX_VALUE;
        for (var bus : stations.get(target)) {
            if (visited[bus.id] != -1) {
                ans = Math.min(ans, visited[bus.id]);
            }
        }
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    public static void main(String[] args) {
        assert new Solution().numBusesToDestination(new int[][]{{1,2,7}, {3,6,7}}, 1, 1) == 0;
        assert new Solution().numBusesToDestination(new int[][]{{1,2,7}, {3,6,7}}, 1, 6) == 2;
        assert new Solution().numBusesToDestination(new int[][]{{7,12}, {4,5,15}, {6}, {15,19}, {9,12,13}}, 15, 12) == -1;
    }

}
