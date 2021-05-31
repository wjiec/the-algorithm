package problem.p682baseballgame;

import java.util.ArrayDeque;

/**
 * 682. Baseball Game
 *
 * https://leetcode-cn.com/problems/baseball-game/
 *
 * You are keeping score for a baseball game with strange rules.
 * The game consists of several rounds, where the scores of past rounds may affect future rounds' scores.
 *
 * At the beginning of the game, you start with an empty record.
 * You are given a list of strings ops, where ops[i] is the ith operation
 * you must apply to the record and is one of the following:
 *
 * An integer x - Record a new score of x.
 * "+" - Record a new score that is the sum of the previous two scores.
 *      It is guaranteed there will always be two previous scores.
 * "D" - Record a new score that is double the previous score.
 *      It is guaranteed there will always be a previous score.
 * "C" - Invalidate the previous score, removing it from the record.
 *      It is guaranteed there will always be a previous score.
 *
 * Return the sum of all the scores on the record.
 */

public class Solution {

    public int calPoints(String[] ops) {
        ArrayDeque<Integer> scores = new ArrayDeque<>();
        for (var s : ops) {
            switch (s) {
                case "C":
                    scores.removeLast(); break;
                case "D":
                    scores.addLast(scores.getLast() * 2); break;
                case "+":
                    int last = scores.removeLast(), sum = last + scores.getLast();
                    scores.addLast(last); scores.add(sum);
                    break;
                default:
                    scores.addLast(Integer.valueOf(s, 10));
            }
        }

        int ans = 0;
        for (var score : scores) {
            ans += score;
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().calPoints(new String[]{"5","2","C","D","+"}) == 30;
        assert new Solution().calPoints(new String[]{"5","-2","4","C","D","9","+","+"}) == 27;
    }

}
