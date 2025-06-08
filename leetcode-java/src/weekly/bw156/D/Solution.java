package weekly.bw156.D;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Q4. Subtree Inversion Sum
 *
 * https://leetcode.cn/contest/biweekly-contest-156/problems/subtree-inversion-sum/
 *
 * You are given an undirected tree rooted at node 0, with n nodes numbered from 0 to n - 1.
 *
 * The tree is represented by a 2D integer array edges of length n - 1, where
 * edges[i] = [ui, vi] indicates an edge between nodes ui and vi.
 *
 * You are also given an integer array nums of length n, where nums[i] represents
 * the value at node i, and an integer k.
 *
 * You may perform inversion operations on a subset of nodes subject to the following rules:
 *
 * Subtree Inversion Operation:
 *
 * When you invert a node, every value in the subtree rooted at that node is multiplied by -1.
 *
 * Distance Constraint on Inversions:
 *
 * You may only invert a node if it is "sufficiently far" from any other inverted node.
 *
 * Specifically, if you invert two nodes a and b such that one is an ancestor of the
 * other (i.e., if LCA(a, b) = a or LCA(a, b) = b), then the distance (the number of
 * edges on the unique path between them) must be at least k.
 *
 * Return the maximum possible sum of the tree's node values after applying inversion operations.
 */

public class Solution {

    private List<Integer>[] g = null;

    // 可以反转一个子树, 即将这个子树的节点值乘以 -1, 每次反转的节点必须离他已反转的祖先节点至少间隔 k 条边
    @SuppressWarnings("unchecked")
    public long subtreeInversionSum(int[][] edges, int[] nums, int k) {
        g = new List[nums.length];
        Arrays.setAll(g, i -> new ArrayList<>());
        for (var edge : edges) {
            g[edge[0]].add(edge[1]);
            g[edge[1]].add(edge[0]);
        }

        memo = new long[3][k][nums.length];
        for (var m : memo) for (var r : m) Arrays.fill(r, -1);
        return dfs(0, -1, 0, 1, nums, k);
    }

    private long[][][] memo = null;

    // 当前位于 curr 节点, 父节点是 parent, 当前还需要等待 wait 次移动才可以进行反转, 当前子树的正负为 base
    private long dfs(int curr, int parent, int wait, int base, int[] nums, int k) {
        if (memo[base + 1][wait][curr] != -1) return memo[base + 1][wait][curr];

        // 首先不反转当前节点
        long ans = (long) base * nums[curr];
        for (var next : g[curr]) {
            if (next != parent) {
                ans += dfs(next, curr, wait - (wait > 0 ? 1 : 0), base, nums, k);
            }
        }

        // 如果满足条件, 则可以反转当前节点
        if (wait == 0) {
            long inversion = base * -1L * nums[curr];
            for (var next : g[curr]) {
                if (next != parent) {
                    inversion += dfs(next, curr, k - 1, base * -1, nums, k);
                }
            }
            ans = Math.max(ans, inversion);
        }

        return memo[base + 1][wait][curr] = ans;
    }

    public static void main(String[] args) {
    }

}
