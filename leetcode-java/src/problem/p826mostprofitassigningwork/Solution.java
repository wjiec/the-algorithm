package problem.p826mostprofitassigningwork;

import java.util.Arrays;

/**
 * 826. Most Profit Assigning Work
 *
 * https://leetcode.cn/problems/most-profit-assigning-work/
 *
 * You have n jobs and m workers. You are given three arrays: difficulty, profit, and worker where:
 *
 * difficulty[i] and profit[i] are the difficulty and the profit of the ith job, and
 *
 * worker[j] is the ability of jth worker (i.e., the jth worker can only complete a job
 * with difficulty at most worker[j]).
 *
 * Every worker can be assigned at most one job, but one job can be completed multiple times.
 *
 * For example, if three workers attempt the same job that pays $1, then the total profit will be $3.
 * If a worker cannot complete any job, their profit is $0.
 *
 * Return the maximum profit we can achieve after assigning the workers to the jobs.
 */

public class Solution {

    public int maxProfitAssignment(int[] difficulty, int[] profit, int[] worker) {
        int[][] unit = new int[difficulty.length][2];
        for (int i = 0; i < difficulty.length; i++) {
            unit[i][0] = profit[i];
            unit[i][1] = difficulty[i];
        }

        Arrays.sort(unit, (a, b) -> {
            if (a[1] == b[1]) return b[0] - a[0];
            return a[1] - b[1];
        });

        for (int i = 0, max = unit[0][0]; i < unit.length; i++) {
            if (unit[i][0] > max) max = unit[i][0];
            unit[i][0] = max;
        }

        int ans = 0;
        for (var w : worker) ans += assign(unit, w);
        return ans;
    }

    private int assign(int[][] unit, int target) {
        if (target < unit[0][1]) return 0;
        if (target >= unit[unit.length - 1][1]) return unit[unit.length - 1][0];

        int ans = -1;
        for (int l = 0, r = unit.length; l < r; ) {
            int mid = l + (r - l) / 2;
            if (unit[mid][1] > target) r = mid;
            else { ans = mid; l = mid + 1; }
        }

        return ans == -1 ? 0 : unit[ans][0];
    }

    public static void main(String[] args) {
        assert new Solution().maxProfitAssignment(new int[]{68,35,52,47,86}, new int[]{67,17,1,81,3}, new int[]{92,10,85,84,82}) == 324;
        assert new Solution().maxProfitAssignment(new int[]{2,4,6,8,10}, new int[]{10,20,30,40,50}, new int[]{4,5,6,7}) == 100;
        assert new Solution().maxProfitAssignment(new int[]{85,47,57}, new int[]{24,66,99}, new int[]{40,25,25}) == 0;
    }

}
