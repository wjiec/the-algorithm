package problem.p1394findluckyintegerinanarray;

import java.util.Arrays;

/**
 * 1394. Find Lucky Integer in an Array
 *
 * https://leetcode-cn.com/problems/find-lucky-integer-in-an-array/
 *
 * Given an array of integers arr, a lucky integer is an integer which has a frequency in the array equal to its value.
 *
 * Return a lucky integer in the array. If there are multiple lucky integers return the largest of them.
 *
 * If there is no lucky integer return -1.
 */

public class Solution {

    public int findLucky(int[] arr) {
        Arrays.sort(arr);
        for (int i = arr.length - 1, j = i - 1; i >= 0; i = j) {
            for (; j >= 0 && arr[j] == arr[i]; j--);
            if (arr[i] == i - j) return arr[i];
        }
        return -1;
    }

    public static void main(String[] args) {
        assert new Solution().findLucky(new int[]{2,2,3,4}) == 2;
        assert new Solution().findLucky(new int[]{1,2,2,3,3,3}) == 3;
        assert new Solution().findLucky(new int[]{2,2,2,3,3}) == -1;
        assert new Solution().findLucky(new int[]{5}) == -1;
        assert new Solution().findLucky(new int[]{7,7,7,7,7,7,7}) == 7;
    }

}
