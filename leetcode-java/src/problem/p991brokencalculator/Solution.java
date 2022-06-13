package problem.p991brokencalculator;

/**
 * 991. Broken Calculator
 *
 * https://leetcode.cn/problems/broken-calculator/
 *
 * There is a broken calculator that has the integer startValue on its display initially.
 *
 * In one operation, you can:
 * multiply the number on display by 2, or
 * subtract 1 from the number on display.
 * Given two integers startValue and target, return the minimum number of operations
 * needed to display target on the calculator.
 */

public class Solution {

    public int brokenCalc(int startValue, int target) {
        int ans = 0;
        for (; target > startValue; ans++) {
            if (target % 2 == 1) target++;
            else target /= 2;
        }
        return ans + startValue - target;
    }

    public static void main(String[] args) {
        assert new Solution().brokenCalc(2, 3) == 2;
        assert new Solution().brokenCalc(5, 8) == 2;
        assert new Solution().brokenCalc(3, 10) == 3;
    }

}
