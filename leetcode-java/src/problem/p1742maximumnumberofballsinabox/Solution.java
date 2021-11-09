package problem.p1742maximumnumberofballsinabox;

/**
 * 1742. Maximum Number of Balls in a Box
 *
 * https://leetcode-cn.com/problems/maximum-number-of-balls-in-a-box/
 *
 * You are working in a ball factory where you have n balls numbered
 * from lowLimit up to highLimit inclusive (i.e., n == highLimit - lowLimit + 1),
 * and an infinite number of boxes numbered from 1 to infinity.
 *
 * Your job at this factory is to put each ball in the box
 * with a number equal to the sum of digits of the ball's number.
 *
 * For example, the ball number 321 will be put in the box number 3 + 2 + 1 = 6
 * and the ball number 10 will be put in the box number 1 + 0 = 1.
 *
 * Given two integers lowLimit and highLimit, return the number of balls in the box with the most balls.
 */

public class Solution {

    public int countBalls(int lowLimit, int highLimit) {
        int[] map = new int[100];
        for (; lowLimit <= highLimit; lowLimit++) {
            map[sumDigits(lowLimit)]++;
        }

        int ans = 0;
        for (int c : map) ans = Math.max(ans, c);
        return ans;
    }

    private int sumDigits(int n) {
        int sum = 0;
        for (; n != 0; n /= 10) sum += n % 10;
        return sum;
    }

    public static void main(String[] args) {
        assert new Solution().countBalls(1, 10) == 2;
        assert new Solution().countBalls(5, 15) == 2;
        assert new Solution().countBalls(19, 28) == 2;
    }

}
