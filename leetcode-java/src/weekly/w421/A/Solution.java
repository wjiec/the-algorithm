package weekly.w421.A;

import java.util.ArrayDeque;

import static ability.Ability.Math.gcd;
import static ability.Ability.Math.lcm;

/**
 * 3334. Find the Maximum Factor Score of Array
 *
 * https://leetcode.cn/contest/weekly-contest-421/problems/find-the-maximum-factor-score-of-array/
 *
 * You are given an integer array nums.
 *
 * The factor score of an array is defined as the product of the LCM and GCD of all elements of that array.
 *
 * Return the maximum factor score of nums after removing at most one element from it.
 *
 * Note that both the LCM and GCD of a single number are the number itself, and the factor score of an empty array is 0.
 */

public class Solution {

    public long maxScore(int[] nums) {
        if (nums.length == 0) return 0;
        if (nums.length == 1) return (long) nums[0] * nums[0];

        long ans = 0;
        for (int i = -1; i < nums.length; i++) {
            ArrayDeque<Long> g = new ArrayDeque<>();
            ArrayDeque<Long> l = new ArrayDeque<>();
            for (int j = 0; j < nums.length; j++) {
                if (i == j) continue;

                if (g.isEmpty()) g.push((long) nums[j]);
                else g.push(gcd(nums[j], g.pop()));

                if (l.isEmpty()) l.push((long) nums[j]);
                else l.push(lcm(nums[j], l.pop()));
            }

            ans = Math.max(ans, g.pop() * l.pop());
        }

        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().maxScore(new int[]{6, 14, 20}) == 840;
    }

}
