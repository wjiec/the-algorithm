package lcci.s17.p12binodelcci;

import common.TreeNode;

/**
 * 面试题 17.12. BiNode
 *
 * https://leetcode-cn.com/problems/binode-lcci/
 *
 * 二叉树数据结构TreeNode可用来表示单向链表（其中left置空，right为下一个链表节点）。实现一个方法，把二叉搜索树转换为单向链表，
 *
 * 要求依然符合二叉搜索树的性质，转换操作应是原址的，也就是在原始的二叉搜索树上直接修改。
 *
 * 返回转换后的单向链表的头节点。
 */

public class Solution {

    private TreeNode node = null;

    public TreeNode convertBiNode(TreeNode root) {
        if (root == null) return null;

        convertBiNode(root.right);
        root.right = node;
        node = root;

        convertBiNode(root.left);
        root.left = null;

        return node;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().convertBiNode(TreeNode.build(4,2,5,1,3,null,6,0)));
    }

}
