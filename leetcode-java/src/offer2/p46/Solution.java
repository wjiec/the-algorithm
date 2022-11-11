package offer2.p46;

import common.Checker;
import common.TreeNode;

import java.util.*;

/**
 * 剑指 Offer II 046. 二叉树的右侧视图
 *
 * https://leetcode.cn/problems/WNC0Lk/
 *
 * 给定一个二叉树的 根节点 root，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。
 */

public class Solution {

    public List<Integer> rightSideView(TreeNode root) {
        if (root == null) return Collections.emptyList();

        List<Integer> ans = new ArrayList<>();
        Queue<TreeNode> queue = new ArrayDeque<>();
        for (queue.add(root); !queue.isEmpty(); ) {
            ans.add(queue.peek().val);
            for (int i = 0, n = queue.size(); i < n; i++) {
                TreeNode curr = queue.remove();
                if (curr.right != null) queue.add(curr.right);
                if (curr.left != null) queue.add(curr.left);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().rightSideView(TreeNode.build(1,2,3,null,5,null,4)), List.of(1,3,4));
        assert Checker.check(new Solution().rightSideView(TreeNode.build(1,null,3)), List.of(1,3));
        assert Checker.check(new Solution().rightSideView(TreeNode.build()), List.of());
    }

}
