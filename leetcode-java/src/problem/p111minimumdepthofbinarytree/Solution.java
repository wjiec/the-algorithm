package problem.p111minimumdepthofbinarytree;

import common.TreeNode;

/**
 * 111. Minimum Depth of Binary Tree
 *
 * https://leetcode-cn.com/problems/minimum-depth-of-binary-tree/
 *
 * Given a binary tree, find its minimum depth.
 *
 * The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.
 *
 * Note: A leaf is a node with no children.
 */

public class Solution {

    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        return dfs(root, 1, Integer.MAX_VALUE);
    }

    private int dfs(TreeNode root, int depth, int min) {
        if (depth >= min) {
            return Math.min(min, depth);
        }

        if (root.left == null && root.right == null) {
            return depth;
        }

        if (root.left != null) {
            min = dfs(root.left, depth + 1, min);
        }
        if (root.right != null) {
            return Math.min(min, dfs(root.right, depth + 1, min));
        }
        return min;
    }

    public static void main(String[] args) {
        assert new Solution().minDepth(TreeNode.build(3,9,20,null,null,15,7)) == 2;
        assert new Solution().minDepth(TreeNode.build(2,null,3,null,4,null,5,null,6)) == 5;
    }

}
