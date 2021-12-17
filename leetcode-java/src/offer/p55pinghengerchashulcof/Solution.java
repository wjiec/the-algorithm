package offer.p55pinghengerchashulcof;

import common.TreeNode;

/**
 * 剑指 Offer 55 - II. 平衡二叉树
 *
 * https://leetcode-cn.com/problems/ping-heng-er-cha-shu-lcof/
 *
 * 输入一棵二叉树的根节点，判断该树是不是平衡二叉树。
 *
 * 如果某二叉树中任意节点的左右子树的深度相差不超过1，那么它就是一棵平衡二叉树。
 */

public class Solution {

    public boolean isBalanced(TreeNode root) {
        if (root == null) return true;
        int left = depth(root.left), right = depth(root.right);
        return left != -1 && right != -1 && Math.abs(left - right) < 2;
    }

    private int depth(TreeNode node) {
        if (node == null) return 0;
        int left = depth(node.left), right = depth(node.right);
        if (left == -1 || right == -1 || Math.abs(left - right) > 1) return -1;
        return Math.max(left, right) + 1;
    }

    public static void main(String[] args) {
        assert !new Solution().isBalanced(TreeNode.build(1,2,2,3,null,null,3,4,null,null,4));

        assert new Solution().isBalanced(TreeNode.build(3,9,20,null,null,15,7));
        assert !new Solution().isBalanced(TreeNode.build(1,2,2,3,3,null,null,4,4));
    }

}
