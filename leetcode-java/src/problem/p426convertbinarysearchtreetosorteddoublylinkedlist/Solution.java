package problem.p426convertbinarysearchtreetosorteddoublylinkedlist;

/**
 * 426. Convert Binary Search Tree to Sorted Doubly Linked List
 *
 * https://leetcode.cn/problems/convert-binary-search-tree-to-sorted-doubly-linked-list/
 *
 * Convert a Binary Search Tree to a sorted Circular Doubly-Linked List in place.
 *
 * You can think of the left and right pointers as synonymous to the predecessor and successor
 * pointers in a doubly-linked list. For a circular doubly linked list, the predecessor of the
 * first element is the last element, and the successor of the last element is the first element.
 *
 * We want to do the transformation in place. After the transformation, the left pointer of the
 * tree node should point to its predecessor, and the right pointer should point to its successor.
 *
 * You should return the pointer to the smallest element of the linked list.
 */

public class Solution {

    private static class Node {
        public int val;
        public Node left;
        public Node right;
    };

    public Node treeToDoublyList(Node root) {
        if (root == null) return null;

        Node leftMin = treeToDoublyList(root.left);
        Node rightMin = treeToDoublyList(root.right);

        // 只有一个根节点
        if (leftMin == null && rightMin == null) {
            root.left = root.right = root;
            return root;
        }

        // 左子树是空的, 当前节点是最小的
        if (leftMin == null) {
            Node rightMax = rightMin.left;
            // 右侧最大节点的前驱是最小的
            rightMax.right = root;
            // 右侧最小节点的前驱是当前元素
            rightMin.left = root;
            // 最小的前驱是最大的
            root.left = rightMax;
            // 最小的后驱是右侧最小的
            root.right = rightMin;
            // 返回最小元素
            return root;
        }

        // 右子树是空的, 当前节点是最大的
        if (rightMin == null) {
            Node leftMax = leftMin.left;
            // 左侧元素的后驱是最大的当前节点
            leftMax.right = root;
            // 左侧最小元素的前驱是最大的当前节点
            leftMin.left = root;
            // 最大节点的后驱是最小的
            root.right = leftMin;
            // 最大节点的前驱是左侧最大的
            root.left = leftMax;
            // 返回最小元素
            return leftMin;
        }

        Node leftMax = leftMin.left;
        Node rightMax = rightMin.left;

        leftMin.left = rightMax;
        leftMax.right = root;
        root.left = leftMax; root.right = rightMin;
        rightMin.left = root;
        rightMax.right = leftMin;

        return leftMin;
    }

    public static void main(String[] args) {
    }

}
