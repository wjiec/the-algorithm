package problem.p480slidingwindowmedian;

import common.Checker;
import daily.d230118p1825findingmkaverage.Solution.MKAverage;

/**
 * 480. Sliding Window Median
 *
 * https://leetcode.cn/problems/sliding-window-median/
 *
 * The median is the middle value in an ordered integer list.
 * If the size of the list is even, there is no middle value.
 * So the median is the mean of the two middle values.
 *
 * For examples, if arr = [2,3,4], the median is 3.
 * For examples, if arr = [1,2,3,4], the median is (2 + 3) / 2 = 2.5.
 *
 * You are given an integer array nums and an integer k. There is a sliding
 * window of size k which is moving from the very left of the array to the very right.
 *
 * You can only see the k numbers in the window. Each time the sliding window
 * moves right by one position.
 *
 * Return the median array for each window in the original array.
 *
 * Answers within 10-5 of the actual value will be accepted.
 */

public class Solution {

    public double[] medianSlidingWindow(int[] nums, int k) {
        if (k == 1) {
            double[] ans = new double[nums.length];
            for (int i = 0; i < nums.length; i++) {
                ans[i] = nums[i];
            }
            return ans;
        }

        double[] ans = new double[nums.length - k + 1];
        MKAverage average = new MKAverage(k, (k - (k % 2 == 0 ? 2 : 1)) / 2);
        for (int i = 0, j = 0; i < nums.length; i++) {
            average.addElement(nums[i]);
            if (i + 1 >= k) ans[j++] = average.calculateMKAverage();
        }
        return ans;
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().medianSlidingWindow(new int[]{1,4,2,3}, 4),
            new double[]{2.500000});

        assert Checker.check(new Solution().medianSlidingWindow(new int[]{1,3,-1,-3,5,3,6,7}, 3),
            new double[]{1.00000,-1.00000,-1.00000,3.00000,5.00000,6.00000});

        assert Checker.check(new Solution().medianSlidingWindow(new int[]{1,2,3,4,2,3,1,4,2}, 3),
            new double[]{2.00000,3.00000,3.00000,3.00000,2.00000,3.00000,2.00000});
    }

}
