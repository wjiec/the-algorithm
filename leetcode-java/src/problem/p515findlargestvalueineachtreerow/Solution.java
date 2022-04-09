package problem.p515findlargestvalueineachtreerow;

import common.TreeNode;

import java.util.*;

/**
 * 515. Find Largest Value in Each Tree Row
 *
 * https://leetcode-cn.com/problems/find-largest-value-in-each-tree-row/
 *
 * Given the root of a binary tree, return an array of the largest value in each row of the tree (0-indexed).
 */

public class Solution {

    public List<Integer> largestValues(TreeNode root) {
        if (root == null) return Collections.emptyList();

        List<Integer> ans = new ArrayList<>();
        Queue<TreeNode> queue = new ArrayDeque<>();
        for (queue.add(root); !queue.isEmpty(); ) {
            int v = queue.peek().val;
            for (int i = 0, n = queue.size(); i < n; i++) {
                TreeNode node = queue.remove();
                if (node.val > v) v = node.val;
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }
            ans.add(v);
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().largestValues(TreeNode.build(1,3,2,5,3,null,9)).equals(List.of(1,3,9));
        assert new Solution().largestValues(TreeNode.build(1,2,3)).equals(List.of(1,3));
    }

}
