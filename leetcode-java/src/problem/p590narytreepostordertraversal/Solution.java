package problem.p590narytreepostordertraversal;

import common.NTreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 590. N-ary Tree Postorder Traversal
 *
 * https://leetcode-cn.com/problems/n-ary-tree-postorder-traversal/
 *
 * Given the root of an n-ary tree, return the postorder traversal of its nodes' values.
 *
 * Nary-Tree input serialization is represented in their level order traversal.
 * Each group of children is separated by the null value (See examples)
 */

public class Solution {

    private static class Node extends NTreeNode {
        public Node(int val) { super(val); }
    }

    public List<Integer> postorder(Node root) {
        if (root == null) return List.of();

        ArrayDeque<Node> nodes = new ArrayDeque<>();
        nodes.addLast(root);

        LinkedList<Integer> ans = new LinkedList<>();
        while (!nodes.isEmpty()) {
            var node = nodes.removeLast();
            ans.addFirst(node.val);

            if (node.children != null) {
                for (var n : node.children) {
                    nodes.addLast((Node) n);
                }
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().postorder(Node.build(Node.class, 1,null,3,2,4,null,5,6))
            .equals(List.of(5,6,3,2,4,1));
        assert new Solution().postorder(Node.build(Node.class, 1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14))
            .equals(List.of(2,6,14,11,7,3,12,8,4,13,9,10,5,1));
    }

}
