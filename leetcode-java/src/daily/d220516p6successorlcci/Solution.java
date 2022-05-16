package daily.d220516p6successorlcci;

import common.TreeNode;

/**
 * 面试题 04.06. Successor LCCI
 *
 * https://leetcode.cn/problems/successor-lcci/
 *
 * Write an algorithm to find the "next" node (i.e., in-order successor) of a given node in a binary search tree.
 *
 * Return null if there's no "next" node for the given node.
 */

@SuppressWarnings("ConstantConditions")
public class Solution {

    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        if (p.right != null) {
            TreeNode successor = p.right;
            while (successor != null && successor.left != null)
                successor = successor.left;
            return successor;
        }

        TreeNode successor = null;
        for (TreeNode curr = root; curr != null; ) {
            if (curr.val > p.val) { successor = curr; curr = curr.left; }
            else curr = curr.right;
        }

        return successor;
    }

    public static void main(String[] args) {
        TreeNode t1 = TreeNode.build(2,1,3);
        assert new Solution().inorderSuccessor(t1, t1.left).val == 2;

        TreeNode t2 = TreeNode.build(5,3,6,2,4,null,null,1);
        assert new Solution().inorderSuccessor(t2, t2.right) == null;

        TreeNode t3 = TreeNode.build(0);
        assert new Solution().inorderSuccessor(t3, t3) == null;

        TreeNode t4 = TreeNode.build(5,3,6,1,4,null,null,null,2);
        assert new Solution().inorderSuccessor(t4, t4.left.right).val == 5;
    }

}
