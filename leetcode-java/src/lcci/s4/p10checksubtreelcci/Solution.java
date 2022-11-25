package lcci.s4.p10checksubtreelcci;

import common.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * 面试题 04.10. 检查子树
 *
 * https://leetcode.cn/problems/check-subtree-lcci/
 *
 * 检查子树。你有两棵非常大的二叉树：T1，有几万个节点；T2，有几万个节点。设计一个算法，判断 T2 是否为 T1 的子树。
 *
 * 如果 T1 有这么一个节点 n，其子树与 T2 一模一样，则 T2 为 T1 的子树，也就是说，从节点 n 处把树砍断，得到的树与 T2 完全相同。
 */

public class Solution {

    public boolean checkSubTree(TreeNode t1, TreeNode t2) {
        List<Integer> l1 = inorder(t1), l2 = inorder(t2);
        return isSubSequence(l1, l2);
    }

    private boolean isSubSequence(List<Integer> longs, List<Integer> shorts) {
        int[] next = lps(shorts);
        for (int i = 0, j = 0; i < longs.size(); ) {
            if ((int) longs.get(i) == shorts.get(j)) {
                i++; j++;
            } else {
                if (j != 0) j = next[j - 1];
                else i++;
            }

            if (j == next.length) return true;
        }
        return false;
    }

    private int[] lps(List<Integer> list) {
        int[] next = new int[list.size()];
        for (int i = 1, j = 0; i < next.length; ) {
            if ((int) list.get(i) == list.get(j)) {
                next[i++] = ++j;
            } else {
                if (j != 0) j = next[j - 1];
                else next[i++] = j;
            }
        }
        return next;
    }

    private List<Integer> inorder(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        while (!stack.isEmpty() || root != null) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }

            root = stack.pop();
            ans.add(root.val);
            root = root.right;
        }
        return ans;
    }

    private static class DFS {
        public boolean checkSubTree(TreeNode t1, TreeNode t2) {
            if (t2 == null) return true;
            return subTree(t1, t2);
        }

        private boolean subTree(TreeNode tree, TreeNode target) {
            if (tree == null) return false;
            if (tree.val == target.val && matches(tree, target)) return true;
            return subTree(tree.left, target) || subTree(tree.right, target);
        }

        private boolean matches(TreeNode l, TreeNode r) {
            if (l == null || r == null) return l == r;
            if (l.val != r.val) return false;
            return matches(l.left, r.left) && matches(l.right, r.right);
        }
    }

    public static void main(String[] args) {
        assert new Solution().checkSubTree(TreeNode.build(1, 2, 3), TreeNode.build(2));
        assert !new Solution().checkSubTree(TreeNode.build(1, null, 2, 4), TreeNode.build(3, 2));

        assert new DFS().checkSubTree(TreeNode.build(1, 2, 3), TreeNode.build(2));
        assert !new DFS().checkSubTree(TreeNode.build(1, null, 2, 4), TreeNode.build(3, 2));
    }

}
