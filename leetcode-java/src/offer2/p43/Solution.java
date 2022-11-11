package offer2.p43;

import common.Checker;
import common.TreeNode;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 剑指 Offer II 043. 往完全二叉树添加节点
 *
 * https://leetcode.cn/problems/NaqhDT/
 *
 * 完全二叉树是每一层（除最后一层外）都是完全填充（即，节点数达到最大，第 n 层有 2n-1 个节点）的，并且所有的节点都尽可能地集中在左侧。
 *
 * 设计一个用完全二叉树初始化的数据结构 CBTInserter，它支持以下几种操作：
 *
 * CBTInserter(TreeNode root) 使用根节点为 root 的给定树初始化该数据结构；
 * CBTInserter.insert(int v)  向树中插入一个新节点，节点类型为 TreeNode，值为 v 。
 * 使树保持完全二叉树的状态，并返回插入的新节点的父节点的值；
 *
 * CBTInserter.get_root() 将返回树的根节点。
 */

@SuppressWarnings("DuplicatedCode")
public class Solution {

    private static class CBTInserter {
        private final TreeNode root;
        private final Queue<TreeNode> next = new ArrayDeque<>();
        public TreeNode get_root() { return root; }
        public CBTInserter(TreeNode root) {
            this.root = root;
            Queue<TreeNode> queue = new ArrayDeque<>();
            for (queue.add(root); !queue.isEmpty(); ) {
                for (int i = 0, n = queue.size(); i < n; i++) {
                    TreeNode node = queue.remove();
                    if (node.left != null) queue.add(node.left);
                    if (node.right != null) queue.add(node.right);
                    if (node.left == null || node.right == null) next.add(node);
                }
            }
        }

        public int insert(int v) {
            TreeNode node = new TreeNode(v); next.add(node);
            while (!next.isEmpty() && next.peek().left != null && next.peek().right != null) next.remove();
            if (!next.isEmpty() && next.peek().left == null) next.peek().left = node;
            else if (!next.isEmpty() && next.peek().right == null) next.peek().right = node;
            return next.isEmpty() ? -1 : next.peek().val;
        }
    }

    public static void main(String[] args) {
        CBTInserter cBTInserter = new CBTInserter(TreeNode.build(1, 2));
        assert cBTInserter.insert(3) == 1;  // return 1
        assert cBTInserter.insert(4) == 2;  // return 2
        assert Checker.check(cBTInserter.get_root(), TreeNode.build(1, 2, 3, 4));

        cBTInserter = new CBTInserter(TreeNode.build(1,2,3,4,5,6));
        assert cBTInserter.insert(7) == 3;  // return 1
        assert cBTInserter.insert(8) == 4;  // return 2
        assert Checker.check(cBTInserter.get_root(), TreeNode.build(1,2,3,4,5,6,7,8));
    }

}
