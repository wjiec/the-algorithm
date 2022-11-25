package lcci.s4.p5legalbinarysearchtreelcci;

import common.TreeNode;

/**
 * 面试题 04.05. 合法二叉搜索树
 *
 * https://leetcode.cn/problems/legal-binary-search-tree-lcci/
 *
 * 实现一个函数，检查一棵二叉树是否为二叉搜索树。
 */

public class Solution {

    public boolean isValidBST(TreeNode root) {
        return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private boolean isValidBST(TreeNode node, long l, long r) {
        if (node == null) return true;
        if (node.val <= l || node.val >= r) return false;
        return isValidBST(node.left, l, node.val) && isValidBST(node.right, node.val, r);
    }

    public static void main(String[] args) {
    }

}
