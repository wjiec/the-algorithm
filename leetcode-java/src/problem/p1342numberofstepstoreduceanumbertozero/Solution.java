package problem.p1342numberofstepstoreduceanumbertozero;

/**
 * 1342. Number of Steps to Reduce a Number to Zero
 *
 * https://leetcode-cn.com/problems/number-of-steps-to-reduce-a-number-to-zero/
 *
 * Given an integer num, return the number of steps to reduce it to zero.
 *
 * In one step, if the current number is even, you have to divide it by 2, otherwise, you have to subtract 1 from it.
 */

public class Solution {

    public int numberOfSteps(int num) {
        int ans = 0;
        for (; num != 0; ans++) {
            if (num % 2 == 0) num /= 2;
            else num -= 1;
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().numberOfSteps(14) == 6;
        assert new Solution().numberOfSteps(8) == 4;
        assert new Solution().numberOfSteps(123) == 12;
    }

}
