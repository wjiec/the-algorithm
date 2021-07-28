package daily.d210728p863allnodesdistancekinbinarytree;

import common.TreeNode;

import java.util.*;

/**
 * 863. All Nodes Distance K in Binary Tree
 *
 * https://leetcode-cn.com/problems/all-nodes-distance-k-in-binary-tree/
 *
 * Given the root of a binary tree, the value of a target node target, and an integer k,
 * return an array of the values of all nodes that have a distance k from the target node.
 *
 * You can return the answer in any order.
 */

public class Solution {

    private final List<Integer> ans = new ArrayList<>();
    private final Map<TreeNode, TreeNode> parents = new HashMap<>();

    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        if (root == null) return Collections.emptyList();

        findParent(root);
        dfs(target, null, 0, k);

        return ans;
    }

    private void findParent(TreeNode node) {
        if (node.left != null) {
            parents.put(node.left, node);
            findParent(node.left);
        }

        if (node.right != null) {
            parents.put(node.right, node);
            findParent(node.right);
        }
    }

    private void dfs(TreeNode node, TreeNode parent, int level, int k) {
        if (node == null) return;
        if (level == k) { ans.add(node.val); return; }

        if (node.left != parent) dfs(node.left, node, level + 1, k);
        if (node.right != parent) dfs(node.right, node, level + 1, k);
        if (parents.get(node) != parent) dfs(parents.get(node), node, level + 1, k);
    }

    public static void main(String[] args) {
        System.out.println(new Solution().distanceK(TreeNode.build(0,null,1,2,5,null,3,null,null,null,4), TreeNode.build(2), 2));
        System.out.println(new Solution().distanceK(TreeNode.build(0,null,1,null,2,null,3,null,4), TreeNode.build(0), 2));

        assert new Solution().distanceK(TreeNode.build(3,5,1,6,2,0,8,null,null,7,4), TreeNode.build(5), 2)
            .equals(List.of(1, 4, 7));
    }

}
