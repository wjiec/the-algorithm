package weekly.w350.D;

import java.util.HashMap;
import java.util.Map;

/**
 * 6447. Painting the Walls
 *
 * https://leetcode.cn/contest/weekly-contest-350/problems/painting-the-walls/
 *
 * You are given two 0-indexed integer arrays, cost and time, of size n representing the costs
 * and the time taken to paint n different walls respectively. There are two painters available:
 *
 * A paid painter that paints the ith wall in time[i] units of time and takes cost[i] units of money.
 *
 * A free painter that paints any wall in 1 unit of time at a cost of 0. But the free painter can
 * only be used if the paid painter is already occupied.
 *
 * Return the minimum amount of money required to paint the n walls.
 */

@SuppressWarnings("unchecked")
public class Solution {

    public int paintWalls(int[] cost, int[] time) {
        return (int) dfs(cost, time, 0, 0);
    }

    private final Map<Integer, Long>[] memo = new Map[501];

    private long dfs(int[] cost, int[] time, int i, int free) {
        if (free >= cost.length - i) return 0;
        if (i == cost.length) return Integer.MAX_VALUE;

        if (memo[i] == null) memo[i] = new HashMap<>();
        if (!memo[i].containsKey(free)) {
            memo[i].put(free, Math.min(
                dfs(cost, time, i + 1, free + time[i]) + cost[i], // 收费
                dfs(cost, time, i + 1, free - 1) // 免费
            ));
        }
        return memo[i].get(free);
    }

    public static void main(String[] args) {
        assert new Solution().paintWalls(new int[]{26,53,10,24,25,20,63,51}, new int[]{1,1,1,1,2,2,2,1}) == 55;

        assert new Solution().paintWalls(new int[]{1,2,3,2}, new int[]{1,2,3,2}) == 3;
        assert new Solution().paintWalls(new int[]{2,3,4,2}, new int[]{1,1,1,1}) == 4;
    }

}
