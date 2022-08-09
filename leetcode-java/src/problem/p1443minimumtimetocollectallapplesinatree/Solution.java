package problem.p1443minimumtimetocollectallapplesinatree;

import java.util.*;

/**
 * 1443. Minimum Time to Collect All Apples in a Tree
 *
 * https://leetcode.cn/problems/minimum-time-to-collect-all-apples-in-a-tree/
 *
 * Given an undirected tree consisting of n vertices numbered from 0 to n-1, which
 * has some apples in their vertices. You spend 1 second to walk over one edge of
 * the tree. Return the minimum time in seconds you have to spend to collect all
 * apples in the tree, starting at vertex 0 and coming back to this vertex.
 *
 * The edges of the undirected tree are given in the array edges, where edges[i] = [ai, bi]
 * means that exists an edge connecting the vertices ai and bi. Additionally, there is a boolean
 * array hasApple, where hasApple[i] = true means that vertex i has an apple; otherwise, it does
 * not have any apple.
 */

@SuppressWarnings("DuplicatedCode")
public class Solution {

    private boolean[] visited;
    private List<Boolean> hasApple;
    private final Map<Integer, Set<Integer>> map = new HashMap<>();

    public int minTime(int n, int[][] edges, List<Boolean> hasApple) {
        visited = new boolean[n];
        this.hasApple = hasApple;
        for (var edge : edges) {
            map.computeIfAbsent(edge[0], v -> new HashSet<>()).add(edge[1]);
            map.computeIfAbsent(edge[1], v -> new HashSet<>()).add(edge[0]);
        }
        return dfs(0)[0];
    }

    private final Map<Integer, int[]> memo = new HashMap<>();

    private int[] dfs(int curr) {
        if (memo.containsKey(curr)) {
            return memo.get(curr);
        }

        visited[curr] = true;
        int ans = 0, count = 0;
        if (map.containsKey(curr)) {
            for (int next : map.get(curr)) {
                if (!visited[next]) {
                    int[] val = dfs(next);
                    if (val[1] != 0) {
                        ans += val[0] + 2;
                        count += val[1];
                    }
                }
            }
        }
        if (hasApple.get(curr)) count++;
        memo.put(curr, new int[]{ans, count});
        return new int[]{ans, count};
    }

    public static void main(String[] args) {
        assert new Solution().minTime(7, new int[][]{
            {0,1},{0,2},{1,4},{1,5},{2,3},{2,6}
        }, List.of(false,false,true,false,true,true,false)) == 8;

        assert new Solution().minTime(7, new int[][]{
            {0,1},{0,2},{1,4},{1,5},{2,3},{2,6}
        }, List.of(false,false,true,false,false,true,false)) == 6;

        assert new Solution().minTime(7, new int[][]{
            {0,1},{0,2},{1,4},{1,5},{2,3},{2,6}
        }, List.of(false,false,false,false,false,false,false)) == 0;
    }

}
