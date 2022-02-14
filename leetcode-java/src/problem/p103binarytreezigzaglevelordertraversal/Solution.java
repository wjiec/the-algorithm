package problem.p103binarytreezigzaglevelordertraversal;

import common.TreeNode;

import java.util.Collections;
import java.util.List;

/**
 * 103. Binary Tree Zigzag Level Order Traversal
 *
 * https://leetcode-cn.com/problems/binary-tree-zigzag-level-order-traversal/
 *
 * Given the root of a binary tree, return the zigzag level order traversal of its nodes' values.
 * (i.e., from left to right, then right to left for the next level and alternate between).
 */

public class Solution {

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        if (root == null) return Collections.emptyList();
    }

    public static void main(String[] args) {
        System.out.println(new Solution().zigzagLevelOrder(TreeNode.build(3,9,20,null,null,15,7)));
        System.out.println(new Solution().zigzagLevelOrder(TreeNode.build(1)));
        System.out.println(new Solution().zigzagLevelOrder(TreeNode.build()));
    }

}
