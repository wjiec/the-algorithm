package daily.d241003p1928minimumcosttoreachdestinationintime;

import java.util.*;

/**
 * 1928. Minimum Cost to Reach Destination in Time
 *
 * https://leetcode.cn/problems/minimum-cost-to-reach-destination-in-time/
 *
 * There is a country of n cities numbered from 0 to n - 1 where all the
 * cities are connected by bi-directional roads. The roads are represented
 * as a 2D integer array edges where edges[i] = [xi, yi, timei] denotes a road
 * between cities xi and yi that takes timei minutes to travel.
 *
 * There may be multiple roads of differing travel times connecting the same two
 * cities, but no road connects a city to itself.
 *
 * Each time you pass through a city, you must pay a passing fee. This is represented
 * as a 0-indexed integer array passingFees of length n where passingFees[j] is the
 * amount of dollars you must pay when you pass through city j.
 *
 * In the beginning, you are at city 0 and want to reach city n - 1 in maxTime
 * minutes or less. The cost of your journey is the summation of passing fees
 * for each city that you passed through at some moment of your journey
 * (including the source and destination cities).
 *
 * Given maxTime, edges, and passingFees, return the minimum cost to complete
 * your journey, or -1 if you cannot complete it within maxTime minutes.
 */

@SuppressWarnings("unchecked")
public class Solution {

    public int minCost(int maxTime, int[][] edges, int[] passingFees) {
        int n = passingFees.length;
        List<int[]>[] g = new List[n];
        Arrays.setAll(g, i -> new ArrayList<>());
        for (var edge : edges) {
            g[edge[0]].add(new int[]{edge[1], edge[2]});
            g[edge[1]].add(new int[]{edge[0], edge[2]});
        }

        // dis[i][j] 表示在 j 时到达 i 的最小花费
        int[][] dis = new int[n][maxTime + 1];
        for (var row : dis) Arrays.fill(row, Integer.MAX_VALUE);
        dis[0][0] = passingFees[0];

        // [node, time, fee]
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(v -> v[2]));
        pq.add(new int[]{0, 0, passingFees[0]});

        while (!pq.isEmpty()) {
            var curr = pq.remove();
            int node = curr[0], time = curr[1], fee = curr[2];

            for (var next : g[node]) {
                int city = next[0];

                int nextTime = time + next[1];
                int nextFee = fee + passingFees[city];
                if (nextTime <= maxTime && nextFee < dis[city][nextTime]) {
                    dis[city][nextTime] = nextFee;
                    pq.add(new int[]{city, nextTime, nextFee});
                }
            }
        }

        int ans = Integer.MAX_VALUE;
        for (int j = 0; j <= maxTime; j++) {
            ans = Math.min(ans, dis[n - 1][j]);
        }

        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    public static void main(String[] args) {
    }

}
