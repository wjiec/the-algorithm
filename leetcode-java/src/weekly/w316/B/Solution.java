package weekly.w316.B;

/**
 * 6224. Number of Subarrays With GCD Equal to K
 *
 * https://leetcode.cn/contest/weekly-contest-316/problems/number-of-subarrays-with-gcd-equal-to-k/
 *
 * Given an integer array nums and an integer k, return the number of subarrays of nums
 * where the greatest common divisor of the subarray's elements is k.
 *
 * A subarray is a contiguous non-empty sequence of elements within an array.
 *
 * The greatest common divisor of an array is the largest integer that evenly divides all the array elements.
 */

public class Solution {

    public int subarrayGCD(int[] nums, int k) {
        int ans = 0, n = nums.length;
        for (int i = 0; i < n; i++) {
            int gg = nums[i];
            for (int j = i; j < n; j++) {
                gg = gcd(gg, nums[j]);
                if (gg == k) ans++;
                else if (gg < k) break;
            }
        }
        return ans;
    }

    // 最大公约数
    public static int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    public static void main(String[] args) {
    }

}
