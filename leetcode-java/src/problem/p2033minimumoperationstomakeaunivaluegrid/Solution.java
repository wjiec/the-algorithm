package problem.p2033minimumoperationstomakeaunivaluegrid;

/**
 * 2033. Minimum Operations to Make a Uni-Value Grid
 *
 * https://leetcode.cn/problems/minimum-operations-to-make-a-uni-value-grid/
 *
 * You are given a 2D integer grid of size m x n and an integer x.
 * In one operation, you can add x to or subtract x from any element in the grid.
 *
 * A uni-value grid is a grid where all the elements of it are equal.
 *
 * Return the minimum number of operations to make the grid uni-value.
 * If it is not possible, return -1.
 */

public class Solution {

    public int minOperations(int[][] grid, int x) {
        int sum = 0; boolean even = x % 2 == 0;
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        for (var row : grid) {
            for (var v : row) {
                sum += v;
                if (even && v % 2 == 1) return -1;
                min = Math.min(min, v);
                max = Math.max(max, v);
            }
        }

        int tot = grid.length * grid[0].length, ans = Integer.MAX_VALUE;
        for (int i = min; i <= max; i += x) {
            ans = Math.min(ans, Math.abs(sum - tot * i) / x);
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().minOperations(new int[][]{{2,4},{6,8}}, 2) == 4;
        assert new Solution().minOperations(new int[][]{{1,5},{2,3}}, 1) == 5;
        assert new Solution().minOperations(new int[][]{{1,2},{3,4}}, 2) == -1;
    }

}
