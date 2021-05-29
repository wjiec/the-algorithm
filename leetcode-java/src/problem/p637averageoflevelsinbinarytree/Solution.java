package problem.p637averageoflevelsinbinarytree;

import common.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 637. Average of Levels in Binary Tree
 *
 * Given the root of a binary tree, return the average value of the
 * nodes on each level in the form of an array.
 *
 * Answers within 10-5 of the actual answer will be accepted.
 */

public class Solution {


    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> ans = new ArrayList<>();
        List<TreeNode> nodes = new ArrayList<>() {{ add(root); }};
        while (!nodes.isEmpty()) {
            double sum = 0;
            List<TreeNode> next = new ArrayList<>();
            for (var node : nodes) {
                sum += node.val;
                if (node.left != null) next.add(node.left);
                if (node.right != null) next.add(node.right);
            }

            ans.add(sum / nodes.size());
            nodes = next;
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().averageOfLevels(TreeNode.build(3,9,20,null,15,7)).equals(List.of(3.00000,14.50000,11.00000));
        assert new Solution().averageOfLevels(TreeNode.build(3,9,20,15,7)).equals(List.of(3.00000,14.50000,11.00000));
    }

}
