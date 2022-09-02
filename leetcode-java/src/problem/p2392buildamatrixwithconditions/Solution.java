package problem.p2392buildamatrixwithconditions;

import ability.Ability;
import ability.Graph;
import common.PrettyPrinter;

/**
 * 2392. Build a Matrix With Conditions
 *
 * https://leetcode.cn/problems/build-a-matrix-with-conditions/
 *
 * You are given a positive integer k. You are also given:
 *
 * a 2D integer array rowConditions of size n where rowConditions[i] = [abovei, belowi], and
 * a 2D integer array colConditions of size m where colConditions[i] = [lefti, righti].
 * The two arrays contain integers from 1 to k.
 *
 * You have to build a k x k matrix that contains each of the numbers from 1 to k exactly once.
 * The remaining cells should have the value 0.
 *
 * The matrix should also satisfy the following conditions:
 *
 * The number abovei should appear in a row that is strictly above the row at
 * which the number belowi appears for all i from 0 to n - 1.
 *
 * The number lefti should appear in a column that is strictly left of the column
 * at which the number righti appears for all i from 0 to m - 1.
 *
 * Return any matrix that satisfies the conditions. If no answer exists, return an empty matrix.
 */

public class Solution {

    public int[][] buildMatrix(int k, int[][] rowConditions, int[][] colConditions) {
        Graph.TopologicalSort rts = new Graph.TopologicalSort(k);
        for (var cond : rowConditions) rts.addEdge(cond[0] - 1, cond[1] - 1);

        Graph.TopologicalSort cts = new Graph.TopologicalSort(k);
        for (var cond : colConditions) cts.addEdge(cond[0] - 1, cond[1] - 1);

        int[] rs = rts.sort(), cs = cts.sort();
        if (rs.length == 0 || cs.length == 0) return new int[][]{};

        int[][] matrix = new int[k][k];
        for (int i = 0; i < k; i++) {
            matrix[Ability.indexOf(rs, i)][Ability.indexOf(cs, i)] = i + 1;
        }
        return matrix;
    }

    public static void main(String[] args) {
        PrettyPrinter.println(new Solution().buildMatrix(8, new int[][]{
            {1,2},{7,3},{4,3},{5,8},{7,8},{8,2},{5,8},{3,2},{1,3},{7,6},{4,3},{7,4},{4,8},{7,3},{7,5}
        }, new int[][]{
            {5,7},{2,7},{4,3},{6,7},{4,3},{2,3},{6,2}
        }));

        PrettyPrinter.println(new Solution().buildMatrix(3, new int[][]{{1,2},{3,2}}, new int[][]{{2,1},{3,2}}));
        PrettyPrinter.println(new Solution().buildMatrix(3, new int[][]{{1,2},{2,3},{3,1},{2,3}}, new int[][]{{2,1}}));
    }

}
