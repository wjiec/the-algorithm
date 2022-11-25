package lcci.s4.p8firstcommonancestorlcci;

import common.Offer;
import common.TODO;
import common.TreeNode;

/**
 * 面试题 04.08. 首个共同祖先
 *
 * https://leetcode.cn/problems/first-common-ancestor-lcci/
 *
 * 设计并实现一个算法，找出二叉树中某两个节点的第一个共同祖先。不得将其他的节点存储在另外的数据结构中。
 *
 * 注意：这不一定是二叉搜索树。
 */

public class Solution {

    private TreeNode ans = null;

    @TODO
    @Offer
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        dfs(root, p, q);
        return ans;
    }

    private boolean dfs(TreeNode node, TreeNode p, TreeNode q) {
        if (node == null) return false;

        boolean l = dfs(node.left, p, q);
        boolean r = dfs(node.right, p, q);

        // 分别在两个子树中
        if (l && r) ans = node;
        // 另一个节点是当前节点的子节点
        if ((node.val == p.val || node.val == q.val) && (l || r)) ans = node;

        return l || r || node.val == p.val || node.val == q.val;
    }

    public static void main(String[] args) {
        assert new Solution().lowestCommonAncestor(TreeNode.build(3,5,1,6,2,0,8,null,null,7,4),
            TreeNode.build(5), TreeNode.build(1)) != null;
    }

}
