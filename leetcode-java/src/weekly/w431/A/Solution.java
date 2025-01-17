package weekly.w431.A;

import static ability.Ability.Math.gcd;
import static ability.Ability.Math.lcm;

/**
 * 3411. Maximum Subarray With Equal Products
 *
 * https://leetcode.cn/contest/weekly-contest-431/problems/maximum-subarray-with-equal-products/
 *
 * You are given an array of positive integers nums.
 *
 * An array arr is called product equivalent if prod(arr) == lcm(arr) * gcd(arr), where:
 *
 * prod(arr) is the product of all elements of arr.
 * gcd(arr) is the GCD of all elements of arr.
 * lcm(arr) is the LCM of all elements of arr.
 *
 * Return the length of the longest product equivalent subarray of nums.
 */

public class Solution {

    public int maxLength(int[] nums) {
        int ans = 0;
        for (int i = 0; i < nums.length; i++) {
            long prod = 1, lcm = 1, gcd = 0;
            for (int j = i; j < nums.length; j++) {
                prod *= nums[j];
                lcm = lcm(lcm, nums[j]);
                gcd = gcd(gcd, nums[j]);
                if (prod == lcm * gcd) ans = Math.max(ans, j - i + 1);
            }
        }

        return ans;
    }

    public static void main(String[] args) {
    }

}
