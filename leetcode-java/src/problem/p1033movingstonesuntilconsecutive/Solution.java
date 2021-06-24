package problem.p1033movingstonesuntilconsecutive;

import common.Checker;

import java.util.Arrays;

/**
 * 1033. Moving Stones Until Consecutive
 *
 * https://leetcode-cn.com/problems/moving-stones-until-consecutive/
 *
 * Three stones are on a number line at positions a, b, and c.
 *
 * Each turn, you pick up a stone at an endpoint (ie., either the lowest or highest position stone),
 * and move it to an unoccupied position between those endpoints.
 *
 * Formally, let's say the stones are currently at positions x, y, z with x < y < z.
 *
 * You pick up the stone at either position x or position z,
 * and move that stone to an integer position k, with x < k < z and k != y.
 *
 * The game ends when you cannot make any more moves, ie. the stones are in consecutive positions.
 *
 * When the game ends, what is the minimum and maximum number of moves that you could have made?
 *
 * Return the answer as an length 2 array: answer = [minimum_moves, maximum_moves]
 */

public class Solution {

    public int[] numMovesStones(int a, int b, int c) {
        int max = Math.max(a, Math.max(b, c)), min = Math.min(a, Math.min(b, c)), mid = a + b + c - min - max;
        if (max - min == 2) return new int[]{0, 0};
        if (mid - min <= 2 || max - mid <= 2) return new int[]{1, max - min - 2};
        return new int[]{2, max - min - 2};
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().numMovesStones(1,2,5), new int[]{1,2});
        assert Checker.check(new Solution().numMovesStones(4,3,2), new int[]{0,0});

        System.out.println(Arrays.toString(new Solution().numMovesStones(1, 2, 89)));
        System.out.println(Arrays.toString(new Solution().numMovesStones(1, 3, 89)));
        System.out.println(Arrays.toString(new Solution().numMovesStones(1, 4, 89)));
    }

}
