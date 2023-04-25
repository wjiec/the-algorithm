package weekly.w341.B;

import java.util.Arrays;

/**
 * 2644. Find the Maximum Divisibility Score
 *
 * https://leetcode.cn/contest/weekly-contest-341/problems/find-the-maximum-divisibility-score/
 *
 * You are given two 0-indexed integer arrays nums and divisors.
 *
 * The divisibility score of divisors[i] is the number of indices j such
 * that nums[j] is divisible by divisors[i].
 *
 * Return the integer divisors[i] with the maximum divisibility score.
 * If there is more than one integer with the maximum score, return the minimum of them.
 */

public class Solution {

    public int maxDivScore(int[] nums, int[] divisors) {
        Arrays.sort(divisors);

        int ans = 0, cnt = -1;
        for (var div : divisors) {
            int curr = 0;
            for (var n : nums) {
                if (n % div == 0) curr++;
            }
            if (curr > cnt) { cnt = curr; ans = div; }
        }
        return ans;
    }

    public static void main(String[] args) {
    }
    
}
