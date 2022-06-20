package problem.p1024videostitching;

import java.util.Arrays;

/**
 * 1024. Video Stitching
 *
 * https://leetcode.cn/problems/video-stitching/
 *
 * You are given a series of video clips from a sporting event that lasted time seconds. These
 * video clips can be overlapping with each other and have varying lengths.
 *
 * Each video clip is described by an array clips where clips[i] = [starti, endi] indicates that
 * the ith clip started at starti and ended at endi.
 *
 * We can cut these clips into segments freely.
 *
 * For example, a clip [0, 7] can be cut into segments [0, 1] + [1, 3] + [3, 7].
 * Return the minimum number of clips needed so that we can cut the clips into segments that
 * cover the entire sporting event [0, time]. If the task is impossible, return -1.
 */

public class Solution {

    public int videoStitching(int[][] clips, int time) {
        int[] dp = new int[time + 1];
        Arrays.fill(dp, 1000); dp[0] = 0;
        for (int i = 1; i <= time; i++) {
            for (int[] clip : clips) {
                if (clip[0] < i && i <= clip[1]) {
                    dp[i] = Math.min(dp[i], dp[clip[0]] + 1);
                }
            }
        }
        return dp[time] == 1000 ? -1 : dp[time];
    }

    public static void main(String[] args) {
        assert new Solution().videoStitching(new int[][]{
            {0,2},{4,8}
        }, 5) == -1;

        assert new Solution().videoStitching(new int[][]{
            {0,2},{4,6},{8,10},{1,9},{1,5},{5,9}
        }, 10) == 3;

        assert new Solution().videoStitching(new int[][]{{0,1},{1,2}}, 5) == -1;

        assert new Solution().videoStitching(new int[][]{
            {0,1},{6,8},{0,2},{5,6},{0,4},{0,3},{6,7},{1,3},
            {4,7},{1,4},{2,5},{2,6},{3,4},{4,5},{5,7},{6,9}
        }, 9) == 3;
    }

}
