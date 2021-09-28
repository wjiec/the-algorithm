package daily.d210928p437pathsumiii;

import common.TreeNode;

/**
 * 437. Path Sum III
 *
 * https://leetcode-cn.com/problems/path-sum-iii/
 *
 * Given the root of a binary tree and an integer targetSum, return the number of paths
 * where the sum of the values along the path equals targetSum.
 *
 * The path does not need to start or end at the root or a leaf, but it must go downwards
 * (i.e., traveling only from parent nodes to child nodes).
 */

public class Solution {

    public int pathSum(TreeNode root, int targetSum) {
        if (root == null) return 0;

        int ans = dfs(root, targetSum);
        ans += pathSum(root.left, targetSum);
        ans += pathSum(root.right, targetSum);
        return ans;
    }

    private int dfs(TreeNode node, int target) {
        if (node == null) return 0;

        int ans = 0;
        if (node.val == target) ans++;

        ans += dfs(node.left, target - node.val);
        ans += dfs(node.right, target - node.val);
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().pathSum(TreeNode.build(10,5,-3,3,2,null,11,3,-2,null,1), 8) == 3;
        assert new Solution().pathSum(TreeNode.build(5,4,8,11,null,13,4,7,2,null,null,5,1), 22) == 3;
    }

}
