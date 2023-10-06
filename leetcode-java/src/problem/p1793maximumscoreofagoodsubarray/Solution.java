package problem.p1793maximumscoreofagoodsubarray;

/**
 * 1793. Maximum Score of a Good Subarray
 *
 * https://leetcode.cn/problems/maximum-score-of-a-good-subarray
 *
 * You are given an array of integers nums (0-indexed) and an integer k.
 *
 * The score of a subarray (i, j) is defined as min(nums[i], nums[i+1], ..., nums[j]) * (j - i + 1).
 * A good subarray is a subarray where i <= k <= j.
 *
 * Return the maximum possible score of a good subarray.
 */

public class Solution {

    public int maximumScore(int[] nums, int k) {
        int ans = 0, n = nums.length;
        for (int i = nums[k], l = k, r = k; i > 0; i--) {
            while (r < n && nums[r] >= i) r++;
            while (l >= 0 && nums[l] >= i) l--;
            ans = Math.max(ans, (r - l - 1) * i);
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().maximumScore(new int[]{1,4,3,7,4,5}, 3) == 15;
        assert new Solution().maximumScore(new int[]{5,5,4,5,4,1,1,1}, 0) == 20;
    }

}
