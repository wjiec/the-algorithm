package problem.p1128numberofequivalentdominopairs;

import java.util.HashMap;
import java.util.Map;

/**
 * 1128. Number of Equivalent Domino Pairs
 *
 * https://leetcode-cn.com/problems/number-of-equivalent-domino-pairs/
 *
 * Given a list of dominoes, dominoes[i] = [a, b] is equivalent to dominoes[j] = [c, d]
 * if and only if either (a == c and b == d), or (a == d and b == c) - that is,
 * one domino can be rotated to be equal to another domino.
 *
 * Return the number of pairs (i, j) for which 0 <= i < j < dominoes.length,
 * and dominoes[i] is equivalent to dominoes[j].
 */

public class Solution {

    public int numEquivDominoPairs(int[][] dominoes) {
        Map<Integer, Integer> count = new HashMap<>();
        for (int[] item : dominoes) {
            int val = item[0] << 16 | item[1];
            if (item[0] < item[1]) {
                val = item[1] << 16 | item[0];
            }

            count.merge(val, 1, Integer::sum);
        }

        int ans = 0;
        for (var kv : count.entrySet()) {
            if (kv.getValue() != 1) {
                ans += (1 + kv.getValue() - 1) * (kv.getValue() - 1) / 2;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().numEquivDominoPairs(new int[][]{
            {1, 2}, {2, 1}, {3, 4}, {5, 6}
        }) == 1;

        assert new Solution().numEquivDominoPairs(new int[][]{
            {1, 2}, {1, 2}, {1, 1}, {1, 2}, {2, 2}
        }) == 3;
    }

}
