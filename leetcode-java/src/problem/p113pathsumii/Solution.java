package problem.p113pathsumii;

import common.TreeNode;

import java.util.*;

/**
 * 113. Path Sum II
 *
 * https://leetcode-cn.com/problems/path-sum-ii/
 *
 * Given the root of a binary tree and an integer targetSum,
 * return all root-to-leaf paths where each path's sum equals targetSum.
 *
 * A leaf is a node with no children.
 */

public class Solution {

    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> rs = new ArrayList<>();
        doPathSum(root, targetSum, new ArrayDeque<>(), rs);
        return rs;
    }

    private void doPathSum(TreeNode root, int target, ArrayDeque<Integer> paths, List<List<Integer>> rs) {
        if (root == null) {
            return;
        }

        if (root.val == target && root.left == null && root.right == null) {
            rs.add(new ArrayList<>(paths) {{ add(root.val); }});
        }

        paths.addLast(root.val);
        doPathSum(root.left, target - root.val, paths, rs);
        doPathSum(root.right, target - root.val, paths, rs);
        paths.removeLast();
    }

    public static void main(String[] args) {
        System.out.println(new Solution().pathSum(TreeNode.build(1,2), 0));
        System.out.println(new Solution().pathSum(TreeNode.build(1,2,3), 5));
        System.out.println(new Solution().pathSum(TreeNode.build(5,4,8,11,null,13,4,7,2,null,null,5,1), 22));
    }

}
