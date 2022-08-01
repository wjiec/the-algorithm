package problem.p1367linkedlistinbinarytree;

import common.ListNode;
import common.TreeNode;

/**
 * 1367. Linked List in Binary Tree
 *
 * https://leetcode.cn/problems/linked-list-in-binary-tree/
 *
 * Given a binary tree root and a linked list with head as the first node. 
 *
 * Return True if all the elements in the linked list starting from the head correspond
 * to some downward path connected in the binary tree otherwise return False.
 *
 * In this context downward path means a path that starts at some node and goes downwards.
 */

public class Solution {

    public boolean isSubPath(ListNode head, TreeNode root) {
        if (root == null) return false;
        if (check(root, head)) return true;
        return isSubPath(head, root.left) || isSubPath(head, root.right);
    }

    private boolean check(TreeNode tree, ListNode list) {
        if (list == null) return true;
        if (tree == null || tree.val != list.val) return false;
        return check(tree.left, list.next) || check(tree.right, list.next);
    }

    public static void main(String[] args) {
        assert new Solution().isSubPath(ListNode.build(4,2,8),
            TreeNode.build(1,4,4,null,2,2,null,1,null,6,8,null,null,null,null,1,3));

        assert new Solution().isSubPath(ListNode.build(1,4,2,6),
            TreeNode.build(1,4,4,null,2,2,null,1,null,6,8,null,null,null,null,1,3));

        assert !new Solution().isSubPath(ListNode.build(1,4,2,6,8),
            TreeNode.build(1,4,4,null,2,2,null,1,null,6,8,null,null,null,null,1,3));
    }

}
