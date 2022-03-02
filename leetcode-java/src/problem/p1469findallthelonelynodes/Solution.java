package problem.p1469findallthelonelynodes;

import common.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 1469. Find All The Lonely Nodes
 *
 * https://leetcode-cn.com/problems/find-all-the-lonely-nodes/
 *
 * In a binary tree, a lonely node is a node that is the only child of its parent node.
 * The root of the tree is not lonely because it does not have a parent node.
 *
 * Given the root of a binary tree, return an array containing the values of all lonely nodes in the tree.
 * Return the list in any order.
 */

public class Solution {

    public List<Integer> getLonelyNodes(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        dfs(root, ans);
        return ans;
    }

    private void dfs(TreeNode node, List<Integer> ans) {
        if (node.left != null && node.right == null) ans.add(node.left.val);
        if (node.left == null && node.right != null) ans.add(node.right.val);

        if (node.left != null) dfs(node.left, ans);
        if (node.right != null) dfs(node.right, ans);
    }

    public static void main(String[] args) {
        System.out.println(new Solution().getLonelyNodes(TreeNode.build(1,2,3,null,4)));
        System.out.println(new Solution().getLonelyNodes(TreeNode.build(7,1,4,6,null,5,3,null,null,null,null,null,2)));
        System.out.println(new Solution().getLonelyNodes(TreeNode.build(11,99,88,77,null,null,66,55,null,null,44,33,null,null,22)));
        System.out.println(new Solution().getLonelyNodes(TreeNode.build(197)));
        System.out.println(new Solution().getLonelyNodes(TreeNode.build(31,null,78,null,28)));
    }

}
