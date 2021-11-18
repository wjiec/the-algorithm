package daily.d211118pbinarytreetilt;

import common.TreeNode;

/**
 * 563. Binary Tree Tilt
 *
 * https://leetcode-cn.com/problems/binary-tree-tilt/
 *
 * Given the root of a binary tree, return the sum of every tree node's tilt.
 *
 * The tilt of a tree node is the absolute difference between the sum of all
 * left subtree node values and all right subtree node values.
 *
 * If a node does not have a left child, then the sum of the left subtree node values is treated as 0.
 *
 * The rule is similar if there the node does not have a right child.
 */

public class Solution {

    public int findTilt(TreeNode root) {
        return dfs(root)[1];
    }

    private int[] dfs(TreeNode root) {
        if (root == null) return new int[]{0, 0};

        var left = dfs(root.left);
        var right = dfs(root.right);
        int l = root.left != null ? root.left.val : 0, r = root.right != null ? root.right.val : 0;

        return new int[]{left[0] + right[0] + l + r, Math.abs(left[0] + l - right[0] - r) + left[1] + right[1]};
    }

    public static void main(String[] args) {
        assert new Solution().findTilt(TreeNode.build(1,2,3)) == 1;
        assert new Solution().findTilt(TreeNode.build(4,2,9,3,5,null,7)) == 15;
        assert new Solution().findTilt(TreeNode.build(21,7,14,1,1,2,2,3,3)) == 9;
    }

}
