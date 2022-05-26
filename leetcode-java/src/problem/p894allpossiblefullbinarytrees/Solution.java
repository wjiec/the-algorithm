package problem.p894allpossiblefullbinarytrees;

import common.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 894. All Possible Full Binary Trees
 *
 * https://leetcode.cn/problems/all-possible-full-binary-trees/
 *
 * Given an integer n, return a list of all possible full binary trees with n nodes.
 * Each node of each tree in the answer must have Node.val == 0.
 *
 * Each element of the answer is the root node of one possible tree. You may return the final list of trees in any order.
 *
 * A full binary tree is a binary tree where each node has exactly 0 or 2 children.
 */

public class Solution {

    private Map<Integer, List<TreeNode>> memo = new HashMap<>();

    public List<TreeNode> allPossibleFBT(int n) {
        if (!memo.containsKey(n)) {
            List<TreeNode> ans = new ArrayList<>();
            if (n == 1) {
                ans.add(new TreeNode());
            } else if (n % 2 == 1) {
                for (int i = 0; i < n; i++) {
                    for (TreeNode left : allPossibleFBT(i)) {
                        for (TreeNode right : allPossibleFBT(n - 1 - i)) {
                            TreeNode node = new TreeNode();
                            node.left = left;
                            node.right = right;
                            ans.add(node);
                        }
                    }
                }
            }
            memo.put(n, ans);
        }
        return memo.get(n);
    }

    public static void main(String[] args) {
        System.out.println(new Solution().allPossibleFBT(7));
        System.out.println(new Solution().allPossibleFBT(3));
    }

}
