package problem.p560subarraysumequalsk;

import java.util.HashMap;
import java.util.Map;

/**
 * 560. Subarray Sum Equals K
 *
 * https://leetcode-cn.com/problems/subarray-sum-equals-k/
 *
 * Given an array of integers nums and an integer k, return the total number
 * of subarrays whose sum equals to k.
 */

public class Solution {

    public int subarraySum(int[] nums, int k) {
        int ans = 0, sum = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        for (int num : nums) {
            sum += num;
            ans += map.getOrDefault(sum - k, 0);
            map.merge(sum, 1, Integer::sum);
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().subarraySum(new int[]{1,1,1}, 2) == 2;
        assert new Solution().subarraySum(new int[]{1,2,3}, 3) == 2;
    }

}
