package problem.p508mostfrequentsubtreesum;

import common.Checker;
import common.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * 508. Most Frequent Subtree Sum
 *
 * https://leetcode-cn.com/problems/most-frequent-subtree-sum/
 *
 * Given the root of a binary tree, return the most frequent subtree sum. If there is a tie,
 * return all the values with the highest frequency in any order.
 *
 * The subtree sum of a node is defined as the sum of all the node values formed by the subtree rooted
 * at that node (including the node itself).
 */

public class Solution {

    private Map<Integer, Integer> map = new HashMap<>();

    public int[] findFrequentTreeSum(TreeNode root) {
        dfs(root);

        int max = 0, n = 0, i = 0;
        for (var kv : map.entrySet()) {
            if (kv.getValue() > max) {
                max = kv.getValue();
                n = 1;
            } else if (kv.getValue() == max) n++;
        }

        int[] ans = new int[n];
        for (var kv : map.entrySet()) {
            if (kv.getValue() == max) {
                ans[i++] = kv.getKey();
            }
        }
        return ans;
    }

    private int dfs(TreeNode node) {
        if (node == null) return 0;

        int value = node.val + dfs(node.left) + dfs(node.right);
        map.merge(value, 1, Integer::sum);

        return value;
    }

    public static void main(String[] args) {
        assert Checker.anyOrder(new Solution().findFrequentTreeSum(TreeNode.build(5,2,-3)), new int[]{2,-3,4});
        assert Checker.anyOrder(new Solution().findFrequentTreeSum(TreeNode.build(5,2,-5)), new int[]{2});
    }

}
