package daily.d240228p2673makecostsofpathsequalinabinarytree;

/**
 * 2673. Make Costs of Paths Equal in a Binary Tree
 *
 * https://leetcode.cn/problems/make-costs-of-paths-equal-in-a-binary-tree/
 *
 * You are given an integer n representing the number of nodes in a perfect binary tree
 * consisting of nodes numbered from 1 to n. The root of the tree is node 1 and each
 * node i in the tree has two children where the left child is the node 2 * i and
 * the right child is 2 * i + 1.
 *
 * Each node in the tree also has a cost represented by a given 0-indexed integer
 * array cost of size n where cost[i] is the cost of node i + 1. You are allowed
 * to increment the cost of any node by 1 any number of times.
 *
 * Return the minimum number of increments you need to make the cost of paths
 * from the root to each leaf node equal.
 *
 * Note:
 *
 * A perfect binary tree is a tree where each node, except the leaf nodes, has exactly 2 children.
 * The cost of a path is the sum of costs of nodes in the path.
 */

public class Solution {

    private int n = 0;
    private int[] cost = null;

    public int minIncrements(int n, int[] cost) {
        this.n = n; this.cost = cost;
        dfs(1, 0);
        dfs1(1, max);
        dfs2(1, 0);
        return ans;
    }

    private int ans = 0;

    private void dfs2(int curr, int pads) {
        if (curr > n) return;

        int currPad = cost[curr - 1] - pads;
        ans += currPad;
        dfs2(curr * 2, pads + currPad);
        dfs2(curr * 2 + 1, pads + currPad);
    }

    private int dfs1(int curr, int remain) {
        if (curr > n) return remain;
        int left = dfs1(curr * 2, remain - cost[curr - 1]);
        int right = dfs1(curr * 2 + 1, remain - cost[curr - 1]);

        return (cost[curr - 1] = Math.min(left, right));
    }

    private int max = 0;

    private void dfs(int curr, int sum) {
        if (curr > n) {
            max = Math.max(max, sum);
            return;
        }

        dfs(curr * 2, sum + cost[curr - 1]);
        dfs(curr * 2 + 1, sum + cost[curr - 1]);
    }

    public static void main(String[] args) {
        assert new Solution().minIncrements(7, new int[]{1,5,2,2,3,3,1}) == 6;
        assert new Solution().minIncrements(3, new int[]{5,3,3}) == 0;
    }

}
