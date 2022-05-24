package problem.p865smallestsubtreewithallthedeepestnodes;

import common.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 865. Smallest Subtree with all the Deepest Nodes
 *
 * https://leetcode.cn/problems/smallest-subtree-with-all-the-deepest-nodes/
 *
 * Given the root of a binary tree, the depth of each node is the shortest distance to the root.
 *
 * Return the smallest subtree such that it contains all the deepest nodes in the original tree.
 *
 * A node is called the deepest if it has the largest depth possible among any node in the entire tree.
 *
 * The subtree of a node is a tree consisting of that node, plus the set of all descendants of that node.
 */

public class Solution {

    private int index = 0;
    private List<TreeNode> paths = new ArrayList<>();

    public TreeNode subtreeWithAllDeepest(TreeNode root) {
        dfs(root, new ArrayList<>());
        return paths.get(index);
    }

    private void dfs(TreeNode node, List<TreeNode> curr) {
        if (curr.size() >= paths.size()) {
            if (curr.size() > paths.size()) {
                paths = curr; index = paths.size() - 1;
            } else if (!paths.isEmpty()) {
                while (curr.get(index) != paths.get(index)) index--;
            }
        }

        if (node == null) return;
        dfs(node.left, new ArrayList<>(curr) {{ add(node); }});
        dfs(node.right, new ArrayList<>(curr) {{ add(node); }});
    }

    public static void main(String[] args) {
        assert new Solution().subtreeWithAllDeepest(TreeNode.build(3,5,1,6,2,0,8,null,null,7,4)).val == 2;
        assert new Solution().subtreeWithAllDeepest(TreeNode.build(1)).val == 1;
        assert new Solution().subtreeWithAllDeepest(TreeNode.build(0,1,3,null,2)).val == 2;
        assert new Solution().subtreeWithAllDeepest(TreeNode.build(3,5,1,6,2,0,8,null,null,7,4,1,1)).val == 3;
    }

}
