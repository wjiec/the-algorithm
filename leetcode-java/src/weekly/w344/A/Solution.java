package weekly.w344.A;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 6416. Find the Distinct Difference Array
 *
 * https://leetcode.cn/contest/weekly-contest-344/problems/find-the-distinct-difference-array/
 *
 * You are given a 0-indexed array nums of length n.
 *
 * The distinct difference array of nums is an array diff of length n such that diff[i] is equal to
 * the number of distinct elements in the suffix nums[i + 1, ..., n - 1] subtracted from
 * the number of distinct elements in the prefix nums[0, ..., i].
 *
 * Return the distinct difference array of nums.
 *
 * Note that nums[i, ..., j] denotes the subarray of nums starting at index i and ending
 * at index j inclusive. Particularly, if i > j then nums[i, ..., j] denotes an empty subarray.
 */

public class Solution {

    public int[] distinctDifferenceArray(int[] nums) {
        Map<Integer, Integer> suffix = new HashMap<>();
        for (var v : nums) suffix.merge(v, 1, Integer::sum);

        int[] ans = new int[nums.length];
        Set<Integer> uniq = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            uniq.add(nums[i]);
            suffix.merge(nums[i], -1, (a, b) -> a + b == 0 ? null : a + b);
            ans[i] = uniq.size() - suffix.size();
        }
        return ans;
    }

    public static void main(String[] args) {
    }

}
