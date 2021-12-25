package daily.d211225p1609evenoddtree;

import common.TreeNode;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 1609. Even Odd Tree
 *
 * https://leetcode-cn.com/problems/even-odd-tree/
 *
 * A binary tree is named Even-Odd if it meets the following conditions:
 *
 * The root of the binary tree is at level index 0, its children are at level index 1,
 * their children are at level index 2, etc.
 *
 * For every even-indexed level, all nodes at the level have odd integer values
 * in strictly increasing order (from left to right).
 *
 * For every odd-indexed level, all nodes at the level have even integer values
 * in strictly decreasing order (from left to right).
 *
 * Given the root of a binary tree, return true if the binary tree is Even-Odd,
 * otherwise return false.
 */

public class Solution {

    public boolean isEvenOddTree(TreeNode root) {
        Queue<TreeNode> layers = new ArrayDeque<>(); layers.add(root);
        for (int layer = 0; !layers.isEmpty(); layer++) {
            int prev = layer % 2 == 0 ? 0 : 1000001;
            for (int l = layers.size(); l > 0; l--) {
                TreeNode node = layers.remove();
                if (layer % 2 == node.val % 2) return false;
                if (layer % 2 == 0 && node.val <= prev) return false;
                if (layer % 2 == 1 && node.val >= prev) return false;

                prev = node.val;
                if (node.left != null) layers.add(node.left);
                if (node.right != null) layers.add(node.right);
            }
        }
        return true;
    }

    public static void main(String[] args) {
        assert new Solution().isEvenOddTree(TreeNode.build(1,10,4,3,null,7,9,12,8,6,null,null,2));
        assert !new Solution().isEvenOddTree(TreeNode.build(5,4,2,3,3,7));
        assert !new Solution().isEvenOddTree(TreeNode.build(5,9,1,3,5,7));
    }

}
