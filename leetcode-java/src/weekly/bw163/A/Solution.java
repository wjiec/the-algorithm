package weekly.bw163.A;

/**
 * Q1. Minimum Sensors to Cover Grid
 *
 * https://leetcode.cn/contest/biweekly-contest-163/problems/minimum-sensors-to-cover-grid/
 *
 * You are given n × m grid and an integer k.
 *
 * A sensor placed on cell (r, c) covers all cells whose Chebyshev distance from (r, c) is at most k.
 *
 * The Chebyshev distance between two cells (r1, c1) and (r2, c2) is max(|r1 − r2|,|c1 − c2|).
 *
 * Your task is to return the minimum number of sensors required to cover every cell of the grid.
 */

public class Solution {

    public int minSensors(int n, int m, int k) {
        // 对于 (0, 0) 至少要在 (k, k) 放一个传感器
        //  - 也就是一个传感器可以覆盖 [2k + 1, 2k + 1] 个正方形
        return ((n + 2 * k) / (2 * k + 1)) * ((m + 2 * k) / (2 * k + 1));
    }

    public static void main(String[] args) {
        assert new Solution().minSensors(54, 19, 9) == 3;
        assert new Solution().minSensors(5, 5, 1) == 4;
        assert new Solution().minSensors(2, 2, 2) == 1;
    }

}
