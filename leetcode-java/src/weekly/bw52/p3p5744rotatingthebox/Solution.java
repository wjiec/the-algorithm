package weekly.bw52.p3p5744rotatingthebox;

import java.util.Arrays;

/**
 * 5744. Rotating the Box
 *
 * https://leetcode-cn.com/contest/biweekly-contest-52/problems/rotating-the-box/
 *
 * You are given an m x n matrix of characters box representing a side-view of a box.
 * Each cell of the box is one of the following:
 *
 * A stone '#'
 * A stationary obstacle '*'
 * Empty '.'
 * The box is rotated 90 degrees clockwise, causing some of the stones to fall due to gravity.
 * Each stone falls down until it lands on an obstacle, another stone,
 * or the bottom of the box. Gravity does not affect the obstacles' positions,
 * and the inertia from the box's rotation does not affect the stones' horizontal positions.
 *
 * It is guaranteed that each stone in box rests on an obstacle, another stone, or the bottom of the box.
 *
 * Return an n x m matrix representing the box after the rotation described above.
 */

public class Solution {

    public char[][] rotateTheBox(char[][] box) {
        int line = box.length, column = box[0].length;
        for (int i = 0; i < line; i++) {
            int right = column - 1;
            for (int j = column - 1; j >= 0; j--) {
                if (box[i][j] == '*') {
                    right = j - 1;
                } else if (box[i][j] == '#') {
                    box[i][j] = '.';
                    box[i][right--] = '#';
                }
            }
        }

        char[][] rs = new char[column][line];
        for (int i = 0; i < line; i++) {
            for (int j = 0; j < column; j++) {
                rs[j][line - i - 1] = box[i][j];
            }
        }

        return rs;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.deepToString(new Solution().rotateTheBox(new char[][]{
            {'#','.','#'},
        })));
        System.out.println();
        System.out.println(Arrays.deepToString(new Solution().rotateTheBox(new char[][]{
            {'#','.','*','.'},
            {'#','#','*','.'},
        })));
        System.out.println();
        System.out.println(Arrays.deepToString(new Solution().rotateTheBox(new char[][]{
            {'#','#','*','.','*','.'},
            {'#','#','#','*','.','.'},
            {'#','#','#','.','#','.'},
        })));
    }

}
