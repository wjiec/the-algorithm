package weekly.w391.C;

/**
 * 3101. Count Alternating Subarrays
 *
 * https://leetcode.cn/contest/weekly-contest-391/problems/count-alternating-subarrays/
 *
 * You are given a binary array nums.
 *
 * We call a subarray alternating if no two adjacent elements in the subarray have the same value.
 *
 * Return the number of alternating subarrays in nums.
 */

public class Solution {

    public long countAlternatingSubarrays(int[] nums) {
        long ans = 1, curr = 1;
        for (int i = 1; i < nums.length; i++) {
            if ((nums[i - 1] ^ nums[i]) == 1) curr++;
            else curr = 1;
            ans += curr;
        }
        return ans;
    }

    public static void main(String[] args) {
    }

}
