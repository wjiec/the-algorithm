package weekly.w400.D;

/**
 * 3171. Find Subarray With Bitwise AND Closest to K
 *
 * https://leetcode.cn/contest/weekly-contest-400/problems/find-subarray-with-bitwise-and-closest-to-k/
 *
 * You are given an array nums and an integer k. You need to find a subarray of nums such that the
 * absolute difference between k and the bitwise AND of the subarray elements is as small as possible.
 *
 * In other words, select a subarray nums[l..r] such that |k - (nums[l] AND nums[l + 1] ... AND nums[r])| is minimum.
 *
 * Return the minimum possible value of the absolute difference.
 *
 * A subarray is a contiguous non-empty sequence of elements within an array.
 */

public class Solution {

    public int minimumDifference(int[] nums, int k) {
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            ans = Math.min(ans, Math.abs(nums[i] - k));
            for (int j = i - 1; j >= 0; j--) {
                if ((nums[i] & nums[j]) != nums[j]) {
                    ans = Math.min(ans, Math.abs(k - (nums[j] &= nums[i])));
                } else break;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
    }

}
