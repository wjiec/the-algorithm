package weekly.w311.C;

import common.PrettyPrinter;
import common.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * 6182. Reverse Odd Levels of Binary Tree
 *
 * https://leetcode.cn/contest/weekly-contest-311/problems/reverse-odd-levels-of-binary-tree/
 *
 * Given the root of a perfect binary tree, reverse the node values at each odd level of the tree.
 *
 * For example, suppose the node values at level 3 are [2,1,3,4,7,11,29,18], then it
 * should become [18,29,11,7,4,3,1,2].
 *
 * Return the root of the reversed tree.
 *
 * A binary tree is perfect if all parent nodes have two children and all leaves are on the same level.
 *
 * The level of a node is the number of edges along the path between it and the root node.
 */

@SuppressWarnings("DuplicatedCode")
public class Solution {

    public TreeNode reverseOddLevels(TreeNode root) {
        List<List<TreeNode>> nodes = new ArrayList<>();

        Queue<TreeNode> queue = new ArrayDeque<>();
        for (queue.add(root); !queue.isEmpty(); ) {
            List<TreeNode> curr = new ArrayList<>();
            for (int i = 0, n = queue.size(); i < n; i++) {
                TreeNode node = queue.remove();
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
                curr.add(node);
            }
            nodes.add(curr);
        }

        for (int i = 1; i < nodes.size(); i += 2) {
            List<TreeNode> curr = nodes.get(i);
            int l = 0, r = curr.size() - 1;
            while (l < r) {
                TreeNode left = curr.get(l);
                TreeNode right = curr.get(r);

                int temp = left.val;
                left.val = right.val;
                right.val = temp;

                l++; r--;
            }
        }

        return root;
    }

    public static void main(String[] args) {
        PrettyPrinter.println(new Solution().reverseOddLevels(TreeNode.build(2,3,5,8,13,21,34)));
    }

}
