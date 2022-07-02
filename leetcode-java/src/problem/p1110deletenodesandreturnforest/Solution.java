package problem.p1110deletenodesandreturnforest;

import common.TreeNode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 1110. Delete Nodes And Return Forest
 *
 * https://leetcode.cn/problems/delete-nodes-and-return-forest/
 *
 * Given the root of a binary tree, each node in the tree has a distinct value.
 *
 * After deleting all nodes with a value in to_delete, we are left with a forest (a disjoint union of trees).
 *
 * Return the roots of the trees in the remaining forest. You may return the result in any order.
 */

public class Solution {

    private final Set<TreeNode> ans = new HashSet<>();
    private final Set<Integer> candidates = new HashSet<>();

    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        for (var delete : to_delete) candidates.add(delete);
        ans.add(root); dfs(root);
        return new ArrayList<>(ans);
    }

    private void dfs(TreeNode node) {
        if (node == null) return;

        dfs(node.left);
        dfs(node.right);

        if (node.left != null && candidates.contains(node.left.val))
            node.left = null;
        if (node.right != null && candidates.contains(node.right.val))
            node.right = null;

        if (candidates.contains(node.val)) {
            if (node.left != null) ans.add(node.left);
            if (node.right != null) ans.add(node.right);
            node.left = node.right = null;
            ans.remove(node);
        }
    }

    public static void main(String[] args) {
        System.out.println(new Solution().delNodes(TreeNode.build(1,2,null,4,3), new int[]{2,3}));

        System.out.println(new Solution().delNodes(TreeNode.build(1,2,3,4,5,6,7), new int[]{3,5}));
        System.out.println(new Solution().delNodes(TreeNode.build(1,2,4,null,3), new int[]{3}));
    }

}
