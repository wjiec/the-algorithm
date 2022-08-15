package problem.p1497checkifarraypairsaredivisiblebyk;

import java.util.HashMap;
import java.util.Map;

/**
 * 1497. Check If Array Pairs Are Divisible by k
 *
 * https://leetcode.cn/problems/check-if-array-pairs-are-divisible-by-k/
 *
 * Given an array of integers arr of even length n and an integer k.
 *
 * We want to divide the array into exactly n / 2 pairs such that the sum of each pair is divisible by k.
 *
 * Return true If you can find a way to do that or false otherwise.
 */

public class Solution {

    public boolean canArrange(int[] arr, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (var n : arr) {
            int mod = Math.floorMod(n, k), req = (k - mod) % k;
            if (map.containsKey(req)) {
                map.merge(req, 1, (old, val) -> old - val == 0 ? null : old - val);
            } else map.merge(mod, 1, Integer::sum);
        }
        return map.isEmpty();
    }

    public static void main(String[] args) {
        assert new Solution().canArrange(new int[]{-1,1,-2,2,-3,3,-4,4}, 3);

        assert new Solution().canArrange(new int[]{1,2,3,4,5,10,6,7,8,9}, 5);
        assert new Solution().canArrange(new int[]{1,2,3,4,5,6}, 7);
        assert !new Solution().canArrange(new int[]{1,2,3,4,5,6}, 10);
    }

}
