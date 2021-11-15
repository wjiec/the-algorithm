package problem.p1812determinecolorofachessboardsquare;

/**
 * 1812. Determine Color of a Chessboard Square
 *
 * https://leetcode-cn.com/problems/determine-color-of-a-chessboard-square/
 *
 * You are given coordinates, a string that represents the coordinates of a square of the chessboard.
 *
 * Below is a chessboard for your reference.
 */

public class Solution {

    public boolean squareIsWhite(String coordinates) {
        return (coordinates.charAt(0) - 'a' + 1) % 2 != (coordinates.charAt(1) - '0') % 2;
    }

    public static void main(String[] args) {
        assert !new Solution().squareIsWhite("a1");
        assert new Solution().squareIsWhite("h3");
        assert !new Solution().squareIsWhite("c7");
    }

}
