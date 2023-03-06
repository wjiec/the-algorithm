package weekly.bw99.B;

/**
 * 2579. Count Total Number of Colored Cells
 *
 * https://leetcode.cn/contest/biweekly-contest-99/problems/count-total-number-of-colored-cells/
 *
 * There exists an infinitely large two-dimensional grid of uncolored unit cells.
 * You are given a positive integer n, indicating that you must do the following routine for n minutes:
 *
 * At the first minute, color any arbitrary unit cell blue.
 * Every minute thereafter, color blue every uncolored cell that touches a blue cell.
 */

public class Solution {

    public long coloredCells(int n) {
        long ans = 1;
        for (long i = 2; i <= n; i++) {
            ans += i * 2 + (i - 2) * 2;
        }
        return ans;
    }

    public static void main(String[] args) {
    }

}
