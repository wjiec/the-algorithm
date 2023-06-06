package weekly.w348.C;

import java.util.HashSet;
import java.util.Set;

/**
 * 2718. Sum of Matrix After Queries
 *
 * https://leetcode.cn/contest/weekly-contest-348/problems/sum-of-matrix-after-queries/
 *
 * You are given an integer n and a 0-indexed 2D array queries where queries[i] = [typei, indexi, vali].
 *
 * Initially, there is a 0-indexed n x n matrix filled with 0's.
 * For each query, you must apply one of the following changes:
 *
 * if typei == 0, set the values in the row with indexi to vali, overwriting any previous values.
 * if typei == 1, set the values in the column with indexi to vali, overwriting any previous values.
 * Return the sum of integers in the matrix after all queries are applied.
 */

public class Solution {

    public long matrixSumQueries(int n, int[][] queries) {
        long ans = 0;
        Set<Integer>[] sets = new Set[]{new HashSet<Integer>(), new HashSet<Integer>()};
        for (int i = queries.length - 1; i >= 0; i--) {
            Set<Integer> curr = sets[queries[i][0]];
            if (curr.add(queries[i][1])) {
                ans += (long) queries[i][2] * (n - sets[queries[i][0] ^ 1].size());
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().matrixSumQueries(3, new int[][]{
            {0,0,1}, {1,2,2}, {0,2,3}, {1,0,4}
        }) == 23;
    }

}
