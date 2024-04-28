package weekly.bw129.A;

/**
 * 100286. Make a Square with the Same Color
 *
 * https://leetcode.cn/contest/biweekly-contest-129/problems/make-a-square-with-the-same-color/
 *
 * You are given a 2D matrix grid of size 3 x 3 consisting only of characters 'B' and 'W'.
 * Character 'W' represents the white color, and character 'B' represents the black color.
 *
 * Your task is to change the color of at most one cell so that the matrix has a 2 x 2 square
 * where all cells are of the same color.
 *
 * Return true if it is possible to create a 2 x 2 square of the same color, otherwise, return false.
 */

public class Solution {

    public boolean canMakeSquare(char[][] grid) {
        for (int i = 0; i <= 1; i++) {
            for (int j = 0; j <= 1; j++) {
                int black = 0, white = 0;
                for (int a = 0; a < 2; a++) {
                    for (int b = 0; b < 2; b++) {
                        switch (grid[i + a][j + b]) {
                            case 'B' -> black++;
                            case 'W' -> white++;
                        }
                    }
                }
                if (black == 0 || white == 0) return true;
                if (black == 1 || white == 1) return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
    }

}
