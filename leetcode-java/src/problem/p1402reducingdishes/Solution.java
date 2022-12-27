package problem.p1402reducingdishes;

import java.util.Arrays;

/**
 * 1402. Reducing Dishes
 *
 * https://leetcode.cn/problems/reducing-dishes/
 *
 * A chef has collected data on the satisfaction level of his n dishes.
 * Chef can cook any dish in 1 unit of time.
 *
 * Like-time coefficient of a dish is defined as the time taken to cook that dish including
 * previous dishes multiplied by its satisfaction level (i.e. time[i] * satisfaction[i]).
 *
 * Return the maximum sum of like-time coefficient that the chef can obtain after dishes preparation.
 *
 * Dishes can be prepared in any order and the chef can discard some dishes to get this maximum value.
 */

public class Solution {

    public int maxSatisfaction(int[] satisfaction) {
        Arrays.sort(satisfaction);

        int n = satisfaction.length, idx = 0;
        while (idx < n && satisfaction[idx] < 0) idx++;
        if (idx == n) return 0; // 不做任何菜

        int sum = 0, ans = 0; // 只做正数的菜
        for (int i = idx; i < n; i++) {
            sum += satisfaction[i];
            ans += satisfaction[i] * (i - idx + 1);
        }

        // 如果做负数的菜能让数值变得更大，那就做
        for (int i = idx - 1; i >= 0; i--) {
            if (satisfaction[i] + sum > 0) {
                ans += satisfaction[i] + sum;
                sum += satisfaction[i];
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().maxSatisfaction(new int[]{-1,-8,0,5,-9}) == 14;
        assert new Solution().maxSatisfaction(new int[]{4,3,2}) == 20;
        assert new Solution().maxSatisfaction(new int[]{-1,-4,-5}) == 0;
    }

}
