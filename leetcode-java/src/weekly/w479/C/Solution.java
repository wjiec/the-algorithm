package weekly.w479.C;

import java.util.Arrays;

/**
 * Q3. Total Score of Dungeon Runs
 *
 * https://leetcode.cn/contest/weekly-contest-479/problems/total-score-of-dungeon-runs/
 *
 * You are given a positive integer hp and two positive 1-indexed integer arrays damage and requirement.
 *
 * There is a dungeon with n trap rooms numbered from 1 to n. Entering room i reduces
 * your health points by damage[i]. After that reduction, if your remaining health
 * points are at least requirement[i], you earn 1 point for that room.
 *
 * Let score(j) be the number of points you get if you start with hp health points and
 * enter the rooms j, j + 1, ..., n in this order.
 *
 * Return the integer score(1) + score(2) + ... + score(n), the sum of scores over all starting rooms.
 *
 * Note: You cannot skip rooms. You can finish your journey even if your health points become non-positive.
 */

public class Solution {

    public long totalScore(int hp, int[] damage, int[] requirement) {
        // 对于每个位置 i, 我们只考虑在当前位置获得得分的方案数
        //  - 也就是我们只考虑在经过任意个位置后, 能否在当前位置满足 requirement 获得得分
        long[] sum = new long[damage.length + 1]; long ans = 0;
        for (int i = 0; i < damage.length; i++) {
            sum[i + 1] = sum[i] + damage[i];
            // hp - (s[i + 1] - s[j]) >= req[i]
            //  => hp - s[i + 1] + s[j] >= req[i]
            //  => s[j] >= req[i] + s[i + 1] - hp
            long sj = requirement[i] + sum[i + 1] - hp;

            int j = Arrays.binarySearch(sum, 0, i + 1, sj);
            if (j < 0) j = ~j;

            ans += i - j + 1;
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().totalScore(1, new int[]{1,1}, new int[]{2,1}) == 0;

        assert new Solution().totalScore(11, new int[]{3,6,7}, new int[]{4,2,5}) == 3;
        assert new Solution().totalScore(2, new int[]{10000,1}, new int[]{1,1}) == 1;
    }

}
