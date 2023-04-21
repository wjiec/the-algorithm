package weekly.bw102.C;

import common.PrettyPrinter;
import common.TreeNode;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

/**
 * 2641. Cousins in Binary Tree II
 *
 * https://leetcode.cn/contest/biweekly-contest-102/problems/cousins-in-binary-tree-ii/
 *
 * Given the root of a binary tree, replace the value of each node in the tree
 * with the sum of all its cousins' values.
 *
 * Two nodes of a binary tree are cousins if they have the same depth with different parents.
 *
 * Return the root of the modified tree.
 *
 * Note that the depth of a node is the number of edges in the path from the root node to it.
 */

public class Solution {

    private record Travel(TreeNode node, int depth) {}

    public TreeNode replaceValueInTree(TreeNode root) {
        TreeNode dummy = new TreeNode(); dummy.right = root;
        Map<Integer, Integer> depthSum = new HashMap<>();
        Map<TreeNode, Integer> childSum = new HashMap<>();
        Map<TreeNode, TreeNode> parent = new HashMap<>();

        Queue<Travel> queue = new ArrayDeque<>();
        queue.add(new Travel(dummy, 0));

        while (!queue.isEmpty()) {
            Travel curr = queue.remove();
            depthSum.merge(curr.depth, curr.node.val, Integer::sum);
            if (curr.node.left != null) {
                parent.put(curr.node.left, curr.node);
                queue.add(new Travel(curr.node.left, curr.depth + 1));
                childSum.merge(curr.node, curr.node.left.val, Integer::sum);
            }
            if (curr.node.right != null) {
                parent.put(curr.node.right, curr.node);
                queue.add(new Travel(curr.node.right, curr.depth + 1));
                childSum.merge(curr.node, curr.node.right.val, Integer::sum);
            }
        }

        queue.add(new Travel(root, 1));
        while (!queue.isEmpty()) {
            Travel curr = queue.remove();
            curr.node.val = depthSum.get(curr.depth) - childSum.get(parent.get(curr.node));
            if (curr.node.left != null) queue.add(new Travel(curr.node.left, curr.depth + 1));
            if (curr.node.right != null) queue.add(new Travel(curr.node.right, curr.depth + 1));
        }

        return root;
    }

    public static void main(String[] args) {
        PrettyPrinter.println(new Solution().replaceValueInTree(TreeNode.build(5,4,9,1,10,null,7)));
        PrettyPrinter.println(new Solution().replaceValueInTree(TreeNode.build(3,1,2)));
    }

}
