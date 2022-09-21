package problem.p353designsnakegame;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 353. Design Snake Game
 *
 * https://leetcode.cn/problems/design-snake-game/
 *
 * Design a Snake game that is played on a device with screen size height x width.
 * Play the game online if you are not familiar with the game.
 *
 * The snake is initially positioned at the top left corner (0, 0) with a length of 1 unit.
 *
 * You are given an array food where food[i] = (ri, ci) is the row and column position of a piece of food that
 * the snake can eat. When a snake eats a piece of food, its length and the game's score both increase by 1.
 *
 * Each piece of food appears one by one on the screen, meaning the second piece of food will not appear
 * until the snake eats the first piece of food.
 *
 * When a piece of food appears on the screen, it is guaranteed that it
 * will not appear on a block occupied by the snake.
 *
 * The game is over if the snake goes out of bounds (hits a wall) or if its head occupies a space that
 * its body occupies after moving (i.e. a snake of length 4 cannot run into itself).
 *
 * Implement the SnakeGame class:
 *
 * SnakeGame(int width, int height, int[][] food) Initializes the object with a screen of
 * size height x width and the positions of the food.
 * int move(String direction) Returns the score of the game after applying one direction
 * move by the snake. If the game is over, return -1.
 */

public class Solution {

    private static class SnakeGame {
        private int index = 0;
        private int x = 0, y = 0;
        private final int[][] foods;
        private final int width, height;
        private final Queue<int[]> bodies = new ArrayDeque<>();
        public SnakeGame(int width, int height, int[][] food) {
            this.width = width; this.height = height; this.foods = food;
        }

        public int move(String direction) {
            switch (direction.charAt(0)) {
                case 'U' -> { return move(x, y - 1); }
                case 'D' -> { return move(x, y + 1); }
                case 'L' -> { return move(x - 1, y); }
                case 'R' -> { return move(x + 1, y); }
            }
            return bodies.size();
        }

        private int move(int dx, int dy) {
            if (dx < 0 || dx >= width || dy < 0 || dy >= height) return -1;
            if (index < foods.length && dx == foods[index][0] && dy == foods[index][1]) {
            }

            x = dx; y = dy;
            return bodies.size();
        }
    }

    public static void main(String[] args) {
        SnakeGame snakeGame = new SnakeGame(3, 2, new int[][]{{1, 2}, {0, 1}});
        assert snakeGame.move("R") == 0;
        assert snakeGame.move("D") == 0;
        assert snakeGame.move("R") == 1;
        assert snakeGame.move("U") == 1;
        assert snakeGame.move("L") == 2;
        assert snakeGame.move("U") == -1;
    }

}
