package problem.p1791findcenterofstargraph;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 * 1791. Find Center of Star Graph
 *
 * https://leetcode-cn.com/problems/find-center-of-star-graph/
 *
 * There is an undirected star graph consisting of n nodes labeled from 1 to n.
 *
 * A star graph is a graph where there is one center node and exactly n - 1 edges
 * that connect the center node with every other node.
 *
 * You are given a 2D integer array edges where each edges[i] = [ui, vi] indicates that
 * there is an edge between the nodes ui and vi.
 *
 * Return the center of the given star graph.
 */

public class Solution {

    public int findCenter(int[][] edges) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0, l = Math.min(3, edges.length); i < l; i++) {
            map.merge(edges[i][0], 1, Integer::sum);
            map.merge(edges[i][1], 1, Integer::sum);
        }
        return map.entrySet().stream().max(Comparator.comparingInt(Map.Entry::getValue)).get().getKey();
    }

    public static void main(String[] args) {
        assert new Solution().findCenter(new int[][]{
            {1,2}, {2,3}, {4,2}
        }) == 2;

        assert new Solution().findCenter(new int[][]{
            {1,2}, {5,1}, {1,3}, {1,4}
        }) == 1;
    }

}
