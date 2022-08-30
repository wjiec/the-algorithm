package problem.p1686stonegamevi;

import java.util.ArrayList;
import java.util.List;

/**
 * 1686. Stone Game VI
 *
 * https://leetcode.cn/problems/stone-game-vi/
 *
 * Alice and Bob take turns playing a game, with Alice starting first.
 *
 * There are n stones in a pile. On each player's turn, they can remove a stone from the pile and
 * receive points based on the stone's value. Alice and Bob may value the stones differently.
 *
 * You are given two integer arrays of length n, aliceValues and bobValues. Each aliceValues[i] and
 * bobValues[i] represents how Alice and Bob, respectively, value the ith stone.
 *
 * The winner is the person with the most points after all the stones are chosen. If both players
 * have the same amount of points, the game results in a draw.
 *
 * Both players will play optimally. Both players know the other's values.
 *
 * Determine the result of the game, and:
 *
 * If Alice wins, return 1.
 * If Bob wins, return -1.
 * If the game results in a draw, return 0.
 */

public class Solution {

    public int stoneGameVI(int[] aliceValues, int[] bobValues) {
        List<int[]> list = new ArrayList<>();
        for (int i = 0; i < aliceValues.length; i++) {
            list.add(new int[]{i, aliceValues[i] + bobValues[i]});
        }
        list.sort((a, b) -> b[1] - a[1]);

        int alice = 0, bob = 0;
        for (int i = 0; i < aliceValues.length; i++) {
            if (i % 2 == 0) alice += aliceValues[list.get(i)[0]];
            else  bob += bobValues[list.get(i)[0]];
        }
        return Integer.compare(alice, bob);
    }

    public static void main(String[] args) {
        assert new Solution().stoneGameVI(new int[]{1,3}, new int[]{2,1}) == 1;
        assert new Solution().stoneGameVI(new int[]{1,2}, new int[]{3,1}) == 0;
        assert new Solution().stoneGameVI(new int[]{2,4,3}, new int[]{1,6,7}) == -1;
    }

}
