package lcp.p44;

import common.TreeNode;

import java.util.HashSet;
import java.util.Set;

/**
 * LCP 44. 开幕式焰火
 *
 * https://leetcode-cn.com/problems/sZ59z6/
 *
 * 「力扣挑战赛」开幕式开始了，空中绽放了一颗二叉树形的巨型焰火。
 * 给定一棵二叉树 root 代表焰火，节点值表示巨型焰火这一位置的颜色种类。请帮小扣计算巨型焰火有多少种不同的颜色。
 */

public class Solution {

    public int numColor(TreeNode root) {
        Set<Integer> set = new HashSet<>();
        preorder(root, set);
        return set.size();
    }

    private void preorder(TreeNode node, Set<Integer> set) {
        set.add(node.val);
        if (node.left != null) preorder(node.left, set);
        if (node.right != null) preorder(node.right, set);
    }

    public static void main(String[] args) {
        assert new Solution().numColor(TreeNode.build(1,3,2,1,null,2)) == 3;
        assert new Solution().numColor(TreeNode.build(3,3,3)) == 1;
    }

}
