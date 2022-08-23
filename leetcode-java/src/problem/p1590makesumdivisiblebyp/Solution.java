package problem.p1590makesumdivisiblebyp;

import java.util.HashMap;
import java.util.Map;

/**
 * 1590. Make Sum Divisible by P
 *
 * https://leetcode.cn/problems/make-sum-divisible-by-p/
 *
 * Given an array of positive integers nums, remove the smallest subarray (possibly empty) such that
 * the sum of the remaining elements is divisible by p. It is not allowed to remove the whole array.
 *
 * Return the length of the smallest subarray that you need to remove, or -1 if it's impossible.
 *
 * A subarray is defined as a contiguous block of elements in the array.
 */

public class Solution {

    public int minSubarray(int[] nums, int p) {
        long sum = 0;
        for (var n : nums) sum += n;
        if ((sum %= p) == 0) return 0;

        int ans = Integer.MAX_VALUE;
        Map<Long, Integer> map = new HashMap<>();
        map.put(sum, -1);

        long curr = 0;
        for (int i = 0; i < nums.length; i++) {
            curr = (curr + nums[i]) % p;
            if (map.containsKey(curr)) {
                ans = Math.min(ans, i - map.get(curr));
            }
            map.put((sum + curr) % p, i);
        }

        return ans == nums.length ? -1 : ans;
    }

    public static void main(String[] args) {
        assert new Solution().minSubarray(new int[]{
            17,86,51,82,82,14,80,83,59,22,4,16,83,35,30,64,63,
            30,55,41,69,67,9,65,73,52,11,18,62,58,75,5,81,35,
            13,77,4,61,50,21,66,84,56,70
        }, 278) == 17;
        assert new Solution().minSubarray(new int[]{14,22,14,12,1,20,8,21,20,7,12,14,24,12}, 2) == 1;
        assert new Solution().minSubarray(new int[]{26,19,11,14,18,4,7,1,30,23,19,8,10,6,26,3}, 26) == 3;
        assert new Solution().minSubarray(new int[]{4,4,2}, 7) == -1;

        assert new Solution().minSubarray(new int[]{3,1,4,2}, 6) == 1;
        assert new Solution().minSubarray(new int[]{6,3,5,2}, 9) == 2;
        assert new Solution().minSubarray(new int[]{1,2,3}, 3) == 0;
        assert new Solution().minSubarray(new int[]{1,2,3}, 7) == -1;
        assert new Solution().minSubarray(new int[]{1000000000,1000000000,1000000000}, 3) == 0;
    }

}
