package weekly.w364.B;

import java.util.List;

/**
 * 2865. Beautiful Towers I
 *
 * https://leetcode.cn/contest/weekly-contest-364/problems/beautiful-towers-i/
 *
 * You are given a 0-indexed array maxHeights of n integers.
 *
 * You are tasked with building n towers in the coordinate line. The ith tower is built at
 * coordinate i and has a height of heights[i].
 *
 * A configuration of towers is beautiful if the following conditions hold:
 *
 * 1 <= heights[i] <= maxHeights[i]
 * heights is a mountain array.
 * Array heights is a mountain if there exists an index i such that:
 *
 * For all 0 < j <= i, heights[j - 1] <= heights[j]
 * For all i <= k < n - 1, heights[k + 1] <= heights[k]
 * Return the maximum possible sum of heights of a beautiful configuration of towers.
 */

public class Solution {

    public long maximumSumOfHeights(List<Integer> maxHeights) {
        long ans = 0, n = maxHeights.size();
        for (int i = 0; i < n; i++) {
            long curr = maxHeights.get(i);
            for (int h = maxHeights.get(i), l = i - 1; l >= 0; l--) {
                curr += (h = Math.min(h, maxHeights.get(l)));
            }
            for (int h = maxHeights.get(i), r = i + 1; r < n; r++) {
                curr += (h = Math.min(h, maxHeights.get(r)));
            }
            ans = Math.max(ans, curr);
        }
        return ans;
    }

    public static void main(String[] args) {
    }

}
