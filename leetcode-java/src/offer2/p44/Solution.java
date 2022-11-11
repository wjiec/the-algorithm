package offer2.p44;

import common.Checker;
import common.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 剑指 Offer II 044. 二叉树每层的最大值
 *
 * https://leetcode.cn/problems/hPov7L/
 *
 * 给定一棵二叉树的根节点 root ，请找出该二叉树中每一层的最大值。
 */

public class Solution {

    private final List<Integer> ans = new ArrayList<>();

    public List<Integer> largestValues(TreeNode root) {
        dfs(root, 0);
        return ans;
    }

    private void dfs(TreeNode node, int depth) {
        if (node == null) return;

        if (depth == ans.size()) ans.add(node.val);
        else if (node.val > ans.get(depth)) ans.set(depth, node.val);

        dfs(node.left, depth + 1);
        dfs(node.right, depth + 1);
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().largestValues(TreeNode.build(1,3,2,5,3,null,9)), List.of(1, 3, 9));
        assert Checker.check(new Solution().largestValues(TreeNode.build(1,2,3)), List.of(1, 3));
        assert Checker.check(new Solution().largestValues(TreeNode.build(1)), List.of(1));
        assert Checker.check(new Solution().largestValues(TreeNode.build(1,null,2)), List.of(1,2));
        assert Checker.check(new Solution().largestValues(TreeNode.build()), List.of());
    }

}
