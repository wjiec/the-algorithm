package problem.p173binarysearchtreeiterator;

import common.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 173. Binary Search Tree Iterator
 *
 * https://leetcode-cn.com/problems/binary-search-tree-iterator/
 *
 * Implement the BSTIterator class that represents an iterator over the in-order traversal of a binary search tree (BST):
 *
 * BSTIterator(TreeNode root) Initializes an object of the BSTIterator class.
 * The root of the BST is given as part of the constructor.
 * The pointer should be initialized to a non-existent number smaller than any element in the BST.
 *
 * boolean hasNext() Returns true if there exists a number in the traversal to the right of the pointer,
 * otherwise returns false.
 *
 * int next() Moves the pointer to the right, then returns the number at the pointer.
 *
 * Notice that by initializing the pointer to a non-existent smallest number,
 * the first call to next() will return the smallest element in the BST.
 *
 * You may assume that next() calls will always be valid. That is,
 * there will be at least a next number in the in-order traversal when next() is called.
 */

public class Solution {

    private static class BSTIterator {
        private TreeNode curr;
        private final Deque<TreeNode> tree = new ArrayDeque<>();
        public BSTIterator(TreeNode root) {
            for (; root != null; root = root.left) tree.addLast(root);
            curr = tree.removeLast();
        }

        public int next() {
            int r = curr.val;

            for (curr = curr.right; curr != null; curr = curr.left) tree.addLast(curr);
            if (!tree.isEmpty()) curr = tree.removeLast();
            return r;
        }

        public boolean hasNext() { return tree.size() != 0 || curr != null; }
    }

    public static void main(String[] args) {
        BSTIterator iterator = new BSTIterator(TreeNode.build(7, 3, 15, null, null, 9, 20));
        assert iterator.next() == 3;
        assert iterator.next() == 7;
        assert iterator.hasNext();
        assert iterator.next() == 9;
        assert iterator.hasNext();
        assert iterator.next() == 15;
        assert iterator.hasNext();
        assert iterator.next() == 20;
        assert !iterator.hasNext();
    }

}
