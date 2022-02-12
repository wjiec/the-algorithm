package problem.p888faircandyswap;

import common.Checker;

import java.util.HashMap;
import java.util.Map;

/**
 * 888. Fair Candy Swap
 *
 * https://leetcode-cn.com/problems/fair-candy-swap/
 *
 * Alice and Bob have candy bars of different sizes: aliceSizes[i] is the size of the i-th bar of candy that Alice has,
 * and bobSizes[j] is the size of the j-th bar of candy that Bob has.
 *
 * Since they are friends, they would like to exchange one candy bar each so that after the exchange,
 * they both have the same total amount of candy.
 * (The total amount of candy a person has is the sum of the sizes of candy bars they have.)
 *
 * Return an integer array ans where ans[0] is the size of the candy bar that Alice must exchange,
 * and ans[1] is the size of the candy bar that Bob must exchange.
 *
 * If there are multiple answers, you may return any one of them.  It is guaranteed an answer exists.
 */

public class Solution {

    public int[] fairCandySwap(int[] aliceSizes, int[] bobSizes) {
        int m = aliceSizes.length, n = bobSizes.length, aliceTotal = 0, bobTotal = 0;
        Map<Integer, Integer> aliceCandies = new HashMap<>(), bobCandies = new HashMap<>();
        for (int i = 0; i < m; i++) {
            aliceTotal += aliceSizes[i];
            aliceCandies.putIfAbsent(aliceSizes[i], i);
        }
        for (int i = 0; i < n; i++) {
            bobTotal += bobSizes[i];
            bobCandies.putIfAbsent(bobSizes[i], i);
        }

        int avg = (aliceTotal + bobTotal) / 2;
        for (var size : aliceCandies.keySet()) {
            int gap = avg - (aliceTotal - size);
            if (bobCandies.containsKey(gap)) {
                return new int[]{size, gap};
            }
        }
        return new int[]{0, 0}; // unreached
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().fairCandySwap(new int[]{1,1}, new int[]{2,2}), new int[]{1,2});
        assert Checker.check(new Solution().fairCandySwap(new int[]{1,2}, new int[]{2,3}), new int[]{1,2});
        assert Checker.check(new Solution().fairCandySwap(new int[]{2}, new int[]{1,3}), new int[]{2,3});
        assert Checker.check(new Solution().fairCandySwap(new int[]{1,2,5}, new int[]{2,4}), new int[]{5,4});
    }

}
