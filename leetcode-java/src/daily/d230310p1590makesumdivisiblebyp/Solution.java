package daily.d230310p1590makesumdivisiblebyp;

import java.util.HashMap;
import java.util.Map;

/**
 * 1590. Make Sum Divisible by P
 *
 * https://leetcode.cn/problems/make-sum-divisible-by-p/
 *
 * Given an array of positive integers nums, remove the smallest subarray (possibly empty)
 * such that the sum of the remaining elements is divisible by p.
 *
 * It is not allowed to remove the whole array.
 *
 * Return the length of the smallest subarray that you need to remove, or -1 if it's impossible.
 *
 * A subarray is defined as a contiguous block of elements in the array.
 */

@SuppressWarnings("DuplicatedCode")
public class Solution {

    public int minSubarray(int[] nums, int p) {
        int sum = 0;
        for (var v : nums) sum = (sum + v) % p;
        if (sum == 0) return 0;

        Map<Integer, Integer> mods = new HashMap<>();
        mods.put(sum, -1);

        int curr = 0, ans = nums.length;
        for (int i = 0; i < nums.length; i++) {
            curr = (curr + nums[i]) % p;
            if (mods.containsKey(curr)) {
                ans = Math.min(ans, i - mods.get(curr));
            }
            mods.put((sum + curr) % p, i);
        }
        return ans == nums.length ? -1 : ans;
    }

    public static void main(String[] args) {
        assert new Solution().minSubarray(new int[]{3,6,8,1}, 8) == -1;

        assert new Solution().minSubarray(new int[]{8,32,31,18,34,20,21,13,1,27,23,22,11,15,30,4,2}, 148) == 7;

        assert new Solution().minSubarray(new int[]{3,1,4,2}, 6) == 1;
        assert new Solution().minSubarray(new int[]{6,3,5,2}, 9) == 2;
        assert new Solution().minSubarray(new int[]{1,2,3}, 3) == 0;
        assert new Solution().minSubarray(new int[]{1000000000,1000000000,1000000000}, 3) == 0;
    }

}
