package weekly.w423.A;

import java.util.List;

/**
 * 3349. Adjacent Increasing Subarrays Detection I
 *
 * https://leetcode.cn/contest/weekly-contest-423/problems/adjacent-increasing-subarrays-detection-i/
 *
 * Given an array nums of n integers and an integer k, determine whether there exist two adjacent
 * subarrays of length k such that both subarrays are strictly increasing.
 *
 * Specifically, check if there are two subarrays starting at indices a and b (a < b), where:
 *
 * Both subarrays nums[a..a + k - 1] and nums[b..b + k - 1] are strictly increasing.
 * The subarrays must be adjacent, meaning b = a + k.
 *
 * Return true if it is possible to find two such subarrays, and false otherwise.
 */

public class Solution {

    public boolean hasIncreasingSubarrays(List<Integer> nums, int k) {
        for (int i = 0; i <= nums.size() - 2 * k; i++) {
            boolean success = true;
            for (int a = i + 1, b = i + k + 1; a < i + k; a++, b++) {
                if (nums.get(a) <= nums.get(a - 1) || nums.get(b) <= nums.get(b - 1)) {
                    success = false; break;
                }
            }
            if (success) return true;
        }

        return false;
    }

    public static void main(String[] args) {
        assert !new Solution().hasIncreasingSubarrays(List.of(1, 2, 3, 4, 4, 4, 4, 5, 6, 7), 5);
    }

}
