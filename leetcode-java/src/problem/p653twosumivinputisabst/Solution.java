package problem.p653twosumivinputisabst;

import common.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 653. Two Sum IV - Input is a BST
 *
 * https://leetcode-cn.com/problems/two-sum-iv-input-is-a-bst/
 *
 * Given the root of a Binary Search Tree and a target number k,
 * return true if there exist two elements in the BST such that their sum is equal to the given target.
 */

public class Solution {

    public boolean findTarget(TreeNode root, int k) {
        List<Integer> list = new ArrayList<>();
        inorder(root, list);

        for (int l = 0, r = list.size() - 1; l < r; l++) {
            int target = k - list.get(l);
            while (l < r && target < list.get(r)) r--;
            if (list.get(r) == target) return l != r;
        }
        return false;
    }

    private void inorder(TreeNode root, List<Integer> list) {
        if (root == null) return;

        inorder(root.left, list);
        list.add(root.val);
        inorder(root.right, list);
    }

    public static void main(String[] args) {
        assert !new Solution().findTarget(TreeNode.build(1,null,2), 2);
        assert new Solution().findTarget(TreeNode.build(5,3,6,2,4,null,7), 9);
        assert !new Solution().findTarget(TreeNode.build(5,3,6,2,4,null,7), 28);
        assert new Solution().findTarget(TreeNode.build(2,1,3), 4);
        assert !new Solution().findTarget(TreeNode.build(2,1,3), 1);
        assert new Solution().findTarget(TreeNode.build(2,1,3), 3);
    }

}
