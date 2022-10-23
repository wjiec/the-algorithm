package problem.p2441largestpositiveintegerthatexistswithitsnegative;

import java.util.HashSet;
import java.util.Set;

/**
 * 2441. Largest Positive Integer That Exists With Its Negative
 *
 * https://leetcode.cn/problems/largest-positive-integer-that-exists-with-its-negative/
 *
 * Given an integer array nums that does not contain any zeros, find the largest positive
 * integer k such that -k also exists in the array.
 *
 * Return the positive integer k. If there is no such integer, return -1.
 */

public class Solution {

    public int findMaxK(int[] nums) {
        Set<Integer> negative = new HashSet<>();
        for (var v : nums) if (v < 0) negative.add(v);

        int ans = -1;
        for (var v : nums) {
            if (v > 0 && negative.contains(-v)) {
                ans = Math.max(ans, v);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().findMaxK(new int[]{-1,2,-3,3}) == 3;
        assert new Solution().findMaxK(new int[]{-1,10,6,7,-7,1}) == 7;
        assert new Solution().findMaxK(new int[]{-10,8,6,7,-2,-3}) == -1;
    }

}
