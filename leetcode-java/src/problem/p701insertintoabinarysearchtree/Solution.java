package problem.p701insertintoabinarysearchtree;

import common.TreeNode;

/**
 * 701. Insert into a Binary Search Tree
 *
 * https://leetcode-cn.com/problems/insert-into-a-binary-search-tree/
 *
 * You are given the root node of a binary search tree (BST) and a value to insert into the tree.
 * Return the root node of the BST after the insertion. It is guaranteed that the new value
 * does not exist in the original BST.
 *
 * Notice that there may exist multiple valid ways for the insertion, as long as the
 * tree remains a BST after insertion.
 *
 * You can return any of them.
 */

public class Solution {

    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null) return new TreeNode(val);
        if (val > root.val) {
            if (root.right == null) root.right = new TreeNode(val);
            else insertIntoBST(root.right, val);
        } else {
            if (root.left == null) root.left = new TreeNode(val);
            else insertIntoBST(root.left, val);
        }

        return root;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().insertIntoBST(TreeNode.build(4,2,7,1,3), 5));
        System.out.println(new Solution().insertIntoBST(TreeNode.build(40,20,60,10,30,50,70), 25));
        System.out.println(new Solution().insertIntoBST(TreeNode.build(4,2,7,1,3,null,null,null,null,null,null), 5));
    }

}
