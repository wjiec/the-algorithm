package daily.d220120p2029stonegameix;

/**
 * 2029. Stone Game IX
 *
 * https://leetcode-cn.com/problems/stone-game-ix/
 *
 * Alice and Bob continue their games with stones. There is a row of n stones, and each stone has an associated value.
 *
 * You are given an integer array stones, where stones[i] is the value of the ith stone.
 *
 * Alice and Bob take turns, with Alice starting first. On each turn, the player may remove any stone from stones.
 *
 * The player who removes a stone loses if the sum of the values of all removed stones is divisible by 3.
 *
 * Bob will win automatically if there are no remaining stones (even if it is Alice's turn).
 *
 * Assuming both players play optimally, return true if Alice wins and false if Bob wins.
 */

public class Solution {

    public boolean stoneGameIX(int[] stones) {
        int a = 0, b = 0, c = 0;
        for (var stone : stones) {
            switch (stone % 3) {
                case 0: a++; break;
                case 1: b++; break;
                case 2: c++; break;
            }
        }

        if (a % 2 == 0) {
            return b >= 1 && c >= 1;
        }
        return b - c > 2 || c - b > 2;
    }

    public static void main(String[] args) {
        assert new Solution().stoneGameIX(new int[]{2,1});
        assert !new Solution().stoneGameIX(new int[]{2});
        assert !new Solution().stoneGameIX(new int[]{5,1,2,4,3});
    }

}
