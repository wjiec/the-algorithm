package problem.p697degreeofanarray;

import java.util.HashMap;

/**
 * 697. Degree of an Array
 *
 * https://leetcode-cn.com/problems/degree-of-an-array/
 *
 * Given a non-empty array of non-negative integers nums,
 * the degree of this array is defined as the maximum frequency of any one of its elements.
 *
 * Your task is to find the smallest possible length of
 * a (contiguous) subarray of nums, that has the same degree as nums.
 */

public class Solution {

    public int findShortestSubArray(int[] nums) {
        HashMap<Integer, Integer> count = new HashMap<>(), start = new HashMap<>(), end = new HashMap<>();
        int n = nums.length, max = 1, ans = 50001;
        for (int i = 0; i < n; i++) {
            if (count.containsKey(nums[i])) {
                max = Math.max(max, count.put(nums[i], count.get(nums[i]) + 1) + 1);
                end.put(nums[i], i);
            } else {
                count.put(nums[i], 1);
                start.put(nums[i], i);
            }
        }

        if (max == 1) return 1;
        for (var kv : count.entrySet()) {
            if (kv.getValue() == max) {
                ans = Math.min(ans, end.get(kv.getKey()) - start.get(kv.getKey()) + 1);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().findShortestSubArray(new int[]{1,2,2,3,1}) == 2;
        assert new Solution().findShortestSubArray(new int[]{1,2,2,3,1,4,2}) == 6;
    }

}
