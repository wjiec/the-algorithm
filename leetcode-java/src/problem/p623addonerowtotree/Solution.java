package problem.p623addonerowtotree;

import common.Checker;
import common.TreeNode;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 623. Add One Row to Tree
 *
 * https://leetcode-cn.com/problems/add-one-row-to-tree/
 *
 * Given the root of a binary tree and two integers val and depth,
 * add a row of nodes with value val at the given depth depth.
 *
 * Note that the root node is at depth 1.
 *
 * The adding rule is:
 *
 * Given the integer depth, for each not null tree node cur at the depth depth - 1,
 * create two tree nodes with value val as cur's left subtree root and right subtree root.
 *
 * cur's original left subtree should be the left subtree of the new left subtree root.
 *
 * cur's original right subtree should be the right subtree of the new right subtree root.
 *
 * If depth == 1 that means there is no depth depth - 1 at all, then create a tree node
 * with value val as the new root of the whole original tree,
 * and the original tree is the new root's left subtree.
 */

@SuppressWarnings("DuplicatedCode")
public class Solution {

    public TreeNode addOneRow(TreeNode root, int val, int depth) {
        if (depth == 1) return new TreeNode(val, root, null);

        Queue<TreeNode> queue = new ArrayDeque<>();
        for (queue.add(root), depth -= 2; !queue.isEmpty() && depth != 0; depth--) {
            for (int i = 0, n = queue.size(); i < n; i++) {
                TreeNode curr = queue.remove();
                if (curr.left != null) queue.add(curr.left);
                if (curr.right != null) queue.add(curr.right);
            }
        }

        while (!queue.isEmpty()) {
            TreeNode curr = queue.remove();
            curr.left = new TreeNode(val, curr.left, null);
            curr.right = new TreeNode(val, null, curr.right);
        }

        return root;
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().addOneRow(TreeNode.build(1,2,3,4), 5, 4),
            TreeNode.build(1,2,3,4,null,null,null,5,5));

        assert Checker.check(new Solution().addOneRow(TreeNode.build(4,2,6,3,1,5), 1, 2),
            TreeNode.build(4,1,1,2,null,null,6,3,1,5));

        assert Checker.check(new Solution().addOneRow(TreeNode.build(4,2,null,3,1), 1, 3),
            TreeNode.build(4,2,null,1,1,3,null,null,1));
    }

}
