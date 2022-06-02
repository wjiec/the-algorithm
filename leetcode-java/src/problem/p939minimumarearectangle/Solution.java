package problem.p939minimumarearectangle;

import java.util.*;

/**
 * 939. Minimum Area Rectangle
 *
 * https://leetcode.cn/problems/minimum-area-rectangle/
 *
 * You are given an array of points in the X-Y plane points where points[i] = [xi, yi].
 *
 * Return the minimum area of a rectangle formed from these points, with sides parallel to the X and Y axes.
 * If there is not any such rectangle, return 0.
 */

public class Solution {

    public int minAreaRect(int[][] points) {
        Map<Integer, List<Integer>> xPt = new TreeMap<>();
        for (var point : points) {
            xPt.computeIfAbsent(point[0], v -> new ArrayList<>())
                .add(point[1]);
        }

        int ans = Integer.MAX_VALUE;
        Map<Integer, Integer> xPos = new HashMap<>();
        for (var x : xPt.keySet()) {
            List<Integer> yList = xPt.get(x);
            Collections.sort(yList);
            for (int i = 0, n = yList.size(); i < n; i++) {
                for (int j = i + 1; j < n; j++) {
                    int y1 = yList.get(i), y2 = yList.get(j);
                    int point = (y1 << 16) | y2;
                    if (xPos.containsKey(point)) {
                        ans = Math.min(ans, (x - xPos.get(point)) * (y2 - y1));
                    }
                    xPos.put(point, x);
                }
            }
        }

        return ans < Integer.MAX_VALUE ? ans : 0;
    }

    public static void main(String[] args) {
        assert new Solution().minAreaRect(new int[][]{
            {36219,4673},{26311,36047},{26311,4673},{36219,16024},
            {17010,16024},{26311,6287},{22367,6287},{17010,36047},
            {17010,6287},{22367,16024},{36219,6287},{22367,4673},
            {17010,4673},{36219,36047}
        }) == 6365616;

        assert new Solution().minAreaRect(new int[][]{{1,1},{1,3},{3,1},{3,3},{2,2}}) == 4;
        assert new Solution().minAreaRect(new int[][]{{1,1},{1,3},{3,1},{3,3},{4,1},{4,3}}) == 2;
    }

}
