package weekly.w320.B;

import common.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

/**
 * 6242. Closest Nodes Queries in a Binary Search Tree
 *
 * https://leetcode.cn/contest/weekly-contest-320/problems/closest-nodes-queries-in-a-binary-search-tree/
 *
 * You are given the root of a binary search tree and an array
 * queries of size n consisting of positive integers.
 *
 * Find a 2D array answer of size n where answer[i] = [mini, maxi]:
 *
 * mini is the largest value in the tree that is smaller than or equal to queries[i].
 * If a such value does not exist, add -1 instead.
 *
 * maxi is the smallest value in the tree that is greater than or equal to queries[i].
 * If a such value does not exist, add -1 instead.
 *
 * Return the array answer.
 */

public class Solution {

    private final TreeSet<Integer> sorted = new TreeSet<>();

    public List<List<Integer>> closestNodes(TreeNode root, List<Integer> queries) {
        dfs(root);
        List<List<Integer>> ans = new ArrayList<>();
        for (var query : queries) {
            Integer l = sorted.floor(query);
            Integer r = sorted.ceiling(query);
            ans.add(List.of(l == null ? -1 : l, r == null ? -1 : r));
        }
        return ans;
    }

    private void dfs(TreeNode node) {
        if (node == null) return;

        sorted.add(node.val);
        dfs(node.left);
        dfs(node.right);
    }

    public static void main(String[] args) {
        System.out.println(new Solution().closestNodes(TreeNode.build(4,null,9), List.of(3)));
        System.out.println(new Solution().closestNodes(TreeNode.build(6,2,13,1,4,9,15,null,null,null,null,null,null,14), List.of(2,5,16)));
    }

}
