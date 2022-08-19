package problem.p1558minimumnumbersoffunctioncallstomaketargetarray;

/**
 * 1558. Minimum Numbers of Function Calls to Make Target Array
 *
 * https://leetcode.cn/problems/minimum-numbers-of-function-calls-to-make-target-array/
 *
 * You are given an integer array nums. You have an integer array arr of the same length with all values
 * set to 0 initially. You also have the following modify function:
 *  func modify(arr, op, idx) {
 *      if (op == 0) {
 *          arr[idx] = arr[idx] + 1
 *      }
 *      if (op == 1) {
 *          for (int i = 0; i < arr.length; i++) {
 *              arr[i] = arr[i] * 2;
 *          }
 *      }
 *  }
 *
 * You want to use the modify function to covert arr to nums using the minimum number of calls.
 *
 * Return the minimum number of function calls to make nums from arr.
 *
 * The test cases are generated so that the answer fits in a 32-bit signed integer.
 */

public class Solution {

    public int minOperations(int[] nums) {
        int ans = 0, max = 0;
        for (var num : nums) {
            if (num > max) max = num;
            ans += Integer.bitCount(num);
        }

        if (max != 0) {
            for (ans--; max != 0; max >>= 1) ans++;
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().minOperations(new int[]{1,5}) == 5;
        assert new Solution().minOperations(new int[]{2,2}) == 3;
        assert new Solution().minOperations(new int[]{4,2,5}) == 6;
        assert new Solution().minOperations(new int[]{3,2,2,4}) == 7;
        assert new Solution().minOperations(new int[]{2,4,8,16}) == 8;
    }

}
