package problem.p239slidingwindowmaximum;

import java.util.TreeMap;

/**
 * 239. Sliding Window Maximum
 *
 * https://leetcode.cn/problems/sliding-window-maximum
 *
 * You are given an array of integers nums, there is a sliding window of size k
 * which is moving from the very left of the array to the very right.
 *
 * You can only see the k numbers in the window. Each time the sliding window moves right by one position.
 *
 * Return the max sliding window.
 */

public class Solution {

    @SuppressWarnings("DuplicatedCode")
    public int[] maxSlidingWindow(int[] nums, int k) {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int i = 0; i < k - 1; i++) map.merge(nums[i], 1, Solution::sum);

        int[] ans = new int[nums.length - k + 1];
        for (int i = k - 1; i < nums.length; i++) {
            map.merge(nums[i], 1, Integer::sum);
            ans[i - k + 1] = map.lastKey();
            map.merge(nums[i - k + 1], -1, Solution::sum);
        }
        return ans;
    }

    private static Integer sum(Integer a, Integer b) { return a + b == 0 ? null : a + b; }

    public static void main(String[] args) {
    }

}
