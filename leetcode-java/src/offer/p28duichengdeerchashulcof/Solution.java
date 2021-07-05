package offer.p28duichengdeerchashulcof;

import common.TreeNode;

/**
 * 剑指 Offer 28. 对称的二叉树
 *
 * https://leetcode-cn.com/problems/dui-cheng-de-er-cha-shu-lcof/
 *
 * 请实现一个函数，用来判断一棵二叉树是不是对称的。如果一棵二叉树和它的镜像一样，那么它是对称的。
 */

public class Solution {

    public boolean isSymmetric(TreeNode root) {
        return root == null || isSymmetric(root.left, root.right);
    }

    private boolean isSymmetric(TreeNode left, TreeNode right) {
        if (left == null || right == null) return left == right;
        return left.val == right.val && isSymmetric(left.left, right.right) && isSymmetric(left.right, right.left);
    }

    public static void main(String[] args) {
        assert new Solution().isSymmetric(TreeNode.build(1,2,2,3,4,4,3));
        assert !new Solution().isSymmetric(TreeNode.build(1,2,2,null,3,null,3));
    }

}
