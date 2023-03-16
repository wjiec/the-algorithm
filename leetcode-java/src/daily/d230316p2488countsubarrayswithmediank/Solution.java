package daily.d230316p2488countsubarrayswithmediank;

import java.util.HashMap;
import java.util.Map;

/**
 * 2488. Count Subarrays With Median K
 *
 * https://leetcode.cn/problems/count-subarrays-with-median-k/
 *
 * You are given an array nums of size n consisting of distinct integers
 * from 1 to n and a positive integer k.
 *
 * Return the number of non-empty subarrays in nums that have a median equal to k.
 *
 * Note:
 *
 * The median of an array is the middle element after sorting the array in ascending order.
 * If the array is of even length, the median is the left middle element.
 *
 * For example, the median of [2,3,1,4] is 2, and the median of [8,4,3,5,1] is 4.
 *
 * A subarray is a contiguous part of an array.
 *
 * The integers in nums are distinct.
 */

public class Solution {

    public int countSubarrays(int[] nums, int k) {
        int idx = -1, n = nums.length;
        for (int i = 0; i < n; i++) {
            if (nums[i] == k) {
                idx = i; break;
            }
        }
        if (idx == -1) return 0;

        int ans = 1;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = idx + 1, x = 0; i < n; i++) {
            x += Integer.compare(nums[i], k);
            map.merge(x, 1, Integer::sum);
        }

        for (int i = idx - 1, x = 0; i >= 0; i--) {
            x += Integer.compare(nums[i], k);
            ans += map.getOrDefault(-x, 0);
            ans += map.getOrDefault(-x - 1, 0);
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().countSubarrays(new int[]{2,5,1,4,3,6}, 1) == 3;

        assert new Solution().countSubarrays(new int[]{3,2,1,4,5}, 4) == 3;
        assert new Solution().countSubarrays(new int[]{2,3,1}, 3) == 1;
    }

}
