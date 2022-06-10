package problem.p974subarraysumsdivisiblebyk;

import common.TODO;

import java.util.HashMap;
import java.util.Map;

/**
 * 974. Subarray Sums Divisible by K
 *
 * https://leetcode.cn/problems/subarray-sums-divisible-by-k/
 *
 * Given an integer array nums and an integer k, return the number of non-empty
 * subarrays that have a sum divisible by k.
 *
 * A subarray is a contiguous part of an array.
 */

public class Solution {

    @TODO(tips = "同余定理")
    public int subarraysDivByK(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);

        int ans = 0, sum = 0;
        for (var v : nums) {
            sum += v;
            int modulus = (sum % k + k) % k;
            int same = map.getOrDefault(modulus, 0);
            ans += same;
            map.put(modulus, same + 1);
        }

        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().subarraysDivByK(new int[]{4,5,0,-2,-3,1}, 5) == 7;
        assert new Solution().subarraysDivByK(new int[]{5}, 9) == 0;
    }

}
