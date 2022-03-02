package problem.p1228missingnumberinarithmeticprogression;

/**
 * 1228. Missing Number In Arithmetic Progression
 *
 * https://leetcode-cn.com/problems/missing-number-in-arithmetic-progression/
 *
 * In some array arr, the values were in arithmetic progression:
 * the values arr[i + 1] - arr[i] are all equal for every 0 <= i < arr.length - 1.
 *
 * A value from arr was removed that was not the first or last value in the array.
 *
 * Given arr, return the removed value.
 */

public class Solution {

    public int missingNumber(int[] arr) {
        int k = (arr[arr.length - 1] - arr[0]) / arr.length;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] - arr[i - 1] != k) {
                return arr[i] - k;
            }
        }
        return arr[0];
    }

    public static void main(String[] args) {
        assert new Solution().missingNumber(new int[]{5,7,11,13}) == 9;
        assert new Solution().missingNumber(new int[]{15,13,12}) == 14;
    }

}
