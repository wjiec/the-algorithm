package problem.p2136earliestpossibledayoffullbloom;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 2136. Earliest Possible Day of Full Bloom
 *
 * https://leetcode.cn/problems/earliest-possible-day-of-full-bloom/
 *
 * You have n flower seeds. Every seed must be planted first before it can
 * begin to grow, then bloom. Planting a seed takes time and so does the
 * growth of a seed.
 *
 * You are given two 0-indexed integer arrays plantTime and growTime, of length n each:
 *
 * plantTime[i] is the number of full days it takes you to plant the ith seed.
 * Every day, you can work on planting exactly one seed. You do not have to work
 * on planting the same seed on consecutive days, but the planting of a seed is not
 * complete until you have worked plantTime[i] days on planting it in total.
 *
 * growTime[i] is the number of full days it takes the ith seed to grow after being
 * completely planted. After the last day of its growth, the flower blooms and stays
 * bloomed forever.
 *
 * From the beginning of day 0, you can plant the seeds in any order.
 *
 * Return the earliest possible day where all seeds are blooming.
 */

public class Solution {

    public int earliestFullBloom(int[] plantTime, int[] growTime) {
        Integer[] index = new Integer[plantTime.length];
        for (int i = 0; i < index.length; i++) index[i] = i;
        Arrays.sort(index, (a, b) -> growTime[b] - growTime[a]);

        int ans = 0, prev = 0;
        for (var idx : index) {
            ans = Math.max(ans, prev + plantTime[idx] + growTime[idx]);
            prev += plantTime[idx];
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().earliestFullBloom(new int[]{5,5}, new int[]{5,5}) == 15;

        assert new Solution().earliestFullBloom(new int[]{1,4,3}, new int[]{2,3,1}) == 9;
        assert new Solution().earliestFullBloom(new int[]{1,2,3,2}, new int[]{2,1,2,1}) == 9;
        assert new Solution().earliestFullBloom(new int[]{1}, new int[]{1}) == 2;
    }

}
