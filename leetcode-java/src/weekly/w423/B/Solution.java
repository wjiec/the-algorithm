package weekly.w423.B;

import java.util.ArrayList;
import java.util.List;

/**
 * 3350. Adjacent Increasing Subarrays Detection II
 *
 * https://leetcode.cn/contest/weekly-contest-423/problems/adjacent-increasing-subarrays-detection-ii/
 *
 * Given an array nums of n integers, your task is to find the maximum value of k for which there
 * exist two adjacent subarrays of length k each, such that both subarrays are strictly increasing.
 *
 * Specifically, check if there are two subarrays of length k starting at indices a and b (a < b), where:
 *
 * Both subarrays nums[a..a + k - 1] and nums[b..b + k - 1] are strictly increasing.
 * The subarrays must be adjacent, meaning b = a + k.
 * Return the maximum possible value of k.
 *
 * A subarray is a contiguous non-empty sequence of elements within an array.
 */

public class Solution {

    public int maxIncreasingSubarrays(List<Integer> nums) {
        List<int[]> seg = new ArrayList<>();
        for (int p = 0, i = 1; i <= nums.size(); i++) {
            if (i == nums.size() || nums.get(i) <= nums.get(i - 1)) {
                seg.add(new int[]{p, i, i - p}); p = i;
            }
        }

        int ans = (seg.get(0)[2]) / 2;
        for (int i = 1; i < seg.size(); i++) {
            ans = Math.max(ans, seg.get(i)[2] / 2);
            if (seg.get(i)[0] == seg.get(i - 1)[1]) {
                ans = Math.max(ans, Math.min(seg.get(i - 1)[2], seg.get(i)[2]));
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().maxIncreasingSubarrays(List.of(2,5,7,8,9,2,3,4,3,1)) == 3;
    }

}
