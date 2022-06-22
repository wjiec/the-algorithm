package problem.p1040movingstonesuntilconsecutiveii;

import common.Checker;
import common.TODO;

import java.util.Arrays;

/**
 * 1040. Moving Stones Until Consecutive II
 *
 * https://leetcode.cn/problems/moving-stones-until-consecutive-ii/
 *
 * There are some stones in different positions on the X-axis. You are given an
 * integer array stones, the positions of the stones.
 *
 * Call a stone an endpoint stone if it has the smallest or largest position. In
 * one move, you pick up an endpoint stone and move it to an unoccupied position
 * so that it is no longer an endpoint stone.
 *
 * In particular, if the stones are at say, stones = [1,2,5], you cannot move the
 * endpoint stone at position 5, since moving it to any position (such as 0, or 3)
 * will still keep that stone as an endpoint stone.
 * The game ends when you cannot make any more moves (i.e., the stones are in three
 * consecutive positions).
 *
 * Return an integer array answer of length 2 where:
 *
 * answer[0] is the minimum number of moves you can play, and
 * answer[1] is the maximum number of moves you can play.
 */

public class Solution {

    @TODO
    public int[] numMovesStonesII(int[] stones) {
        Arrays.sort(stones);
        int n = stones.length, max = stones[n - 1] - stones[0] + 1 - n;
        max -= Math.min(stones[n - 1] - stones[n - 2] - 1, stones[1] - stones[0] - 1);
        int min = max;

        for (int i = 0, j = 0; i < n; i++) {
            while (j + 1 < n && stones[j + 1] - stones[i] + 1 <= n) j++;
            int cost = n - (j - i + 1);
            if (j - i + 1 == n - 1 && stones[j] - stones[i] + 1 == n - 1) cost = 2;
            if (cost < min) min = cost;
        }
        return new int[]{min, max};
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().numMovesStonesII(new int[]{7,4,9}), new int[]{1,2});
        assert Checker.check(new Solution().numMovesStonesII(new int[]{6,5,4,3,10}), new int[]{2,3});
        assert Checker.check(new Solution().numMovesStonesII(new int[]{100,101,104,102,103}), new int[]{0,0});
    }

}
