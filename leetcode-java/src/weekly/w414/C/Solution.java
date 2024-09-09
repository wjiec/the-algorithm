package weekly.w414.C;

import java.util.List;

/**
 * 3282. Reach End of Array With Max Score
 *
 * https://leetcode.cn/contest/weekly-contest-414/problems/reach-end-of-array-with-max-score/
 *
 * You are given an integer array nums of length n.
 *
 * Your goal is to start at index 0 and reach index n - 1. You can only jump to indices
 * greater than your current index.
 *
 * The score for a jump from index i to index j is calculated as (j - i) * nums[i].
 *
 * Return the maximum possible total score by the time you reach the last index.
 */

public class Solution {

    public long findMaximumScore(List<Integer> nums) {
        long ans = 0; int prev = 0, n = nums.size();
        for (int i = 1; i < n; i++) {
            if (nums.get(i) > nums.get(prev) || i + 1 == n) {
                ans += ((long) i - prev) * nums.get(prev);
                prev = i;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
    }

}
