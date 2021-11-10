package daily.d211110p495teemoattacking;

/**
 * 495. Teemo Attacking
 *
 * https://leetcode-cn.com/problems/teemo-attacking/
 *
 * Our hero Teemo is attacking an enemy Ashe with poison attacks! When Teemo attacks Ashe,
 * Ashe gets poisoned for a exactly duration seconds.
 *
 * More formally, an attack at second t will mean Ashe is poisoned during the
 * inclusive time interval [t, t + duration - 1].
 *
 * If Teemo attacks again before the poison effect ends, the timer for it is reset,
 * and the poison effect will end duration seconds after the new attack.
 *
 * You are given a non-decreasing integer array timeSeries, where timeSeries[i] denotes
 * that Teemo attacks Ashe at second timeSeries[i], and an integer duration.
 *
 * Return the total number of seconds that Ashe is poisoned.
 */

public class Solution {

    public int findPoisonedDuration(int[] timeSeries, int duration) {
        if (duration == 0) return 0;
        int ans = 0;
        for (int i = 1; i < timeSeries.length; i++) {
            ans += Math.min(timeSeries[i] - timeSeries[i - 1], duration);
        }
        return ans + duration;
    }

    public static void main(String[] args) {
        assert new Solution().findPoisonedDuration(new int[]{1,4}, 2) == 4;
        assert new Solution().findPoisonedDuration(new int[]{1,2}, 2) == 3;
    }

}
