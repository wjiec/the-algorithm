package problem.p429narytreelevelordertraversal;

import java.util.*;

/**
 * 429. N-ary Tree Level Order Traversal
 *
 * https://leetcode-cn.com/problems/n-ary-tree-level-order-traversal/
 *
 * Given an n-ary tree, return the level order traversal of its nodes' values.
 *
 * Nary-Tree input serialization is represented in their level order traversal,
 * each group of children is separated by the null value (See examples).
 */

public class Solution {

    private static class Node {
        public int val;
        public List<Node> children;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }

    public List<List<Integer>> levelOrder(Node root) {
        if (root == null) return Collections.emptyList();

        List<List<Integer>> ans = new ArrayList<>();
        Queue<Node> queue = new ArrayDeque<>();
        for (queue.add(root); !queue.isEmpty(); ) {
            List<Integer> level = new ArrayList<>();
            for (int i = 0, n = queue.size(); i < n; i++) {
                Node curr = queue.remove();

                level.add(curr.val);
                queue.addAll(curr.children);
            }
            ans.add(level);
        }
        return ans;
    }

    public static void main(String[] args) {
        new Solution().levelOrder(null).equals(List.of());
    }

}
