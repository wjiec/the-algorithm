package problem.p1615maximalnetworkrank;

import java.util.HashSet;
import java.util.Set;

/**
 * 1615. Maximal Network Rank
 *
 * https://leetcode.cn/problems/maximal-network-rank/
 *
 * There is an infrastructure of n cities with some number of roads connecting these cities.
 * Each roads[i] = [ai, bi] indicates that there is a bidirectional road between cities ai and bi.
 *
 * The network rank of two different cities is defined as the total number of directly connected
 * roads to either city. If a road is directly connected to both cities, it is only counted once.
 *
 * The maximal network rank of the infrastructure is the maximum network rank of all pairs of different cities.
 *
 * Given the integer n and the array roads, return the maximal network rank of the entire infrastructure.
 */

@SuppressWarnings("DuplicatedCode")
public class Solution {

    public int maximalNetworkRank(int n, int[][] roads) {
        Set<Integer>[] rank = new Set[n];
        for (int i = 0; i < n; i++) rank[i] = new HashSet<>();
        for (var road : roads) {
            rank[road[0]].add(road[1]);
            rank[road[1]].add(road[0]);
        }

        int[] size = new int[n];
        for (int i = 0; i < n; i++) size[i] = rank[i].size();

        int ans = 0;
        for (int i = 0; i < n; i++) {
            Set<Integer> s = rank[i];
            for (int j = 0; j < n; j++) {
                if (i == j) continue;
                ans = Math.max(ans, size[i] + size[j] + (s.contains(j) ? -1 : 0));
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().maximalNetworkRank(4, new int[][]{{0,1},{0,3},{1,2},{1,3}}) == 4;
        assert new Solution().maximalNetworkRank(5, new int[][]{{0,1},{0,3},{1,2},{1,3},{2,3},{2,4}}) == 5;
        assert new Solution().maximalNetworkRank(8, new int[][]{{0,1},{1,2},{2,3},{2,4},{5,6},{5,7}}) == 5;
    }

}
