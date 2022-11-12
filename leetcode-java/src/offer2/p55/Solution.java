package offer2.p55;

import common.TreeNode;

import java.util.ArrayDeque;

/**
 * 剑指 Offer II 055. 二叉搜索树迭代器
 *
 * https://leetcode.cn/problems/kTOapQ/
 *
 * 实现一个二叉搜索树迭代器类BSTIterator ，表示一个按中序遍历二叉搜索树（BST）的迭代器：
 *
 * BSTIterator(TreeNode root) 初始化 BSTIterator 类的一个对象。BST 的根节点 root 会作为构造函数的一部分给出。
 * 指针应初始化为一个不存在于 BST 中的数字，且该数字小于 BST 中的任何元素。
 * boolean hasNext() 如果向指针右侧遍历存在数字，则返回 true ；否则返回 false 。
 * int next()将指针向右移动，然后返回指针处的数字。
 * 注意，指针初始化为一个不存在于 BST 中的数字，所以对 next() 的首次调用将返回 BST 中的最小元素。
 *
 * 可以假设 next() 调用总是有效的，也就是说，当调用 next() 时，BST 的中序遍历中至少存在一个下一个数字。
 */

@SuppressWarnings("DuplicatedCode")
public class Solution {

    private static class BSTIterator {
        private final ArrayDeque<TreeNode> stack = new ArrayDeque<>();
        public BSTIterator(TreeNode root) {
            for (; root != null; root = root.left) {
                stack.push(root);
            }
        }
        public boolean hasNext() { return !stack.isEmpty(); }

        public int next() {
            TreeNode curr = stack.pop();
            for (TreeNode x = curr.right; x != null; x = x.left) {
                stack.push(x);
            }
            return curr.val;
        }
    }

    public static void main(String[] args) {
        BSTIterator bSTIterator = new BSTIterator(TreeNode.build(7, 3, 15, null, null, 9, 20));
        assert bSTIterator.next() == 3;
        assert bSTIterator.next() == 7;
        assert bSTIterator.hasNext();
        assert bSTIterator.next() == 9;
        assert bSTIterator.hasNext();
        assert bSTIterator.next() == 15;
        assert bSTIterator.hasNext();
        assert bSTIterator.next() == 20;
        assert !bSTIterator.hasNext();
    }

}
