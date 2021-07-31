package daily.d210731p987verticalordertraversalofabinarytree;

import common.TreeNode;

import java.util.*;

/**
 * 987. Vertical Order Traversal of a Binary Tree
 *
 * https://leetcode-cn.com/problems/vertical-order-traversal-of-a-binary-tree/
 *
 * Given the root of a binary tree, calculate the vertical order traversal of the binary tree.
 *
 * For each node at position (row, col), its left and right children will be at positions (row + 1, col - 1)
 * and (row + 1, col + 1) respectively. The root of the tree is at (0, 0).
 *
 * The vertical order traversal of a binary tree is a list of top-to-bottom orderings
 * for each column index starting from the leftmost column and ending on the rightmost column.
 *
 * There may be multiple nodes in the same row and same column. In such a case,
 * sort these nodes by their values.
 *
 * Return the vertical order traversal of the binary tree.
 */

public class Solution {

    private static class Coordinate {
        private final int x, y, val;
        public Coordinate(int x, int y, int val) { this.x = x; this.y = y; this.val = val; }
    }

    public List<List<Integer>> verticalTraversal(TreeNode root) {
        Map<TreeNode, Coordinate> map = new HashMap<>();
        map.put(root, new Coordinate(0, 0, root.val));

        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);

        int minXCoordinate = 0;
        Map<Integer, PriorityQueue<Coordinate>> indexMap = new HashMap<>();
        while (!queue.isEmpty()) {
            var node = queue.remove();
            var coordinate = map.get(node);

            minXCoordinate = Math.min(minXCoordinate, coordinate.x);
            indexMap.putIfAbsent(coordinate.x, new PriorityQueue<>((a, b) -> {
                if (a.y != b.y) return a.y - b.y;
                return a.val - b.val;
            }));
            indexMap.get(coordinate.x).add(coordinate);

            if (node.left != null) {
                queue.add(node.left);
                map.put(node.left, new Coordinate(coordinate.x - 1, coordinate.y + 1, node.left.val));
            }

            if (node.right != null) {
                queue.add(node.right);
                map.put(node.right, new Coordinate(coordinate.x + 1, coordinate.y + 1, node.right.val));
            }
        }

        List<List<Integer>> ans = new ArrayList<>();
        for (int i = minXCoordinate; indexMap.containsKey(i); i++) {
            List<Integer> vertical = new ArrayList<>();
            for (var pq = indexMap.get(i); !pq.isEmpty(); ) vertical.add(pq.remove().val);
            ans.add(vertical);
        }

        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().verticalTraversal(TreeNode.build(3,9,20,null,null,15,7)));
        System.out.println(new Solution().verticalTraversal(TreeNode.build(1,2,3,4,5,6,7)));
        System.out.println(new Solution().verticalTraversal(TreeNode.build(1,2,3,4,6,5,7)));
    }

}
