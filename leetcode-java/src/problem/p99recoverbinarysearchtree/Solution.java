package problem.p99recoverbinarysearchtree;

import common.TreeNode;

import java.util.*;

/**
 * 99. Recover Binary Search Tree
 *
 * https://leetcode-cn.com/problems/recover-binary-search-tree/
 *
 * You are given the root of a binary search tree (BST), where the values of exactly two nodes of
 * the tree were swapped by mistake.
 *
 * Recover the tree without changing its structure.
 */

public class Solution {

    public void recoverTree(TreeNode root) {
        List<TreeNode> nodes = new ArrayList<>();
        List<Integer> numbers = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        while (!stack.isEmpty() || root != null) {
            for (; root != null; root = root.left) stack.push(root);

            TreeNode node = stack.pop();
            root = node.right;

            nodes.add(node);
            numbers.add(node.val);
        }

        numbers.sort(Integer::compareTo);
        for (int i = 0; i < nodes.size(); i++) {
            nodes.get(i).val = numbers.get(i);
        }
    }

    public void recoverTreeO1(TreeNode root) {
    }

    public static void main(String[] args) {
        TreeNode t1 = TreeNode.build(1,3,null,null,2);
        new Solution().recoverTree(t1);
        System.out.println(t1);

        TreeNode t2 = TreeNode.build(3,1,4,null,null,2);
        new Solution().recoverTree(t2);
        System.out.println(t2);

        TreeNode t3 = TreeNode.build(1,3,null,null,2);
        new Solution().recoverTreeO1(t3);
        System.out.println(t3);

        TreeNode t4 = TreeNode.build(3,1,4,null,null,2);
        new Solution().recoverTreeO1(t4);
        System.out.println(t4);
    }

}
