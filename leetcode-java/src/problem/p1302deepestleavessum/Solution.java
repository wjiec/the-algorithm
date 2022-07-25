package problem.p1302deepestleavessum;

import common.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 1302. Deepest Leaves Sum
 *
 * https://leetcode.cn/problems/deepest-leaves-sum/
 *
 * Given the root of a binary tree, return the sum of values of its deepest leaves.
 */

public class Solution {

    private record Tuple(TreeNode node, int depth) {}

    public int deepestLeavesSum(TreeNode root) {
        int ans = 0, max = 0, depth = 1;
        Deque<Tuple> stack = new ArrayDeque<>();
        while (!stack.isEmpty() || root != null) {
            for (; root != null; root = root.left) {
                stack.push(new Tuple(root, depth++));
            }

            Tuple tuple = stack.pop();
            root = tuple.node; depth = tuple.depth;

            if (depth > max) { ans = root.val; max = depth; }
            else if (depth == max) ans += root.val;

            root = root.right; depth++;
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().deepestLeavesSum(TreeNode.build(1,2,3,4,5,null,6,7,null,null,null,null,8)) == 15;
        assert new Solution().deepestLeavesSum(TreeNode.build(6,7,8,2,7,1,3,9,null,1,4,null,null,null,5)) == 19;
    }

}
