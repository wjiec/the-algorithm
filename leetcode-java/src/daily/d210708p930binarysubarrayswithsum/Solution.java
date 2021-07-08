package daily.d210708p930binarysubarrayswithsum;

import java.util.HashMap;
import java.util.Map;

/**
 * 930. Binary Subarrays With Sum
 *
 * https://leetcode-cn.com/problems/binary-subarrays-with-sum/
 *
 * Given a binary array nums and an integer goal, return the number of non-empty subarrays with a sum goal.
 *
 * A subarray is a contiguous part of the array.
 */

public class Solution {

    // @TODO
    public int numSubarraysWithSum(int[] nums, int goal) {
        int sum = 0, ans = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (var n : nums) {
            map.put(sum, map.getOrDefault(sum, 0) + 1);
            sum += n;
            ans += map.getOrDefault(sum - goal, 0);
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().numSubarraysWithSum(new int[]{1,0,1,0,1}, 2) == 4;
        assert new Solution().numSubarraysWithSum(new int[]{0,0,0,0,0}, 0) == 15;
    }

}
