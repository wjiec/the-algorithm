package problem.p219containsduplicateii;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 219. Contains Duplicate II
 *
 * https://leetcode-cn.com/problems/contains-duplicate-ii/
 *
 * Given an integer array nums and an integer k,
 * return true if there are two distinct indices i and j in the array such that nums[i] == nums[j] and abs(i - j) <= k.
 */

public class Solution {

    // slow
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (set.contains(nums[i])) {
                return true;
            }

            set.add(nums[i]);
            if (set.size() > k) set.remove(nums[i - k]);
        }
        return false;
    }

    // fast
    public boolean containsNearbyDuplicate1(int[] nums, int k) {
        Map<Integer, Integer> indexes = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (indexes.containsKey(nums[i]) && i - indexes.get(nums[i]) <= k) {
                return true;
            }
            indexes.put(nums[i], i);
        }
        return false;
    }

    public static void main(String[] args) {
        assert new Solution().containsNearbyDuplicate(new int[]{1,2,3,1}, 3);
        assert new Solution().containsNearbyDuplicate(new int[]{1,0,1,1}, 1);
        assert !new Solution().containsNearbyDuplicate(new int[]{1,2,3,1,2,3}, 2);

        assert new Solution().containsNearbyDuplicate1(new int[]{1,2,3,1}, 3);
        assert new Solution().containsNearbyDuplicate1(new int[]{1,0,1,1}, 1);
        assert !new Solution().containsNearbyDuplicate1(new int[]{1,2,3,1,2,3}, 2);
    }

}
