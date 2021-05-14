package problem.p226invertbinarytree;

import common.Checker;
import common.TreeNode;

/**
 * 226. Invert Binary Tree
 *
 * Given the root of a binary tree, invert the tree, and return its root.
 */

public class Solution {

    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }

        TreeNode t = root.left;
        root.left = invertTree(root.right);
        root.right = invertTree(t);

        return root;
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().invertTree(TreeNode.build(4,2,7,1,3,6,9)), TreeNode.build(4,7,2,9,6,3,1));
        assert Checker.check(new Solution().invertTree(TreeNode.build(2,1,3)), TreeNode.build(2,3,1));
    }

}
