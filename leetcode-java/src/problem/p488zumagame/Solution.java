package problem.p488zumagame;

import java.util.*;

/**
 * 488. Zuma Game
 *
 * https://leetcode.cn/problems/zuma-game/
 *
 * You are playing a variation of the game Zuma.
 *
 * In this variation of Zuma, there is a single row of colored balls on a board, where
 * each ball can be colored red 'R', yellow 'Y', blue 'B', green 'G', or white 'W'.
 *
 * You also have several colored balls in your hand.
 *
 * Your goal is to clear all of the balls from the board. On each turn:
 *
 * Pick any ball from your hand and insert it in between two balls
 * in the row or on either end of the row.
 *
 * If there is a group of three or more consecutive balls of the same color, remove
 * the group of balls from the board.
 *
 * If this removal causes more groups of three or more of the same color to form, then
 * continue removing each group until there are none left.
 *
 * If there are no more balls on the board, then you win the game.
 *
 * Repeat this process until you either win or do not have any more balls in your hand.
 *
 * Given a string board, representing the row of balls on the board, and a string hand,
 * representing the balls in your hand, return the minimum number of balls you have to
 * insert to clear all the balls from the board. If you cannot clear all the balls
 * from the board using the balls in your hand, return -1.
 */

public class Solution {


    private record State(List<Group> gps, int[] balls, int step) {}

    private static class Group {
        private int n = 0;
        private final char c;
        public Group(char c) { this.c = c; }
        public Group(char c, int n) { this.c = c; this.n = n; }
        @Override public String toString() { return String.format("<%c, %d>", c, n); }
    }

    public int findMinStep(String board, String hand) {
        int[] cnt = new int[26];
        for (var c : hand.toCharArray()) cnt[c - 'A']++;

        Deque<Group> groups = new ArrayDeque<>();
        for (var c : board.toCharArray()) {
            if (groups.isEmpty() || groups.peekLast().c != c) {
                groups.addLast(new Group(c));
            }
            assert groups.peekLast() != null;
            groups.peekLast().n++;
        }

        Queue<State> queue = new ArrayDeque<>();
        queue.add(new State(new ArrayList<>(groups), cnt, 0));

        while (!queue.isEmpty()) {
            State curr = queue.remove();
            System.out.printf("%s %s %d\n", curr.gps, Arrays.toString(curr.balls), curr.step);

            if (curr.gps.isEmpty()) return curr.step;
            for (int i = 0; i < curr.gps.size(); i++) {
                Group gp = curr.gps.get(i);
                int requirement = 3 - gp.n;
                if (curr.balls[gp.c - 'A'] >= requirement) {
                    State next = nextState(curr, i, gp.c, requirement);
                    System.out.printf("\t[%d] %s %s %d\n", i, next.gps, Arrays.toString(next.balls), next.step);

                    queue.add(next);
                }
            }
        }
        return -1;
    }

    private State nextState(State curr, int i, char c, int r) {
        int[] nextBalls = new int[26];
        System.arraycopy(curr.balls, 0, nextBalls, 0, nextBalls.length);
        nextBalls[c - 'A'] -= r;

        Deque<Group> deque = new ArrayDeque<>();
        for (int j = 0; j < curr.gps.size(); j++) {
            if (j == i) continue;

            Group gp = curr.gps.get(j);
            if (!deque.isEmpty() && deque.peekLast().c == gp.c) {
                deque.peekLast().n += gp.n;
                if (deque.peekLast().n >= 3) deque.removeLast();
            } else deque.addLast(new Group(gp.c, gp.n));
        }

        return new State(new ArrayList<>(deque), nextBalls, curr.step + r);
    }

    public static void main(String[] args) {
        assert new Solution().findMinStep("RRWWRRBBRR", "WB") == 2;

        assert new Solution().findMinStep("WRRBBW", "RB") == -1;
        assert new Solution().findMinStep("WWRRBBWW", "WRBRW") == 2;
        assert new Solution().findMinStep("G", "GGGGG") == 2;
        assert new Solution().findMinStep("RBYYBBRRB", "YRBGB") == 3;
    }

}
