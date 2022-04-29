package problem.p654maximumbinarytree;

import common.Checker;
import common.TreeNode;

/**
 * 654. Maximum Binary Tree
 *
 * https://leetcode-cn.com/problems/maximum-binary-tree/
 *
 * You are given an integer array nums with no duplicates. A maximum binary tree
 * can be built recursively from nums using the following algorithm:
 *
 * Create a root node whose value is the maximum value in nums.
 * Recursively build the left subtree on the subarray prefix to the left of the maximum value.
 * Recursively build the right subtree on the subarray suffix to the right of the maximum value.
 * Return the maximum binary tree built from nums.
 */

public class Solution {

    public TreeNode constructMaximumBinaryTree(int[] nums) {
        return build(nums, 0, nums.length);
    }

    private TreeNode build(int[] num, int l, int r) {
        if (l >= r) return null;

        int max = l;
        for (int i = l; i < r; i++) if (num[i] > num[max]) max = i;

        TreeNode root = new TreeNode(num[max]);
        root.left = build(num, l, max);
        root.right = build(num, max + 1, r);

        return root;
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().constructMaximumBinaryTree(new int[]{3,2,1,6,0,5}),
            TreeNode.build(6,3,5,null,2,0,null,null,1));
        assert Checker.check(new Solution().constructMaximumBinaryTree(new int[]{3,2,1}),
            TreeNode.build(3,null,2,null,1));
    }

}
