package weekly.bw140.B;

import java.util.Arrays;

/**
 * 3301. Maximize the Total Height of Unique Towers
 *
 * https://leetcode.cn/contest/biweekly-contest-140/problems/maximize-the-total-height-of-unique-towers/
 *
 * You are given an array maximumHeight, where maximumHeight[i] denotes the maximum height the ith tower can be assigned.
 *
 * Your task is to assign a height to each tower so that:
 *
 * The height of the ith tower is a positive integer and does not exceed maximumHeight[i].
 * No two towers have the same height.
 * Return the maximum possible total sum of the tower heights. If it's not possible to assign heights, return -1.
 */

public class Solution {

    public long maximumTotalSum(int[] maximumHeight) {
        Arrays.sort(maximumHeight);

        long ans = 0; int limit = Integer.MAX_VALUE;
        for (int i = maximumHeight.length - 1; i >= 0; i--) {
            limit = Math.min(maximumHeight[i], limit) - 1;
            if (limit < 0) return -1;
            ans += limit + 1;
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().maximumTotalSum(new int[]{2,2,1}) == -1;
    }

}
