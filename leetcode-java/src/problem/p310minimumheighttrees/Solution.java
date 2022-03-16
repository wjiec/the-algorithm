package problem.p310minimumheighttrees;

import common.Checker;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * 310. Minimum Height Trees
 *
 * https://leetcode-cn.com/problems/minimum-height-trees/
 *
 * A tree is an undirected graph in which any two vertices are connected by exactly one path.
 * In other words, any connected graph without simple cycles is a tree.
 *
 * Given a tree of n nodes labelled from 0 to n - 1, and an array of n - 1 edges
 * where edges[i] = [ai, bi] indicates that there is an undirected edge between
 * the two nodes ai and bi in the tree, you can choose any node of the tree as the root.
 * When you select a node x as the root, the result tree has height h. Among all possible
 * rooted trees, those with minimum height (i.e. min(h)) are called minimum height trees (MHTs).
 *
 * Return a list of all MHTs' root labels. You can return the answer in any order.
 *
 * The height of a rooted tree is the number of edges on the longest downward path
 * between the root and a leaf.
 */

public class Solution {

    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        if (n == 1) return List.of(0);

        int[] count = new int[n];
        List<List<Integer>> map = new ArrayList<>();
        for (int i = 0; i < n; i++) map.add(new ArrayList<>());

        for (var edge : edges) {
            count[edge[0]]++;
            count[edge[1]]++;
            map.get(edge[0]).add(edge[1]);
            map.get(edge[1]).add(edge[0]);
        }

        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < n; i++) if (count[i] == 1) queue.add(i);

        List<Integer> ans = new ArrayList<>();
        while (!queue.isEmpty()) {
            ans.clear();
            for (int i = 0, sz = queue.size(); i < sz; i++) {
                int curr = queue.remove(); ans.add(curr);
                for (var next : map.get(curr)) {
                    if (--count[next] == 1) {
                        queue.add(next);
                    }
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        assert Checker.anyOrder(new Solution().findMinHeightTrees(4,
            new int[][]{{1,0}, {1,2}, {1,3}}), List.of(1));

        assert Checker.anyOrder(new Solution().findMinHeightTrees(6,
            new int[][]{{3,0},{3,1},{3,2},{3,4},{5,4}}), List.of(3,4));
    }

}
