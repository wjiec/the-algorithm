package weekly.bw140.A;

/**
 * 3300. Minimum Element After Replacement With Digit Sum
 *
 * https://leetcode.cn/contest/biweekly-contest-140/problems/minimum-element-after-replacement-with-digit-sum/
 *
 * You are given an integer array nums.
 *
 * You replace each element in nums with the sum of its digits.
 *
 * Return the minimum element in nums after all replacements.
 */

public class Solution {

    public int minElement(int[] nums) {
        int ans = Integer.MAX_VALUE;
        for (var v : nums) {
            int curr = 0;
            for (; v != 0; v /= 10) {
                curr += v % 10;
            }
            ans = Math.min(ans, curr);
        }
        return ans;
    }

    public static void main(String[] args) {
    }

}
