package problem.p543diameterofbinarytree;

import common.TreeNode;

/**
 * 543. Diameter of Binary Tree
 *
 * https://leetcode-cn.com/problems/diameter-of-binary-tree/
 *
 * Given the root of a binary tree, return the length of the diameter of the tree.
 *
 * The diameter of a binary tree is the length of the longest path between any two nodes in a tree.
 * This path may or may not pass through the root.
 *
 * The length of a path between two nodes is represented by the number of edges between them.
 */

public class Solution {

    private int depth = 1;

    public int diameterOfBinaryTree(TreeNode root) {
        dfs(root);
        return depth - 1;
    }

    private int dfs(TreeNode root) {
        if (root == null) return 0;

        int left = dfs(root.left);
        int right = dfs(root.right);
        depth = Math.max(depth, left + right + 1);

        return Math.max(left, right) + 1;
    }

    public static void main(String[] args) {
        assert new Solution().diameterOfBinaryTree(TreeNode.build(1)) == 0;
        assert new Solution().diameterOfBinaryTree(TreeNode.build(1,2,3,4,5)) == 3;
        assert new Solution().diameterOfBinaryTree(TreeNode.build(1,2)) == 1;
    }

}
