package problem.p325maximumsizesubarraysumequalsk;

import java.util.HashMap;
import java.util.Map;

/**
 * 325. Maximum Size Subarray Sum Equals k
 *
 * https://leetcode.cn/problems/maximum-size-subarray-sum-equals-k/
 *
 * Given an integer array nums and an integer k, return the maximum length of a
 * subarray that sums to k. If there is not one, return 0 instead.
 */

public class Solution {

    public int maxSubArrayLen(int[] nums, int k) {
        int ans = 0, sum = 0, n = nums.length;
        Map<Integer, Integer> map = new HashMap<>(); map.put(0, 0);
        for (int i = 1; i <= n; i++) {
            sum += nums[i - 1];
            Integer x = map.get(sum - k);
            if (x != null) {
                ans = Math.max(ans, i - x);
            }
            map.putIfAbsent(sum, i);
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().maxSubArrayLen(new int[]{1,-1,5,-2,3}, 3) == 4;
        assert new Solution().maxSubArrayLen(new int[]{-2,-1,2,1}, 1) == 2;
    }

}
