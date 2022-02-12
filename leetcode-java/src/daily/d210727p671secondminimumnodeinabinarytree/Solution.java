package daily.d210727p671secondminimumnodeinabinarytree;

import common.TreeNode;

/**
 * 671. Second Minimum Node In a Binary Tree
 *
 * https://leetcode-cn.com/problems/second-minimum-node-in-a-binary-tree/
 *
 * Given a non-empty special binary tree consisting of nodes with the non-negative value,
 * where each node in this tree has exactly two or zero sub-node. If the node has two sub-nodes,
 * then this node's value is the smaller value among its two sub-nodes. More formally,
 * the property root.val = min(root.left.val, root.right.val) always holds.
 *
 * Given such a binary tree, you need to output the second minimum value
 * in the set made of all the nodes' value in the whole tree.
 *
 * If no such second minimum value exists, output -1 instead
 */

public class Solution {

    public int findSecondMinimumValue(TreeNode root) {
        return root == null ? -1 : dfs(root, root.val);
    }

    private int dfs(TreeNode root, int target) {
        if (root.val > target) return root.val;
        int left = root.left == null ? -1 : dfs(root.left, target);
        int right = root.right == null ? -1 : dfs(root.right, target);
        if (left == -1) return right;
        if (right == -1) return left;
        return Math.min(left, right);
    }

    public static void main(String[] args) {
        assert new Solution().findSecondMinimumValue(TreeNode.build("[2,2,2]")) == -1;
        assert new Solution().findSecondMinimumValue(TreeNode.build("[1,1,3,1,1,3,4,3,1,1,1,3,8,4,8,3,3,1,6,2,1]")) == 2;
        assert new Solution().findSecondMinimumValue(TreeNode.build("[2,2,5,null,null,5,5]")) == 5;
        assert new Solution().findSecondMinimumValue(TreeNode.build(2,2,2)) == -1;
        assert new Solution().findSecondMinimumValue(TreeNode.build(2,2,5,null,null,5,7)) == 5;
    }

}
