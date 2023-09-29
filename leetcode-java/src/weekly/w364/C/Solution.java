package weekly.w364.C;

import java.util.List;

/**
 * 2866. Beautiful Towers II
 *
 * https://leetcode.cn/contest/weekly-contest-364/problems/beautiful-towers-ii/
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
        int[] hs = new int[maxHeights.size() + 2];
        for (int i = 0; i < maxHeights.size(); i++) {
            hs[i + 1] = maxHeights.get(i);
        }

        // 枚举山顶
        long ans = 0, n = hs.length;
        for (int i = 1; i < n - 1; i++) {
            if (hs[i - 1] < hs[i] && hs[i + 1] <= hs[i]) {
                long curr = hs[i];
                for (int l = i - 1, h = hs[i]; l >= 0; l--) {
                    curr += (h = Math.min(h, hs[l]));
                }
                for (int r = i + 1, h = hs[i]; r < n; r++) {
                    curr += (h = Math.min(h, hs[r]));
                }
                ans = Math.max(ans, curr);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
    }

}
