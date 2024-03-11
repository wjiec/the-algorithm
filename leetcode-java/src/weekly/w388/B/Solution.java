package weekly.w388.B;

import java.util.Arrays;

/**
 * 100247. Maximize Happiness of Selected Children
 *
 * https://leetcode.cn/contest/weekly-contest-388/problems/maximize-happiness-of-selected-children/
 *
 * You are given an array happiness of length n, and a positive integer k.
 *
 * There are n children standing in a queue, where the ith child has happiness value happiness[i].
 * You want to select k children from these n children in k turns.
 *
 * In each turn, when you select a child, the happiness value of all the children that have
 * not been selected till now decreases by 1. Note that the happiness value cannot become
 * negative and gets decremented only if it is positive.
 *
 * Return the maximum sum of the happiness values of the selected children you can achieve by selecting k children.
 */

public class Solution {

    public long maximumHappinessSum(int[] happiness, int k) {
        Arrays.sort(happiness);

        long ans = 0; int n = happiness.length;
        for (int i = 0; i < n && i < k; i++) {
            ans += Math.max(0, happiness[n - i - 1] - i);
        }
        return ans;
    }

    public static void main(String[] args) {
    }

}
