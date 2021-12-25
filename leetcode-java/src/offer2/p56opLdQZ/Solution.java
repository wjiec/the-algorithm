package offer2.p56opLdQZ;

import common.TreeNode;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 剑指 Offer II 056. 二叉搜索树中两个节点之和
 *
 * https://leetcode-cn.com/problems/opLdQZ/
 *
 * 给定一个二叉搜索树的 根节点 root 和一个整数 k , 请判断该二叉搜索树中是否存在两个节点它们的值之和等于 k 。
 *
 * 假设二叉搜索树中节点的值均唯一。
 */

public class Solution {

    public boolean findTarget(TreeNode root, int k) {
        Queue<TreeNode> queue = new ArrayDeque<>(); queue.add(root);
        while (!queue.isEmpty()) {
            var node = queue.remove();
            if (find(root, k - node.val) && k - node.val != node.val) return true;

            if (node.left != null) queue.add(node.left);
            if (node.right != null) queue.add(node.right);
        }
        return false;
    }

    private boolean find(TreeNode node, int k) {
        if (node.val > k && node.left != null) return find(node.left, k);
        if (node.val < k && node.right != null) return find(node.right, k);
        return node.val == k;
    }

    public static void main(String[] args) {
        assert new Solution().findTarget(TreeNode.build(8,6,10,5,7,9,11), 12);
        assert !new Solution().findTarget(TreeNode.build(8,6,10,5,7,9,11), 22);
    }

}
