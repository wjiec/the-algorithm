package weekly.w362.B;

/**
 * 2849. Determine if a Cell Is Reachable at a Given Time
 *
 * https://leetcode.cn/contest/weekly-contest-362/problems/determine-if-a-cell-is-reachable-at-a-given-time/
 *
 * You are given four integers sx, sy, fx, fy, and a non-negative integer t.
 *
 * In an infinite 2D grid, you start at the cell (sx, sy). Each second, you must move to any of its adjacent cells.
 *
 * Return true if you can reach cell (fx, fy) after exactly t seconds, or false otherwise.
 *
 * A cell's adjacent cells are the 8 cells around it that share at least one corner with it.
 *
 * You can visit the same cell several times.
 */

public class Solution {

    public boolean isReachableAtTime(int sx, int sy, int fx, int fy, int t) {
        if (sx == fx && sy == fy) return t != 1;

        return Math.max(Math.abs(sx - fx), Math.abs(sy - fy)) <= t;
    }

    public static void main(String[] args) {
        assert new Solution().isReachableAtTime(1, 1, 2, 1, 2);
        assert !new Solution().isReachableAtTime(1, 2, 1, 2, 1);

        assert new Solution().isReachableAtTime(2, 4, 7, 7, 6);
        assert !new Solution().isReachableAtTime(3, 1, 7, 3, 3);
        assert new Solution().isReachableAtTime(1, 1, 1, 1, 3);
        assert new Solution().isReachableAtTime(1, 1, 1, 5, 8);
    }

}
