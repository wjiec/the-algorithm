package weekly.w319.C;

import common.TreeNode;

import java.util.*;

/**
 * 6235. Minimum Number of Operations to Sort a Binary Tree by Level
 *
 * https://leetcode.cn/contest/weekly-contest-319/problems/minimum-number-of-operations-to-sort-a-binary-tree-by-level/
 *
 * You are given the root of a binary tree with unique values.
 *
 * In one operation, you can choose any two nodes at the same level and swap their values.
 *
 * Return the minimum number of operations needed to make the values
 * at each level sorted in a strictly increasing order.
 *
 * The level of a node is the number of edges along the path
 * between it and the root node.
 */

@SuppressWarnings("DuplicatedCode")
public class Solution {

    public int minimumOperations(TreeNode root) {
        int ans = 0;
        Queue<TreeNode> queue = new ArrayDeque<>();
        for (queue.add(root); !queue.isEmpty(); ) {
            List<Integer> rows = new ArrayList<>();
            for (int i = 0, n = queue.size(); i < n; i++) {
                TreeNode node = queue.remove();
                rows.add(node.val);
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }
            ans += swaps(rows);
        }
        System.out.println(ans);
        return ans;
    }

    private int swaps(List<Integer> nums) {
        if (nums.size() == 1) return 0;
        List<Integer> sorted = new ArrayList<>(nums);
        sorted.sort(Integer::compare);

        int n = nums.size();
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) map.put(nums.get(i), i);

        int ans = 0;
        for (int i = 0; i < nums.size(); i++) {
            if (!sorted.get(i).equals(nums.get(i))) {
                ans++;
                int v = nums.get(i);
                int idx = map.get(sorted.get(i));
                swap(nums, i, idx);
                map.put(sorted.get(i), i);
                map.put(v, idx);
            }
        }

        return ans;
    }

    private void swap(List<Integer> nums, int a, int b) {
        int temp = nums.get(a);
        nums.set(a, nums.get(b));
        nums.set(b, temp);
    }

    public static void main(String[] args) {
        assert new Solution().minimumOperations(TreeNode.build(1,4,3,7,6,8,5,null,null,null,null,9,null,10)) == 3;
        assert new Solution().minimumOperations(TreeNode.build(1,3,2,7,6,5,4)) == 3;
        assert new Solution().minimumOperations(TreeNode.build(1,2,3,4,5,6)) == 0;
    }

}
