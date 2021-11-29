package problem.p1971findifpathexistsingraph;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 1971. Find if Path Exists in Graph
 *
 * https://leetcode-cn.com/problems/find-if-path-exists-in-graph/
 *
 * There is a bi-directional graph with n vertices, where each vertex is labeled from 0 to n - 1 (inclusive).
 *
 * The edges in the graph are represented as a 2D integer array edges, where each edges[i] = [ui, vi]
 * denotes a bi-directional edge between vertex ui and vertex vi.
 *
 * Every vertex pair is connected by at most one edge, and no vertex has an edge to itself.
 *
 * You want to determine if there is a valid path that exists from vertex start to vertex end.
 *
 * Given edges and the integers n, start, and end,
 * return true if there is a valid path from start to end, or false otherwise.
 */

public class Solution {

    private int[] numbers;

    public boolean validPath(int n, int[][] edges, int start, int end) {
        numbers = new int[n];
        for (int i = 0; i < n; i++) numbers[i] = i;
        for (var edge : edges) {
            int a = find(edge[0]), b = find(edge[1]);
            if (a != b) numbers[b] = a;
        }
        return find(start) == find(end);
    }

    private int find(int v) {
        if (numbers[v] == v) return v;
        return numbers[v] = find(numbers[v]);
    }

    public static void main(String[] args) {
        assert new Solution().validPath(3, new int[][]{{0,1}, {1,2}, {2,0}}, 0, 2);
        assert !new Solution().validPath(6, new int[][]{{0,1}, {0,2}, {3,5}, {5,4}, {4,3}}, 0, 5);
    }

}
