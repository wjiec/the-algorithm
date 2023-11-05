package weekly.w370.B;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 100116. Find Champion II
 *
 * https://leetcode.cn/contest/weekly-contest-370/problems/find-champion-ii/
 *
 * There are n teams numbered from 0 to n - 1 in a tournament; each team is also a node in a DAG.
 *
 * You are given the integer n and a 0-indexed 2D integer array edges of length m representing
 * the DAG, where edges[i] = [ui, vi] indicates that there is a directed edge from team ui to team vi in the graph.
 *
 * A directed edge from a to b in the graph means that team a is stronger than team b and team b is weaker than team a.
 *
 * Team a will be the champion of the tournament if there is no team b that is stronger than team a.
 *
 * Return the team that will be the champion of the tournament if there is a unique champion, otherwise, return -1.
 *
 * Notes
 *
 * A cycle is a series of nodes a1, a2, ..., an, an+1 such that node a1 is the same node as node an+1, the
 * nodes a1, a2, ..., an are distinct, and there is a directed edge from the node ai to node ai+1 for
 * every i in the range [1, n].
 *
 * A DAG is a directed graph that does not have any cycle.
 */

public class Solution {

    private List<Integer>[] map = new List[101];
    { Arrays.setAll(map, v -> new ArrayList<>()); }

    public int findChampion(int n, int[][] edges) {
        for (var edge : edges) map[edge[0]].add(edge[1]);

        int ans = -1;
        for (int i = 0; i < n; i++) {
            if (dfs(i, new boolean[n]) == n) {
                if (ans != -1) return -1;
                ans = i;
            }
        }
        return ans;
    }


    private int dfs(int curr, boolean[] seen) {
        int ans = 1;
        for (var next : map[curr]) {
            if (!seen[next]) {
                seen[next] = true;
                ans += dfs(next, seen);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
    }

}
