package daily.d230315p1615maximalnetworkrank;

import java.util.HashSet;
import java.util.Set;

/**
 * 615. Maximal Network Rank
 *
 * https://leetcode.cn/problems/maximal-network-rank/
 *
 * There is an infrastructure of n cities with some number of roads connecting these cities.
 * Each roads[i] = [ai, bi] indicates that there is a bidirectional road between cities ai and bi.
 *
 * The network rank of two different cities is defined as the total number of directly
 * connected roads to either city. If a road is directly connected to both cities, it is only
 * counted once.
 *
 * The maximal network rank of the infrastructure is the maximum network rank of all
 * pairs of different cities.
 *
 * Given the integer n and the array roads, return the maximal network rank of the
 * entire infrastructure.
 */

@SuppressWarnings("DuplicatedCode")
public class Solution {

    public int maximalNetworkRank(int n, int[][] roads) {
        Set<Integer>[] edges = new Set[n];
        for (int i = 0; i < n; i++) edges[i] = new HashSet<>();
        for (var road : roads) {
            edges[road[0]].add(road[1]);
            edges[road[1]].add(road[0]);
        }

        int ans = 0;
        for (int a = 0; a < n; a++) {
            for (int b = a + 1; b < n; b++) {
                int curr = edges[a].size() + edges[b].size();
                if (edges[a].contains(b)) curr--;
                ans = Math.max(ans, curr);
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
