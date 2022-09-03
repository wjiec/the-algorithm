package weekly.bw86.D;

import java.util.TreeMap;

/**
 * 6143. Maximum Number of Robots Within Budget
 *
 * https://leetcode.cn/contest/biweekly-contest-86/problems/maximum-number-of-robots-within-budget/
 *
 * You have n robots. You are given two 0-indexed integer arrays, chargeTimes and runningCosts, both
 * of length n. The ith robot costs chargeTimes[i] units to charge and costs runningCosts[i] units to run.
 *
 * You are also given an integer budget.
 *
 * The total cost of running k chosen robots is equal to max(chargeTimes) + k * sum(runningCosts), where
 * max(chargeTimes) is the largest charge cost among the k robots and sum(runningCosts) is the sum of
 * running costs among the k robots.
 *
 * Return the maximum number of consecutive robots you can run such that the total cost does not exceed budget.
 */

public class Solution {

    public int maximumRobots(int[] chargeTimes, int[] runningCosts, long budget) {
        long ans = 0, sum = 0, n = chargeTimes.length;
        TreeMap<Integer, Integer> max = new TreeMap<>();
        for (int l = 0, r = 0; r < n; r++) {
            sum += runningCosts[r];
            max.merge(chargeTimes[r], 1, Integer::sum);
            while (l <= r && max.lastKey() + (r - l + 1) * sum > budget) {
                sum -= runningCosts[l];
                max.merge(chargeTimes[l++], 1, (old, val) -> old - val == 0 ? null : old - val);
            }
            ans = Math.max(ans, r - l + 1);
        }

        return (int) ans;
    }

    public static void main(String[] args) {
        assert new Solution().maximumRobots(new int[]{11,12,74,67,37,87,42,34,18,90,36,28,34,20}, new int[]{18,98,2,84,7,57,54,65,59,91,7,23,94,20}, 937) == 4;

        assert new Solution().maximumRobots(new int[]{3,6,1,3,4}, new int[]{2,1,3,4,5}, 25) == 3;
        assert new Solution().maximumRobots(new int[]{11,12,19}, new int[]{10,8,7}, 19) == 0;
    }

}
