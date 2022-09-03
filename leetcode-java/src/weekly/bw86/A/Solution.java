package weekly.bw86.A;

import java.util.HashSet;
import java.util.Set;

/**
 * 6171. Find Subarrays With Equal Sum
 *
 * https://leetcode.cn/contest/biweekly-contest-86/problems/find-subarrays-with-equal-sum/
 *
 * Given a 0-indexed integer array nums, determine whether there exist two subarrays of length 2
 * with equal sum. Note that the two subarrays must begin at different indices.
 *
 * Return true if these subarrays exist, and false otherwise.
 *
 * A subarray is a contiguous non-empty sequence of elements within an array.
 */

public class Solution {

    public boolean findSubarrays(int[] nums) {
        Set<Integer> sum = new HashSet<>();
        for (int i = 1; i < nums.length; i++) {
            int s = nums[i] + nums[i - 1];
            if (sum.contains(s)) return true;
            sum.add(s);
        }
        return false;
    }

    public static void main(String[] args) {
    }

}
