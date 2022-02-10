package daily.d210829p1588sumofalloddlengthsubarrays;

/**
 * 1588. Sum of All Odd Length Subarrays
 *
 * https://leetcode-cn.com/problems/sum-of-all-odd-length-subarrays/
 *
 * Given an array of positive integers arr, calculate the sum of all possible odd-length subarrays.
 *
 * A subarray is a contiguous subsequence of the array.
 *
 * Return the sum of all odd-length subarrays of arr.
 */

public class Solution {

    public int sumOddLengthSubarrays(int[] arr) {
        int[] prefix = new int[arr.length + 1];
        for (int i = 0; i < arr.length; i++) {
            prefix[i + 1] += prefix[i] + arr[i];
        }

        int ans = 0, l = arr.length;
        for (int i = 0; i < l; i++) {
            for (int j = 1; i + j <= l; j += 2) {
                ans += prefix[i + j] - prefix[i];
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().sumOddLengthSubarrays(new int[]{1,4,2,5,3}) == 58;
        assert new Solution().sumOddLengthSubarrays(new int[]{1,2}) == 3;
        assert new Solution().sumOddLengthSubarrays(new int[]{10,11,12}) == 66;
    }

}
