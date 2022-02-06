package problem.p257binarytreepaths;

import common.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 257. Binary Tree Paths
 *
 * https://leetcode-cn.com/problems/binary-tree-paths/
 *
 * Given the root of a binary tree, return all root-to-leaf paths in any order.
 *
 * A leaf is a node with no children.
 */

public class Solution {

    public List<String> binaryTreePaths(TreeNode root) {
        List<String> result = new ArrayList<>();
        dfs(root, new ArrayList<>(), result);
        return result;
    }

    private void dfs(TreeNode root, List<String> numbers, List<String> result) {
        numbers.add(String.valueOf(root.val));
        if (root.left == null && root.right == null) {
            result.add(String.join("->", numbers));
        } else {
            if (root.left != null) {
                dfs(root.left, numbers, result);
            }
            if (root.right != null) {
                dfs(root.right, numbers, result);
            }
        }
        numbers.remove(numbers.size() - 1);
    }

    public static void main(String[] args) {
        assert new Solution().binaryTreePaths(TreeNode.build(1)).equals(List.of("1"));
        assert new Solution().binaryTreePaths(TreeNode.build(1,2,3,null,5)).equals(List.of("1->2->5", "1->3"));
    }

}
