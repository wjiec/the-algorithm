package lcci.s16.p4tictactoelcci;

/**
 * 面试题 16.04. 井字游戏
 *
 * https://leetcode.cn/problems/tic-tac-toe-lcci/
 *
 * 设计一个算法，判断玩家是否赢了井字游戏。输入是一个 N x N 的数组棋盘，由字符" "，"X"和"O"组成，其中字符" "代表一个空位。
 *
 * 以下是井字游戏的规则：
 *
 * 玩家轮流将字符放入空位（" "）中。
 * 第一个玩家总是放字符"O"，且第二个玩家总是放字符"X"。
 * "X"和"O"只允许放置在空位中，不允许对已放有字符的位置进行填充。
 * 当有N个相同（且非空）的字符填充任何行、列或对角线时，游戏结束，对应该字符的玩家获胜。
 * 当所有位置非空时，也算为游戏结束。
 * 如果游戏结束，玩家不允许再放置字符。
 * 如果游戏存在获胜者，就返回该游戏的获胜者使用的字符（"X"或"O"）；如果游戏以平局结束，则返回 "Draw"；
 * 如果仍会有行动（游戏未结束），则返回 "Pending"。
 */

public class Solution {

    public String tictactoe(String[] board) {
        char[][] grid = new char[board.length][];
        for (int i = 0; i < board.length; i++) {
            grid[i] = board[i].toCharArray();
        }

        char principal = check(grid, 0, 0, 1, 1);
        if (principal != 0 && principal != ' ') return String.valueOf(principal);

        char counter = check(grid, 0, grid.length - 1, 1, -1);
        if (counter != 0 && counter != ' ') return String.valueOf(counter);

        boolean blank = principal == ' ' || counter == ' ';
        for (int i = 0; i < grid.length; i++) {
            char horizontal = check(grid, i, 0, 0, 1);
            if (horizontal != 0 && horizontal != ' ') return String.valueOf(horizontal);
            blank = blank || horizontal == ' ';

            char vertical = check(grid, 0, i, 1, 0);
            if (vertical != 0 && vertical != ' ') return String.valueOf(vertical);
            blank = blank || vertical == ' ';
        }

        return blank ? "Pending" : "Draw";
    }

    private char check(char[][] grid, int x, int y, int dx, int dy) {
        char curr = grid[x][y];
        boolean blank = false, equals = true;
        while (x >= 0 && x < grid.length && y >= 0 && y < grid.length) {
            if (grid[x][y] == ' ') blank = true;
            if (grid[x][y] != curr) equals = false;
            x += dx; y += dy;
        }
        return blank ? ' ' : (equals ? curr : 0);
    }

    public static void main(String[] args) {
        assert new Solution().tictactoe(new String[]{"O X"," XO","X O"}).equals("X");
        assert new Solution().tictactoe(new String[]{"OOX","XXO","OXO"}).equals("Draw");
        assert new Solution().tictactoe(new String[]{"OOX","XXO","OX "}).equals("Pending");
    }

}
