package weekly.w335.B;

import common.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * 2583. Kth Largest Sum in a Binary Tree
 *
 * https://leetcode.cn/contest/weekly-contest-335/problems/kth-largest-sum-in-a-binary-tree/
 *
 * You are given the root of a binary tree and a positive integer k.
 *
 * The level sum in the tree is the sum of the values of the nodes that are on the same level.
 *
 * Return the kth largest level sum in the tree (not necessarily distinct).
 * If there are fewer than k levels in the tree, return -1.
 *
 * Note that two nodes are on the same level if they have the same distance from the root.
 */

@SuppressWarnings("DuplicatedCode")
public class Solution {

    public long kthLargestLevelSum(TreeNode root, int k) {
        List<Long> level = new ArrayList<>();
        Queue<TreeNode> queue = new ArrayDeque<>();
        for (queue.add(root); !queue.isEmpty(); ) {
            long curr = 0;
            for (int i = 0, n = queue.size(); i < n; i++) {
                TreeNode node = queue.remove();
                curr += node.val;
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }
            level.add(curr);
        }
        if (level.size() < k) return -1;

        level.sort(Long::compareTo);
        return level.get(level.size() - k);
    }

    public static void main(String[] args) {
    }

}
