package weekly.w413.A;

/**
 * 3274. Check if Two Chessboard Squares Have the Same Color
 *
 * https://leetcode.cn/contest/weekly-contest-413/problems/check-if-two-chessboard-squares-have-the-same-color/
 *
 * You are given two strings, coordinate1 and coordinate2, representing the
 * coordinates of a square on an 8 x 8 chessboard.
 *
 * Return true if these two squares have the same color and false otherwise.
 *
 * The coordinate will always represent a valid chessboard square. The coordinate
 * will always have the letter first (indicating its column), and the number second (indicating its row).
 */

public class Solution {

    public boolean checkTwoChessboards(String coordinate1, String coordinate2) {
        return ((coordinate1.charAt(0) - 'a' + coordinate1.charAt(1) - '1') % 2) == ((coordinate2.charAt(0) - 'a' + coordinate2.charAt(1) - '1') % 2);
    }

    public static void main(String[] args) {
    }

}
