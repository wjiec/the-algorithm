package problem.p1261findelementsinacontaminatedbinarytree;

import common.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

/**
 * 1261. Find Elements in a Contaminated Binary Tree
 *
 * https://leetcode.cn/problems/find-elements-in-a-contaminated-binary-tree/
 *
 * Given a binary tree with the following rules:
 *
 * root.val == 0
 * If treeNode.val == x and treeNode.left != null, then treeNode.left.val == 2 * x + 1
 * If treeNode.val == x and treeNode.right != null, then treeNode.right.val == 2 * x + 2
 * Now the binary tree is contaminated, which means all treeNode.val have been changed to -1.
 *
 * Implement the FindElements class:
 *
 * FindElements(TreeNode* root) Initializes the object with a contaminated binary tree and recovers it.
 * bool find(int target) Returns true if the target value exists in the recovered binary tree.
 */

public class Solution {

    private static class FindElements {
        private final Set<Integer> els = new HashSet<>();
        public FindElements(TreeNode root) { rebuild(root, 0); }
        public boolean find(int target) { return els.contains(target); }

        private void rebuild(TreeNode node, int v) {
            if (node != null) {
                els.add(v);

                if (node.left != null) rebuild(node.left, 2 * v + 1);
                if (node.right != null) rebuild(node.right, 2 * v + 2);
            }
        }
    }

    private static class BuildPath {
        private final TreeNode tree;
        public BuildPath(TreeNode root) { tree = root; }
        public boolean find(int target) {
            if (target == 0) return true;

            // 0: right, 1: left
            Deque<Integer> stack = new ArrayDeque<>();
            while (target != 0) {
                stack.push(target % 2);
                if (target % 2 == 1) target = (target - 1) / 2;
                else target = (target - 2) / 2;
            }

            TreeNode curr = tree;
            while (!stack.isEmpty() && curr != null) {
                if (stack.pop() == 1) curr = curr.left;
                else curr = curr.right;
            }
            return stack.isEmpty() && curr != null;
        }
    }

    public static void main(String[] args) {
        FindElements findElements = new FindElements(TreeNode.build(-1,null,-1));
        assert !findElements.find(1);
        assert findElements.find(2);

        findElements = new FindElements(TreeNode.build(-1,-1,-1,-1,-1));
        assert findElements.find(1);
        assert findElements.find(3);
        assert !findElements.find(5);

        findElements = new FindElements(TreeNode.build(-1,null,-1,-1,null,-1));
        assert findElements.find(2);
        assert !findElements.find(3);
        assert !findElements.find(4);
        assert findElements.find(5);


        BuildPath buildPath = new BuildPath(TreeNode.build(-1,null,-1));
        assert !buildPath.find(1);
        assert buildPath.find(2);

        buildPath = new BuildPath(TreeNode.build(-1,-1,-1,-1,-1));
        assert buildPath.find(1);
        assert buildPath.find(3);
        assert !buildPath.find(5);

        buildPath = new BuildPath(TreeNode.build(-1,null,-1,-1,null,-1));
        assert buildPath.find(2);
        assert !buildPath.find(3);
        assert !buildPath.find(4);
        assert buildPath.find(5);
    }

}
