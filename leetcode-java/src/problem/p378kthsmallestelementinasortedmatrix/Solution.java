package problem.p378kthsmallestelementinasortedmatrix;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 378. Kth Smallest Element in a Sorted Matrix
 *
 * https://leetcode-cn.com/problems/kth-smallest-element-in-a-sorted-matrix/
 *
 * Given an n x n matrix where each of the rows and columns is sorted in ascending order,
 * return the kth smallest element in the matrix.
 *
 * Note that it is the kth smallest element in the sorted order, not the kth distinct element.
 *
 * You must find a solution with a memory complexity better than O(n2).
 */

public class Solution {

    // @TODO
    public int kthSmallest(int[][] matrix, int k) {
        PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(v -> v[0]));
        for (int i = 0; i < matrix.length; i++) {
            queue.add(new int[]{matrix[i][0], i, 0});
        }

        while (--k > 0) {
            int[] curr = queue.remove();
            int x = curr[1], y = curr[2];
            if (y + 1 != matrix.length) {
                queue.add(new int[]{matrix[x][y + 1], x, y + 1});
            }
        }
        return queue.remove()[0];
    }

    public static void main(String[] args) {
        assert new Solution().kthSmallest(new int[][]{
            { 1, 5, 9},
            {10,11,13},
            {12,13,15}
        }, 1) == 1;

        assert new Solution().kthSmallest(new int[][]{
            { 1, 5, 9},
            {10,11,13},
            {12,13,15}
        }, 2) == 5;

        assert new Solution().kthSmallest(new int[][]{
            { 1, 5, 9},
            {10,11,13},
            {12,13,15}
        }, 3) == 9;

        assert new Solution().kthSmallest(new int[][]{
            { 1, 5, 9},
            {10,11,13},
            {12,13,15}
        }, 4) == 10;

        assert new Solution().kthSmallest(new int[][]{
            { 1, 5, 9},
            {10,11,13},
            {12,13,15}
        }, 5) == 11;

        assert new Solution().kthSmallest(new int[][]{
            { 1, 5, 9},
            {10,11,13},
            {12,13,15}
        }, 6) == 12;

        assert new Solution().kthSmallest(new int[][]{
            { 1, 5, 9},
            {10,11,13},
            {12,13,15}
        }, 7) == 13;

        assert new Solution().kthSmallest(new int[][]{
            { 1, 5, 9},
            {10,11,13},
            {12,13,15}
        }, 8) == 13;

        assert new Solution().kthSmallest(new int[][]{{-5}}, 1) == -5;
    }

}
