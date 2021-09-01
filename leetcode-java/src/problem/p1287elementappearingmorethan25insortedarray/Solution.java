package problem.p1287elementappearingmorethan25insortedarray;

/**
 * 1287. Element Appearing More Than 25% In Sorted Array
 *
 * https://leetcode-cn.com/problems/element-appearing-more-than-25-in-sorted-array/
 *
 * Given an integer array sorted in non-decreasing order,
 * there is exactly one integer in the array that occurs more than 25% of the time, return that integer.
 */

public class Solution {

    public int findSpecialInteger(int[] arr) {
        int l = arr.length, quarter = l / 4;
        for (int i = 0; i < l; i++) {
            if (i + quarter < l && arr[i + quarter] == arr[i]) {
                return arr[i];
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        assert new Solution().findSpecialInteger(new int[]{1,2,2,6,6,6,6,7,10}) == 6;
    }

}
