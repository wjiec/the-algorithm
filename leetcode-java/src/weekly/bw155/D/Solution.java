package weekly.bw155.D;

import ability.Ability.UnionFind;
import common.PrettyPrinter;

import java.util.*;

/**
 * 3530. Maximum Profit from Valid Topological Order in DAG
 *
 * https://leetcode.cn/contest/biweekly-contest-155/problems/maximum-profit-from-valid-topological-order-in-dag/
 *
 * You are given a Directed Acyclic Graph (DAG) with n nodes labeled from 0 to n - 1, represented
 * by a 2D array edges, where edges[i] = [ui, vi] indicates a directed edge from node ui to vi.
 *
 * Each node has an associated score given in an array score, where score[i] represents the score of node i.
 *
 * You must process the nodes in a valid topological order.
 * Each node is assigned a 1-based position in the processing order.
 *
 * The profit is calculated by summing up the product of each node's score and its position in the ordering.
 *
 * Return the maximum possible profit achievable with an optimal topological order.
 *
 * A topological order of a DAG is a linear ordering of its nodes
 * such that for every directed edge u → v, node u comes before v in the ordering.
 */

public class Solution {

    // 拓扑排序之后的节点分数乘以索引的和的最大值
    //  - 有可能有多个集合
    public int maxProfit(int n, int[][] edges, int[] score) {
        UnionFind uf = new UnionFind(n);
        for (var edge : edges) uf.union(edge[0], edge[1]);

        Map<Integer, Set<Integer>> groups = new HashMap<>();
        for (int i = 0; i < n; i++) {
            groups.computeIfAbsent(uf.find(i), k -> new HashSet<>()).add(i);
        }

        // 每一组里都需要进行拓扑排序并找到每一组的最佳顺序
        List<List<Integer>> sortedGroups = new ArrayList<>();
        for (var group : groups.values()) {
            sortedGroups.add(sortGroup(n, edges, group, score));
        }

        PrettyPrinter.println(sortedGroups);

        return dfs(sortedGroups, new boolean[sortedGroups.size()], 1);
    }

    private int dfs(List<List<Integer>> groups, boolean[] seen, int idx) {
        int ans = 0;
        for (int i = 0; i < groups.size(); i++) {
            if (!seen[i]) {
                int curr = 0, x = idx;
                for (var v : groups.get(i)) {
                    curr += v * x++;
                }

                seen[i] = true;
                ans = Math.max(ans, curr + dfs(groups, seen, x));
                seen[i] = false;
            }
        }

        return ans;
    }

    /** @noinspection unchecked*/
    private List<Integer> sortGroup(int n, int[][] edges, Set<Integer> group, int[] score) {
        int[] indegree = new int[n];
        for (int i = 0; i < n; i++) {
            if (!group.contains(i)) {
                indegree[i] = -1;
            }
        }

        List<Integer>[] g = new List[n];
        Arrays.setAll(g, i -> new ArrayList<>());
        for (var edge : edges) {
            if (group.contains(edge[0])) {
                g[edge[0]].add(edge[1]);
                indegree[edge[1]]++;
            }
        }

        int[] level = new int[n];
        Queue<Integer> q = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            if (indegree[i] == 0) {
                level[i] = 1;
                q.add(i);
            }
        }

        int maxLevel = 1;
        while (!q.isEmpty()) {
            int curr = q.remove();
            for (var next : g[curr]) {
                if (--indegree[next] == 0) {
                    q.add(next);
                    level[next] = level[curr] + 1;
                    maxLevel = Math.max(maxLevel, level[next]);
                }
            }
        }

        List<Integer>[] levelScore = new List[maxLevel + 1];
        Arrays.setAll(levelScore, i -> new ArrayList<>());
        for (int i = 0; i < n; i++) {
            if (level[i] > 0) {
                levelScore[level[i]].add(score[i]);
            }
        }


        List<Integer> ans = new ArrayList<>();
        for (var elem : levelScore) {
            elem.sort(Integer::compare);
            ans.addAll(elem);
        }
        return ans;
    }

    public static void main(String[] args) {
        // 53407 -> 92726
        // 89087
        // 53407 * 1 + 89087 * 2 + 92726 * 3
        assert new Solution().maxProfit(3, new int[][]{{0,2}}, new int[]{53407,89087,92726}) == 509759;
        assert new Solution().maxProfit(3, new int[][]{{0,1}}, new int[]{21915,50942,52739}) == 282016;
    }

}
