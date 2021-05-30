package problem.p657robotreturntoorigin;

/**
 * 657. Robot Return to Origin
 *
 * https://leetcode-cn.com/problems/robot-return-to-origin/
 *
 * There is a robot starting at position (0, 0), the origin, on a 2D plane.
 * Given a sequence of its moves, judge if this robot ends up at (0, 0) after it completes its moves.
 *
 * The move sequence is represented by a string, and the character moves[i] represents its ith move.
 * Valid moves are R (right), L (left), U (up), and D (down).
 *
 * If the robot returns to the origin after it finishes all of its moves, return true. Otherwise, return false.
 *
 * Note: The way that the robot is "facing" is irrelevant.
 * "R" will always make the robot move to the right once,
 * "L" will always make it move left, etc. Also,
 * assume that the magnitude of the robot's movement is the same for each move.
 */

public class Solution {

    public boolean judgeCircle(String moves) {
        int x = 0, y = 0;
        for (int i = 0, n = moves.length(); i < n; i++) {
            switch (moves.charAt(i)) {
                case 'U': y++; break;
                case 'D': y--; break;
                case 'L': x++; break;
                case 'R': x--; break;
            }
        }
        return x == 0 && y == 0;
    }

    public static void main(String[] args) {
        assert new Solution().judgeCircle("UD");
        assert !new Solution().judgeCircle("LL");
        assert !new Solution().judgeCircle("RRDD");
        assert !new Solution().judgeCircle("LDRRLRUULR");
    }

}
