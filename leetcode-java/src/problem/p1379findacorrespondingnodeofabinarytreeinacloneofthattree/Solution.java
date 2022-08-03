package problem.p1379findacorrespondingnodeofabinarytreeinacloneofthattree;

import common.TreeNode;

/**
 * 1379. Find a Corresponding Node of a Binary Tree in a Clone of That Tree
 *
 * https://leetcode.cn/problems/find-a-corresponding-node-of-a-binary-tree-in-a-clone-of-that-tree/
 *
 * Given two binary trees original and cloned and given a reference to a node target in the original tree.
 *
 * The cloned tree is a copy of the original tree.
 *
 * Return a reference to the same node in the cloned tree.
 *
 * Note that you are not allowed to change any of the two trees or the target node and the answer
 * must be a reference to a node in the cloned tree.
 */

public class Solution {

    public TreeNode getTargetCopy(final TreeNode original, final TreeNode cloned, final TreeNode target) {
        if (original == null) return null;
        if (original == target) return cloned;

        TreeNode left = getTargetCopy(original.left, cloned.left, target);
        if (left != null) return left;
        return getTargetCopy(original.right, cloned.right, target);
    }

    public static void main(String[] args) {
        assert new Solution().getTargetCopy(
            TreeNode.build(7,4,3,null,null,6,19),
            TreeNode.build(7,4,3,null,null,6,19),
            TreeNode.build(3,6,9)
        ).val == 3;
    }

}
