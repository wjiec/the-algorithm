package problem.p941validmountainarray;

/**
 * 941. Valid Mountain Array
 *
 * https://leetcode-cn.com/problems/valid-mountain-array/
 *
 * Given an array of integers arr, return true if and only if it is a valid mountain array.
 *
 * Recall that arr is a mountain array if and only if:
 *
 * arr.length >= 3
 * There exists some i with 0 < i < arr.length - 1 such that:
 *  arr[0] < arr[1] < ... < arr[i - 1] < arr[i]
 *  arr[i] > arr[i + 1] > ... > arr[arr.length - 1]
 */

public class Solution {

    public boolean validMountainArray(int[] arr) {
        int n = arr.length, idx = 1;
        if (n < 3) return false;

        while (idx < n && arr[idx] > arr[idx - 1]) idx++;
        if (idx == n || idx == 1) return false;
        while (idx < n && arr[idx] < arr[idx - 1]) idx++;
        return idx == n;
    }

    public static void main(String[] args) {
        assert !new Solution().validMountainArray(new int[]{2,1});
        assert !new Solution().validMountainArray(new int[]{3,5,5});
        assert new Solution().validMountainArray(new int[]{0,3,2,1});
        assert !new Solution().validMountainArray(new int[]{1,2,3,4,5});
        assert !new Solution().validMountainArray(new int[]{5,4,3,2,1});
        assert new Solution().validMountainArray(new int[]{1,3,2});
    }

}
