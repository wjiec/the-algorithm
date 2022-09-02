package problem.p1738findkthlargestxorcoordinatevalue;

import common.Tag;

import java.util.ArrayList;
import java.util.List;

/**
 * 1738. Find Kth Largest XOR Coordinate Value
 *
 * https://leetcode.cn/problems/find-kth-largest-xor-coordinate-value/
 *
 * You are given a 2D matrix of size m x n, consisting of non-negative integers. You are also given an integer k.
 *
 * The value of coordinate (a, b) of the matrix is the XOR of all matrix[i][j]
 * where 0 <= i <= a < m and 0 <= j <= b < n (0-indexed).
 *
 * Find the kth largest value (1-indexed) of all the coordinates of matrix.
 */

public class Solution {

    @Tag({"矩阵", "异或", "第K个"})
    public int kthLargestValue(int[][] matrix, int k) {
        int m = matrix.length, n = matrix[0].length;
        int[][] preSum = new int[m + 1][n + 1];
        List<Integer> sorted = new ArrayList<>();
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                preSum[i][j] = preSum[i - 1][j] ^ preSum[i][j - 1] ^ preSum[i - 1][j - 1] ^ matrix[i - 1][j - 1];
                sorted.add(preSum[i][j]);
            }
        }
        sorted.sort((a, b) -> b - a);
        return sorted.get(k - 1);
    }

    public static void main(String[] args) {
        assert new Solution().kthLargestValue(new int[][]{{5,2},{1,6}}, 1) == 7;
        assert new Solution().kthLargestValue(new int[][]{{5,2},{1,6}}, 2) == 5;
        assert new Solution().kthLargestValue(new int[][]{{5,2},{1,6}}, 3) == 4;
        assert new Solution().kthLargestValue(new int[][]{{5,2},{1,6}}, 4) == 0;
    }

}
