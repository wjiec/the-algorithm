package problem.p156binarytreeupsidedown;

import common.Checker;
import common.TreeNode;

/**
 * 156. Binary Tree Upside Down
 *
 * https://leetcode-cn.com/problems/binary-tree-upside-down/
 *
 * Given the root of a binary tree, turn the tree upside down and return the new root.
 *
 * You can turn a binary tree upside down with the following steps:
 *
 * The original left child becomes the new root.
 * The original root becomes the new right child.
 * The original right child becomes the new left child.
 *
 * The mentioned steps are done level by level. It is guaranteed that every right node
 * has a sibling (a left node with the same parent) and has no children.
 */

public class Solution {

    public TreeNode upsideDownBinaryTree(TreeNode root) {
        TreeNode right = null, parent = null;
        while (root != null) {
            TreeNode left = root.left;
            root.left = right;
            right = root.right;
            root.right = parent;
            parent = root;
            root = left;
        }
        return parent;
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().upsideDownBinaryTree(TreeNode.build(1,2,3,4,5)), TreeNode.build(4,5,2,null,null,3,1));
        assert new Solution().upsideDownBinaryTree(null) == null;
        assert Checker.check(new Solution().upsideDownBinaryTree(TreeNode.build(1)), TreeNode.build(1));
    }

}
