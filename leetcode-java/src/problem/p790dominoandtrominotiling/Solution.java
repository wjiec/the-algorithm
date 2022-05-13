package problem.p790dominoandtrominotiling;

import common.TODO;

/**
 * 790. Domino and Tromino Tiling
 *
 * https://leetcode.cn/problems/domino-and-tromino-tiling/
 *
 * You have two types of tiles: a 2 x 1 domino shape and a tromino shape. You may rotate these shapes.
 *
 * Given an integer n, return the number of ways to tile an 2 x n board.
 * Since the answer may be very large, return it modulo 109 + 7.
 *
 * In a tiling, every square must be covered by a tile. Two tilings are different
 * if and only if there are two 4-directionally adjacent cells on the board
 * such that exactly one of the tilings has both squares occupied by a tile.
 */

public class Solution {

    @TODO(tips = "DP")
    public int numTilings(int n) {
        int MOD = 1_000_000_007;

        long[] state = new long[]{1, 0, 0, 0};
        for (int i = 0; i < n; i++) {
            long[] next = new long[]{0, 0, 0, 0};
            next[0b00] = (state[0b00] + state[0b11]) % MOD;
            next[0b01] = (state[0b00] + state[0b10]) % MOD;
            next[0b10] = (state[0b00] + state[0b01]) % MOD;
            next[0b11] = (state[0b00] + state[0b01] + state[0b10]) % MOD;
            state = next;
        }
        return (int) state[0];
    }

    public static void main(String[] args) {
        assert new Solution().numTilings(1) == 1;
        assert new Solution().numTilings(2) == 2;
        assert new Solution().numTilings(3) == 5;
        assert new Solution().numTilings(4) == 11;
        assert new Solution().numTilings(5) == 24;
        assert new Solution().numTilings(6) == 53;
        assert new Solution().numTilings(7) == 117;
        assert new Solution().numTilings(8) == 258;
        assert new Solution().numTilings(9) == 569;
    }

}
