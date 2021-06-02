package daily.d210602p523continuoussubarraysum;

import java.util.HashMap;
import java.util.Map;

/**
 * 523. Continuous Subarray Sum
 *
 * https://leetcode-cn.com/problems/continuous-subarray-sum/
 *
 * Given an integer array nums and an integer k, return true if nums has a continuous
 * subarray of size at least two whose elements sum up to a multiple of k, or false otherwise.
 *
 * An integer x is a multiple of k if there exists an integer n such that x = n * k. 0 is always a multiple of k.
 */

public class Solution {

    public boolean checkSubarraySum(int[] nums, int k) {
        int n = nums.length, remainder = 0;
        if (n < 2) return false;
        if (n == 2) return (nums[0] + nums[1]) % k == 0;

        Map<Integer, Integer> map = new HashMap<>() {{ put(0, -1); }};
        for (int i = 0; i < n; i++) {
            remainder = (remainder + nums[i]) % k;
            if (map.containsKey(remainder)) {
                if (i - map.get(remainder) >= 2) {
                    return true;
                }
            } else {
                map.put(remainder, i);
            }
        }
        return false;
    }

    public static void main(String[] args) {
        assert new Solution().checkSubarraySum(new int[]{23,2,4,6,7}, 6);
        assert new Solution().checkSubarraySum(new int[]{23,2,6,4,7}, 6);
        assert !new Solution().checkSubarraySum(new int[]{23,2,6,4,7}, 13);
    }

}
