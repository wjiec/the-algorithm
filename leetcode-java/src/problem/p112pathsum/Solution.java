package problem.p112pathsum;

import common.TreeNode;

/**
 * 112. Path Sum
 *
 * https://leetcode-cn.com/problems/path-sum/
 *
 * Given the root of a binary tree and an integer targetSum, return true if the tree has a
 * root-to-leaf path such that adding up all the values along the path equals targetSum.
 *
 * A leaf is a node with no children.
 */

public class Solution {

    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        }

        if (root.val == targetSum && root.left == null && root.right == null) {
            return true;
        }

        return hasPathSum(root.left, targetSum - root.val)
            || hasPathSum(root.right, targetSum - root.val);
    }

    public static void main(String[] args) {
        assert !new Solution().hasPathSum(TreeNode.build(1,2), 0);
        assert !new Solution().hasPathSum(TreeNode.build(1,2,3), 5);
        assert new Solution().hasPathSum(TreeNode.build(5,4,8,11,null,13,4,7,2,null,null,null,1), 22);
    }

}
