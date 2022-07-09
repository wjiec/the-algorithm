package weekly.bw82.C;

/**
 * 6118. Minimum Sum of Squared Difference
 *
 * https://leetcode.cn/contest/biweekly-contest-82/problems/minimum-sum-of-squared-difference/
 *
 * You are given two positive 0-indexed integer arrays nums1 and nums2, both of length n.
 *
 * The sum of squared difference of arrays nums1 and nums2 is defined as the
 * sum of (nums1[i] - nums2[i])2 for each 0 <= i < n.
 *
 * You are also given two positive integers k1 and k2. You can modify any of the elements of nums1
 * by +1 or -1 at most k1 times. Similarly, you can modify any of the elements of nums2 by +1 or -1 at most k2 times.
 *
 * Return the minimum sum of squared difference after modifying array nums1 at most k1 times
 * and modifying array nums2 at most k2 times.
 *
 * Note: You are allowed to modify the array elements to become negative integers.
 */

public class Solution {

    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
        long[] diff = new long[100001];
        for (int i = 0; i < nums1.length; i++) {
            diff[Math.abs(nums1[i] - nums2[i])]++;
        }

        int k = k1 + k2;
        for (int i = diff.length - 1; i > 0; i--) {
            if (k > diff[i]) {
                diff[i - 1] += diff[i];
                k -= diff[i];
                diff[i] = 0;
            } else {
                diff[i - 1] += k;
                diff[i] -= k;
                break;
            }
        }

        long ans = 0;
        for (int i = 1; i < diff.length; i++) {
            if (diff[i] != 0) {
                ans += diff[i] * i * i;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
    }

}
