package weekly.bw117.B;

/**
 * 2929. Distribute Candies Among Children II
 *
 * https://leetcode.cn/contest/biweekly-contest-117/problems/distribute-candies-among-children-ii/
 *
 * You are given two positive integers n and limit.
 *
 * Return the total number of ways to distribute n candies among 3 children
 * such that no child gets more than limit candies.
 */

public class Solution {

    public long distributeCandies(int n, int limit) {
        return c(n + 2) - 3 * c(n - limit + 1) + 3 * c(n - 2L * limit) - c(n - 3L * limit - 1);
    }

    private long c(long n) { return n > 1 ? (n * (n - 1) / 2) : 0; }

    public static void main(String[] args) {
    }

}
