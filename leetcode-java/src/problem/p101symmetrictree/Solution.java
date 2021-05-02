package problem.p101symmetrictree;

/**
 * 101. Symmetric Tree
 *
 * https://leetcode-cn.com/problems/symmetric-tree/
 *
 * Given the root of a binary tree, check whether it is a mirror of itself (i.e., symmetric around its center).
 */

public class Solution {

    public boolean isSymmetric(TreeNode root) {
        return doSymmetric(root.left, root.right);
    }

    private boolean doSymmetric(TreeNode left, TreeNode right) {
        if (left == null || right == null) {
            return left == null && right == null;
        }
        return left.val == right.val && doSymmetric(left.left, right.right) && doSymmetric(left.right, right.left);
    }

    public static void main(String[] args) {
    }

}
