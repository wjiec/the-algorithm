package daily.d210425p897increasingordersearchtree;

import common.TreeNode;

import java.util.Stack;

/**
 * 897. Increasing Order Search Tree
 *
 * https://leetcode-cn.com/problems/increasing-order-search-tree/
 *
 * Given the root of a binary search tree, rearrange the tree in in-order so that the leftmost
 * node in the tree is now the root of the tree, and every node has no left child and only one right child.
 */

public class Solution {

    public TreeNode increasingBST(TreeNode root) {
        if (root == null) {
            return null;
        }

        var l = increasingBST(root.left);
        root.left = null;
        if (l != null) {
            var hole = l;
            while (hole.right != null) {
                hole = hole.right;
            }
            hole.right = root;
        }

        if (root.right != null) {
            root.right = increasingBST(root.right);
        }

        return l == null ? root : l;
    }

    public TreeNode byStack(TreeNode root) {
        TreeNode head = new TreeNode();
        TreeNode hole = head;

        Stack<TreeNode> rs = new Stack<>();
        for (var curr = root; curr != null || !rs.empty(); ) {
            while (curr != null) {
                rs.add(curr);
                curr = curr.left;
            }

            if (!rs.empty()) {
                hole.right = rs.peek();
                hole.right.left = null;
                hole = hole.right;

                curr = rs.pop().right;
            }
        }

        return head.right;
    }

    public TreeNode incredible(TreeNode root) {
        return doIncredible(root, null);
    }
    private TreeNode doIncredible(TreeNode root, TreeNode tail) {
        if (root == null) {
            return tail;
        }

        var rs = doIncredible(root.left, root);
        root.left = null;
        root.right = doIncredible(root.right, tail);

        return rs;
    }

    public static void main(String[] args) {
    }

}
