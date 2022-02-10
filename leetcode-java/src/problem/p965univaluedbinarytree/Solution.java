package problem.p965univaluedbinarytree;

import common.TreeNode;

/**
 * 965. Univalued Binary Tree
 *
 * https://leetcode-cn.com/problems/univalued-binary-tree/
 *
 * A binary tree is univalued if every node in the tree has the same value.
 *
 * Return true if and only if the given tree is univalued.
 */

public class Solution {

    public boolean isUnivalTree(TreeNode root) {
        if (root == null) return true;
        if (root.left != null && root.left.val != root.val) return false;
        if (root.right != null && root.right.val != root.val) return false;
        return isUnivalTree(root.left) && isUnivalTree(root.right);
    }

    public static void main(String[] args) {
        assert new Solution().isUnivalTree(TreeNode.build(1,1,1,1,1,null,1));
        assert !new Solution().isUnivalTree(TreeNode.build(2,2,2,5,2));
    }

}
