package daily.d230512p1330reversesubarraytomaximizearrayvalue;

/**
 * 1330. Reverse Subarray To Maximize Array Value
 *
 * https://leetcode.cn/problems/reverse-subarray-to-maximize-array-value/
 *
 * You are given an integer array nums. The value of this array is defined
 * as the sum of |nums[i] - nums[i + 1]| for all 0 <= i < nums.length - 1.
 *
 * You are allowed to select any subarray of the given array and reverse it.
 * You can perform this operation only once.
 *
 * Find maximum possible value of the final array.
 */

public class Solution {

    public int maxValueAfterReverse(int[] nums) {
        int tot = 0, n = nums.length, sum = 0;
        int lox = Integer.MIN_VALUE, him = Integer.MAX_VALUE;
        for (int i = 1; i < n; i++) {
            int l = nums[i - 1], r = nums[i];
            int d = Math.abs(l - r);
            sum += d;
            lox = Math.max(lox, Math.min(l, r));
            him = Math.min(him, Math.max(l, r));
            tot = Math.max(tot, Math.max(Math.abs(nums[0] - r) - d, Math.abs(l - nums[n - 1]) - d));
        }
        return Math.max((lox - him) * 2, tot) + sum;
    }

    public static void main(String[] args) {
        assert new Solution().maxValueAfterReverse(new int[]{2,3,1,5,4}) == 10;
        assert new Solution().maxValueAfterReverse(new int[]{2,4,9,24,2,1,10}) == 68;
    }

}
