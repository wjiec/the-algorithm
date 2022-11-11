package offer2.p47;

import common.Checker;
import common.TreeNode;

/**
 * 剑指 Offer II 047. 二叉树剪枝
 *
 * https://leetcode.cn/problems/pOCWxh/
 *
 * 给定一个二叉树 根节点 root ，树的每个节点的值要么是 0，要么是 1。请剪除该二叉树中所有节点的值为 0 的子树。
 *
 * 节点 node 的子树为 node 本身，以及所有 node 的后代。
 */

@SuppressWarnings("DuplicatedCode")
public class Solution {

    public TreeNode pruneTree(TreeNode root) {
        if (root == null) return null;

        root.left = pruneTree(root.left);
        root.right = pruneTree(root.right);

        return root.left == null && root.right == null && root.val == 0 ? null : root;
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().pruneTree(TreeNode.build(1,null,0,0,1)), TreeNode.build(1,null,0,null,1));
        assert Checker.check(new Solution().pruneTree(TreeNode.build(1,0,1,0,0,0,1)), TreeNode.build(1,null,1,null,1));
        assert Checker.check(new Solution().pruneTree(TreeNode.build(1,1,0,1,1,0,1,0)), TreeNode.build(1,1,0,1,1,null,1));
    }

}
