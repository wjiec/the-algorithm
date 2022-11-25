package lcci.s4.p12pathswithsumlcci;

import common.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * 面试题 04.12. 求和路径
 *
 * https://leetcode.cn/problems/paths-with-sum-lcci/
 *
 * 给定一棵二叉树，其中每个节点都含有一个整数数值(该值或正或负)。设计一个算法，打印节点数值总和等于某个给定值的所有路径的数量。
 * 注意，路径不一定非得从二叉树的根节点或叶节点开始或结束，但是其方向必须向下(只能从父节点指向子节点方向)。
 */

public class Solution {

    public int pathSum(TreeNode root, int sum) {
        if (root == null) return 0;

        int ans = sumOf(root, sum);
        ans += pathSum(root.left, sum);
        ans += pathSum(root.right, sum);
        return ans;
    }

    private int sumOf(TreeNode node, int sum) {
        if (node == null) return 0;

        int ans = 0;
        if (node.val == sum) ans++;

        ans += sumOf(node.left, sum - node.val);
        ans += sumOf(node.right, sum - node.val);

        return ans;
    }

    private static class PrefixSum {
        private final Map<Long, Integer> prefix = new HashMap<>();
        public int pathSum(TreeNode root, int sum) {
            prefix.clear();
            prefix.put(0L, 1);

            return dfs(root, 0, sum);
        }

        private int dfs(TreeNode node, long curr, int sum) {
            if (node == null) return 0;

            curr += node.val;
            int ans = prefix.getOrDefault(curr - sum, 0);
            prefix.merge(curr, 1, Integer::sum);

            ans += dfs(node.left, curr, sum);
            ans += dfs(node.right, curr, sum);

            prefix.merge(curr, -1, Integer::sum);
            return ans;
        }
    }

    public static void main(String[] args) {
        assert new Solution().pathSum(TreeNode.build(1,null,2,null,3,null,4,null,5), 3) == 2;
        assert new Solution().pathSum(TreeNode.build(5,4,8,11,null,13,4,7,2,5,1), 22) == 3;

        assert new PrefixSum().pathSum(TreeNode.build(1,null,2,null,3,null,4,null,5), 3) == 2;
        assert new PrefixSum().pathSum(TreeNode.build(5,4,8,11,null,13,4,7,2,5,1), 22) == 3;
    }

}
