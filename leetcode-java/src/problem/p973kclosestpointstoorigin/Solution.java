package problem.p973kclosestpointstoorigin;

import common.PrettyPrinter;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 973. K Closest Points to Origin
 *
 * https://leetcode.cn/problems/k-closest-points-to-origin/
 *
 * Given an array of points where points[i] = [xi, yi] represents a point on the X-Y
 * plane and an integer k, return the k closest points to the origin (0, 0).
 *
 * The distance between two points on the X-Y plane is the Euclidean distance (i.e., √(x1 - x2)2 + (y1 - y2)2).
 *
 * You may return the answer in any order. The answer is guaranteed to be unique (except for the order that it is in).
 */

public class Solution {

    public int[][] kClosest(int[][] points, int k) {
        if (points.length == k) return points;
        PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> b[0] - a[0]);
        for (var point : points) {
            int d = point[0] * point[0] + point[1] * point[1];
            queue.add(new int[]{d, point[0], point[1]});
            if (queue.size() > k) queue.remove();
        }

        int[][] ans = new int[k][2];
        for (int i = 0; i < k; i++) {
            int[] curr = queue.remove();
            ans[i][0] = curr[1];
            ans[i][1] = curr[2];
        }

        return ans;
    }

    public static void main(String[] args) {
        PrettyPrinter.println(new Solution().kClosest(new int[][]{{1,3},{-2,2}}, 1));
        PrettyPrinter.println(new Solution().kClosest(new int[][]{{3,3},{5,-1},{-2,4}}, 2));
    }

}
