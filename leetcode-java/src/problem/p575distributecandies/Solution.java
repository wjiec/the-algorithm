package problem.p575distributecandies;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 575. Distribute Candies
 *
 * https://leetcode-cn.com/problems/distribute-candies/
 *
 * Alice has n candies, where the ith candy is of type candyType[i].
 * Alice noticed that she started to gain weight, so she visited a doctor.
 *
 * The doctor advised Alice to only eat n / 2 of the candies she has (n is always even).
 * Alice likes her candies very much, and she wants to eat the maximum number of
 * different types of candies while still following the doctor's advice.
 *
 * Given the integer array candyType of length n,
 * return the maximum number of different types of candies she can eat if she only eats n / 2 of them.
 */

public class Solution {

    public int distributeCandies(int[] candyType) {
        Set<Integer> types = new HashSet<>();
        for (var n : candyType) {
            types.add(n);
        }

        return Math.min(types.size(), candyType.length / 2);
    }

    public static void main(String[] args) {
        assert new Solution().distributeCandies(new int[]{1,1,2,2,3,3}) == 3;
        assert new Solution().distributeCandies(new int[]{1,1,2,3}) == 2;
        assert new Solution().distributeCandies(new int[]{6,6,6,6}) == 1;
    }

}
