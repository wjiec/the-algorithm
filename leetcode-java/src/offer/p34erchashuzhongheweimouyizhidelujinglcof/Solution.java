package offer.p34erchashuzhongheweimouyizhidelujinglcof;

import common.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 剑指 Offer 34. 二叉树中和为某一值的路径
 *
 * https://leetcode-cn.com/problems/er-cha-shu-zhong-he-wei-mou-yi-zhi-de-lu-jing-lcof/
 *
 * 输入一棵二叉树和一个整数，打印出二叉树中节点值的和为输入整数的所有路径。从树的根节点开始往下一直到叶节点所经过的节点形成一条路径。
 */

public class Solution {

    private final List<List<Integer>> ans = new ArrayList<>();

    public List<List<Integer>> pathSum(TreeNode root, int target) {
        if (root != null) dfs(root, new ArrayList<>(), target);
        return ans;
    }

    private void dfs(TreeNode root, List<Integer> paths, int target) {
        paths.add(root.val);

        if (root.left == null && root.right == null && target == root.val) {
            ans.add(new ArrayList<>(paths));
        } else {
            if (root.left != null) dfs(root.left, paths, target - root.val);
            if (root.right != null) dfs(root.right, paths, target - root.val);
        }

        paths.remove(paths.size() - 1);
    }

    public static void main(String[] args) {
        System.out.println(new Solution().pathSum(TreeNode.build(5,4,8,11,null,13,4,7,2,null,null,5,1), 22));
    }

}
