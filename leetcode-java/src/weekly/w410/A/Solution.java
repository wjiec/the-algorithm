package weekly.w410.A;

import java.util.List;

/**
 * 3248. Snake in Matrix
 *
 * https://leetcode.cn/contest/weekly-contest-410/problems/snake-in-matrix/
 *
 * There is a snake in an n x n matrix grid and can move in four possible directions.
 *
 * Each cell in the grid is identified by the position: grid[i][j] = (i * n) + j.
 *
 * The snake starts at cell 0 and follows a sequence of commands.
 *
 * You are given an integer n representing the size of the grid and an array of strings
 * commands where each command[i] is either "UP", "RIGHT", "DOWN", and "LEFT".
 *
 * It's guaranteed that the snake will remain within the grid boundaries throughout its movement.
 *
 * Return the position of the final cell where the snake ends up after executing commands.
 */

public class Solution {

    public int finalPositionOfSnake(int n, List<String> commands) {
        int x = 0, y = 0;
        for (var command : commands) {
            switch (command) {
                case "UP" -> x--;
                case "DOWN" -> x++;
                case "RIGHT" -> y++;
                case "LEFT" -> y--;
            }
        }
        return x * n + y;
    }

    public static void main(String[] args) {
    }

}
