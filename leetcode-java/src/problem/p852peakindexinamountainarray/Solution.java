package problem.p852peakindexinamountainarray;

/**
 * 852. Peak Index in a Mountain Array
 *
 * https://leetcode-cn.com/problems/peak-index-in-a-mountain-array/
 *
 * Let's call an array arr a mountain if the following properties hold:
 *
 * arr.length >= 3
 * There exists some i with 0 < i < arr.length - 1 such that:
 *  arr[0] < arr[1] < ... arr[i-1] < arr[i]
 *  arr[i] > arr[i+1] > ... > arr[arr.length - 1]
 *
 * Given an integer array arr that is guaranteed to be a mountain,
 * return any i such that arr[0] < arr[1] < ... arr[i - 1] < arr[i] > arr[i + 1] > ... > arr[arr.length - 1].
 */

public class Solution {

    public int peakIndexInMountainArray(int[] arr) {
        int l = 0, r = arr.length - 1;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (arr[mid] > arr[mid - 1] && arr[mid] > arr[mid + 1]) {
                return mid;
            } else if (arr[mid] < arr[mid - 1]) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }

            if (r == 1) return r;
        }
        return l;
    }

    public static void main(String[] args) {
        assert new Solution().peakIndexInMountainArray(new int[]{3,5,3,2,0}) == 1;
        assert new Solution().peakIndexInMountainArray(new int[]{0,1,0}) == 1;
        assert new Solution().peakIndexInMountainArray(new int[]{0,2,1,0}) == 1;
        assert new Solution().peakIndexInMountainArray(new int[]{0,10,5,2}) == 1;
        assert new Solution().peakIndexInMountainArray(new int[]{3,4,5,1}) == 2;
        assert new Solution().peakIndexInMountainArray(new int[]{24,69,100,99,79,78,67,36,26,19}) == 2;
        assert new Solution().peakIndexInMountainArray(new int[]{24,69,70,71,72,73,74,88,1000,19}) == 8;
    }

}
