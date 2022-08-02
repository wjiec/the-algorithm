package problem.p1372longestzigzagpathinabinarytree;

import common.TreeNode;

/**
 * 1372. Longest ZigZag Path in a Binary Tree
 *
 * https://leetcode.cn/problems/longest-zigzag-path-in-a-binary-tree/
 *
 * You are given the root of a binary tree.
 *
 * A ZigZag path for a binary tree is defined as follow:
 *
 * Choose any node in the binary tree and a direction (right or left).
 * If the current direction is right, move to the right child of the current node;
 * otherwise, move to the left child.
 * Change the direction from right to left or from left to right.
 * Repeat the second and third steps until you can't move in the tree.
 * Zigzag length is defined as the number of nodes visited - 1. (A single node has a length of 0).
 *
 * Return the longest ZigZag path contained in that tree.
 */

public class Solution {

    private int ans = 0;

    public int longestZigZag(TreeNode root) {
        dfs(root, 0, true);
        dfs(root, 0, false);
        return ans;
    }

    private void dfs(TreeNode node, int len, boolean left) {
        if (len > ans) ans = len;
        if (left) {
            if (node.left != null) dfs(node.left, len + 1, false);
            if (node.right != null) dfs(node.right, 1, true);
        } else {
            if (node.right != null) dfs(node.right, len + 1, true);
            if (node.left != null) dfs(node.left, 1, false);
        }
    }

    public static void main(String[] args) {
        assert new Solution().longestZigZag(TreeNode.build(
            1,null,1,1,1,null,null,1,1,null,1,null,null,null,1,null,1
        )) == 3;

        assert new Solution().longestZigZag(TreeNode.build(
            1,1,1,null,1,null,null,1,1,null,1
        )) == 4;

        assert new Solution().longestZigZag(TreeNode.build(1)) == 0;
    }

}
