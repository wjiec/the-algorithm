package weekly.bw178.B;

import java.util.Arrays;

/**
 * Q2. Sum of GCD of Formed Pairs
 *
 * https://leetcode.cn/contest/biweekly-contest-178/problems/sum-of-gcd-of-formed-pairs/
 *
 * You are given an integer array nums of length n.
 *
 * Construct an array prefixGcd where for each index i:
 *
 * Let mxi = max(nums[0], nums[1], ..., nums[i]).
 * prefixGcd[i] = gcd(nums[i], mxi).
 * After constructing prefixGcd:
 *
 * Sort prefixGcd in non-decreasing order.
 * Form pairs by taking the smallest unpaired element and the largest unpaired element.
 * Repeat this process until no more pairs can be formed.
 * For each formed pair, compute the gcd of the two elements.
 * If n is odd, the middle element in the prefixGcd array remains unpaired and should be ignored.
 * Return an integer denoting the sum of the GCD values of all formed pairs.
 *
 * The term gcd(a, b) denotes the greatest common divisor of a and b.
 */

public class Solution {

    public long gcdSum(int[] nums) {
        long[] pre = new long[nums.length];
        for (int i = 0, mx = 0; i < nums.length; i++) {
            pre[i] = gcd(mx = Math.max(mx, nums[i]), nums[i]);
        }
        Arrays.sort(pre);

        long ans = 0;
        for (int l = 0, r = pre.length - 1; l < r; l++, r--) {
            ans += gcd(pre[l], pre[r]);
        }
        return ans;
    }

    private long gcd(long a, long b) { return a % b == 0 ? b : gcd(b, a % b); }

    public static void main(String[] args) {
    }

}
