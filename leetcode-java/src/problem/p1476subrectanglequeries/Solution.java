package problem.p1476subrectanglequeries;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 1476. Subrectangle Queries
 *
 * https://leetcode.cn/problems/subrectangle-queries/
 *
 * Implement the class SubrectangleQueries which receives a rows x cols rectangle as a matrix of
 * integers in the constructor and supports two methods:
 *
 * 1. updateSubrectangle(int row1, int col1, int row2, int col2, int newValue)
 *
 * Updates all values with newValue in the subrectangle whose upper left coordinate is (row1,col1)
 * and bottom right coordinate is (row2,col2).
 *
 * 2. getValue(int row, int col)
 *
 * Returns the current value of the coordinate (row,col) from the rectangle.
 */

public class Solution {

    private static class SubrectangleQueries {
        private final int[][] rectangle;
        private final Deque<int[]> record = new ArrayDeque<>();
        public SubrectangleQueries(int[][] rectangle) { this.rectangle = rectangle; }
        public void updateSubrectangle(int row1, int col1, int row2, int col2, int newValue) {
            record.addFirst(new int[]{row1, col1, row2, col2, newValue});
        }

        public int getValue(int row, int col) {
            for (var rect : record) {
                int lr = rect[0], lc = rect[1], rr = rect[2], rc = rect[3], v = rect[4];
                if (lr <= row && row <= rr && lc <= col && col <= rc) return v;
            }
            return rectangle[row][col];
        }
    }

    public static void main(String[] args) {
        SubrectangleQueries query = new SubrectangleQueries(new int[][]{
            {1,2,1},{4,3,4},{3,2,1},{1,1,1}
        });
        assert query.getValue(0, 2) == 1;
        query.updateSubrectangle(0, 0, 3, 2, 5);
        assert query.getValue(0, 2) == 5;
        assert query.getValue(3, 1) == 5;
        query.updateSubrectangle(3, 0, 3, 2, 10);
        assert query.getValue(3, 1) == 10;
        assert query.getValue(0, 2) == 5;

        query = new SubrectangleQueries(new int[][]{{1,1,1},{2,2,2},{3,3,3}});
        assert query.getValue(0, 0) == 1;
        query.updateSubrectangle(0, 0, 2, 2, 100);
        assert query.getValue(0, 0) == 100;
        assert query.getValue(2, 2) == 100;
        query.updateSubrectangle(1, 1, 2, 2, 20);
        assert query.getValue(2, 2) == 20;
    }

}
