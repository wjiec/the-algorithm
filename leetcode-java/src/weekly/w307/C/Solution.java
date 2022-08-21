package weekly.w307.C;

import common.TreeNode;

import java.util.*;

/**
 * 6154. Amount of Time for Binary Tree to Be Infected
 *
 * https://leetcode.cn/contest/weekly-contest-307/problems/amount-of-time-for-binary-tree-to-be-infected/
 *
 * You are given the root of a binary tree with unique values, and an integer start.
 * At minute 0, an infection starts from the node with value start.
 *
 * Each minute, a node becomes infected if:
 *
 * The node is currently uninfected.
 * The node is adjacent to an infected node.
 * Return the number of minutes needed for the entire tree to be infected.
 */

public class Solution {

    private final Map<Integer, Set<Integer>> map = new HashMap<>();

    public int amountOfTime(TreeNode root, int start) {
        dfs(root, -1);

        boolean[] visited = new boolean[100_001];
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{start, 0});
        visited[start] = true;

        int ans = 0;
        while (!queue.isEmpty()) {
            int[] curr = queue.remove();
            if (curr[1] > ans) ans = curr[1];
            for (var next : map.get(curr[0])) {
                if (!visited[next]) {
                    visited[next] = true;
                    queue.add(new int[]{next, curr[1] + 1});
                }
            }
        }
        return ans;
    }

    private void dfs(TreeNode node, int parent) {
        Set<Integer> set = new HashSet<>();
        if (parent != -1) set.add(parent);
        if (node.left != null) set.add(node.left.val);
        if (node.right != null) set.add(node.right.val);
        map.put(node.val, set);

        if (node.left != null) dfs(node.left, node.val);
        if (node.right != null) dfs(node.right, node.val);
    }

    public static void main(String[] args) {
        assert new Solution().amountOfTime(TreeNode.build(1,5,3,null,4,10,6,9,2), 3) == 4;
        assert new Solution().amountOfTime(TreeNode.build(1), 1) == 0;
        assert new Solution().amountOfTime(TreeNode.build(2,5), 5) == 1;
    }

}
