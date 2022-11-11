package offer2.p50;

import common.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * 剑指 Offer II 050. 向下的路径节点之和
 *
 * https://leetcode.cn/problems/6eUYwP/
 *
 * 给定一个二叉树的根节点 root ，和一个整数 targetSum ，求该二叉树里节点值之和等于 targetSum 的 路径 的数目。
 *
 * 路径 不需要从根节点开始，也不需要在叶子节点结束，但是路径方向必须是向下的（只能从父节点到子节点）。
 */

@SuppressWarnings("DuplicatedCode")
public class Solution {

    private int ans = 0;

    public int pathSum(TreeNode root, int targetSum) {
        if (root == null) return 0;
        dfs(root.left, root.val, targetSum);
        dfs(root.right, root.val, targetSum);
        pathSum(root.left, targetSum);
        pathSum(root.right, targetSum);
        return ans / 2;
    }

    private void dfs(TreeNode node, long curr, long target) {
        if (curr == target) ans++;
        if (node == null) return;

        dfs(node.left, curr + node.val, target);
        dfs(node.right, curr + node.val, target);
    }

    private static class PrefixSum {
        public int pathSum(TreeNode root, int targetSum) {
            Map<Long, Integer> prefix = new HashMap<>();
            prefix.put(0L, 1);
            return dfs(root, prefix, 0, targetSum);
        }
        public int dfs(TreeNode node, Map<Long, Integer> prefix, long curr, long target) {
            if (node == null) return 0;

            int ans = 0;
            curr += node.val;
            ans += prefix.getOrDefault(curr - target, 0);

            prefix.merge(curr, 1, Integer::sum);
            ans += dfs(node.left, prefix, curr, target);
            ans += dfs(node.right, prefix, curr, target);
            prefix.merge(curr, -1, Integer::sum);

            return ans;
        }
    }

    public static void main(String[] args) {
        assert new Solution().pathSum(TreeNode.build(10,5,-3,3,2,null,11,3,-2,null,1), 8) == 3;
        assert new Solution().pathSum(TreeNode.build(5,4,8,11,null,13,4,7,2,null,null,5,1), 22) == 3;

        assert new PrefixSum().pathSum(TreeNode.build(10,5,-3,3,2,null,11,3,-2,null,1), 8) == 3;
        assert new PrefixSum().pathSum(TreeNode.build(5,4,8,11,null,13,4,7,2,null,null,5,1), 22) == 3;
    }

}
