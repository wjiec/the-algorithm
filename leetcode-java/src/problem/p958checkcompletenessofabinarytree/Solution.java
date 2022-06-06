package problem.p958checkcompletenessofabinarytree;

import common.TreeNode;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 958. Check Completeness of a Binary Tree
 *
 * https://leetcode.cn/problems/check-completeness-of-a-binary-tree/
 *
 * Given the root of a binary tree, determine if it is a complete binary tree.
 *
 * In a complete binary tree, every level, except possibly the last,
 * is completely filled, and all nodes in the last level are as far left as possible.
 * It can have between 1 and 2h nodes inclusive at the last level h.
 */

public class Solution {

    public boolean isCompleteTree(TreeNode root) {
        boolean incompletely = false;
        Queue<TreeNode> queue = new ArrayDeque<>();
        for (queue.add(root); !queue.isEmpty(); ) {
            for (int i = 0, n = queue.size(); i < n; i++) {
                TreeNode curr = queue.remove();
                if (incompletely && (curr.left != null || curr.right != null)) return false;

                incompletely = incompletely || curr.left == null || curr.right == null;
                if (incompletely && curr.right != null) return false;

                if (curr.left != null) queue.add(curr.left);
                if (curr.right != null) queue.add(curr.right);
            }
        }
        return true;
    }

    public static void main(String[] args) {
        assert new Solution().isCompleteTree(TreeNode.build(1,2,3,4,5,6));
        assert !new Solution().isCompleteTree(TreeNode.build(1,2,3,4,5,null,7));
        assert !new Solution().isCompleteTree(TreeNode.build(1,2,null,4,null,null,null));
    }

}
