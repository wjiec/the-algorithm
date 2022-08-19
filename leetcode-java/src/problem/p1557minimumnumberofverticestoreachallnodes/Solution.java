package problem.p1557minimumnumberofverticestoreachallnodes;

import common.Checker;

import java.util.ArrayList;
import java.util.List;

/**
 * 1557. Minimum Number of Vertices to Reach All Nodes
 *
 * https://leetcode.cn/problems/minimum-number-of-vertices-to-reach-all-nodes/
 *
 * Given a directed acyclic graph, with n vertices numbered from 0 to n-1, and an array edges
 * where edges[i] = [fromi, toi] represents a directed edge from node fromi to node toi.
 *
 * Find the smallest set of vertices from which all nodes in the graph are reachable.
 * It's guaranteed that a unique solution exists.
 *
 * Notice that you can return the vertices in any order.
 */

public class Solution {

    public List<Integer> findSmallestSetOfVertices(int n, List<List<Integer>> edges) {
        boolean[] tome = new boolean[n];
        for (var edge : edges) tome[edge.get(1)] = true;

        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < n; i++) if (!tome[i]) ans.add(i);
        return ans;
    }

    public static void main(String[] args) {
        assert Checker.anyOrder(new Solution().findSmallestSetOfVertices(6, List.of(
            List.of(0,1),List.of(0,2),List.of(2,5),List.of(3,4),List.of(4,2)
        )), List.of(0,3));

        assert Checker.anyOrder(new Solution().findSmallestSetOfVertices(5, List.of(
            List.of(0,1),List.of(2,1),List.of(3,1),List.of(1,4),List.of(2,4)
        )), List.of(0,2,3));
    }

}
