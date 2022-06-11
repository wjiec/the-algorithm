package weekly.bw80.B;

import common.Checker;

import java.util.Arrays;

/**
 * 6096. Successful Pairs of Spells and Potions
 *
 * https://leetcode.cn/contest/biweekly-contest-80/problems/successful-pairs-of-spells-and-potions/
 *
 * You are given two positive integer arrays spells and potions, of length n and m respectively, where
 * spells[i] represents the strength of the ith spell and potions[j] represents the strength of the jth potion.
 *
 * You are also given an integer success. A spell and potion pair is considered successful if the
 * product of their strengths is at least success.
 *
 * Return an integer array pairs of length n where pairs[i] is the number of potions that will
 * form a successful pair with the ith spell.
 */

public class Solution {

    public int[] successfulPairs(int[] spells, int[] potions, long success) {
        Arrays.sort(potions);

        int[] ans = new int[spells.length];
        for (int i = 0; i < spells.length; i++) {
            long target = (success + spells[i] - 1) / spells[i];
            if (target <= Integer.MAX_VALUE) {
                ans[i] = potions.length - lowerBound(potions, (int) target);
            }
        }
        return ans;
    }

    private int lowerBound(int[] nums, int target) {
        int l = 0, r = nums.length, ans = nums.length;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] >= target) {
                ans = mid;
                r = mid;
            } else l = mid + 1;
        }
        return ans;
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().successfulPairs(new int[]{5,1,3}, new int[]{1,2,3,4,5}, 7), new int[]{4,0,3});
        assert Checker.check(new Solution().successfulPairs(new int[]{3,1,2}, new int[]{8,5,8}, 16), new int[]{2,0,2});
    }

}
