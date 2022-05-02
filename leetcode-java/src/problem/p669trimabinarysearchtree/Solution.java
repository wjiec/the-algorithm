package problem.p669trimabinarysearchtree;

import common.Checker;
import common.TreeNode;

/**
 * 669. Trim a Binary Search Tree
 *
 * https://leetcode-cn.com/problems/trim-a-binary-search-tree/
 *
 * Given the root of a binary search tree and the lowest and highest boundaries as low and high,
 * trim the tree so that all its elements lies in [low, high].
 *
 * Trimming the tree should not change the relative structure of the elements that
 * will remain in the tree (i.e., any node's descendant should remain a descendant).
 *
 * It can be proven that there is a unique answer.
 *
 * Return the root of the trimmed binary search tree.
 *
 * Note that the root may change depending on the given bounds.
 */

public class Solution {

    public TreeNode trimBST(TreeNode root, int low, int high) {
        if (root == null) return null;
        if (root.val < low) return trimBST(root.right, low, high);
        if (root.val > high) return trimBST(root.left, low, high);

        root.left = trimBST(root.left, low, high);
        root.right = trimBST(root.right, low, high);

        return root;
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().trimBST(TreeNode.build(1,0,2), 1, 2), TreeNode.build(1,null,2));
        assert Checker.check(new Solution().trimBST(TreeNode.build(3,0,4,null,2,null,null,1), 1, 3), TreeNode.build(3,2,null,1));
    }

}
