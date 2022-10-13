package problem.p2120executionofallsuffixinstructionsstayinginagrid;

import common.Checker;

/**
 * 2120. Execution of All Suffix Instructions Staying in a Grid
 *
 * https://leetcode.cn/problems/execution-of-all-suffix-instructions-staying-in-a-grid/
 *
 * There is an n x n grid, with the top-left cell at (0, 0) and the bottom-right cell at (n - 1, n - 1).
 * You are given the integer n and an integer array startPos where startPos = [startrow, startcol] indicates
 * that a robot is initially at cell (startrow, startcol).
 *
 * You are also given a 0-indexed string s of length m where s[i] is the ith instruction for the
 * robot: 'L' (move left), 'R' (move right), 'U' (move up), and 'D' (move down).
 *
 * The robot can begin executing from any ith instruction in s. It executes the instructions one by one towards
 * the end of s but it stops if either of these conditions is met:
 *
 * The next instruction will move the robot off the grid.
 * There are no more instructions left to execute.
 * Return an array answer of length m where answer[i] is the number of instructions the robot can execute
 * if the robot begins executing from the ith instruction in s.
 */

public class Solution {

    public int[] executeInstructions(int n, int[] startPos, String s) {
        char[] chars = s.toCharArray();
        int[] ans = new int[s.length()];
        for (int i = 0; i < chars.length; i++) {
            ans[i] = execute(n, startPos[0], startPos[1], chars, i);
        }
        return ans;
    }

    private int execute(int n, int x, int y, char[] chars, int i) {
        int ans = 0;
        for (; i < chars.length; i++, ans++) {
            switch (chars[i]) {
                case 'L' -> y--;
                case 'R' -> y++;
                case 'U' -> x--;
                case 'D' -> x++;
            }

            if (!(x >= 0 && x < n && y >= 0 && y < n)) break;
        }
        return ans;
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().executeInstructions(3, new int[]{0, 1}, "RRDDLU"), new int[]{1,5,4,3,1,0});
        assert Checker.check(new Solution().executeInstructions(2, new int[]{1, 1}, "LURD"), new int[]{4,1,0,0});
        assert Checker.check(new Solution().executeInstructions(1, new int[]{0, 0}, "LRUD"), new int[]{0,0,0,0});
    }

}
