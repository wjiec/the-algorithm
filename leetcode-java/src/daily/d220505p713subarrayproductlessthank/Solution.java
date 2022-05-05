package daily.d220505p713subarrayproductlessthank;

/**
 * 713. Subarray Product Less Than K
 *
 * https://leetcode-cn.com/problems/subarray-product-less-than-k/
 *
 * Given an array of integers nums and an integer k, return the number of contiguous subarrays
 * where the product of all the elements in the subarray is strictly less than k.
 */

public class Solution {

    public int numSubarrayProductLessThanK(int[] nums, int k) {
        int n = nums.length, ans = 0;
        for (int l = 0, r = 0, sum = 1; r < n; r++) {
            sum *= nums[r];
            while (l <= r && sum >= k) {
                sum /= nums[l++];
            }
            ans += r - l + 1;
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().numSubarrayProductLessThanK(new int[]{10,5,2,6}, 100) == 8;
        assert new Solution().numSubarrayProductLessThanK(new int[]{1,2,3}, 0) == 0;
    }

}
