package daily.d230221p1326minimumnumberoftapstoopentowateragarden;

import java.util.Arrays;

/**
 * 1326. Minimum Number of Taps to Open to Water a Garden
 *
 * https://leetcode.cn/problems/minimum-number-of-taps-to-open-to-water-a-garden/
 *
 * There is a one-dimensional garden on the x-axis. The garden starts at the
 * point 0 and ends at the point n. (i.e The length of the garden is n).
 *
 * There are n + 1 taps located at points [0, 1, ..., n] in the garden.
 *
 * Given an integer n and an integer array ranges of length n + 1 where ranges[i] (0-indexed)
 * means the i-th tap can water the area [i - ranges[i], i + ranges[i]] if it was open.
 *
 * Return the minimum number of taps that should be open to water the whole garden,
 * If the garden cannot be watered return -1.
 */

@SuppressWarnings("ComparatorCombinators")
public class Solution {

    public int minTaps(int n, int[] ranges) {
        int[][] intervals = new int[n + 1][2];
        for (int i = 0; i < ranges.length; i++) {
            intervals[i][0] = Math.max(i - ranges[i], 0);
            intervals[i][1] = Math.min(i + ranges[i], n);
        }
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);

        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int i = 0; i <= n; i++) {
            int l = intervals[i][0], r = intervals[i][1];
            if (dp[l] == Integer.MAX_VALUE) return -1;
            for (int j = l; j <= r; j++) {
                dp[j] = Math.min(dp[j], dp[l] + 1);
            }
        }
        return dp[n];
    }

    private static class Greedy {
        public int minTaps(int n, int[] ranges) {
            int[] reached = new int[n + 1];
            for (int i = 0; i <= n; i++) reached[i] = i;
            for (int i = 0; i <= n; i++) {
                int l = Math.max(i - ranges[i], 0);
                int r = Math.min(i + ranges[i], n);
                reached[l] = Math.max(reached[l], r);
            }

            int ans = 0;
            for (int i = 0, l = 0, r = 0; i < n; i++) {
                r = Math.max(r, reached[i]);
                if (i == r) return -1;
                if (i == l) { ans++; l = r; }
            }
            return ans;
        }
    }

    public static void main(String[] args) {
        assert new Solution().minTaps(7, new int[]{1,2,1,0,2,1,0,1}) == 3;
        assert new Solution().minTaps(8, new int[]{4,0,0,0,0,0,0,0,4}) == 2;

        assert new Solution().minTaps(5, new int[]{3,4,1,1,0,0}) == 1;
        assert new Solution().minTaps(3, new int[]{0,0,0,0}) == -1;

        assert new Greedy().minTaps(7, new int[]{1,2,1,0,2,1,0,1}) == 3;
        assert new Greedy().minTaps(8, new int[]{4,0,0,0,0,0,0,0,4}) == 2;
        assert new Greedy().minTaps(5, new int[]{3,4,1,1,0,0}) == 1;
        assert new Greedy().minTaps(3, new int[]{0,0,0,0}) == -1;
    }

}
