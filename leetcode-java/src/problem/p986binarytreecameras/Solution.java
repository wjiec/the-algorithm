package problem.p986binarytreecameras;

import common.Optimizable;
import common.Tag;
import common.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * 968. Binary Tree Cameras
 *
 * https://leetcode.cn/problems/binary-tree-cameras
 *
 * You are given the root of a binary tree. We install cameras on the tree nodes
 * where each camera at a node can monitor its parent, itself, and its immediate children.
 *
 * Return the minimum number of cameras needed to monitor all nodes of the tree.
 */

@Optimizable("贪心")
public class Solution {

    public int minCameraCover(TreeNode root) {
        return cover(root, 0);
    }

    private final Map<TreeNode, int[]> memo = new HashMap<>();

    // 计算节点 node 的亮度为 lm 时 所需的最小摄像机数量
    private int cover(TreeNode node, int lm) {
        if (node == null) return 0;
        if (node.left == null && node.right == null) return lm > 0 ? 0 : 1;

        var cache = memo.computeIfAbsent(node, k -> new int[]{-1, -1, -1});
        if (cache[lm] != -1) return cache[lm];

        int ans = Integer.MAX_VALUE;

        // 放在左子节点
        if (node.left != null) ans = Math.min(ans, 1 + cover(node.left, 2) + cover(node.right, Math.max(0, lm - 1)));
        // 放在右子节点
        if (node.right != null) ans = Math.min(ans, cover(node.left, Math.max(0, lm - 1)) + 1 + cover(node.right, 2));
        // 两个子节点都放上
        if (node.left != null && node.right != null) ans = Math.min(ans, 2 + cover(node.left, 2) + cover(node.right, 2));
        // 如果当前节点可以不放, 那就跳过
        if (lm > 0) ans = Math.min(ans, cover(node.left, lm - 1) + cover(node.right, lm - 1));
        // 或者当前节点再放一个
        ans = Math.min(ans, (lm == 2 ? 0 : 1) + cover(node.left, 1) + cover(node.right, 1));

        return cache[lm] = ans;
    }

    public static void main(String[] args) {
        assert new Solution().minCameraCover(TreeNode.build(1,2,null,null,3,4,null,null,5,6)) == 2;

        assert new Solution().minCameraCover(TreeNode.build(0,0,null,0,0)) == 1;
        assert new Solution().minCameraCover(TreeNode.build(0,0,null,0,null,0,null,null,0)) == 2;
    }

}
