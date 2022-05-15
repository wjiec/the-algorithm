package weekly.w293.p2largestcombinationwithbitwiseandgreaterthanzero;

/**
 * 6065. Largest Combination With Bitwise AND Greater Than Zero
 *
 * https://leetcode.cn/contest/weekly-contest-293/problems/largest-combination-with-bitwise-and-greater-than-zero/
 *
 * The bitwise AND of an array nums is the bitwise AND of all integers in nums.
 *
 * For example, for nums = [1, 5, 3], the bitwise AND is equal to 1 & 5 & 3 = 1.
 * Also, for nums = [7], the bitwise AND is 7.
 * You are given an array of positive integers candidates. Evaluate the bitwise AND of every
 * combination of numbers of candidates. Each number in candidates may only be used once in each combination.
 *
 * Return the size of the largest combination of candidates with a bitwise AND greater than 0.
 */

public class Solution {

    public int largestCombination(int[] candidates) {
        int ans = 0;
        for (int i = 0; i < 31; i++) {
            int curr = 0, base = 1 << i;
            for (var candidate : candidates) {
                if ((candidate & base) != 0) curr++;
            }
            if (curr > ans) ans = curr;
        }
        return ans;
    }

    public static void main(String[] args) {
    }

}
