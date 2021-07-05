package offer.p26shudezijiegoulcof;

import common.TreeNode;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 剑指 Offer 26. 树的子结构
 *
 * https://leetcode-cn.com/problems/shu-de-zi-jie-gou-lcof/
 *
 * 输入两棵二叉树A和B，判断B是不是A的子结构。(约定空树不是任意一个树的子结构)
 *
 * B是A的子结构， 即 A中有出现和B相同的结构和节点值。
 */

public class Solution {

    public boolean isSubStructure(TreeNode A, TreeNode B) {
        if (A == null || B == null) return false;

        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(A);

        while (!queue.isEmpty()) {
            var node = queue.remove();
            if (node.val == B.val && isSameTree(node, B)) return true;

            if (node.left != null) queue.add(node.left);
            if (node.right != null) queue.add(node.right);
        }
        return false;
    }

    private boolean isSameTree(TreeNode left, TreeNode right) {
        if (left == null || left.val != right.val) return false;
        if (right.left != null && !isSameTree(left.left, right.left)) return false;
        return right.right == null || isSameTree(left.right, right.right);
    }

    public static void main(String[] args) {
        assert !new Solution().isSubStructure(TreeNode.build(1,0,1,-4,-3), TreeNode.build(1,-4));

        assert !new Solution().isSubStructure(TreeNode.build(1,2,3), TreeNode.build(3,1));
        assert new Solution().isSubStructure(TreeNode.build(3,4,5,1,2), TreeNode.build(4,1));
    }

}
