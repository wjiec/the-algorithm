package weekly.w471.D;

/**
 * Q4. Sum of Perfect Square Ancestors
 *
 * https://leetcode.cn/contest/weekly-contest-471/problems/sum-of-perfect-square-ancestors/
 *
 * You are given an integer n and an undirected tree rooted at node 0 with n nodes numbered from 0 to n - 1.
 *
 * This is represented by a 2D array edges of length n - 1, where edges[i] = [ui, vi]
 * indicates an undirected edge between nodes ui and vi.
 *
 * You are also given an integer array nums, where nums[i] is the positive integer assigned to node i.
 *
 * Define a value ti as the number of ancestors of node i such that the
 * product nums[i] * nums[ancestor] is a perfect square.
 *
 * Return the sum of all ti values for all nodes i in range [1, n - 1].
 *
 * Note:
 * In a rooted tree, the ancestors of node i are all nodes on the path
 * from node i to the root node 0, excluding i itself.
 */

public class Solution {

    public long sumOfAncestors(int n, int[][] edges, int[] nums) {
        return 1;
    }

    public static void main(String[] args) {
    }

}
