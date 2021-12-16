package offer.p54erchasousuoshudedikdajiedianlcof;

import common.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 剑指 Offer 54. 二叉搜索树的第k大节点
 *
 * https://leetcode-cn.com/problems/er-cha-sou-suo-shu-de-di-kda-jie-dian-lcof/
 *
 * 给定一棵二叉搜索树，请找出其中第k大的节点。
 */

public class Solution {

    public int kthLargest(TreeNode root, int k) {
        List<Integer> seq = new ArrayList<>();
        dfs(root, seq);
        return seq.get(k - 1);
    }

    private void dfs(TreeNode root, List<Integer> seq) {
        if (root.right != null) dfs(root.right, seq);
        seq.add(root.val);
        if (root.left != null) dfs(root.left, seq);
    }

    public static void main(String[] args) {
        assert new Solution().kthLargest(TreeNode.build(3,1,4,null,2), 1) == 4;
        assert new Solution().kthLargest(TreeNode.build(5,3,6,2,4,null,null,1), 3) == 4;
    }

}
