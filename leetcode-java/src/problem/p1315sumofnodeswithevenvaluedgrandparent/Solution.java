package problem.p1315sumofnodeswithevenvaluedgrandparent;

import common.TreeNode;

/**
 * 1315. Sum of Nodes with Even-Valued Grandparent
 *
 * https://leetcode.cn/problems/sum-of-nodes-with-even-valued-grandparent/
 *
 * Given the root of a binary tree, return the sum of values of nodes with an even-valued grandparent.
 * If there are no nodes with an even-valued grandparent, return 0.
 *
 * A grandparent of a node is the parent of its parent if it exists.
 */

public class Solution {

    private int ans = 0;

    public int sumEvenGrandparent(TreeNode root) {
        dfs(root, 1, 1);
        return ans;
    }

    private void dfs(TreeNode node, int p, int pp) {
        if (pp % 2 == 0) ans += node.val;

        if (node.left != null) dfs(node.left, node.val, p);
        if (node.right != null) dfs(node.right, node.val, p);
    }

    public static void main(String[] args) {
        assert new Solution().sumEvenGrandparent(TreeNode.build(
            6,7,8,2,7,1,3,9,null,1,4,null,null,null,5
        )) == 18;
    }

}
