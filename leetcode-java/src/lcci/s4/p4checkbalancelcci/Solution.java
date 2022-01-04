package lcci.s4.p4checkbalancelcci;

import common.TreeNode;

/**
 * 面试题 04.04. 检查平衡性
 *
 * https://leetcode-cn.com/problems/check-balance-lcci/
 *
 * 实现一个函数，检查二叉树是否平衡。在这个问题中，平衡树的定义如下：
 *
 * 任意一个节点，其两棵子树的高度差不超过 1。
 */

public class Solution {

    public boolean isBalanced(TreeNode root) {
        if (root == null) return true;
        return isBalanced(root.left) && isBalanced(root.right) && Math.abs(height(root.left) - height(root.right)) <= 1;
    }

    private int height(TreeNode node) {
        if (node == null) return 0;

        int left = height(node.left);
        int right = height(node.right);

        return Math.max(left, right) + 1;
    }

    public static void main(String[] args) {
        assert new Solution().isBalanced(TreeNode.build(3,9,20,null,null,15,7));
        assert !new Solution().isBalanced(TreeNode.build(1,2,2,3,3,null,null,4,4));
    }

}
