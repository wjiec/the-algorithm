package daily.d210824pcheapestflightswithinkstops;

import java.util.Arrays;

/**
 * 787. Cheapest Flights Within K Stops
 *
 * https://leetcode-cn.com/problems/cheapest-flights-within-k-stops/
 *
 * There are n cities connected by some number of flights.
 * You are given an array flights where flights[i] = [fromi, toi, pricei]
 * indicates that there is a flight from city fromi to city toi with cost pricei.
 *
 * You are also given three integers src, dst, and k,
 * return the cheapest price from src to dst with at most k stops.
 *
 * If there is no such route, return -1.
 */

public class Solution {

    private final int INF = 10000 * 101 + 1;

    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        int[][] map = new int[k + 2][n];
        for (int i = 0; i < k + 2; i++) {
            Arrays.fill(map[i], INF);
        }

        map[0][src] = 0;
        for (int i = 1; i < k + 2; i++) {
            for (var flight : flights) {
                int s = flight[0], d = flight[1], c = flight[2];
                map[i][d] = Math.min(map[i][d], map[i - 1][s] + c);
            }
        }

        int ans = INF;
        for (int i = 1; i < k + 2; i++) {
            ans = Math.min(ans, map[i][dst]);
        }
        return ans == INF ? -1 : ans;
    }

    public static void main(String[] args) {
        assert new Solution().findCheapestPrice(3, new int[][]{{0,1,100}, {1,2,100}, {0,2,500}}, 0, 2, 1) == 200;
        assert new Solution().findCheapestPrice(3, new int[][]{{0,1,100}, {1,2,100}, {0,2,500}}, 0, 2, 0) == 500;
    }

}
