package lcci4.p2minimumheighttreelcci;

import common.TreeNode;

/**
 * 面试题 04.02. 最小高度树
 *
 * https://leetcode-cn.com/problems/minimum-height-tree-lcci/
 *
 * 给定一个有序整数数组，元素各不相同且按升序排列，编写一个算法，创建一棵高度最小的二叉搜索树。
 */

public class Solution {

    public TreeNode sortedArrayToBST(int[] nums) {
        return sortedArrayToBST(nums, 0, nums.length);
    }

    private TreeNode sortedArrayToBST(int[] nums, int l, int r) {
        if (l >= r) return null;
        int mid = l + (r - l) / 2;
        TreeNode node = new TreeNode(nums[mid]);
        node.left = sortedArrayToBST(nums, l, mid);
        node.right = sortedArrayToBST(nums, mid + 1, r);
        return node;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().sortedArrayToBST(new int[]{-10,-3,0,5,9}));
    }

}
