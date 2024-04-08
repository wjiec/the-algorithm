package daily.d240408p2009minimumnumberofoperationstomakearraycontinuous;

import java.util.*;

/**
 * 2009. Minimum Number of Operations to Make Array Continuous
 *
 * https://leetcode.cn/problems/minimum-number-of-operations-to-make-array-continuous
 *
 * You are given an integer array nums. In one operation, you can replace any element in nums with any integer.
 *
 * nums is considered continuous if both of the following conditions are fulfilled:
 *
 * All elements in nums are unique.
 * The difference between the maximum element and the minimum element in nums equals nums.length - 1.
 * For example, nums = [4, 2, 5, 3] is continuous, but nums = [1, 2, 3, 5, 6] is not continuous.
 *
 * Return the minimum number of operations to make nums continuous.
 */

public class Solution {

    public int minOperations(int[] nums) {
        Arrays.sort(nums);

        int ans = Integer.MAX_VALUE, n = nums.length;
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int l = 0, r = 0; r < nums.length; r++) {
            map.merge(nums[r], 1, Integer::sum);
            for (; map.lastKey() - map.firstKey() + 1 > n; l++) {
                int merged = map.merge(nums[l], -1, Integer::sum);
                if (merged == 0) map.remove(nums[l]);
            }
            ans = Math.min(ans, n - map.size());
        }

        return ans;
    }

    private static class BySet {
        public int minOperations(int[] nums) {
            Set<Integer> set = new HashSet<>();
            for (var v : nums) set.add(v);

            List<Integer> sorted = new ArrayList<>(set);
            sorted.sort(Integer::compare);

            int ans = Integer.MAX_VALUE, n = nums.length;
            for (int l = 0, r = 0; r < sorted.size(); r++) {
                while (sorted.get(r) - sorted.get(l) + 1 > n) l++;
                ans = Math.min(ans, n - (r - l + 1));
            }

            return ans;
        }
    }

    public static void main(String[] args) {
    }

}
