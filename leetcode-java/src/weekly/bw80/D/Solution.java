package weekly.bw80.D;

/**
 * 6098. Count Subarrays With Score Less Than K
 *
 * https://leetcode.cn/contest/biweekly-contest-80/problems/count-subarrays-with-score-less-than-k/
 *
 * The score of an array is defined as the product of its sum and its length.
 *
 * For example, the score of [1, 2, 3, 4, 5] is (1 + 2 + 3 + 4 + 5) * 5 = 75.
 * Given a positive integer array nums and an integer k, return the number of non-empty subarrays of nums
 * whose score is strictly less than k.
 *
 * A subarray is a contiguous sequence of elements within an array.
 */

public class Solution {

    public long countSubarrays(int[] nums, long k) {
        long ans = 0, sum = 0;
        for (int l = 0, r = 0; r < nums.length; r++) {
            sum += nums[r];
            while ((r - l + 1) * sum >= k) {
                sum -= nums[l++];
            }
            ans += r - l + 1;
        }
        return ans;
    }

    public static void main(String[] args) {
    }

}
