package offer2.p52NYBBNL;

import common.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 剑指 Offer II 052. 展平二叉搜索树
 *
 * https://leetcode-cn.com/problems/NYBBNL/
 *
 * 给你一棵二叉搜索树，请 按中序遍历 将其重新排列为一棵递增顺序搜索树，
 * 使树中最左边的节点成为树的根节点，并且每个节点没有左子节点，只有一个右子节点。
 */

public class Solution {

    public TreeNode increasingBST(TreeNode root) {
        List<TreeNode> nodes = new ArrayList<>();
        inorder(root, nodes);

        TreeNode curr = null;
        for (var node : nodes) {
            node.right = curr;
            node.left = null;
            curr = node;
        }
        return curr;
    }

    private void inorder(TreeNode node, List<TreeNode> nodes) {
        if (node == null) return;
        inorder(node.right, nodes);
        nodes.add(node);
        inorder(node.left, nodes);
    }

    public static void main(String[] args) {
        System.out.println(new Solution().increasingBST(TreeNode.build(5,3,6,2,4,null,8,1,null,null,null,7,9)));
        System.out.println(new Solution().increasingBST(TreeNode.build(5,1,7)));
    }

}
