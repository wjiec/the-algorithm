package problem.p1176dietplanperformance;

/**
 * 1176. Diet Plan Performance
 *
 * https://leetcode-cn.com/problems/diet-plan-performance/
 *
 * A dieter consumes calories[i] calories on the i-th day.
 *
 * Given an integer k, for every consecutive sequence of k days
 * (calories[i], calories[i+1], ..., calories[i+k-1] for all 0 <= i <= n-k),
 * they look at T, the total calories consumed during that sequence
 * of k days (calories[i] + calories[i+1] + ... + calories[i+k-1]):
 *
 * If T < lower, they performed poorly on their diet and lose 1 point; 
 * If T > upper, they performed well on their diet and gain 1 point;
 *
 * Otherwise, they performed normally and there is no change in points.
 * Initially, the dieter has zero points. Return the total number of points
 * the dieter has after dieting for calories.length days.
 *
 * Note that the total points can be negative.
 */

public class Solution {

    public int dietPlanPerformance(int[] calories, int k, int lower, int upper) {
        int total = calories[0], ans = 0;
        for (int i = 1; i < k; i++) total += calories[i];

        ans += total < lower ? -1 : (total > upper ? 1 : 0);
        for (int i = k; i < calories.length; i++) {
            total += calories[i] - calories[i - k];
            ans += total < lower ? -1 : (total > upper ? 1 : 0);
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().dietPlanPerformance(new int[]{1,2,3,4,5}, 1, 3, 3) == 0;
        assert new Solution().dietPlanPerformance(new int[]{3,2}, 2, 0, 1) == 1;
        assert new Solution().dietPlanPerformance(new int[]{6,5,0,0}, 2, 1, 5) == 0;
    }

}
