package problem.p95uniquebinarysearchtreesii;

import common.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 95. Unique Binary Search Trees II
 *
 * https://leetcode-cn.com/problems/unique-binary-search-trees-ii/
 *
 * Given an integer n, return all the structurally unique BST's (binary search trees),
 * which has exactly n nodes of unique values from 1 to n. Return the answer in any order.
 */

public class Solution {

    public List<TreeNode> generateTrees(int n) {
        return generateTrees(1, n);
    }

    public List<TreeNode> generateTrees(int start, int end) {
        List<TreeNode> trees = new ArrayList<>();
        if (start > end) { trees.add(null); return trees; }
        for (int i = start; i <= end; i++) {
            var lefts = generateTrees(start, i - 1);
            var rights = generateTrees(i + 1, end);
            for (var left : lefts) {
                for (var right : rights) {
                    TreeNode root = new TreeNode(i);
                    root.left = left;
                    root.right = right;
                    trees.add(root);
                }
            }
        }
        return trees;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().generateTrees(1));
        System.out.println(new Solution().generateTrees(2));
        System.out.println(new Solution().generateTrees(3));
        System.out.println(new Solution().generateTrees(4));
    }

}
