package problem.p114flattenbinarytreetolinkedlist;

import common.TreeNode;

/**
 * 114. Flatten Binary Tree to Linked List
 *
 * https://leetcode-cn.com/problems/flatten-binary-tree-to-linked-list/
 *
 * Given the root of a binary tree, flatten the tree into a "linked list":
 *
 * The "linked list" should use the same TreeNode class where the right child pointer points to
 * the next node in the list and the left child pointer is always null.
 *
 * The "linked list" should be in the same order as a pre-order traversal of the binary tree.
 */

public class Solution {

    public void flatten(TreeNode root) {
        for (TreeNode curr = root; curr != null; curr = curr.right) {
            if (curr.left != null) {
                TreeNode next = curr.left, prev = curr.left;
                while (prev.right != null) prev = prev.right;
                prev.right = curr.right;
                curr.left = null;
                curr.right = next;
            }
        }
    }

    public static void main(String[] args) {
        TreeNode t1 = TreeNode.build(1,2,5,3,4,null,6);
        new Solution().flatten(t1);
        System.out.println(t1);

        TreeNode t2 = TreeNode.build(1);
        new Solution().flatten(t2);
        System.out.println(t2);

        TreeNode t3 = TreeNode.build();
        new Solution().flatten(t3);
        System.out.println(t3);
    }

}
