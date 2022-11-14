package problem.p2465numberofdistinctaverages;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 6237. Number of Distinct Averages
 *
 * https://leetcode.cn/problems/number-of-distinct-averages/
 *
 * You are given a 0-indexed integer array nums of even length.
 *
 * As long as nums is not empty, you must repetitively:
 *
 * Find the minimum number in nums and remove it.
 * Find the maximum number in nums and remove it.
 * Calculate the average of the two removed numbers.
 * The average of two numbers a and b is (a + b) / 2.
 *
 * For example, the average of 2 and 3 is (2 + 3) / 2 = 2.5.
 * Return the number of distinct averages calculated using the above process.
 *
 * Note that when there is a tie for a minimum or maximum number, any can be removed.
 */

public class Solution {

    public int distinctAverages(int[] nums) {
        Set<Double> unique = new HashSet<>();

        Arrays.sort(nums);
        for (int l = 0, r = nums.length - 1; l < r; l++, r--) {
            unique.add(((double) nums[l] + nums[r]) / 2.0);
        }
        return unique.size();
    }

    public static void main(String[] args) {
        assert new Solution().distinctAverages(new int[]{4,1,4,0,3,5}) == 2;
        assert new Solution().distinctAverages(new int[]{1,100}) == 1;
    }

}
