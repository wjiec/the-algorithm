package problem.p1029twocityscheduling;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 1029. Two City Scheduling
 *
 * https://leetcode.cn/problems/two-city-scheduling/
 *
 * A company is planning to interview 2n people. Given the array costs where costs[i] = [aCosti, bCosti], the
 * cost of flying the ith person to city a is aCosti, and the cost of flying the ith person to city b is bCosti.
 *
 * Return the minimum cost to fly every person to a city such that exactly n people arrive in each city.
 */

public class Solution {

    public int twoCitySchedCost(int[][] costs) {
        // 假定所有人都去B市
        // 此时从去B市的人种挑出N个去A市，公司需要额外出的钱是 sum += aCost - bCost (去A市的费用，返还去B市的费用)
        // 所以只需要排序 aCost - bCost , 然后让前N个人去A市，后N个人去B市
        Arrays.sort(costs, Comparator.comparingInt(v -> v[0] - v[1]));

        int ans = 0, n = costs.length / 2;
        for (int i = 0; i < n; i++) {
            ans += costs[i][0] + costs[i + n][1];
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().twoCitySchedCost(new int[][]{
            {10,20},{30,200},{400,50},{30,20}
        }) == 110;
        assert new Solution().twoCitySchedCost(new int[][]{
            {259,770},{448,54},{926,667},{184,139},{840,118},{577,469}
        }) == 1859;
        assert new Solution().twoCitySchedCost(new int[][]{
            {515,563},{451,713},{537,709},{343,819},{855,779},{457,60},{650,359},{631,42}
        }) == 3086;
    }

}
