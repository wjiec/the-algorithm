package problem.p1695maximumerasurevalue;

import java.util.HashSet;
import java.util.Set;

/**
 * 1695. Maximum Erasure Value
 *
 * https://leetcode.cn/problems/maximum-erasure-value/
 *
 * You are given an array of positive integers nums and want to erase a subarray containing unique elements.
 * The score you get by erasing the subarray is equal to the sum of its elements.
 *
 * Return the maximum score you can get by erasing exactly one subarray.
 *
 * An array b is called to be a subarray of a if it forms a contiguous subsequence of a, that is, if it is
 * equal to a[l],a[l+1],...,a[r] for some (l,r).
 */

public class Solution {

    public int maximumUniqueSubarray(int[] nums) {
        int ans = 0, curr = 0;
        Set<Integer> uniq = new HashSet<>();
        for (int l = 0, r = 0; r < nums.length; r++) {
            while (uniq.contains(nums[r])) {
                curr -= nums[l]; uniq.remove(nums[l++]);
            }
            uniq.add(nums[r]); curr += nums[r];
            if (curr > ans) ans = curr;
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().maximumUniqueSubarray(new int[]{4,2,4,5,6}) == 17;
        assert new Solution().maximumUniqueSubarray(new int[]{5,2,1,2,5,2,1,2,5}) == 8;
    }

}
