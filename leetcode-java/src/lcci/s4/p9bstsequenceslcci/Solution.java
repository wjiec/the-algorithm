package lcci.s4.p9bstsequenceslcci;

import common.PrettyPrinter;
import common.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * 面试题 04.09. 二叉搜索树序列
 *
 * https://leetcode.cn/problems/bst-sequences-lcci/
 *
 * 从左向右遍历一个数组，通过不断将其中的元素插入树中可以逐步地生成一棵二叉搜索树。
 *
 * 给定一个由不同节点组成的二叉搜索树 root，输出所有可能生成此树的数组。
 */

@SuppressWarnings("DuplicatedCode")
public class Solution {

    private final List<List<Integer>> ans = new ArrayList<>();

    public List<List<Integer>> BSTSequences(TreeNode root) {
        if (root == null) return List.of(List.of());

        List<TreeNode> queue = new ArrayList<>(); queue.add(root);
        bfs(queue, new ArrayDeque<>());
        return ans;
    }

    private void bfs(List<TreeNode> queue, Deque<Integer> curr) {
        if (queue.isEmpty()) { ans.add(new ArrayList<>(curr)); return; }

        List<TreeNode> copy = new ArrayList<>(queue);
        for (int i = 0; i < queue.size(); i++) {
            TreeNode node = queue.get(i);
            if (node == null) continue;

            if (node.left != null) queue.add(node.left);
            if (node.right != null) queue.add(node.right);

            //noinspection SuspiciousListRemoveInLoop
            queue.remove(i);
            curr.addLast(node.val);
            bfs(queue, curr);
            curr.removeLast();
            queue = new ArrayList<>(copy);
        }
    }

    public static void main(String[] args) {
        PrettyPrinter.println(new Solution().BSTSequences(TreeNode.build(2,1,3)));
        PrettyPrinter.println(new Solution().BSTSequences(TreeNode.build(4,1,null,null,3,2)));
    }

}
