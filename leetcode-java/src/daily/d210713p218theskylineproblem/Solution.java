package daily.d210713p218theskylineproblem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 218. The Skyline Problem
 *
 * https://leetcode-cn.com/problems/the-skyline-problem/
 *
 * A city's skyline is the outer contour of the silhouette formed by all the buildings
 * in that city when viewed from a distance. Given the locations and heights of all the buildings,
 * return the skyline formed by these buildings collectively.
 *
 * The geometric information of each building is given in the array buildings where buildings[i] = [lefti, righti, heighti]:
 *
 * lefti is the x coordinate of the left edge of the ith building.
 * righti is the x coordinate of the right edge of the ith building.
 * heighti is the height of the ith building.
 * You may assume all buildings are perfect rectangles grounded on an absolutely flat surface at height 0.
 *
 * The skyline should be represented as a list of "key points" sorted by their x-coordinate in the form [[x1,y1],[x2,y2],...].
 * Each key point is the left endpoint of some horizontal segment in the skyline except the last point in the list,
 * which always has a y-coordinate 0 and is used to mark the skyline's termination where the rightmost building ends.
 * Any ground between the leftmost and rightmost buildings should be part of the skyline's contour.
 *
 * Note: There must be no consecutive horizontal lines of equal height in the output skyline.
 * For instance, [...,[2 3],[4 5],[7 5],[11 5],[12 7],...] is not acceptable;
 * the three lines of height 5 should be merged into one in the final output as such: [...,[2 3],[4 5],[12 7],...]
 */

public class Solution {

    private static class Coordinate {
        private final int x, y;
        public Coordinate(int x, int y) { this.x = x; this.y = y; }
    }

    // @TODO
    public List<List<Integer>> getSkyline(int[][] buildings) {
        List<Integer> boundaries = new ArrayList<>();
        for (var building : buildings) {
            boundaries.add(building[0]);
            boundaries.add(building[1]);
        }
        Collections.sort(boundaries);

        int l = buildings.length, idx = 0;
        List<List<Integer>> res = new ArrayList<>();
        PriorityQueue<Coordinate> queue = new PriorityQueue<>((a, b) -> b.y - a.y);

        for (var boundary : boundaries) {
            while (idx < l && buildings[idx][0] <= boundary) {
                queue.offer(new Coordinate(buildings[idx][1], buildings[idx][2]));
                idx++;
            }
            while (!queue.isEmpty() && queue.peek().x <= boundary) {
                queue.poll();
            }

            int h = queue.isEmpty() ? 0 : queue.peek().y;
            if (res.size() == 0 || h != res.get(res.size() - 1).get(1)) {
                res.add(List.of(boundary, h));
            }
        }
        return res;
    }

    public static void main(String[] args) {
        assert new Solution().getSkyline(new int[][]{
            {2,9,10}, {3,7,15}, {5,12,12}, {15,20,10}, {19,24,8}
        }).equals(List.of(
            List.of(2,10), List.of(3,15), List.of(7,12), List.of(12,0), List.of(15,10), List.of(20,8), List.of(24,0))
        );

        assert new Solution().getSkyline(new int[][]{
            {0,2,3}, {2,5,3}
        }).equals(List.of(List.of(0,3), List.of(5,0)));
    }

}
