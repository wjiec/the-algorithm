package lcp.p34;

import common.TreeNode;

/**
 * LCP 34. 二叉树染色
 *
 * https://leetcode.cn/problems/er-cha-shu-ran-se-UGC/
 *
 * 小扣有一个根结点为 root 的二叉树模型，初始所有结点均为白色，可以用蓝色染料给模型结点染色，
 * 模型的每个结点有一个 val 价值。小扣出于美观考虑，希望最后二叉树上每个蓝色相连部分的结点个数不能超过 k 个，
 * 求所有染成蓝色的结点价值总和最大是多少？
 */

public class Solution {

    public int maxValue(TreeNode root, int k) {
        return (int) max(dfs(root, k));
    }

    private long[] dfs(TreeNode node, int k) {
        long[] ans = new long[k + 1];
        if (node == null) return ans;

        long[] left = dfs(node.left, k);
        long[] right = dfs(node.right, k);

        ans[0] = max(left) + max(right);
        for (int i = 0; i < k; i++) {
            for (int j = 0; j < k - i; j++) {
                ans[i + j + 1] = Math.max(ans[i + j + 1], left[i] + right[j] + node.val);
            }
        }

        return ans;
    }

    private long max(long[] array) {
        long ans = Integer.MIN_VALUE;
        for (var v : array) ans = Math.max(ans, v);
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().maxValue(TreeNode.build(5,2,3,4), 2) == 12;
        assert new Solution().maxValue(TreeNode.build(4,1,3,9,null,null,2), 2) == 16;
    }

}
