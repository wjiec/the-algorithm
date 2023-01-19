package offer2.p39;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 剑指 Offer II 039. 直方图最大矩形面积
 *
 * https://leetcode.cn/problems/0ynMMM
 *
 * 给定非负整数数组 heights ，数组中的数字用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
 *
 * 求在该柱状图中，能够勾勒出来的矩形的最大面积。
 */

public class Solution {

    public int largestRectangleArea(int[] heights) {
        int n = heights.length;
        // left[i] 表示从左至右第一个小于 height[i] 的位置
        int[] left = new int[n];
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && heights[stack.peek()] > heights[i]) {
                left[stack.pop()] = i;
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) left[stack.pop()] = n;

        // right[i] 表示从右往左第一个小于 height[i] 的位置
        int[] right = new int[n];
        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && heights[stack.peek()] > heights[i]) {
                right[stack.pop()] = i;
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) right[stack.pop()] = -1;

        int ans = 0;
        for (int i = 0; i < n; i++) {
            ans = Math.max(ans, (left[i] - right[i] - 1) * heights[i]);
        }

        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().largestRectangleArea(new int[]{2,1,5,6,2,3}) == 10;
        assert new Solution().largestRectangleArea(new int[]{2,4}) == 4;
    }

}
