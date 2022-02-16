package problem.p109convertsortedlisttobinarysearchtree;

import common.ListNode;
import common.TreeNode;

/**
 * 109. Convert Sorted List to Binary Search Tree
 *
 * https://leetcode-cn.com/problems/convert-sorted-list-to-binary-search-tree/
 *
 * Given the head of a singly linked list where elements are sorted in ascending order,
 * convert it to a height balanced BST.
 *
 * For this problem, a height-balanced binary tree is defined as a binary tree in
 * which the depth of the two subtrees of every node never differ by more than 1.
 */

public class Solution {

    public TreeNode sortedListToBST(ListNode head) {
        return buildTree(head, null);
    }

    private TreeNode buildTree(ListNode head, ListNode tail) {
        if (head == tail) return null;

        ListNode mid = middle(head, tail);
        TreeNode node = new TreeNode(mid.val);
        node.left = buildTree(head, mid);
        node.right = buildTree(mid.next, tail);
        return node;
    }

    private ListNode middle(ListNode head, ListNode tail) {
        ListNode slow = head, fast = head;
        while (fast != tail && fast.next != tail) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().sortedListToBST(ListNode.build(-10, -3, 0, 5, 9)));
    }

}
