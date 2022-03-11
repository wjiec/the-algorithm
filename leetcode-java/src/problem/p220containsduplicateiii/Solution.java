package problem.p220containsduplicateiii;

import java.util.TreeSet;

/**
 * 220. Contains Duplicate III
 *
 * https://leetcode-cn.com/problems/contains-duplicate-iii/
 *
 * Given an integer array nums and two integers k and t, return true if there are two distinct
 * indices i and j in the array such that abs(nums[i] - nums[j]) <= t and abs(i - j) <= k.
 */

public class Solution {

    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        TreeSet<Long> set = new TreeSet<>();
        for (int i = 0; i < nums.length; i++) {
            Long ceiling = set.ceiling((long) nums[i] - (long) t);
            if (ceiling != null && ceiling <= (long) nums[i] + (long) t) {
                return true;
            }

            set.add((long) nums[i]);
            if (i >= k) set.remove((long) nums[i - k]);
        }
        return false;
    }

    public static void main(String[] args) {
        assert new Solution().containsNearbyAlmostDuplicate(new int[]{1,2,3,1}, 3, 0);
        assert new Solution().containsNearbyAlmostDuplicate(new int[]{1,0,1,1}, 1, 2);
        assert !new Solution().containsNearbyAlmostDuplicate(new int[]{1,5,9,1,5,9}, 2, 3);
    }

}
