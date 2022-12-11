package problem.p42trappingrainwater;

/**
 * 42. Trapping Rain Water
 *
 * https://leetcode.cn/problems/trapping-rain-water/
 *
 * Given n non-negative integers representing an elevation map where the
 * width of each bar is 1, compute how much water it can trap after raining.
 */

public class Solution {

    public int trap(int[] height) {
        int ans = 0, n = height.length, curr = 0;

        int prev = height[0];
        for (int i = 1; i < n; i++) {
            if (height[i] >= prev) {
                ans += curr;
                curr = 0;
                prev = height[i];
            } curr += prev - height[i];
        }

        curr = 0; prev = height[n - 1];
        for (int i = n - 1; i >= 0; i--) {
            if (height[i] > prev) {
                ans += curr;
                curr = 0;
                prev = height[i];
            } else curr += prev - height[i];
        }

        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().trap(new int[]{0,1,0,2,1,0,1,3,2,1,2,1}) == 6;
        assert new Solution().trap(new int[]{4,2,0,3,2,5}) == 9;
    }

}
