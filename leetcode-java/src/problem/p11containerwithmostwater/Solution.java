package problem.p11containerwithmostwater;

/**
 * 11. Container With Most Water
 *
 * https://leetcode-cn.com/problems/container-with-most-water/
 *
 * You are given an integer array height of length n. There are n vertical lines drawn such that
 * the two endpoints of the ith line are (i, 0) and (i, height[i]).
 *
 * Find two lines that together with the x-axis form a container, such that the container contains the most water.
 *
 * Return the maximum amount of water a container can store.
 *
 * Notice that you may not slant the container.
 */

public class Solution {

    public int maxArea(int[] height) {
        int l = 0, r = height.length - 1, ans = 0;
        while (l < r) {
            ans = Math.max(ans, (r - l) * Math.min(height[l], height[r]));
            if (height[l] <= height[r]) l++;
            else r--;
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().maxArea(new int[]{1,8,6,2,5,4,8,3,7}) == 49;
        assert new Solution().maxArea(new int[]{1,1}) == 1;
    }

}
