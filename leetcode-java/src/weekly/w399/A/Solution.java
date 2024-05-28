package weekly.w399.A;

/**
 * 3162. Find the Number of Good Pairs I
 *
 * https://leetcode.cn/contest/weekly-contest-399/problems/find-the-number-of-good-pairs-i/
 *
 * You are given 2 integer arrays nums1 and nums2 of lengths n and m respectively.
 *
 * You are also given a positive integer k.
 *
 * A pair (i, j) is called good if nums1[i] is divisible by nums2[j] * k (0 <= i <= n - 1, 0 <= j <= m - 1).
 *
 * Return the total number of good pairs.
 */

public class Solution {

    public int numberOfPairs(int[] nums1, int[] nums2, int k) {
        int ans = 0;
        for (int i = 0; i < nums1.length; i++) {
            for (int j = 0; j < nums2.length; j++) {
                if (nums1[i] % (nums2[j] * k) == 0) ans++;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
    }

}
