package problem.p2139minimummovestoreachtargetscore;

/**
 * 2139. Minimum Moves to Reach Target Score
 *
 * https://leetcode.cn/problems/minimum-moves-to-reach-target-score/
 *
 * You are playing a game with integers. You start with the integer 1
 * and you want to reach the integer target.
 *
 * In one move, you can either:
 *
 * Increment the current integer by one (i.e., x = x + 1).
 * Double the current integer (i.e., x = 2 * x).
 * You can use the increment operation any number of times, however, you
 * can only use the double operation at most maxDoubles times.
 *
 * Given the two integers target and maxDoubles, return the minimum number of
 * moves needed to reach target starting with 1.
 */

public class Solution {

    public int minMoves(int target, int maxDoubles) {
        if (target == 1) return 0;
        if (target == 2) return 1;
        if (maxDoubles == 0) return target - 1;

        if (target % 2 == 1) {
            return 1 + minMoves(target - 1, maxDoubles);
        }

        return 1 + minMoves(target / 2, maxDoubles - 1);
    }

    public static void main(String[] args) {
        assert new Solution().minMoves(5, 0) == 4;
        assert new Solution().minMoves(19, 2) == 7;
        assert new Solution().minMoves(10, 4) == 4;
    }

}
