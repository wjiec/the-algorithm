package offer2.p54;

import common.Checker;
import common.TreeNode;

/**
 * 剑指 Offer II 054. 所有大于等于节点的值之和
 *
 * https://leetcode.cn/problems/w6cpku/
 *
 * 给定一个二叉搜索树，请将它的每个节点的值替换成树中大于或者等于该节点值的所有节点值之和。
 *
 * 提醒一下，二叉搜索树满足下列约束条件：
 *
 * 节点的左子树仅包含键 小于 节点键的节点。
 * 节点的右子树仅包含键 大于 节点键的节点。
 * 左右子树也必须是二叉搜索树。
 */

@SuppressWarnings("DuplicatedCode")
public class Solution {

    public TreeNode convertBST(TreeNode root) {
        dfs(root, 0);
        return root;
    }

    private int dfs(TreeNode node, int sum) {
        if (node == null) return sum;

        node.val += dfs(node.right, sum);
        return dfs(node.left, node.val);
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().convertBST(
            TreeNode.build(4,1,6,0,2,5,7,null,null,null,3,null,null,null,8)
        ), TreeNode.build(30,36,21,36,35,26,15,null,null,null,33,null,null,null,8));

        assert Checker.check(new Solution().convertBST(
            TreeNode.build(0,null,1)
        ), TreeNode.build(1,null,1));

        assert Checker.check(new Solution().convertBST(
            TreeNode.build(1,0,2)
        ), TreeNode.build(3,3,2));

        assert Checker.check(new Solution().convertBST(
            TreeNode.build(3,2,4,1)
        ), TreeNode.build(7,9,4,10));
    }

}
