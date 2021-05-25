package problem.p501findmodeinbinarysearchtree;

import common.Checker;
import common.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 501. Find Mode in Binary Search Tree
 *
 * https://leetcode-cn.com/problems/find-mode-in-binary-search-tree/
 *
 * Given the root of a binary search tree (BST) with duplicates,
 * return all the mode(s) (i.e., the most frequently occurred element) in it.
 *
 * If the tree has more than one mode, return them in any order.
 *
 * Assume a BST is defined as follows:
 *
 * The left subtree of a node contains only nodes with keys less than or equal to the node's key.
 * The right subtree of a node contains only nodes with keys greater than or equal to the node's key.
 * Both the left and right subtrees must also be binary search trees.
 */

public class Solution {

    private List<Integer> rs = new ArrayList<>();
    private int count, val, maxCount;

    public int[] findMode(TreeNode root) {
        dfs(root);

        int[] ns = new int[rs.size()];
        for (int i = 0, sz = ns.length; i < sz; i++) {
            ns[i] = rs.get(i);
        }
        return ns;
    }

    private void dfs(TreeNode root) {
        if (root != null) {
            dfs(root.left);
            if (root.val == val) {
                count++;
            } else {
                count = 1;
                val = root.val;
            }
            if (count == maxCount) {
                rs.add(val);
            } else if (count > maxCount) {
                maxCount = count;
                rs.clear();
                rs.add(val);
            }
            dfs(root.right);
        }
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().findMode(TreeNode.build(1,null,2,2)), new int[]{2});
        assert Checker.check(new Solution().findMode(TreeNode.build(0)), new int[]{0});
    }

}
