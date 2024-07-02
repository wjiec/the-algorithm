package weekly.w404.B;

/**
 * 3201. Find the Maximum Length of Valid Subsequence I
 *
 * https://leetcode.cn/contest/weekly-contest-404/problems/find-the-maximum-length-of-valid-subsequence-i/
 *
 * You are given an integer array nums.
 * A subsequence sub of nums with length x is called valid if it satisfies:
 *
 * (sub[0] + sub[1]) % 2 == (sub[1] + sub[2]) % 2 == ... == (sub[x - 2] + sub[x - 1]) % 2.
 * Return the length of the longest valid subsequence of nums.
 *
 * A subsequence is an array that can be derived from another array by deleting some or no elements
 * without changing the order of the remaining elements.
 */

public class Solution {

    public int maximumLength(int[] nums) {
        int odd = 0, even = 0;
        for (var v : nums) {
            if ((v & 1) == 1) odd++;
            else even++;
        }

        int ans = Math.max(odd, even);
        for (int i = 0, o = 1, e = 0, oc = 0, ec = 0; i < nums.length; i++) {
            if ((nums[i] & 1) != o) { oc++; o ^= 1; }
            if ((nums[i] & 1) != e) { ec++; e ^= 1; }
            ans = Math.max(ans, Math.max(oc, ec));
        }
        return ans;
    }

    public static void main(String[] args) {
    }

}
