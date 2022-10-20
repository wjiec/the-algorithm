package problem.p2250countnumberofrectanglescontainingeachpoint;

import common.Checker;
import common.TODO;

import java.util.*;
import java.util.stream.IntStream;

/**
 * 2250. Count Number of Rectangles Containing Each Point
 *
 * https://leetcode.cn/problems/count-number-of-rectangles-containing-each-point/
 *
 * You are given a 2D integer array rectangles where rectangles[i] = [li, hi] indicates
 * that ith rectangle has a length of li and a height of hi. You are also given a 2D integer
 * array points where points[j] = [xj, yj] is a point with coordinates (xj, yj).
 *
 * The ith rectangle has its bottom-left corner point at the coordinates (0, 0) and
 * its top-right corner point at (li, hi).
 *
 * Return an integer array count of length points.length where count[j] is the number of
 * rectangles that contain the jth point.
 *
 * The ith rectangle contains the jth point if 0 <= xj <= li and 0 <= yj <= hi.
 *
 * Note that points that lie on the edges of a rectangle are also
 * considered to be contained by that rectangle.
 */

public class Solution {

    @TODO
    public int[] countRectangles(int[][] rectangles, int[][] points) {
        Arrays.sort(rectangles, (a, b) -> b[1] - a[1]);

        int n = points.length;
        Integer[] ids = IntStream.range(0, n).boxed().toArray(Integer[]::new);
        Arrays.sort(ids, (i, j) -> points[j][1] - points[i][1]);

        var ans = new int[n];
        var xs = new ArrayList<Integer>();
        var i = 0;
        for (var id : ids) {
            int start = i;
            while (i < rectangles.length && rectangles[i][1] >= points[id][1])
                xs.add(rectangles[i++][0]);
            if (start < i) Collections.sort(xs); // 只有在 xs 插入了新元素时才排序
            ans[id] = i - lowerBound(xs, points[id][0]);
        }
        return ans;
    }

    int lowerBound(List<Integer> xs, int x) {
        int l = 0, r = xs.size();
        while (l < r) {
            var mid = (l + r) / 2;
            if (xs.get(mid) >= x) r = mid;
            else l = mid + 1;
        }
        return l;
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().countRectangles(new int[][]{{4,7},{4,9},{8,5},{6,2},{6,4}}, new int[][]{{4,2},{5,6}}), new int[]{5, 0});

        assert Checker.check(new Solution().countRectangles(new int[][]{{1,2},{2,3},{2,5}}, new int[][]{{2,1},{1,4}}), new int[]{2, 1});
        assert Checker.check(new Solution().countRectangles(new int[][]{{1,1},{2,2},{3,3}}, new int[][]{{1,3},{1,1}}), new int[]{1,3});
    }

}
