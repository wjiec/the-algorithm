package weekly.w476.A;

/**
 * Q1. Maximize Expression of Three Elements
 *
 * https://leetcode.cn/contest/weekly-contest-476/problems/maximize-expression-of-three-elements/
 *
 * You are given an integer array nums.
 *
 * Choose three elements a, b, and c from nums at distinct indices such that
 * the value of the expression a + b - c is maximized.
 *
 * Return an integer denoting the maximum possible value of this expression.
 */

public class Solution {

    public int maximizeExpressionOfThree(int[] nums) {
        int ans = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums.length; j++) {
                if (i == j) continue;
                for (int k = 0; k < nums.length; k++) {
                    if (k == i || k == j) continue;
                    ans = Math.max(ans, nums[i] + nums[j] - nums[k]);
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
    }

}
