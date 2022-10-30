package weekly.w317.D;

import common.Checker;
import common.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * 6223. Height of Binary Tree After Subtree Removal Queries
 *
 * https://leetcode.cn/contest/weekly-contest-317/problems/height-of-binary-tree-after-subtree-removal-queries/
 *
 * You are given the root of a binary tree with n nodes. Each node is assigned a unique
 * value from 1 to n. You are also given an array queries of size m.
 *
 * You have to perform m independent queries on the tree where in the ith query you do the following:
 *
 * Remove the subtree rooted at the node with the value queries[i] from the tree.
 * It is guaranteed that queries[i] will not be equal to the value of the root.
 * Return an array answer of size m where answer[i] is the height of the tree after
 * performing the ith query.
 *
 * Note:
 *
 * The queries are independent, so the tree returns to its initial state after each query.
 * The height of a tree is the number of edges in the longest simple path from the root to some node in the tree.
 */

public class Solution {

    private final Map<Integer, Integer> left = new HashMap<>();
    private final Map<Integer, Integer> right = new HashMap<>();
    private final Map<Integer, Integer> deleted = new HashMap<>();

    public int[] treeQueries(TreeNode root, int[] queries) {
        dfs(root);
        dfs1(root.left, 0, right.get(root.val), 1);
        dfs1(root.right, left.get(root.val), 0, 1);

        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            ans[i] = deleted.get(queries[i]);
        }
        return ans;
    }

    private void dfs1(TreeNode curr, int l, int r, int d) {
        if (curr == null) return;

        int max = Math.max(l, r);
        deleted.put(curr.val, max);

        dfs1(curr.left, 0, Math.max(Math.max(r, right.get(curr.val) + d), max), d + 1);
        dfs1(curr.right, Math.max(Math.max(l, left.get(curr.val) + d), max), 0, d + 1);
    }

    private int dfs(TreeNode node) {
        if (node == null) return 0;

        int l = dfs(node.left), r = dfs(node.right);
        left.put(node.val, l); right.put(node.val, r);

        return Math.max(l, r) + 1;
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().treeQueries(TreeNode.build(1,3,4,2,null,6,5,null,null,null,null,null,7), new int[]{4}), new int[]{2});
        assert Checker.check(new Solution().treeQueries(TreeNode.build(5,8,9,2,1,3,7,4,6), new int[]{3,2,4,8}), new int[]{3,2,3,2});
    }

}
