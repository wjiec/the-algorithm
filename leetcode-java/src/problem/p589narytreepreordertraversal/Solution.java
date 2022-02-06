package problem.p589narytreepreordertraversal;

import common.NTreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 589. N-ary Tree Preorder Traversal
 *
 * https://leetcode-cn.com/problems/n-ary-tree-preorder-traversal/
 *
 * Given the root of an n-ary tree, return the preorder traversal of its nodes' values.
 *
 * Nary-Tree input serialization is represented in their level order traversal.
 * Each group of children is separated by the null value (See examples)
 */

public class Solution {

    private static class Node extends NTreeNode {
        public Node(int val) { super(val); }
    }

    public List<Integer> preorder(Node root) {
        if (root == null) return List.of();

        Stack<Node> nodes = new Stack<>();
        nodes.add(root);

        List<Integer> ans = new ArrayList<>();
        while (!nodes.empty()) {
            var node = nodes.pop();
            if (node.children != null) {
                for (int i = node.children.size() - 1; i >= 0; i--) {
                    nodes.push((Node) node.children.get(i));
                }
            }
            ans.add(node.val);
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().preorder(Node.build(Node.class, 1,null,3,2,4,null,5,6))
            .equals(List.of(1,3,5,6,2,4));
        assert new Solution().preorder(Node.build(Node.class, 1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14))
            .equals(List.of(1,2,3,6,7,11,14,4,8,12,5,9,13,10));
    }

}
