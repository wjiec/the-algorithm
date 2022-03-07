package problem.p199binarytreerightsideview;

import common.TreeNode;

import java.util.*;

/**
 * 199. Binary Tree Right Side View
 *
 * https://leetcode-cn.com/problems/binary-tree-right-side-view/
 *
 * Given the root of a binary tree, imagine yourself standing on the right side of it,
 * return the values of the nodes you can see ordered from top to bottom.
 */

public class Solution {

    public List<Integer> rightSideView(TreeNode root) {
        if (root == null) return Collections.emptyList();

        List<Integer> ans = new ArrayList<>();
        Queue<TreeNode> queue = new ArrayDeque<>();
        for (queue.add(root); !queue.isEmpty(); ) {
            int value = -1;
            for (int i = 0, n = queue.size(); i < n; i++) {
                TreeNode node = queue.remove();
                value = node.val;
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }
            ans.add(value);
        }

        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().rightSideView(TreeNode.build(1,2,3,null,5,null,4)).equals(List.of(1,3,4));
        assert new Solution().rightSideView(TreeNode.build(1,null,3)).equals(List.of(1,3));
        assert new Solution().rightSideView(TreeNode.build()).equals(List.of());
    }

}
