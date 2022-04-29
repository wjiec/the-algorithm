package problem.p652findduplicatesubtrees;

import common.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 652. Find Duplicate Subtrees
 *
 * https://leetcode-cn.com/problems/find-duplicate-subtrees/
 *
 * Given the root of a binary tree, return all duplicate subtrees.
 *
 * For each kind of duplicate subtrees, you only need to return the root node of any one of them.
 *
 * Two trees are duplicate if they have the same structure with the same node values.
 */

public class Solution {

    private int index = 1;
    private final List<TreeNode> ans = new ArrayList<>();
    private final Map<String, Integer> trees = new HashMap<>();
    private final Map<Integer, Integer> counts = new HashMap<>();

    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        dfs(root);
        return ans;
    }

    public int dfs(TreeNode node) {
        if (node == null) return 0;
        String serialize = node.val + "," + dfs(node.left) + "," + dfs(node.right);
        int id = trees.computeIfAbsent(serialize, v -> index++);
        counts.merge(id, 1, Integer::sum);
        if (counts.get(id) == 2) ans.add(node);
        return id;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().findDuplicateSubtrees(TreeNode.build(1,2,3,4,null,2,4,null,null,4)));
        System.out.println(new Solution().findDuplicateSubtrees(TreeNode.build(2,1,1)));
        System.out.println(new Solution().findDuplicateSubtrees(TreeNode.build(2,2,2,3,null,3,null)));
    }

}
