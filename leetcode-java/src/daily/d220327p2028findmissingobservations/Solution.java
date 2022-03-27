package daily.d220327p2028findmissingobservations;

import common.PrettyPrinter;

import java.util.Arrays;

/**
 * 2028. Find Missing Observations
 *
 * https://leetcode-cn.com/problems/find-missing-observations/
 *
 * You have observations of n + m 6-sided dice rolls with each face numbered from 1 to 6. n of the observations
 * went missing, and you only have the observations of m rolls.
 *
 * Fortunately, you have also calculated the average value of the n + m rolls.
 *
 * You are given an integer array rolls of length m where rolls[i] is the value of the ith observation.
 * You are also given the two integers mean and n.
 *
 * Return an array of length n containing the missing observations such that
 * the average value of the n + m rolls is exactly mean.
 *
 * If there are multiple valid answers, return any of them.
 * If no such array exists, return an empty array.
 *
 * The average value of a set of k numbers is the sum of the numbers divided by k.
 * Note that mean is an integer, so the sum of the n + m rolls should be divisible by n + m.
 */

public class Solution {

    public int[] missingRolls(int[] rolls, int mean, int n) {
        int m = rolls.length, sum = mean * (m + n);
        for (var roll : rolls) sum -= roll;
        if (sum < n || sum > 6 * n) return new int[]{};

        int[] ans = new int[n]; int avg = sum / n;
        for (int i = 0; i < n; i++) ans[i] = avg;
        for (int i = 0, r = sum % n; i < r; i++) ans[i]++;
        return ans;
    }

    public static void main(String[] args) {
        PrettyPrinter.println(new Solution().missingRolls(new int[]{3,2,4,3}, 4, 2));
        PrettyPrinter.println(new Solution().missingRolls(new int[]{1,5,6}, 3, 4));
        PrettyPrinter.println(new Solution().missingRolls(new int[]{1,2,3,4}, 6, 4));
        PrettyPrinter.println(new Solution().missingRolls(new int[]{1}, 3, 1));
    }

}
