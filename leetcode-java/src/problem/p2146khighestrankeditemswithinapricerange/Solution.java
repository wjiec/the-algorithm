package problem.p2146khighestrankeditemswithinapricerange;

import common.Checker;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * 2146. K Highest Ranked Items Within a Price Range
 *
 * https://leetcode.cn/problems/k-highest-ranked-items-within-a-price-range/
 *
 * You are given a 0-indexed 2D integer array grid of size m x n that represents a map of the items in a shop.
 * The integers in the grid represent the following:
 *
 * 0 represents a wall that you cannot pass through.
 * 1 represents an empty cell that you can freely move to and from.
 * All other positive integers represent the price of an item in that cell.
 * You may also freely move to and from these item cells.
 * It takes 1 step to travel between adjacent grid cells.
 *
 * You are also given integer arrays pricing and start where pricing = [low, high] and start = [row, col]
 * indicates that you start at the position (row, col) and are interested only in items with a price
 * in the range of [low, high] (inclusive). You are further given an integer k.
 *
 * You are interested in the positions of the k highest-ranked items whose prices are within the given price range.
 * The rank is determined by the first of these criteria that is different:
 *
 * Distance, defined as the length of the shortest path from the start (shorter distance has a higher rank).
 * Price (lower price has a higher rank, but it must be in the price range).
 * The row number (smaller row number has a higher rank).
 * The column number (smaller column number has a higher rank).
 * Return the k highest-ranked items within the price range sorted by their rank (highest to lowest).
 * If there are fewer than k reachable items within the price range, return all of them.
 */

public class Solution {

    private final int[][] dirs = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public List<List<Integer>> highestRankedKItems(int[][] grid, int[] pricing, int[] start, int k) {
        int x = start[0], y = start[1];
        int a = pricing[0], b = pricing[1];
        int m = grid.length, n = grid[0].length;

        // [x, y, step, price]
        List<int[]> items = new ArrayList<>();
        if (grid[x][y] >= a && grid[x][y] <= b) {
            items.add(new int[]{x, y, 0, grid[x][y]});
        }

        boolean[][] visited = new boolean[m][n];
        visited[x][y] = true;

        // [x, y, step]
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{x, y, 0});

        while (!queue.isEmpty()) {
            int[] curr = queue.remove();
            int nextStep = curr[2] + 1;
            for (var dir : dirs) {
                int dx = curr[0] + dir[0], dy = curr[1] + dir[1];
                if (dx >= 0 && dx < m && dy >= 0 && dy < n && !visited[dx][dy]) {
                    visited[dx][dy] = true;
                    int nextPrice = grid[dx][dy];
                    if (nextPrice > 0) {
                        queue.add(new int[]{dx, dy, nextStep});
                        if (nextPrice >= a && nextPrice <= b) {
                            items.add(new int[]{dx, dy, nextStep, grid[dx][dy]});
                        }
                    }
                }
            }
        }

        // [x, y, step, price]
        items.sort((c, d) -> {
            if (c[2] != d[2]) return c[2] - d[2];
            if (c[3] != d[3]) return c[3] - d[3];
            if (c[0] != d[0]) return c[0] - d[0];
            return c[1] - d[1];
        });

        int len = Math.min(k, items.size());
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            ans.add(List.of(items.get(i)[0], items.get(i)[1]));
        }
        return ans;
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().highestRankedKItems(new int[][]{
                {0,2,0}
            }, new int[]{2,2}, new int[]{0,1}, 1),
            List.of(List.of(0,1))
        );

        assert Checker.check(new Solution().highestRankedKItems(new int[][]{
                {1,2,0,1},{1,3,0,1},{0,2,5,1}
            }, new int[]{2,5}, new int[]{0,0}, 3),
            List.of(List.of(0,1),List.of(1,1),List.of(2,1))
        );

        assert Checker.check(new Solution().highestRankedKItems(new int[][]{
                {1,2,0,1},{1,3,3,1},{0,2,5,1}
            }, new int[]{2,3}, new int[]{2,3}, 2),
            List.of(List.of(2,1),List.of(1,2))
        );

        assert Checker.check(new Solution().highestRankedKItems(new int[][]{
                {1,1,1},{0,0,1},{2,3,4}
            }, new int[]{2,3}, new int[]{0,0}, 3),
            List.of(List.of(2,1),List.of(2,0))
        );
    }

}
