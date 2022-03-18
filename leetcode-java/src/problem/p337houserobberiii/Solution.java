package problem.p337houserobberiii;

import common.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * 337. House Robber III
 *
 * https://leetcode-cn.com/problems/house-robber-iii/
 *
 * The thief has found himself a new place for his thievery again.
 * There is only one entrance to this area, called root.
 *
 * Besides the root, each house has one and only one parent house. After a tour,
 * the smart thief realized that all houses in this place form a binary tree.
 * It will automatically contact the police if two directly-linked houses were
 * broken into on the same night.
 *
 * Given the root of the binary tree, return the maximum amount of money
 * the thief can rob without alerting the police.
 */

public class Solution {

    private final Map<TreeNode, Integer> robbed = new HashMap<>();
    private final Map<TreeNode, Integer> skipped = new HashMap<>();

    public int rob(TreeNode root) {
//        // slow
//        return rob(root, false);
        dfs(root);
        return Math.max(robbed.getOrDefault(root, 0), skipped.getOrDefault(root, 0));
    }

    private void dfs(TreeNode node) {
        if (node == null) return;

        dfs(node.left);
        dfs(node.right);

        // 偷当前节点就不能偷子节点
        robbed.put(node, node.val
            + skipped.getOrDefault(node.left, 0)
            + skipped.getOrDefault(node.right, 0));

        // 不偷当前节点则可以选则偷子节点也可以选择不偷子节点
        skipped.put(node,
            // 偷或者不偷左节点
            Math.max(robbed.getOrDefault(node.left, 0), skipped.getOrDefault(node.left, 0))
            // 偷或者不偷右节点
            + Math.max(robbed.getOrDefault(node.right, 0), skipped.getOrDefault(node.right, 0))
        );
    }

    private int rob(TreeNode node, boolean rootRobbed) {
        // 无意义节点直接返回0
        if (node == null) return 0;

        // 如果已经偷了父节点，则不能偷当前节点
        if (rootRobbed) return rob(node.left, false) + rob(node.right, false);

        // 如果父节点没偷，则可以选择偷当前节点，或者选择偷2个子节点
        return Math.max(
            node.val + rob(node.left, true) + rob(node.right, true),
            rob(node.left, false) + rob(node.right, false)
        );
    }

    public static void main(String[] args) {
        assert new Solution().rob(TreeNode.build(2,1,3,null,4)) == 7;

        assert new Solution().rob(TreeNode.build(3,2,3,null,3,null,1)) == 7;
        assert new Solution().rob(TreeNode.build(3,4,5,1,3,null,1)) == 9;
    }

}
