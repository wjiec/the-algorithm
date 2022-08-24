package problem.p1620coordinatewithmaximumnetworkquality;

import common.Checker;

/**
 * 1620. Coordinate With Maximum Network Quality
 *
 * https://leetcode.cn/problems/coordinate-with-maximum-network-quality/
 *
 * You are given an array of network towers towers, where towers[i] = [xi, yi, qi] denotes
 * the ith network tower with location (xi, yi) and quality factor qi. All the coordinates
 * are integral coordinates on the X-Y plane, and the distance between the two coordinates
 * is the Euclidean distance.
 *
 * You are also given an integer radius where a tower is reachable if the distance is less
 * than or equal to radius. Outside that distance, the signal becomes garbled, and
 * the tower is not reachable.
 *
 * The signal quality of the ith tower at a coordinate (x, y) is calculated with the
 * formula ⌊qi / (1 + d)⌋, where d is the distance between the tower and the coordinate.
 * The network quality at a coordinate is the sum of the signal qualities from all the reachable towers.
 *
 * Return the array [cx, cy] representing the integral coordinate (cx, cy) where the network
 * quality is maximum. If there are multiple coordinates with the same network quality, return
 * the lexicographically minimum non-negative coordinate.
 *
 * Note:
 *
 * A coordinate (x1, y1) is lexicographically smaller than (x2, y2) if either:
 * x1 < x2, or
 * x1 == x2 and y1 < y2.
 * ⌊val⌋ is the greatest integer less than or equal to val (the floor function).
 */

public class Solution {

    public int[] bestCoordinate(int[][] towers, int radius) {
        int mx = 0, my = 0;
        for (var tower : towers) {
            mx = Math.max(mx, tower[0]);
            my = Math.max(my, tower[1]);
        }

        int x = 51, y = 51, q = 0;
        for (int i = 0; i <= mx; i++) {
            for (int j = 0; j <= my; j++) {
                int curr = calc(towers, i, j, radius);
                if (curr > q || (curr == q && (i < x || (i == x && j < y)))) {
                    x = i; y = j; q = curr;
                }
            }
        }
        return new int[]{x, y};
    }

    private int calc(int[][] towers, int x, int y, int r) {
        int sum = 0;
        for (var tower : towers) {
            int tx = tower[0], ty = tower[1];
            double dist = Math.sqrt(Math.pow(tx - x, 2) + Math.pow(ty - y, 2));
            if (dist <= r) {
                sum += tower[2] / (1 + dist);
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().bestCoordinate(new int[][]{
            {33,24,12},{5,34,12},{9,45,6}, {28,12,12}
        }, 2), new int[]{5,34});

        assert Checker.check(new Solution().bestCoordinate(new int[][]{
            {1,2,5},{2,1,7},{3,1,9}
        }, 2), new int[]{2, 1});

        assert Checker.check(new Solution().bestCoordinate(new int[][]{
            {23,11,21}
        }, 9), new int[]{23,11});

        assert Checker.check(new Solution().bestCoordinate(new int[][]{
            {1,2,13},{2,1,7},{0,1,9}
        }, 2), new int[]{1,2});
    }

}
