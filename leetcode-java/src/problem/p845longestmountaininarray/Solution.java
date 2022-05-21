package problem.p845longestmountaininarray;

/**
 * 845. Longest Mountain in Array
 *
 * https://leetcode.cn/problems/longest-mountain-in-array/
 *
 * You may recall that an array arr is a mountain array if and only if:
 *
 * arr.length >= 3
 * There exists some index i (0-indexed) with 0 < i < arr.length - 1 such that:
 * arr[0] < arr[1] < ... < arr[i - 1] < arr[i]
 * arr[i] > arr[i + 1] > ... > arr[arr.length - 1]
 *
 * Given an integer array arr, return the length of the longest subarray, which is a mountain.
 * Return 0 if there is no mountain subarray.
 */

public class Solution {

    public int longestMountain(int[] arr) {
        int ans = 0, n = arr.length;
        for (int i = 1; i < n - 1; i++) {
            if (arr[i] > arr[i - 1] && arr[i] > arr[i + 1]) {
                int curr = 1;
                for (int l = i - 1; l >= 0 && arr[l] < arr[l + 1]; l--) curr++;
                for (int r = i + 1; r < n && arr[r] < arr[r - 1]; r++) curr++;
                if (curr > ans) ans = curr;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().longestMountain(new int[]{2,1,4,7,3,2,5}) == 5;
        assert new Solution().longestMountain(new int[]{2,2,2}) == 0;
    }

}
