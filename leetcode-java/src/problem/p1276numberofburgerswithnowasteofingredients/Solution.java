package problem.p1276numberofburgerswithnowasteofingredients;

import common.Checker;

import java.util.List;

/**
 * 1276. Number of Burgers with No Waste of Ingredients
 *
 * https://leetcode.cn/problems/number-of-burgers-with-no-waste-of-ingredients/
 *
 * Given two integers tomatoSlices and cheeseSlices. The ingredients of different burgers are as follows:
 *
 * Jumbo Burger: 4 tomato slices and 1 cheese slice.
 * Small Burger: 2 Tomato slices and 1 cheese slice.
 * Return [total_jumbo, total_small] so that the number of remaining tomatoSlices equal to 0 and the
 * number of remaining cheeseSlices equal to 0. If it is not possible to make the remaining
 * tomatoSlices and cheeseSlices equal to 0 return [].
 */

public class Solution {

    public List<Integer> numOfBurgers(int tomatoSlices, int cheeseSlices) {
        // 4x + 2y = tomatoSlices
        // x + y = cheeseSlices
        // x = tomatoSlices/2 - cheeseSlices
        // y = 2*cheeseSlices - tomatoSlices/2
        int jumbo = tomatoSlices / 2 - cheeseSlices;
        int small = 2 * cheeseSlices - tomatoSlices / 2;
        if (jumbo < 0 || small < 0 || jumbo * 4 + small * 2 != tomatoSlices || jumbo + small != cheeseSlices) {
            return List.of();
        }

        return List.of(jumbo, small);
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().numOfBurgers(3962, 1205), List.of(776, 429));

        assert Checker.check(new Solution().numOfBurgers(16, 7), List.of(1, 6));
        assert Checker.check(new Solution().numOfBurgers(17, 4), List.of());
        assert Checker.check(new Solution().numOfBurgers(4, 17), List.of());
        assert Checker.check(new Solution().numOfBurgers(0, 0), List.of(0, 0));
        assert Checker.check(new Solution().numOfBurgers(2, 1), List.of(0, 1));
    }

}
