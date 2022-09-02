package daily.d210707p1711countgoodmeals;

import java.util.HashMap;
import java.util.Map;

/**
 * 1711. Count Good Meals
 *
 * https://leetcode-cn.com/problems/count-good-meals/
 *
 * A good meal is a meal that contains exactly two different food items
 * with a sum of deliciousness equal to a power of two.
 *
 * You can pick any two different foods to make a good meal.
 *
 * Given an array of integers deliciousness where deliciousness[i] is the deliciousness of the ith item of food,
 * return the number of different good meals you can make from this list modulo 109 + 7.
 *
 * Note that items with different indices are considered different even if they have the same deliciousness value.
 */

public class Solution {

    private static final int MOD = (int) (1e9 + 7);

    public int countPairs(int[] deliciousness) {
        int ans = 0;
        Map<Integer, Integer> counts = new HashMap<>();
        for (var n : deliciousness) {
            for (int v = 1, i = 0; i < 22; i++, v <<= 1) {
                ans = (ans + counts.getOrDefault(v - n, 0)) % MOD;
            }
            counts.put(n, counts.getOrDefault(n, 0) + 1);
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().countPairs(new int[]{2160,1936,3,29,27,5,2503,1593,2,0,16,0,3860,28908,6,2,15,49,6246,1946,23,105,7996,196,0,2,55,457,5,3,924,7268,16,48,4,0,12,116,2628,1468}) == 53;

        assert new Solution().countPairs(new int[]{1,3,5,7,9}) == 4;
        assert new Solution().countPairs(new int[]{1,1,1,3,3,3,7}) == 15;
    }

}
