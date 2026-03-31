package weekly.w477.B;

import java.util.HashMap;
import java.util.Map;

/**
 * Q2. Find Maximum Balanced XOR Subarray Length
 *
 * https://leetcode.cn/contest/weekly-contest-477/problems/find-maximum-balanced-xor-subarray-length/
 *
 * Given an integer array nums, return the length of the longest subarray that has a bitwise
 * XOR of zero and contains an equal number of even and odd numbers.
 *
 * If no such subarray exists, return 0.
 */

public class Solution {

    public int maxBalancedSubarray(int[] nums) {
        int ans = 0;
        Map<Integer, Map<Integer, Integer>> leftMax = new HashMap<>();
        leftMax.computeIfAbsent(0, k -> new HashMap<>()).put(0, -1);
        for (int i = 0, parity = 0, xor = 0; i < nums.length; i++) {
            Integer prev = leftMax.computeIfAbsent(xor ^= nums[i], k -> new HashMap<>())
                .putIfAbsent(parity += ((nums[i] & 1) << 1) - 1, i);
            if (prev != null) ans = Math.max(ans, i - prev);
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().maxBalancedSubarray(new int[]{3,1,3,2,0}) == 4;
        assert new Solution().maxBalancedSubarray(new int[]{3,2,8,5,4,14,9,15}) == 8;
        assert new Solution().maxBalancedSubarray(new int[]{0}) == 0;

        assert new Solution().maxBalancedSubarray(new int[]{3,4,2,1,3,1}) == 0;
    }

}
