package problem.p106constructbinarytreefrominorderandpostordertraversal;

import common.Checker;
import common.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * 106. Construct Binary Tree from Inorder and Postorder Traversal
 *
 * https://leetcode-cn.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/
 *
 * Given two integer arrays inorder and postorder where inorder is the inorder traversal of a binary tree
 * and postorder is the postorder traversal of the same tree, construct and return the binary tree.
 */

public class Solution {

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) map.put(inorder[i], i);

        return buildTree(map, 0, inorder.length, postorder, 0, postorder.length);
    }

    // inorder: [left] [root] [right]
    // postorder: [left] [right] [root]
    private TreeNode buildTree(Map<Integer, Integer> map, int a, int b, int[] postorder, int c, int d) {
        if (a >= b || c >= d) return null;
        TreeNode node = new TreeNode(postorder[d - 1]);

        int index = map.get(node.val);
        int leftCount = index - a, rightCount = b - index - 1;

        node.left = buildTree(map, a, index, postorder, c, c + leftCount);
        node.right = buildTree(map, index + 1, b, postorder, d - rightCount - 1, d - 1);

        return node;
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().buildTree(new int[]{9,3,15,20,7}, new int[]{9,15,7,20,3}),
            TreeNode.build(3,9,20,null,null,15,7));
        assert Checker.check(new Solution().buildTree(new int[]{-1}, new int[]{-1}), TreeNode.build(-1));
    }

}
