package problem.p1343numberofsubarraysofsizekandaveragegreaterthanorequaltothreshold;

/**
 * 1343. Number of Sub-arrays of Size K and Average Greater than or Equal to Threshold
 *
 * https://leetcode.cn/problems/number-of-sub-arrays-of-size-k-and-average-greater-than-or-equal-to-threshold/
 *
 * Given an array of integers arr and two integers k and threshold, return the number of sub-arrays of size k
 * and average greater than or equal to threshold.
 */

public class Solution {

    public int numOfSubarrays(int[] arr, int k, int threshold) {
        int ans = 0, n = arr.length, sum = 0;
        for (int l = 0, r = 0; r < n; r++) {
            sum += arr[r];
            if (r - l + 1 > k) sum -= arr[l++];
            if (r - l + 1 == k && sum / k >= threshold) ans++;
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().numOfSubarrays(new int[]{2,2,2,2,5,5,5,8}, 3, 4) == 3;
        assert new Solution().numOfSubarrays(new int[]{11,13,17,23,29,31,7,5,2,3}, 3, 5) == 6;
    }

}
