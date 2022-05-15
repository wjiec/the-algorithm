package problem.p814binarytreepruning;

import common.Checker;
import common.TreeNode;

/**
 * 814. Binary Tree Pruning
 *
 * https://leetcode.cn/problems/binary-tree-pruning/
 *
 * Given the root of a binary tree, return the same tree where every subtree (of the given tree)
 * not containing a 1 has been removed.
 *
 * A subtree of a node node is node plus every node that is a descendant of node.
 */

public class Solution {

    public TreeNode pruneTree(TreeNode root) {
        if (root == null) return null;

        root.left = pruneTree(root.left);
        root.right = pruneTree(root.right);

        if (root.left == null && root.right == null && root.val == 0) return null;
        return root;
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().pruneTree(TreeNode.build(1,null,0,0,1)), TreeNode.build(1,null,0,null,1));
        assert Checker.check(new Solution().pruneTree(TreeNode.build(1,0,1,0,0,0,1)), TreeNode.build(1,null,1,null,1));
        assert Checker.check(new Solution().pruneTree(TreeNode.build(1,1,0,1,1,0,1,0)), TreeNode.build(1,1,0,1,1,null,1));
    }

}
