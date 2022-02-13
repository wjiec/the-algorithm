package problem.p98validatebinarysearchtree;

import common.TreeNode;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;

/**
 * 98. Validate Binary Search Tree
 *
 * https://leetcode-cn.com/problems/validate-binary-search-tree/
 *
 * Given the root of a binary tree, determine if it is a valid binary search tree (BST).
 *
 * A valid BST is defined as follows:
 *
 * The left subtree of a node contains only nodes with keys less than the node's key.
 * The right subtree of a node contains only nodes with keys greater than the node's key.
 * Both the left and right subtrees must also be binary search trees.
 */

public class Solution {

    public boolean isValidBST(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();

        for (long val = Long.MIN_VALUE; !stack.isEmpty() || root != null; ) {
            for (; root != null; root = root.left) stack.push(root);

            root = stack.pop();
            if (root.val <= val) return false;

            val = root.val;
            root = root.right;
        }
        return true;
    }

    public static void main(String[] args) {
        assert new Solution().isValidBST(TreeNode.build(Integer.MIN_VALUE));

        assert new Solution().isValidBST(TreeNode.build(2,1,3));
        assert !new Solution().isValidBST(TreeNode.build(5,1,4,null,null,3,6));
    }

}
