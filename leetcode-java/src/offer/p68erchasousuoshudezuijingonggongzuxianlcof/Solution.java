package offer.p68erchasousuoshudezuijingonggongzuxianlcof;

import common.TreeNode;

/**
 * 剑指 Offer 68 - I. 二叉搜索树的最近公共祖先
 *
 * https://leetcode-cn.com/problems/er-cha-sou-suo-shu-de-zui-jin-gong-gong-zu-xian-lcof/
 *
 * 给定一个二叉搜索树, 找到该树中两个指定节点的最近公共祖先。
 *
 * 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，
 * 满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
 */

public class Solution {

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root.val > p.val && root.val > q.val) {
            if (root.left != null) {
                return lowestCommonAncestor(root.left, p, q);
            }
        }
        if (root.val < p.val && root.val < q.val) {
            if (root.right != null) {
                return lowestCommonAncestor(root.right, p, q);
            }
        }
        return root;
    }

    public static void main(String[] args) {
        assert new Solution().lowestCommonAncestor(TreeNode.build(6,2,8,0,4,7,9,null,null,3,5), new TreeNode(6), new TreeNode(2)).val == 6;
    }

}
