package problem.p1349maximumstudentstakingexam;

import java.util.Arrays;

/**
 * 1349. Maximum Students Taking Exam
 *
 * https://leetcode.cn/problems/maximum-students-taking-exam/
 *
 * Given a m * n matrix seats  that represent seats distributions in a classroom.
 *
 * If a seat is broken, it is denoted by '#' character otherwise it is denoted by a '.' character.
 *
 * Students can see the answers of those sitting next to the left, right, upper left and upper right,
 * but he cannot see the answers of the student sitting directly in front or behind him.
 *
 * Return the maximum number of students that can take the exam together without any cheating being possible.
 *
 * Students must be placed in seats in good condition.
 */

public class Solution {

    private final int[] broken = new int[8];

    public int maxStudents(char[][] seats) {
        m = seats.length; n = seats[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (seats[i][j] == '#') {
                    broken[i] |= 1 << j;
                }
            }
        }
        return maxStudents( 0, 0);
    }

    private int m = 0, n = 0;
    private final int[][] memo = new int[8][1 << 8];
    { for (var row : memo) Arrays.fill(row, -1); }

    private int maxStudents(int i, int prev) {
        if (i == m) return 0;
        if (memo[i][prev] == -1) {
            int ans = 0, mask = 1 << n;
            for (int b = 0; b < mask; b++) {
                if ((b & broken[i]) != 0) continue;
                if ((b & (b >> 1)) != 0) continue;
                if ((b & (b << 1)) != 0) continue;
                if ((b & (prev >> 1)) != 0) continue;
                if ((b & (prev << 1)) != 0) continue;
                ans = Math.max(ans, Integer.bitCount(b) + maxStudents(i + 1, b));
            }
            memo[i][prev] = ans;
        }
        return memo[i][prev];
    }

    public static void main(String[] args) {
        assert new Solution().maxStudents(new char[][]{
            {'#','.','#','#','.','#'},
            {'.','#','#','#','#','.'},
            {'#','.','#','#','.','#'}
        }) == 4;
    }

}
