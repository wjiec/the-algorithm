package offer.p27erchashudejingxianglcof;

import common.TreeNode;

/**
 * 剑指 Offer 27. 二叉树的镜像
 *
 * https://leetcode-cn.com/problems/er-cha-shu-de-jing-xiang-lcof/
 *
 * 请完成一个函数，输入一个二叉树，该函数输出它的镜像。
 */

public class Solution {

    public TreeNode mirrorTree(TreeNode root) {
        if (root != null) mirror(root, root.left, root.right);
        return root;
    }

    private void mirror(TreeNode root, TreeNode left, TreeNode right) {
        root.left = right;
        root.right = left;
        if (left != null) mirror(left, left.left, left.right);
        if (right != null) mirror(right, right.left, right.right);
    }

    public static void main(String[] args) {
        System.out.println(new Solution().mirrorTree(TreeNode.build(4,2,7,1,3,6,9)));
    }

}
