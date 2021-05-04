package problem.p110balancedbinarytree;

/**
 * 110. Balanced Binary Tree
 *
 * https://leetcode-cn.com/problems/balanced-binary-tree/
 *
 * Given a binary tree, determine if it is height-balanced.
 *
 * For this problem, a height-balanced binary tree is defined as:
 *
 * a binary tree in which the left and right subtrees of every node differ in height by no more than 1.
 */

public class Solution {

    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }

        return Math.abs(bstHeight(root.left, 1) - bstHeight(root.right, 1)) <= 1
            && isBalanced(root.left) && isBalanced(root.right);
    }

    private int bstHeight(TreeNode root, int init) {
        if (root == null) {
            return init;
        }
        return Math.max(bstHeight(root.left, init + 1), bstHeight(root.right, init + 1));
    }

    public static void main(String[] args) {
    }

}
