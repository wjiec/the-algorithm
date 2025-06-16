package weekly.w451.A;

/**
 * Q1. Find Minimum Log Transportation Cost
 *
 * https://leetcode.cn/contest/weekly-contest-451/problems/find-minimum-log-transportation-cost/
 *
 * You are given integers n, m, and k.
 *
 * There are two logs of lengths n and m units, which need to be transported in three trucks
 * where each truck can carry one log with length at most k units.
 *
 * You may cut the logs into smaller pieces, where the cost of cutting a log of length x into
 * logs of length len1 and len2 is cost = len1 * len2 such that len1 + len2 = x.
 *
 * Return the minimum total cost to distribute the logs onto the trucks. If the logs don't
 * need to be cut, the total cost is 0.
 */

public class Solution {

    public long minCuttingCost(int n, int m, int k) {
        if (m <= k && n <= k) return 0;
        return (long) k * (Math.max(m, n) - k);
    }

    public static void main(String[] args) {
    }

}
