package weekly.bw146.C;

import java.util.*;

/**
 * 3394. Check if Grid can be Cut into Sections
 *
 * https://leetcode.cn/contest/biweekly-contest-146/problems/check-if-grid-can-be-cut-into-sections/
 *
 * You are given an integer n representing the dimensions of an n x n grid, with the origin at
 * the bottom-left corner of the grid. You are also given a 2D array of coordinates rectangles,
 * where rectangles[i] is in the form [startx, starty, endx, endy], representing a rectangle on the grid.
 *
 * Each rectangle is defined as follows:
 *
 * (startx, starty): The bottom-left corner of the rectangle.
 * (endx, endy): The top-right corner of the rectangle.
 *
 * Note that the rectangles do not overlap. Your task is to determine if it is possible to make
 * either two horizontal or two vertical cuts on the grid such that:
 *
 * Each of the three resulting sections formed by the cuts contains at least one rectangle.
 * Every rectangle belongs to exactly one section.
 *
 * Return true if such cuts can be made; otherwise, return false.
 */

public class Solution {

    public boolean checkValidCuts(int n, int[][] rectangles) {
        // 是否存在两条切线, 使得切线不穿过任何矩形内部且切线不为边界线
        return check(rectangles, 0, 2) || check(rectangles, 1, 3);
    }

    private boolean check(int[][] rectangles, int a, int b) {
        Set<Integer> pts = new HashSet<>();
        for (var rect : rectangles) { pts.add(rect[a]); pts.add(rect[b]); }

        Queue<Integer> add = new ArrayDeque<>();
        Arrays.sort(rectangles, Comparator.comparingInt(v -> v[a]));
        for (var rect : rectangles) add.add(rect[a]);

        Queue<Integer> rem = new ArrayDeque<>();
        Arrays.sort(rectangles, Comparator.comparingInt(v -> v[b]));
        for (var rect : rectangles) rem.add(rect[b]);

        List<Integer> all = new ArrayList<>(pts);
        all.sort(Integer::compare);

        int ans = 0, cnt = 0;
        for (var elem : all) {
            while (!rem.isEmpty() && rem.peek() <= elem) { cnt--; rem.remove(); }
            if (cnt == 0) if (++ans >= 4) return true;
            while (!add.isEmpty() && add.peek() <= elem) { cnt++; add.remove(); }
        }

        return false;
    }

    public static void main(String[] args) {
        assert new Solution().checkValidCuts(5, new int[][]{{1,0,5,2},{0,2,2,4},{3,2,5,3},{0,4,4,5}});
        assert new Solution().checkValidCuts(5, new int[][]{{0,0,1,1},{2,0,3,4},{0,2,2,3},{3,0,4,3}});
        assert !new Solution().checkValidCuts(5, new int[][]{{0,2,2,4},{1,0,3,2},{2,2,3,4},{3,0,4,2},{3,2,4,4}});
    }

}
