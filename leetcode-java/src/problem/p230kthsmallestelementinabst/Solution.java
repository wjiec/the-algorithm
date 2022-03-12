package problem.p230kthsmallestelementinabst;

import common.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 230. Kth Smallest Element in a BST
 *
 * https://leetcode-cn.com/problems/kth-smallest-element-in-a-bst/
 *
 * Given the root of a binary search tree, and an integer k,
 * return the kth smallest value (1-indexed) of all the values of the nodes in the tree.
 */

public class Solution {

    public int kthSmallest(TreeNode root, int k) {
        Deque<TreeNode> stack = new ArrayDeque<>();
        while (!stack.isEmpty() || root != null) {
            for (; root != null; root = root.left) stack.push(root);

            root = stack.pop();
            if (--k == 0) return root.val;
            root = root.right;
        }
        return -1;
    }

    public static void main(String[] args) {
        assert new Solution().kthSmallest(TreeNode.build(3,1,4,null,2), 1) == 1;
        assert new Solution().kthSmallest(TreeNode.build(5,3,6,2,4,null,null,1), 3) == 3;
    }

}
