package weekly.w404.C;

/**
 * 3202. Find the Maximum Length of Valid Subsequence II
 *
 * https://leetcode.cn/contest/weekly-contest-404/problems/find-the-maximum-length-of-valid-subsequence-ii/
 *
 * You are given an integer array nums and a positive integer k.
 * A subsequence sub of nums with length x is called valid if it satisfies:
 *
 * (sub[0] + sub[1]) % k == (sub[1] + sub[2]) % k == ... == (sub[x - 2] + sub[x - 1]) % k.
 *
 * Return the length of the longest valid subsequence of nums.
 */

public class Solution {

    public int maximumLength(int[] nums, int k) {
        int ans = 2;
        int[] count = new int[k];
        for (int i = 0; i < nums.length; i++) {
            ans = Math.max(ans, ++count[nums[i] %= k]);
        }

        for (int a = 0; a < k; a++) {
            for (int b = 0; b < k; b++) {
                if (a == b || count[a] + count[b] < ans) continue;

                int curr = 0;
                for (var v : nums) {
                    if ((curr & 1) == 0 && v == a) curr++;
                    if ((curr & 1) == 1 && v == b) curr++;
                }
                ans = Math.max(ans, curr);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
    }

}
