package problem.p104maximumdepthofbinarytree;

import common.TreeNode;

/**
 * 104. Maximum Depth of Binary Tree
 *
 * https://leetcode-cn.com/problems/maximum-depth-of-binary-tree/
 *
 * Given the root of a binary tree, return its maximum depth.
 *
 * A binary tree's maximum depth is the number of nodes along the longest
 * path from the root node down to the farthest leaf node.
 */

public class Solution {

    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return doMaxDepth(root, 1);
    }

    public int doMaxDepth(TreeNode node, int n) {
        if (node == null || (node.left == null && node.right == null)) {
            return n;
        }
        return Math.max(doMaxDepth(node.left, n + 1), doMaxDepth(node.right, n + 1));
    }

    public static void main(String[] args) {
    }

}
