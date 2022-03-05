package weekly.bw73.p2allancestorsofanodeinadirectedacyclicgraph;

import java.util.*;

/**
 * 5300. All Ancestors of a Node in a Directed Acyclic Graph
 *
 * https://leetcode-cn.com/contest/biweekly-contest-73/problems/all-ancestors-of-a-node-in-a-directed-acyclic-graph/
 *
 * You are given a positive integer n representing the number of nodes of a Directed Acyclic Graph (DAG).
 * The nodes are numbered from 0 to n - 1 (inclusive).
 *
 * You are also given a 2D integer array edges, where edges[i] = [fromi, toi] denotes that
 * there is a unidirectional edge from fromi to toi in the graph.
 *
 * Return a list answer, where answer[i] is the list of ancestors of the ith node, sorted in ascending order.
 *
 * A node u is an ancestor of another node v if u can reach v via a set of edges.
 */

public class Solution {

    public List<List<Integer>> getAncestors(int n, int[][] edges) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (var edge : edges) {
            int from = edge[0], to = edge[1];
            if (!map.containsKey(to)) map.put(to, new ArrayList<>());
            map.get(to).add(from);
        }

        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            SortedSet<Integer> ancestors = new TreeSet<>();
            Queue<Integer> queue = new ArrayDeque<>(); queue.add(i);
            while (!queue.isEmpty()) {
                int curr = queue.remove();
                if (curr != i) ancestors.add(curr);

                if (curr < i) ancestors.addAll(ans.get(curr));
                else if (map.containsKey(curr)) {
                    for (int next : map.get(curr)) {
                        if (!ancestors.contains(next)) {
                            queue.add(next);
                        }
                    }
                }
            }

            ans.add(new ArrayList<>(ancestors));
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().getAncestors(8, new int[][]{
            {0,3},{0,4},{1,3},{2,4},{2,7},{3,5},{3,6},{3,7},{4,6}
        }));

        System.out.println(new Solution().getAncestors(5, new int[][]{
            {0,1},{0,2},{0,3},{0,4},{1,2},{1,3},{1,4},{2,3},{2,4},{3,4}
        }));
    }

}
