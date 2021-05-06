package daily.d210427p938rangesumofbst;

import common.TreeNode;

/**
 * 938. Range Sum of BST
 *
 * https://leetcode-cn.com/problems/range-sum-of-bst/
 *
 * Given the root node of a binary search tree, return the
 * sum of values of all nodes with a value in the range [low, high].
 */

public class Solution {

    public int rangeSumBST(TreeNode root, int low, int high) {
        int rs = root.val >= low && root.val <= high ? root.val : 0;
        if (root.val > low && root.left != null) {
            rs += rangeSumBST(root.left, low, high);
        }
        if (root.val < high && root.right != null) {
            rs += rangeSumBST(root.right, low, high);
        }

        return rs;
    }

    public static void main(String[] args) {
    }

}
