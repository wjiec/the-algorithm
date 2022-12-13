package problem.p2501longestsquarestreakinanarray;

import java.util.Arrays;
import java.util.TreeSet;

/**
 * 2501. Longest Square Streak in an Array
 *
 * https://leetcode.cn/problems/longest-square-streak-in-an-array/
 *
 * You are given an integer array nums. A subsequence of nums is called a square streak if:
 *
 * The length of the subsequence is at least 2, and after sorting the subsequence, each
 * element (except the first element) is the square of the previous number.
 *
 * Return the length of the longest square streak in nums, or return -1 if there is no square streak.
 *
 * A subsequence is an array that can be derived from another array by deleting some or no
 * elements without changing the order of the remaining elements.
 */

@SuppressWarnings("ConstantConditions")
public class Solution {

    public int longestSquareStreak(int[] nums) {
        Arrays.sort(nums);

        TreeSet<Integer> set = new TreeSet<>();
        for (var v : nums) set.add(v);

        int ans = -1;
        while (!set.isEmpty()) {
            int curr = 1, val = set.pollFirst();
            while (set.contains(val * val)) {
                curr++; set.remove((val = val * val));
            }
            if (curr > 1) ans = Math.max(ans, curr);
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().longestSquareStreak(new int[]{4,3,6,16,8,2}) == 3;
        assert new Solution().longestSquareStreak(new int[]{2,3,5,6,7}) == -1;
    }

}
