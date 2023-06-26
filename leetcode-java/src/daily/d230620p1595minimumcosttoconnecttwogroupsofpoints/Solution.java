package daily.d230620p1595minimumcosttoconnecttwogroupsofpoints;

import java.util.List;

/**
 * 1595. Minimum Cost to Connect Two Groups of Points
 *
 * https://leetcode.cn/problems/minimum-cost-to-connect-two-groups-of-points
 *
 * You are given two groups of points where the first group has size1 points, the
 * second group has size2 points, and size1 >= size2.
 *
 * The cost of the connection between any two points are given in an size1 x size2 matrix
 * where cost[i][j] is the cost of connecting point i of the first group
 * and point j of the second group. The groups are connected if each point
 * in both groups is connected to one or more points in the opposite group.
 *
 * In other words, each point in the first group must be connected to at least
 * one point in the second group, and each point in the second group must be
 * connected to at least one point in the first group.
 *
 * Return the minimum cost it takes to connect the two groups.
 */

public class Solution {

    public int connectTwoGroups(List<List<Integer>> cost) {
        dfs(cost, 0, 0, 0);
        return ans;
    }

    private int ans = Integer.MAX_VALUE;

    private void dfs(List<List<Integer>> cost, int col, int i, int sum) {
        if (sum >= ans) return;

        int m = cost.size(), n = cost.get(0).size();
        if (i == m) {
            if (col + 1 == 1 << n) ans = sum;
            return;
        }

        for (int j = 0; j < n; j++) {
            int mask = 0, curr = 0;
            for (int k = j; k < n; k++) {
                mask |= 1 << k;
                curr += cost.get(i).get(k);
                dfs(cost, col | mask, i + 1, sum + curr);
            }
        }
    }

    public static void main(String[] args) {
    }

}
