package weekly.w291.p2kdivisibleelementssubarrays;

import java.util.HashSet;
import java.util.Set;

/**
 * 6049. K Divisible Elements Subarrays
 *
 * https://leetcode-cn.com/contest/weekly-contest-291/problems/k-divisible-elements-subarrays/
 *
 * Given an integer array nums and two integers k and p, return the number of distinct subarrays
 * which have at most k elements divisible by p.
 *
 * Two arrays nums1 and nums2 are said to be distinct if:
 *
 * They are of different lengths, or
 * There exists at least one index i where nums1[i] != nums2[i].
 * A subarray is defined as a non-empty contiguous sequence of elements in an array.
 */

public class Solution {

    public int countDistinct(int[] nums, int k, int p) {
        Set<String> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            int count = 0;
            StringBuilder sb = new StringBuilder();
            for (int j = i; j < nums.length; j++) {
                if (nums[j] % p == 0) count++;
                if (count <= k) {
                    sb.append(nums[j]).append(".");
                    set.add(sb.toString());
                } else break;
            }
        }
        return set.size();
    }

    public static void main(String[] args) {
        assert new Solution().countDistinct(new int[]{2,3,3,2,2}, 2, 2) == 11;
        assert new Solution().countDistinct(new int[]{1,2,3,4}, 4, 1) == 10;
    }

}
