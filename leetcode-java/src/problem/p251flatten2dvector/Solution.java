package problem.p251flatten2dvector;

/**
 * 251. Flatten 2D Vector
 *
 * https://leetcode-cn.com/problems/flatten-2d-vector/
 *
 * Design an iterator to flatten a 2D vector. It should support the next and hasNext operations.
 *
 * Implement the Vector2D class:
 *
 * Vector2D(int[][] vec) initializes the object with the 2D vector vec.
 *
 * next() returns the next element from the 2D vector and moves the pointer one step forward.
 * You may assume that all the calls to next are valid.
 *
 * hasNext() returns true if there are still some elements in the vector, and false otherwise.
 */

public class Solution {

    private static class Vector2D {
        private final int[][] vector;
        private int outside = 0, inside = 0;
        public Vector2D(int[][] vec) { vector = vec; }
        public int next() {
            if (hasNext()) {
                return vector[outside][inside++];
            }
            return -1;
        }
        public boolean hasNext() {
            while (outside < vector.length) {
                if (inside < vector[outside].length) {
                    return true;
                }

                outside++; inside = 0;
            }
            return false;
        }
    }

    public static void main(String[] args) {
        Vector2D iterator = new Vector2D(new int[][]{{1,2},{3},{4}});

        assert iterator.next() == 1;
        assert iterator.hasNext();
        assert iterator.hasNext();
        assert iterator.next() == 2;
        assert iterator.hasNext();
        assert iterator.hasNext();
        assert iterator.next() == 3;
        assert iterator.hasNext();
        assert iterator.hasNext();
        assert iterator.next() == 4;
        assert !iterator.hasNext();

        iterator = new Vector2D(new int[][]{});
        assert !iterator.hasNext();

        iterator = new Vector2D(new int[][]{{}});
        assert !iterator.hasNext();

        iterator = new Vector2D(new int[][]{{}, {3}});
        assert iterator.hasNext();
        assert iterator.next() == 3;
        assert !iterator.hasNext();
    }

}
