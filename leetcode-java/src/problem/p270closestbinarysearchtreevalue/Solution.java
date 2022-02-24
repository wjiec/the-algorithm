package problem.p270closestbinarysearchtreevalue;

import common.TreeNode;

/**
 * 270. Closest Binary Search Tree Value
 *
 * https://leetcode-cn.com/problems/closest-binary-search-tree-value/
 *
 * Given the root of a binary search tree and a target value,
 * return the value in the BST that is closest to the target.
 */

public class Solution {

    public int closestValue(TreeNode root, double target) {
        int below = belowValue(root, target);
        int above = aboveValue(root, target);

        System.out.println(below);
        System.out.println(above);

        if (below == -1) return above;
        if (above == -1) return below;

        return (above - target) < (target - below) ? above : below;
    }

    private int belowValue(TreeNode root, double target) {
        if (root == null) return -1;
        if (root.val == target) return root.val;
        if (root.val > target) return belowValue(root.left, target);
        return Math.max(root.val, belowValue(root.right, target));
    }

    private int aboveValue(TreeNode root, double target) {
        if (root == null) return -1;
        System.out.println("root: " + root.val);
        if (root.val == target) return root.val;
        if (root.val < target) return aboveValue(root.right, target);

        int below = aboveValue(root.left, target);
        return below == -1 ? root.val : Math.min(root.val, below);
    }

    public static void main(String[] args) {
        assert new Solution().closestValue(TreeNode.build(2,1,3), 0.142857) == 1;
        assert new Solution().closestValue(TreeNode.build(4,2,5,1,3), 3.714286) == 4;
    }

}
