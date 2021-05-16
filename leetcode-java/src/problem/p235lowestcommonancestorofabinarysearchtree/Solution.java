package problem.p235lowestcommonancestorofabinarysearchtree;

import common.TreeNode;

/**
 * 235. Lowest Common Ancestor of a Binary Search Tree
 *
 * https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-search-tree/
 *
 * Given a binary search tree (BST), find the lowest common ancestor (LCA) of two given nodes in the BST.
 *
 * According to the definition of LCA on Wikipedia:
 * “The lowest common ancestor is defined between two nodes p and q as the lowest node in T
 * that has both p and q as descendants (where we allow a node to be a descendant of itself).”
 */

public class Solution {

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root.val > p.val && root.val > q.val) {
            if (root.left != null) {
                return lowestCommonAncestor(root.left, p, q);
            }
        }
        if (root.val < p.val && root.val < q.val) {
            if (root.right != null) {
                return lowestCommonAncestor(root.right, p, q);
            }
        }
        return root;
    }

    public static void main(String[] args) {
        assert new Solution().lowestCommonAncestor(TreeNode.build(6,2,8,0,4,7,9,null,null,3,5), new TreeNode(2), new TreeNode(8)).val == 6;
        assert new Solution().lowestCommonAncestor(TreeNode.build(6, 2, 8, 0, 4, 7, 9, null, null, 3, 5), new TreeNode(2), new TreeNode(4)).val == 2;
    }

}
