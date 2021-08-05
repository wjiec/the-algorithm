package daily.d210805p802findeventualsafestates;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 802. Find Eventual Safe States
 *
 * https://leetcode-cn.com/problems/find-eventual-safe-states/
 *
 * We start at some node in a directed graph, and every turn,
 * we walk along a directed edge of the graph.
 * If we reach a terminal node (that is, it has no outgoing directed edges), we stop.
 *
 * We define a starting node to be safe if we must eventually walk to a terminal node.
 * More specifically, there is a natural number k,
 * so that we must have stopped at a terminal node in less than k steps for any choice of where to walk.
 *
 * Return an array containing all the safe nodes of the graph. The answer should be sorted in ascending order.
 *
 * The directed graph has n nodes with labels from 0 to n - 1, where n is the length of graph.
 * The graph is given in the following form:
 *  graph[i] is a list of labels j such that (i, j) is a directed edge of the graph,
 *  going from node i to node j.
 */

public class Solution {

    public List<Integer> eventualSafeNodes(int[][] graph) {
        int[] color = new int[graph.length];
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < graph.length; i++) if (safe(graph, color, i)) ans.add(i);
        return ans;
    }

    // 0 - unvisited    1 - visited     2 - safe
    private boolean safe(int[][] graph, int[] color, int x) {
        if (color[x] > 0) return color[x] == 2;

        color[x] = 1; // visited
        for (int y : graph[x]) {
            if (!safe(graph, color, y)) return false;
        }

        color[x] = 2; // safe
        return true;
    }

    private final boolean[] loop = new boolean[10000];

    // timeout
    public List<Integer> eventualSafeNodes1(int[][] graph) {
        for (int i = 0; i < graph.length; i++) {
            if (!loop[i]) dfs(graph, i, new HashSet<>());
        }

        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < graph.length; i++) {
            if (!loop[i]) ans.add(i);
        }
        return ans;
    }

    private void dfs(int[][] graph, int from, Set<Integer> route) {
        route.add(from);
        for (var next : graph[from]) {
            if (route.contains(next) || loop[next]) {
                for (var n : route) loop[n] = true;
            } else {
                dfs(graph, next, route);
            }
        }
        route.remove(from);
    }

    public static void main(String[] args) {
        assert new Solution().eventualSafeNodes(new int[][]{{0},{2,3,4},{3,4},{0,4},{}}).equals(List.of(4));

        assert new Solution().eventualSafeNodes(new int[][]{{1,2},{2,3},{5},{0},{5},{},{}}).equals(List.of(2,4,5,6));
        assert new Solution().eventualSafeNodes(new int[][]{{1,2,3,4},{1,2},{3,4},{0,4},{}}).equals(List.of(4));
    }

}
