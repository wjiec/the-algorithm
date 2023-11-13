package weekly.bw117.A;

/**
 * 2928. Distribute Candies Among Children I
 *
 * https://leetcode.cn/contest/biweekly-contest-117/problems/distribute-candies-among-children-i/
 *
 * You are given two positive integers n and limit.
 *
 * Return the total number of ways to distribute n candies among 3 children
 * such that no child gets more than limit candies.
 */

public class Solution {

    public int distributeCandies(int n, int limit) {
        return distribute(0, n, limit);
    }

    private int distribute(int i, int n, int limit) {
        if (i == 3) return n == 0 ? 1 : 0;

        int ans = 0;
        for (int x = 0; x <= limit; x++) {
            ans += distribute(i + 1, n - x, limit);
        }
        return ans;
    }

    public static void main(String[] args) {
    }

}
