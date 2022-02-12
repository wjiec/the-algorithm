package daily.d210510p872leafsimilartrees;

import common.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 872. Leaf-Similar Trees
 *
 * https://leetcode-cn.com/problems/leaf-similar-trees/
 *
 * Consider all the leaves of a binary tree, from left to right order,
 * the values of those leaves form a leaf value sequence.
 *
 * For example, in the given tree above, the leaf value sequence is (6, 7, 4, 9, 8).
 *
 * Two binary trees are considered leaf-similar if their leaf value sequence is the same.
 *
 * Return true if and only if the two given trees with head nodes root1 and root2 are leaf-similar.
 */

public class Solution {

    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        return leafs(root1).equals(leafs(root2));
    }

    private List<Integer> leafs(TreeNode root) {
        List<Integer> rs = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>() {{ add(root); }};
        while (!stack.empty()) {
            TreeNode node = stack.pop();
            if (node != null) {
                stack.push(node.left);
                stack.push(node.right);
                if (node.left == null && node.right == null) {
                    rs.add(node.val);
                }
            }
        }
        return rs;
    }

    public static void main(String[] args) {
        assert new Solution().leafSimilar(TreeNode.build(3,5,1,6,2,9,8,null,null,7,4),
            TreeNode.build(3,5,1,6,7,4,2,null,null,null,null,null,null,9,8));
        assert new Solution().leafSimilar(TreeNode.build(1), TreeNode.build(1));
        assert !new Solution().leafSimilar(TreeNode.build(1), TreeNode.build(2));
        assert new Solution().leafSimilar(TreeNode.build(1,2), TreeNode.build(2,2));
        assert !new Solution().leafSimilar(TreeNode.build(1,2,3), TreeNode.build(1,3,2));
    }

}
