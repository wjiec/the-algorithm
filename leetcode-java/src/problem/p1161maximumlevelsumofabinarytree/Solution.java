package problem.p1161maximumlevelsumofabinarytree;

import common.TreeNode;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 1161. Maximum Level Sum of a Binary Tree
 *
 * https://leetcode.cn/problems/maximum-level-sum-of-a-binary-tree/
 *
 * Given the root of a binary tree, the level of its root is 1, the level of its children is 2, and so on.
 *
 * Return the smallest level x such that the sum of all the values of nodes at level x is maximal.
 */

public class Solution {

    public int maxLevelSum(TreeNode root) {
        int ans = 1, level = 1, sum = root.val;
        Queue<TreeNode> queue = new ArrayDeque<>();
        for (queue.add(root); !queue.isEmpty(); level++) {
            int curr = 0;
            for (int i = 0, n = queue.size(); i < n; i++) {
                TreeNode node = queue.remove();
                curr += node.val;

                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }
            if (curr > sum) { ans = level; sum = curr; }
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().maxLevelSum(TreeNode.build(1,7,0,7,-8,null,null)) == 2;
        assert new Solution().maxLevelSum(TreeNode.build(989,null,10250,98693,-89388,null,null,null,-32127)) == 2;
    }

}
