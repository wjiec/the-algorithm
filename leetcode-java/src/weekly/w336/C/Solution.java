package weekly.w336.C;

import java.util.HashMap;
import java.util.Map;

/**
 * 2588. Count the Number of Beautiful Subarrays
 *
 * https://leetcode.cn/problems/count-the-number-of-beautiful-subarrays/
 *
 * You are given a 0-indexed integer array nums. In one operation, you can:
 *
 * Choose two different indices i and j such that 0 <= i, j < nums.length.
 * Choose a non-negative integer k such that the kth bit (0-indexed)
 * in the binary representation of nums[i] and nums[j] is 1.
 * Subtract 2k from nums[i] and nums[j].
 *
 * A subarray is beautiful if it is possible to make all of its elements equal to 0
 * after applying the above operation any number of times.
 *
 * Return the number of beautiful subarrays in the array nums.
 *
 * A subarray is a contiguous non-empty sequence of elements within an array.
 */

public class Solution {

    public long beautifulSubarrays(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);

        long ans = 0; int state = 0;
        for (int num : nums) {
            for (int j = 1; j <= num; j <<= 1) {
                if ((num & j) != 0) {
                    if ((state & j) == 0) state |= j;
                    else state &= ~j;
                }
            }

            ans += map.getOrDefault(state, 0);
            map.merge(state, 1, Integer::sum);
        }
        return ans;
    }

    private static class XOR {
        public long beautifulSubarrays(int[] nums) {
            Map<Integer, Integer> map = new HashMap<>();
            map.put(0, 1);

            long ans = 0; int state = 0;
            for (int num : nums) {
                state ^= num;
                ans += map.getOrDefault(state, 0);
                map.merge(state, 1, Integer::sum);
            }
            return ans;
        }
    }

    public static void main(String[] args) {
        assert new Solution().beautifulSubarrays(new int[]{4,3,1,2,4}) == 2;
        assert new Solution().beautifulSubarrays(new int[]{1,10,4}) == 0;
    }

}
