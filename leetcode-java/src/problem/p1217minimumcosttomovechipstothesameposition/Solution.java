package problem.p1217minimumcosttomovechipstothesameposition;

/**
 * 1217. Minimum Cost to Move Chips to The Same Position
 *
 * https://leetcode-cn.com/problems/minimum-cost-to-move-chips-to-the-same-position/
 *
 * We have n chips, where the position of the ith chip is position[i].
 *
 * We need to move all the chips to the same position. In one step,
 * we can change the position of the ith chip from position[i] to:
 *
 * position[i] + 2 or position[i] - 2 with cost = 0.
 * position[i] + 1 or position[i] - 1 with cost = 1.
 *
 * Return the minimum cost needed to move all the chips to the same position.
 */

public class Solution {

    public int minCostToMoveChips(int[] position) {
        int odd = 0, even = 0;
        for (var n : position) {
            if (n % 2 == 0) even++;
            else odd++;
        }
        return Math.min(odd, even);
    }

    public static void main(String[] args) {
        assert new Solution().minCostToMoveChips(new int[]{3,3,1,2,2}) == 2;

        assert new Solution().minCostToMoveChips(new int[]{1,2,3}) == 1;
        assert new Solution().minCostToMoveChips(new int[]{2,2,2,3,3}) == 2;
        assert new Solution().minCostToMoveChips(new int[]{1,1000000000}) == 1;
    }

}
