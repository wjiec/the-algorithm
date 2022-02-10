package problem.p1022sumofroottoleafbinarynumbers;

import common.TreeNode;

/**
 * 1022. Sum of Root To Leaf Binary Numbers
 *
 * https://leetcode-cn.com/problems/sum-of-root-to-leaf-binary-numbers/
 *
 * You are given the root of a binary tree where each node has a value 0 or 1.
 * Each root-to-leaf path represents a binary number starting with the most significant bit.
 * For example, if the path is 0 -> 1 -> 1 -> 0 -> 1, then this could represent 01101 in binary, which is 13.
 *
 * For all leaves in the tree, consider the numbers represented by the path from the root to that leaf.
 *
 * Return the sum of these numbers. The answer is guaranteed to fit in a 32-bits integer.
 */

public class Solution {

    private int sum = 0;

    public int sumRootToLeaf(TreeNode root) {
        doSumLeaf(root, 0);

        return sum;
    }

    private void doSumLeaf(TreeNode root, int value) {
        if (root.left == null && root.right == null) {
            sum += (value << 1) | root.val;
            return;
        }

        if (root.left != null) doSumLeaf(root.left, (value << 1) | root.val);
        if (root.right != null)  doSumLeaf(root.right, (value << 1) | root.val);
    }

    public static void main(String[] args) {
        assert new Solution().sumRootToLeaf(TreeNode.build(1,0,1,0,1,0,1)) == 22;
        assert new Solution().sumRootToLeaf(TreeNode.build(0)) == 0;
        assert new Solution().sumRootToLeaf(TreeNode.build(1)) == 1;
        assert new Solution().sumRootToLeaf(TreeNode.build(1,1)) == 3;
    }

}
