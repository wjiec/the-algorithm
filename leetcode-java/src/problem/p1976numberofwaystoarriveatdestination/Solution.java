package problem.p1976numberofwaystoarriveatdestination;

import ability.Graph;
import common.TODO;

/**
 * 1976. Number of Ways to Arrive at Destination
 *
 * https://leetcode.cn/problems/number-of-ways-to-arrive-at-destination/
 *
 * You are in a city that consists of n intersections numbered from 0 to n - 1 with bi-directional roads
 * between some intersections. The inputs are generated such that you can reach any intersection from
 * any other intersection and that there is at most one road between any two intersections.
 *
 * You are given an integer n and a 2D integer array roads where roads[i] = [ui, vi, timei] means that
 * there is a road between intersections ui and vi that takes timei minutes to travel.
 *
 * You want to know in how many ways you can travel from intersection 0 to intersection n - 1
 * in the shortest amount of time.
 *
 * Return the number of ways you can arrive at your destination in the shortest amount of time.
 * Since the answer may be large, return it modulo 109 + 7.
 */

public class Solution {

    @TODO
    public int countPaths(int n, int[][] roads) {
        Graph.Dijkstra dijkstra = new Graph.Dijkstra(n);
        for (var road : roads) dijkstra.addEdge(road[0], road[1], road[2]);

        long[] distance = dijkstra.distance(0);
        Graph.TopologicalSort ts = new Graph.TopologicalSort(n);
        for (var road : roads) {
            int a = road[0], b = road[1], w = road[2];
            if (distance[a] + w == distance[b]) ts.addEdge(a, b);
            if (distance[b] + w == distance[a]) ts.addEdge(b, a);
        }

        // TODO
        return 1;
    }

    public static void main(String[] args) {
        assert new Solution().countPaths(7, new int[][]{
            {0,6,7},{0,1,2},{1,2,3},{1,3,3},{6,3,3},
            {3,5,1},{6,5,1},{2,5,1},{0,4,5},{4,6,2}
        }) == 4;
        assert new Solution().countPaths(2, new int[][]{{1,0,10}}) == 1;
    }

}
