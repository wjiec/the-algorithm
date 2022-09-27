package problem.p1953maximumnumberofweeksforwhichyoucanwork;

/**
 * 1953. Maximum Number of Weeks for Which You Can Work
 *
 * https://leetcode.cn/problems/maximum-number-of-weeks-for-which-you-can-work/
 *
 * There are n projects numbered from 0 to n - 1. You are given an integer array milestones
 * where each milestones[i] denotes the number of milestones the ith project has.
 *
 * You can work on the projects following these two rules:
 *
 * Every week, you will finish exactly one milestone of one project. You must work every week.
 * You cannot work on two milestones from the same project for two consecutive weeks.
 * Once all the milestones of all the projects are finished, or if the only milestones that
 * you can work on will cause you to violate the above rules, you will stop working.
 *
 * Note that you may not be able to finish every project's milestones due to these constraints.
 *
 * Return the maximum number of weeks you would be able to work on the projects without
 * violating the rules mentioned above.
 */

public class Solution {

    public long numberOfWeeks(int[] milestones) {
        long max = 0, rest = 0;
        for (var ms : milestones) {
            rest += ms;
            max = Math.max(max, ms);
        }

        rest -= max;
        return max > rest + 1 ? rest * 2 + 1 : max + rest;
    }

    public static void main(String[] args) {
        assert new Solution().numberOfWeeks(new int[]{5,7,5,7,9,7}) == 40;

        assert new Solution().numberOfWeeks(new int[]{1,2,3}) == 6;
        assert new Solution().numberOfWeeks(new int[]{5,2,1}) == 7;
        assert new Solution().numberOfWeeks(new int[]{5,2,1}) == 7;
    }

}
