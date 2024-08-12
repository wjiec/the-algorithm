package weekly.w410.B;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 3249. Count the Number of Good Nodes
 *
 * https://leetcode.cn/contest/weekly-contest-410/problems/count-the-number-of-good-nodes/
 *
 * There is an undirected tree with n nodes labeled from 0 to n - 1, and rooted at node 0.
 *
 * You are given a 2D integer array edges of length n - 1, where edges[i] = [ai, bi] indicates
 * that there is an edge between nodes ai and bi in the tree.
 *
 * A node is good if all the subtrees rooted at its children have the same size.
 *
 * Return the number of good nodes in the given tree.
 *
 * A subtree of treeName is a tree consisting of a node in treeName and all of its descendants.
 */

public class Solution {

    private int ans = 0;
    private final Map<Integer, Set<Integer>> g = new HashMap<>();

    public int countGoodNodes(int[][] edges) {
        for (var edge : edges) {
            g.computeIfAbsent(edge[0], k -> new HashSet<>()).add(edge[1]);
            g.computeIfAbsent(edge[1], k -> new HashSet<>()).add(edge[0]);
        }

        countNodes(0, -1);
        return ans;
    }

    private int countNodes(int node, int parent) {
        int sum = 1, cnt = -1; boolean ok = true;
        for (var next : g.get(node)) {
            if (next == parent) continue;

            int curr = countNodes(next, node);
            ok = ok && (cnt == -1 || curr == cnt);
            cnt = curr; sum += curr;
        }

        if (ok) ans++;
        return sum;
    }

    public static void main(String[] args) {
    }

}
