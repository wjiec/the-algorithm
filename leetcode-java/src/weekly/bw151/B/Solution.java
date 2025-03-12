package weekly.bw151.B;

/**
 * 3468. Find the Number of Copy Arrays
 *
 * https://leetcode.cn/contest/biweekly-contest-151/problems/find-the-number-of-copy-arrays/
 *
 * You are given an array original of length n and a 2D array bounds of length n x 2, where bounds[i] = [ui, vi].
 *
 * You need to find the number of possible arrays copy of length n such that:
 *
 * (copy[i] - copy[i - 1]) == (original[i] - original[i - 1]) for 1 <= i <= n - 1.
 * ui <= copy[i] <= vi for 0 <= i <= n - 1.
 *
 * Return the number of such arrays.
 */

public class Solution {

    public int countArrays(int[] original, int[][] bounds) {
        // 先让第一个数符合 bounds 的范围, 后续后加上这个数
        int diff = 0, lower = bounds[0][0], upper = bounds[0][1];
        if (original[0] < lower) diff = lower - original[0];
        else if (original[0] > upper) diff = upper - original[0];

        final int INF = Integer.MAX_VALUE / 2;
        int n = original.length, down = INF, up = INF;
        for (int i = 0; i < n; i++) {
            int curr = original[i] + diff;

            up = Math.min(up, bounds[i][1] - curr + 1);
            down = Math.min(down, curr - bounds[i][0]);
        }
        return Math.max(down + up, 0);
    }

    public static void main(String[] args) {
        assert new Solution().countArrays(new int[]{11,42}, new int[][]{{22,55},{72,110}}) == 15;
        assert new Solution().countArrays(new int[]{3,25}, new int[][]{{9,80},{16,35}}) == 5;

        assert new Solution().countArrays(new int[]{1,2,3,4}, new int[][]{{1,2},{2,3},{3,4},{4,5}}) == 2;
        assert new Solution().countArrays(new int[]{1,2,3,4}, new int[][]{{1,10},{2,9},{3,8},{4,7}}) == 4;
        assert new Solution().countArrays(new int[]{1,2,1,2}, new int[][]{{1,1},{2,3},{3,3},{2,3}}) == 0;
    }

}
