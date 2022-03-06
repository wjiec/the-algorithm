package daily.d220306p2100findgooddaystorobthebank;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 2100. Find Good Days to Rob the Bank
 *
 * https://leetcode-cn.com/problems/find-good-days-to-rob-the-bank/
 *
 * You and a gang of thieves are planning on robbing a bank. You are given a 0-indexed integer array security,
 * where security[i] is the number of guards on duty on the ith day.
 *
 * The days are numbered starting from 0. You are also given an integer time.
 *
 * The ith day is a good day to rob the bank if:
 *
 * There are at least time days before and after the ith day,
 * The number of guards at the bank for the time days before i are non-increasing, and
 * The number of guards at the bank for the time days after i are non-decreasing.
 * More formally, this means day i is a good day to rob the bank if and only
 * if security[i - time] >= security[i - time + 1] >= ... >= security[i]
 *      <= ... <= security[i + time - 1] <= security[i + time].
 *
 * Return a list of all days (0-indexed) that are good days to rob the bank.
 * The order that the days are returned in does not matter.
 */

public class Solution {

    public List<Integer> goodDaysToRobBank(int[] security, int time) {
        int n = security.length;
        int[] left = new int[n], right = new int[n];
        for (int i = 1; i < n; i++) {
            if (security[i] <= security[i - 1]) left[i] = left[i - 1] + 1;
            if (security[n - i] >= security[n - i - 1]) right[n - i - 1] = right[n - i] + 1;
        }

        List<Integer> ans = new ArrayList<>();
        for (int i = time, e = n - time; i < e; i++) {
            if (left[i] >= time && right[i] >= time) {
                ans.add(i);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().goodDaysToRobBank(new int[]{5,3,3,3,5,6,2}, 2));
        System.out.println(new Solution().goodDaysToRobBank(new int[]{1,1,1,1,1}, 0));
        System.out.println(new Solution().goodDaysToRobBank(new int[]{1,2,3,4,5,6}, 2));
        System.out.println(new Solution().goodDaysToRobBank(new int[]{1}, 5));
    }

}
