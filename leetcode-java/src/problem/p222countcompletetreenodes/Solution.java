package problem.p222countcompletetreenodes;

import common.TreeNode;

/**
 * 222. Count Complete Tree Nodes
 *
 * https://leetcode-cn.com/problems/count-complete-tree-nodes/
 *
 * Given the root of a complete binary tree, return the number of the nodes in the tree.
 *
 * According to Wikipedia, every level, except possibly the last, is completely filled in a complete binary tree,
 * and all nodes in the last level are as far left as possible.
 *
 * It can have between 1 and 2h nodes inclusive at the last level h.
 *
 * Design an algorithm that runs in less than O(n) time complexity.
 */

public class Solution {

    public int countNodes(TreeNode root) {
        if (root == null) return 0;

        int depth = 0;
        for (TreeNode node = root; node.left != null; node = node.left) depth++;

        int lo = 1 << depth, hi = (1 << (depth + 1)) - 1;
        while (lo < hi) {
            int mid = lo + (hi - lo + 1) / 2;
            if (exists(root, depth, mid)) lo = mid;
            else hi = mid - 1;
        }
        return lo;
    }

    private boolean exists(TreeNode root, int depth, int k) {
        int bits = 1 << (depth - 1); TreeNode node = root;
        while (node != null && bits > 0) {
            if ((bits & k) == 0) node = node.left;
            else node = node.right;

            bits >>= 1;
        }
        return node != null;
    }

    public static void main(String[] args) {
        assert new Solution().countNodes(TreeNode.build(1,2,3,4,5,6)) == 6;
        assert new Solution().countNodes(TreeNode.build()) == 0;
        assert new Solution().countNodes(TreeNode.build(1)) == 1;
    }

}
