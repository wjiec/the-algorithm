package problem.p108convertsortedarraytobinarysearchtree;

import common.TreeNode;

/**
 * 108. Convert Sorted Array to Binary Search Tree
 *
 * https://leetcode-cn.com/problems/convert-sorted-array-to-binary-search-tree/
 *
 * Given an integer array nums where the elements are sorted in ascending order,
 * convert it to a height-balanced binary search tree.
 *
 * A height-balanced binary tree is a binary tree in which the depth of the two
 * subtrees of every node never differs by more than one.
 */

public class Solution {

    public TreeNode sortedArrayToBST(int[] nums) {
        return doBuildBST(nums, 0, nums.length - 1);
    }

    private TreeNode doBuildBST(int[] nums, int start, int end) {
        if (start > end) {
            return null;
        }

        int mid = (end + start) / 2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = doBuildBST(nums, start, mid - 1);
        root.right = doBuildBST(nums, mid + 1, end);

        return root;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().sortedArrayToBST(new int[]{-10,-3,0,5,9}));
        System.out.println(new Solution().sortedArrayToBST(new int[]{1,3}));
    }

}
