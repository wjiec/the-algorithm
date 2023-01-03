package problem.p1074numberofsubmatricesthatsumtotarget;

import common.TODO;
import common.Tag;

import java.util.HashMap;
import java.util.Map;

/**
 * 1074. Number of Submatrices That Sum to Target
 *
 * https://leetcode.cn/problems/number-of-submatrices-that-sum-to-target/
 *
 * Given a matrix and a target, return the number of non-empty submatrices that sum to target.
 *
 * A submatrix x1, y1, x2, y2 is the set of all cells matrix[x][y]
 * with x1 <= x <= x2 and y1 <= y <= y2.
 *
 * Two submatrices (x1, y1, x2, y2) and (x1', y1', x2', y2') are different if they
 * have some coordinate that is different: for example, if x1 != x1'.
 */

@SuppressWarnings("DuplicatedCode")
public class Solution {

    @Tag("枚举二维矩阵的子矩阵和")
    @TODO(tips = "枚举子矩阵的上下边界并计算边界内列和等于目标的子数组数量")
    public int numSubmatrixSumTarget(int[][] matrix, int target) {
        int m = matrix.length, n = matrix[0].length, ans = 0;
        for (int top = 0; top < m; top++) {
            int[] colSum = new int[n];
            for (int bot = top; bot < m; bot++) {
                for (int j = 0; j < n; j++) {
                    colSum[j] += matrix[bot][j];
                }
                ans += subarraySum(colSum, target);
            }
        }
        return ans;
    }

    private int subarraySum(int[] sum, int target) {
        Map<Integer, Integer> map = new HashMap<>(); map.put(0, 1);
        int ans = 0, curr = 0;
        for (var v : sum) {
            curr += v;
            ans += map.getOrDefault(curr - target, 0);
            map.merge(curr, 1, Integer::sum);
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().numSubmatrixSumTarget(new int[][]{{0,1,0},{1,1,1},{0,1,0}}, 0) == 4;
        assert new Solution().numSubmatrixSumTarget(new int[][]{{1,-1},{-1,1}}, 0) == 5;
        assert new Solution().numSubmatrixSumTarget(new int[][]{{904}}, 0) == 0;
    }

}
