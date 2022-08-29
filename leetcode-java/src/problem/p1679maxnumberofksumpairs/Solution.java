package problem.p1679maxnumberofksumpairs;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 1679. Max Number of K-Sum Pairs
 *
 * https://leetcode.cn/problems/max-number-of-k-sum-pairs/
 *
 * You are given an integer array nums and an integer k.
 *
 * In one operation, you can pick two numbers from the array whose sum
 * equals k and remove them from the array.
 *
 * Return the maximum number of operations you can perform on the array.
 */

public class Solution {

    public int maxOperations(int[] nums, int k) {
        Arrays.sort(nums);

        int ans = 0, n = nums.length;
        for (int l = 0, r = n - 1; l < r; ) {
            int sum = nums[l] + nums[r];
            if (sum == k) {
                ans++; l++; r--;
            } else if (sum > k) r--;
            else l++;
        }
        return ans;
    }

    private static class DiffMap {
        public int maxOperations(int[] nums, int k) {
            int ans = 0;
            Map<Integer, Integer> map = new HashMap<>();
            for (var n : nums) {
                Integer cnt = map.get(n);
                if (cnt != null && cnt > 0) {
                    ans++; map.put(n, cnt - 1);
                } else map.merge(k - n, 1, Integer::sum);
            }
            return ans;
        }
    }

    public static void main(String[] args) {
        assert new Solution().maxOperations(new int[]{1,2,3,4}, 5) == 2;
        assert new Solution().maxOperations(new int[]{3,1,3,4,3}, 6) == 1;

        assert new DiffMap().maxOperations(new int[]{1,2,3,4}, 5) == 2;
        assert new DiffMap().maxOperations(new int[]{3,1,3,4,3}, 6) == 1;
    }

}
