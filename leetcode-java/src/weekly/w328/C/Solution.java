package weekly.w328.C;

import java.util.HashMap;
import java.util.Map;

/**
 * 6293. Count the Number of Good Subarrays
 *
 * https://leetcode.cn/problems/count-the-number-of-good-subarrays/
 *
 * Given an integer array nums and an integer k, return the number of good subarrays of nums.
 *
 * A subarray arr is good if it there are at least k pairs of indices (i, j) such
 * that i < j and arr[i] == arr[j].
 *
 * A subarray is a contiguous non-empty sequence of elements within an array.
 */

public class Solution {

    public long countGood(int[] nums, int k) {
        int n = nums.length;
        long ans = 0, pairs = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int l = 0, r = 0; r < nums.length; r++) {
            int cnt = map.merge(nums[r], 1, Integer::sum);
            if (cnt >= 2) pairs += cnt - 1;
            while (pairs >= k) {
                ans += n - r;
                int sub = map.merge(nums[l++], -1, Integer::sum);
                if (sub > 0) pairs -= sub;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().countGood(new int[]{2,1,3,1,2,2,3,3,2,2,1,1,1,3,1}, 11) == 21;

        assert new Solution().countGood(new int[]{1,1,1,1,1}, 10) == 1;
        assert new Solution().countGood(new int[]{3,1,4,3,2,2,4}, 2) == 4;
    }

}
