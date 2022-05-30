package problem.p919completebinarytreeinserter;

import common.Checker;
import common.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 919. Complete Binary Tree Inserter
 *
 * https://leetcode.cn/problems/complete-binary-tree-inserter/
 *
 * A complete binary tree is a binary tree in which every level,
 * except possibly the last, is completely filled,
 * and all nodes are as far left as possible.
 *
 * Design an algorithm to insert a new node to a complete binary tree
 * keeping it complete after the insertion.
 *
 * Implement the CBTInserter class:
 *
 * CBTInserter(TreeNode root) Initializes the data structure
 * with the root of the complete binary tree.
 *
 * int insert(int v) Inserts a TreeNode into the tree with value Node.val == val so that
 * the tree remains complete, and returns the value of the parent of the inserted TreeNode.
 *
 * TreeNode get_root() Returns the root node of the tree.
 */

public class Solution {

    private static class CBTInserter {
        private final TreeNode root;
        private Deque<TreeNode> curr = new ArrayDeque<>();
        private Deque<TreeNode> next = new ArrayDeque<>();
        public CBTInserter(TreeNode root) {
            this.root = root;
            for (curr.add(root); true; ) {
                for (var node : curr) {
                    if (node.left != null) next.add(node.left);
                    if (node.right != null) next.add(node.right);
                }

                if (next.size() != curr.size() * 2) {
                    while (!curr.isEmpty() && curr.peek().left != null && curr.peek().right != null) {
                        curr.remove();
                    }
                    break;
                }

                curr = next; next = new ArrayDeque<>();
            }
        }

        public TreeNode get_root() { return root; }
        public int insert(int val) {
            if (curr.isEmpty()) {
                curr = next; next = new ArrayDeque<>();
            }

            TreeNode top = curr.remove();
            if (top.left == null) {
                top.left = new TreeNode(val);
                next.add(top.left);
                // not completely
                curr.addFirst(top);
            } else {
                top.right = new TreeNode(val);
                next.add(top.right);
            }

            return top.val;
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
