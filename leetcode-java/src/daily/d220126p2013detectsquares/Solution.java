package daily.d220126p2013detectsquares;

import java.util.HashMap;
import java.util.Map;

/**
 * 2013. Detect Squares
 *
 * https://leetcode-cn.com/problems/detect-squares/
 *
 * You are given a stream of points on the X-Y plane. Design an algorithm that:
 *
 * Adds new points from the stream into a data structure. Duplicate points are allowed
 * and should be treated as different points.
 *
 * Given a query point, counts the number of ways to choose three points from the data structure
 * such that the three points and the query point form an axis-aligned square with positive area.
 *
 * An axis-aligned square is a square whose edges are all the same length and are either parallel
 * or perpendicular to the x-axis and y-axis.
 *
 * Implement the DetectSquares class:
 *
 * DetectSquares() Initializes the object with an empty data structure.
 *
 * void add(int[] point) Adds a new point point = [x, y] to the data structure.
 *
 * int count(int[] point) Counts the number of ways to form axis-aligned squares
 * with point point = [x, y] as described above.
 */

public class Solution {

    private static class DetectSquares {
        private final Map<Integer, Map<Integer, Integer>> count = new HashMap<>();
        public DetectSquares() { }

        public void add(int[] point) {
            count.putIfAbsent(point[1], new HashMap<>());
            count.get(point[1]).merge(point[0], 1, Integer::sum);
        }

        public int count(int[] point) {
            int ans = 0;
            int x = point[0], y = point[1];
            if (!count.containsKey(y)) return 0;

            Map<Integer, Integer> map = count.get(y);
            for (var entry : count.entrySet()) {
                int k = entry.getKey();
                var v = entry.getValue();

                if (k != y) {
                    int d = k - y;
                    ans += v.getOrDefault(x, 0) * map.getOrDefault(x + d, 0) * v.getOrDefault(x + d, 0);
                    ans += v.getOrDefault(x, 0) * map.getOrDefault(x - d, 0) * v.getOrDefault(x - d, 0);
                }
            }

            return ans;
        }
    }

    public static void main(String[] args) {
        DetectSquares squares = new DetectSquares();

        squares.add(new int[]{3, 10});
        squares.add(new int[]{11, 2});
        squares.add(new int[]{3, 2});
        assert squares.count(new int[]{11, 10}) == 1;
        assert squares.count(new int[]{14, 8}) == 0;

        squares.add(new int[]{11, 2});
        assert squares.count(new int[]{11, 10}) == 2;
    }

}
