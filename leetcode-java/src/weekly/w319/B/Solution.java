package weekly.w319.B;

/**
 * 6234. Number of Subarrays With LCM Equal to K
 *
 * https://leetcode.cn/contest/weekly-contest-319/problems/number-of-subarrays-with-lcm-equal-to-k/
 *
 * Given an integer array nums and an integer k, return the number of subarrays of nums
 * where the least common multiple of the subarray's elements is k.
 *
 * A subarray is a contiguous non-empty sequence of elements within an array.
 *
 * The least common multiple of an array is the smallest positive integer
 * that is divisible by all the array elements.
 */

public class Solution {

    public int subarrayLCM(int[] nums, int k) {
        int ans = 0, n = nums.length;
        for (int i = 0; i < n; i++) {
            int val = nums[i];
            for (int j = i; j < n; j++) {
                if ((val = lcm(val, nums[j])) == k) ans++;
                if (val > k) break;
            }
        }
        return ans;
    }

    // 最大公约数
    public static int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
    // 最小公倍数
    public static int lcm(int a, int b) {
        return a / gcd(a, b) * b;
    }

    public static void main(String[] args) {
        assert new Solution().subarrayLCM(new int[]{3,6,2,7,1}, 6) == 4;
        assert new Solution().subarrayLCM(new int[]{3}, 2) == 0;
        assert new Solution().subarrayLCM(new int[]{5,1,1,1,2}, 1) == 6;
    }

}
