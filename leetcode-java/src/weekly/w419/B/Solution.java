package weekly.w419.B;

import common.TreeNode;

import java.util.PriorityQueue;

/**
 * 3319. K-th Largest Perfect Subtree Size in Binary Tree
 *
 * https://leetcode.cn/contest/weekly-contest-419/problems/k-th-largest-perfect-subtree-size-in-binary-tree/
 *
 * You are given the root of a binary tree and an integer k.
 *
 * Return an integer denoting the size of the kth largest perfect binary subtree, or -1 if it doesn't exist.
 *
 * A perfect binary tree is a tree where all leaves are on the same level, and every parent has two children.
 */

public class Solution {

    public int kthLargestPerfectSubtree(TreeNode root, int k) {
        dfs(root);
        while (--k > 0 && !pq.isEmpty()) pq.remove();
        return pq.isEmpty() ? -1 : pq.remove();
    }

    private final PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);

    private int dfs(TreeNode node) {
        if (node == null) return 0;

        int left = dfs(node.left);
        int right = dfs(node.right);
        if (left == -1 || right == -1) return -1;
        if ((left & (left + 1)) != 0) return -1;
        if ((right & (right + 1)) != 0) return -1;

        int ans = left + right + 1;
        if ((ans & (ans + 1)) == 0) pq.add(ans);
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().kthLargestPerfectSubtree(TreeNode.build(5,3,6,5,2,5,7,1,8,null,null,6,8), 2) == 3;
        assert new Solution().kthLargestPerfectSubtree(TreeNode.build(1,2,3,4,5,6,7), 1) == 7;
        assert new Solution().kthLargestPerfectSubtree(TreeNode.build(1,2,3,null,4), 3) == -1;
    }

}
