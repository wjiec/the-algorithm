package weekly.w472.B;

import java.util.HashSet;
import java.util.Set;

/**
 * Q2. Longest Balanced Subarray I
 *
 * https://leetcode.cn/contest/weekly-contest-472/problems/longest-balanced-subarray-i/
 *
 * You are given an integer array nums.
 *
 * A subarray is called balanced if the number of distinct even numbers in
 * the subarray is equal to the number of distinct odd numbers.
 *
 * Return the length of the longest balanced subarray.
 */

public class Solution {

    public int longestBalanced(int[] nums) {
        int ans = 0, n = nums.length;
        for (int i = 0; i < n - ans; i++) {
            Set<Integer> odd = new HashSet<>();
            Set<Integer> even = new HashSet<>();
            for (int j = i; j < n; j++) {
                if (nums[j] % 2 == 0) even.add(nums[j]); else odd.add(nums[j]);
                if (odd.size() == even.size()) ans = Math.max(ans, j - i + 1);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
    }

}
