package problem.p1028recoveratreefrompreordertraversal;

import common.Checker;
import common.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 1028. Recover a Tree From Preorder Traversal
 *
 * https://leetcode.cn/problems/recover-a-tree-from-preorder-traversal/
 *
 * We run a preorder depth-first search (DFS) on the root of a binary tree.
 *
 * At each node in this traversal, we output D dashes (where D is the depth of this node), then
 * we output the value of this node.  If the depth of a node is D, the depth of its immediate
 * child is D + 1.
 *
 * The depth of the root node is 0.
 *
 * If a node has only one child, that child is guaranteed to be the left child.
 *
 * Given the output traversal of this traversal, recover the tree and return its root.
 */

public class Solution {

    private record DepthNode(int depth, TreeNode node) {}

    private static class Parser {
        private int idx = 0;
        private final char[] seq;
        public Parser(char[] preorder) { seq = preorder; }
        public boolean hasNext() { return idx != seq.length; }
        public DepthNode next() {
            int depth = 0, value = 0;
            while (seq[idx] == '-') { depth++; idx++; }
            while (idx < seq.length && seq[idx] != '-') {
                value = value * 10 + (seq[idx++] - '0');
            }
            return new DepthNode(depth, new TreeNode(value));
        }
    }

    public TreeNode recoverFromPreorder(String traversal) {
        Parser parser = new Parser(traversal.toCharArray());
        Deque<DepthNode> stack = new ArrayDeque<>();
        while (parser.hasNext()) {
            DepthNode curr = parser.next();
            while (!stack.isEmpty() && stack.peek().depth + 1 != curr.depth) stack.pop();
            if (!stack.isEmpty()) {
                DepthNode parent = stack.peek();
                if (parent.node.left == null) parent.node.left = curr.node;
                else parent.node.right = curr.node;
            }
            stack.push(curr);
        }

        while (!stack.isEmpty() && stack.peek().depth != 0) stack.pop();
        return stack.pop().node;
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().recoverFromPreorder("1-2--3--4-5--6--7"), TreeNode.build(1,2,5,3,4,6,7));
        assert Checker.check(new Solution().recoverFromPreorder("1-2--3---4-5--6---7"), TreeNode.build(1,2,5,3,null,6,null,4,null,7));
        assert Checker.check(new Solution().recoverFromPreorder("1-401--349---90--88"), TreeNode.build(1,401,null,349,88,90));
    }

}
