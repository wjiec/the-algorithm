package problem.p70climbingstairs;

/**
 * 70. Climbing Stairs
 *
 * https://leetcode-cn.com/problems/climbing-stairs/
 *
 * You are climbing a staircase. It takes n steps to reach the top.
 *
 * Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
 */

public class Solution {

    public int climbStairs(int n) {
        int pp, p = 0, c = 1;
        for (int i = 0; i < n; i++) {
            pp = p;
            p = c;
            c = pp + p;
        }
        return c;
    }

    public static void main(String[] args) {
        assert new Solution().climbStairs(1) == 1; // 1
        assert new Solution().climbStairs(2) == 2; // 1,1 2
        assert new Solution().climbStairs(3) == 3; // 1,1,1 2,1 1,2
        assert new Solution().climbStairs(4) == 5; // 1,1,1,1 1,1,2 2,1,1 2,2 1,2,1
    }

}
