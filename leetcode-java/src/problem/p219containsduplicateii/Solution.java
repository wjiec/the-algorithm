package problem.p219containsduplicateii;

import java.util.HashMap;
import java.util.Map;

/**
 * 219. Contains Duplicate II
 *
 * https://leetcode-cn.com/problems/contains-duplicate-ii/
 *
 * Given an integer array nums and an integer k,
 * return true if there are two distinct indices i and j in the array such that nums[i] == nums[j] and abs(i - j) <= k.
 */

public class Solution {

    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Map<Integer, Integer> indexs = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (indexs.containsKey(nums[i]) && i - indexs.get(nums[i]) <= k) {
                return true;
            }
            indexs.put(nums[i], i);
        }
        return false;
    }

    public static void main(String[] args) {
        assert new Solution().containsNearbyDuplicate(new int[]{1,2,3,1}, 3);
        assert new Solution().containsNearbyDuplicate(new int[]{1,0,1,1}, 1);
        assert !new Solution().containsNearbyDuplicate(new int[]{1,2,3,1,2,3}, 2);
    }

}
