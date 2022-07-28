package problem.p1325deleteleaveswithagivenvalue;

import common.Checker;
import common.TreeNode;

/**
 * 1325. Delete Leaves With a Given Value
 *
 * https://leetcode.cn/problems/delete-leaves-with-a-given-value/
 *
 * Given a binary tree root and an integer target, delete all the leaf nodes with value target.
 *
 * Note that once you delete a leaf node with value target, if its parent node becomes a leaf
 * node and has the value target, it should also be deleted (you need to continue doing that until you cannot).
 */

public class Solution {

    public TreeNode removeLeafNodes(TreeNode root, int target) {
        if (root == null) return null;

        root.left = removeLeafNodes(root.left, target);
        root.right = removeLeafNodes(root.right, target);

        if (root.left == null && root.right == null && root.val == target) {
            return null;
        }
        return root;
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().removeLeafNodes(TreeNode.build(1,2,3,2,null,2,4), 2),
            TreeNode.build(1,null,3,null,4));

        assert Checker.check(new Solution().removeLeafNodes(TreeNode.build(1,3,3,3,2), 3),
            TreeNode.build(1,3,null,null,2));

        assert Checker.check(new Solution().removeLeafNodes(TreeNode.build(1,2,null,2,null,2), 2),
            TreeNode.build(1));

        assert Checker.check(new Solution().removeLeafNodes(TreeNode.build(1,1,1), 1),
            TreeNode.build());

        assert Checker.check(new Solution().removeLeafNodes(TreeNode.build(1,2,3), 1),
            TreeNode.build(1,2,3));
    }

}
