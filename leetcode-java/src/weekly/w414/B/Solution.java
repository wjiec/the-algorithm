package weekly.w414.B;

import java.util.Arrays;

/**
 * 3281. Maximize Score of Numbers in Ranges
 *
 * https://leetcode.cn/contest/weekly-contest-414/problems/maximize-score-of-numbers-in-ranges/
 *
 * You are given an array of integers start and an integer d, representing n intervals [start[i], start[i] + d].
 *
 * You are asked to choose n integers where the ith integer must belong to the ith interval.
 *
 * The score of the chosen integers is defined as the minimum absolute difference between
 * any two integers that have been chosen.
 *
 * Return the maximum possible score of the chosen integers.
 */

public class Solution {

    public int maxPossibleScore(int[] start, int d) {
        Arrays.sort(start);

        int l = 0, r = Integer.MAX_VALUE;
        while (l + 1 < r) {
            int mid = l + (r - l) / 2;
            if (check(start, d, mid)) l = mid;
            else r = mid;
        }

        return l;
    }

    // 检查是否可以实现最小绝对值至少为 diff
    private boolean check(int[] start, int d, int diff) {
        long left = Long.MIN_VALUE;
        for (long v : start) {
            left = Math.max(left + diff, v);
            if (left > v + d) return false;
        }

        return true;
    }

    public static void main(String[] args) {
        assert new Solution().maxPossibleScore(new int[]{6,0,3}, 2) == 4;
        assert new Solution().maxPossibleScore(new int[]{2,6,13,13}, 5) == 5;
    }

}
