package weekly.bw116.A;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 100094. Subarrays Distinct Element Sum of Squares I
 *
 * https://leetcode.cn/contest/biweekly-contest-116/problems/subarrays-distinct-element-sum-of-squares-i/
 *
 * You are given a 0-indexed integer array nums.
 *
 * The distinct count of a subarray of nums is defined as:
 *
 * Let nums[i..j] be a subarray of nums consisting of all the indices
 * from i to j such that 0 <= i <= j < nums.length. Then the number of distinct
 * values in nums[i..j] is called the distinct count of nums[i..j].
 *
 * Return the sum of the squares of distinct counts of all subarrays of nums.
 *
 * A subarray is a contiguous non-empty sequence of elements within an array.
 */

public class Solution {

    public int sumCounts(List<Integer> nums) {
        long ans = 0; int n = nums.size();
        for (int i = 0; i < n; i++) {
            Set<Integer> uniq = new HashSet<>();
            for (int j = i; j < n; j++) {
                uniq.add(nums.get(j));
                ans += (long) uniq.size() * uniq.size();
            }
        }
        return (int) (ans % 1_000_000_007);
    }

    public static void main(String[] args) {
    }

}
