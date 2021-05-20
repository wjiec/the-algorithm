package problem.p404sumofleftleaves;

import common.TreeNode;

/**
 * 404. Sum of Left Leaves
 *
 * https://leetcode-cn.com/problems/sum-of-left-leaves/
 *
 * Given the root of a binary tree, return the sum of all left leaves.
 */

public class Solution {

    public int sumOfLeftLeaves(TreeNode root) {
        int sum = 0;
        if (isLeftLeaf(root.left)) {
            sum += root.left.val;
        } else if (root.left != null) {
            sum += sumOfLeftLeaves(root.left);
        }
        return sum + (root.right != null ? sumOfLeftLeaves(root.right) : 0);
    }

    private boolean isLeftLeaf(TreeNode root) {
        return root != null && root.left == null && root.right == null;
    }

    public static void main(String[] args) {
        assert new Solution().sumOfLeftLeaves(TreeNode.build(3,9,20,null,null,15,7)) == 24;
        assert new Solution().sumOfLeftLeaves(TreeNode.build(1)) == 0;
    }

}
