package offer2.p53;

import common.TreeNode;

import java.util.ArrayDeque;

/**
 * 剑指 Offer II 053. 二叉搜索树中的中序后继
 *
 * https://leetcode.cn/problems/P5rCT8/
 *
 * 给定一棵二叉搜索树和其中的一个节点 p ，找到该节点在树中的中序后继。如果节点没有中序后继，请返回 null 。
 *
 * 节点 p 的后继是值比 p.val 大的节点中键值最小的节点，即按中序遍历的顺序节点 p 的下一个节点。
 */

public class Solution {

    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        if (p == null) return null;
        if (p.right != null) return successor(p);

        ArrayDeque<TreeNode> stack = new ArrayDeque<>();
        for (TreeNode curr = root; curr != null; ) {
            stack.push(curr);
            if (curr.val == p.val) break;
            if (curr.val > p.val) curr = curr.left;
            else curr = curr.right;
        }
        stack.pop(); // remove self

        while (!stack.isEmpty() && stack.peek().val < p.val) stack.remove();
        return stack.isEmpty() ? null : stack.remove();
    }

    private TreeNode successor(TreeNode node) {
        TreeNode ans = node.right;
        while (ans.left != null) ans = ans.left;
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().inorderSuccessor(TreeNode.build(2,1,3), TreeNode.build(1)).val == 2;
        assert new Solution().inorderSuccessor(TreeNode.build(5,3,6,2,4,null,null,1), TreeNode.build(6)) == null;
    }

}
