package problem.p1344anglebetweenhandsofaclock;

import common.Checker;

/**
 * 1344. Angle Between Hands of a Clock
 *
 * https://leetcode.cn/problems/angle-between-hands-of-a-clock/
 *
 * Given two numbers, hour and minutes, return the smaller angle (in degrees) formed
 * between the hour and the minute hand.
 *
 * Answers within 10-5 of the actual value will be accepted as correct.
 */

public class Solution {

    public double angleClock(int hour, int minutes) {
        double ma = minutes * 360.0 / 60.0;
        double ha = hour * 360.0 / 12 + ma / 12.0;
        return Math.min(Math.abs(ma - ha), 360 - Math.abs(ma - ha));
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().angleClock(12, 30), 165.0);
        assert Checker.check(new Solution().angleClock(3, 30), 75.0);
        assert Checker.check(new Solution().angleClock(3, 15), 7.5);
        assert Checker.check(new Solution().angleClock(4, 50), 155.0);
        assert Checker.check(new Solution().angleClock(12, 0), 0.);
    }

}
