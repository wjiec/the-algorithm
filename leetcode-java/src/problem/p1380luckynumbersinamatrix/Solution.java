package problem.p1380luckynumbersinamatrix;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 1380. Lucky Numbers in a Matrix
 *
 * https://leetcode-cn.com/problems/lucky-numbers-in-a-matrix/
 *
 * Given a m * n matrix of distinct numbers, return all lucky numbers in the matrix in any order.
 *
 * A lucky number is an element of the matrix such that
 * it is the minimum element in its row and maximum in its column.
 */

public class Solution {

    public List<Integer> luckyNumbers (int[][] matrix) {
        Set<Integer> set = new HashSet<>();
        for (int[] row : matrix) {
            int min = Integer.MAX_VALUE;
            for (var n : row) {
                min = Math.min(min, n);
            }
            set.add(min);
        }

        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < matrix[0].length; i++) {
            int max = 0;
            for (int[] row : matrix) {
                max = Math.max(max, row[i]);
            }
            if (set.contains(max)) ans.add(max);
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().luckyNumbers(new int[][]{
            {3,7,8},
            {9,11,13},
            {15,16,17}
        }));

        System.out.println(new Solution().luckyNumbers(new int[][]{
            {1,10,4,2},
            {9,3,8,7},
            {15,16,17,12}
        }));

        System.out.println(new Solution().luckyNumbers(new int[][]{
            {7,8},
            {1,2}
        }));
    }

}
