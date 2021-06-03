package problem.p700searchinabinarysearchtree;

import common.Checker;
import common.TreeNode;

/**
 * 700. Search in a Binary Search Tree
 *
 * https://leetcode-cn.com/problems/search-in-a-binary-search-tree/
 *
 * You are given the root of a binary search tree (BST) and an integer val.
 *
 * Find the node in the BST that the node's value equals val and return the subtree rooted with that node.
 * If such a node does not exist, return null.
 */

public class Solution {

    public TreeNode searchBST(TreeNode root, int val) {
        while (root != null && root.val != val) {
            if (root.val > val) root = root.left;
            else root = root.right;
        }
        return root;
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().searchBST(TreeNode.build(4,2,7,1,3), 2), TreeNode.build(2,1,3));
        assert Checker.check(new Solution().searchBST(TreeNode.build(4,2,7,1,3), 5), null);
    }

}
