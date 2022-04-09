package problem.p513findbottomlefttreevalue;

import common.TreeNode;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 513. Find Bottom Left Tree Value
 *
 * https://leetcode-cn.com/problems/find-bottom-left-tree-value/
 *
 * Given the root of a binary tree, return the leftmost value in the last row of the tree.
 */

public class Solution {

    private int depth = -1, val = 0;

    public int findBottomLeftValueV1(TreeNode root) {
        dfs(root, 0);
        return val;
    }

    private void dfs(TreeNode node, int d) {
        if (node == null) return;
        if (d > depth) {
            val = node.val;
            depth = d;
        }

        dfs(node.left, d + 1);
        dfs(node.right, d + 1);
    }

    public int findBottomLeftValue(TreeNode root) {
        int ans = 0;
        Queue<TreeNode> queue = new ArrayDeque<>();
        for (queue.add(root); !queue.isEmpty(); ) {
            ans = queue.peek().val;
            for (int i = 0, n = queue.size(); i < n; i++) {
                TreeNode node = queue.remove();
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().findBottomLeftValue(TreeNode.build(2,1,3)) == 1;
        assert new Solution().findBottomLeftValue(TreeNode.build(1,2,3,4,null,5,6,null,null,7)) == 7;

        assert new Solution().findBottomLeftValueV1(TreeNode.build(2,1,3)) == 1;
        assert new Solution().findBottomLeftValueV1(TreeNode.build(1,2,3,4,null,5,6,null,null,7)) == 7;
    }

}
