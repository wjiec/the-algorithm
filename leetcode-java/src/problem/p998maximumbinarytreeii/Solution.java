package problem.p998maximumbinarytreeii;

import common.Checker;
import common.TreeNode;

/**
 * 998. Maximum Binary Tree II
 *
 * https://leetcode.cn/problems/maximum-binary-tree-ii/
 *
 * A maximum tree is a tree where every node has a value greater than any other value in its subtree.
 *
 * You are given the root of a maximum binary tree and an integer val.
 *
 * Just as in the previous problem, the given tree was constructed from a list a (root = Construct(a))
 * recursively with the following Construct(a) routine:
 *
 * If a is empty, return null.
 * Otherwise, let a[i] be the largest element of a. Create a root node with the value a[i].
 * The left child of root will be Construct([a[0], a[1], ..., a[i - 1]]).
 * The right child of root will be Construct([a[i + 1], a[i + 2], ..., a[a.length - 1]]).
 * Return root.
 * Note that we were not given a directly, only a root node root = Construct(a).
 *
 * Suppose b is a copy of a with the value val appended to it. It is guaranteed that b has unique values.
 *
 * Return Construct(b).
 */

public class Solution {

    public TreeNode insertIntoMaxTree(TreeNode root, int val) {
        if (root == null) return new TreeNode(val);
        // 插在最后, 原本的二叉树会在左侧
        if (val > root.val) return new TreeNode(val, root, null);
        // 因为是插在最后面, 所以直接无脑放右子树就行
        root.right = insertIntoMaxTree(root.right, val);
        return root;
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().insertIntoMaxTree(TreeNode.build(3,2), 1),
            TreeNode.build(3,2,1));

        assert Checker.check(new Solution().insertIntoMaxTree(TreeNode.build(4,1,3,null,null,2), 5),
            TreeNode.build(5,4,null,1,3,null,null,2));

        assert Checker.check(new Solution().insertIntoMaxTree(TreeNode.build(5,2,4,null,1), 3),
            TreeNode.build(5,2,4,null,1,null,3));

        assert Checker.check(new Solution().insertIntoMaxTree(TreeNode.build(5,2,3,null,1), 4),
            TreeNode.build(5,2,4,null,1,3));
    }

}
