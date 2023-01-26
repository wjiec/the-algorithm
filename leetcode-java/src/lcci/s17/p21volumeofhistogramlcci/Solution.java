package lcci.s17.p21volumeofhistogramlcci;

/**
 * 面试题 17.21. 直方图的水量
 *
 * https://leetcode.cn/problems/volume-of-histogram-lcci/
 *
 * 给定一个直方图(也称柱状图)，假设有人从上面源源不断地倒水，最后直方图能存多少水量?直方图的宽度为1
 */

@SuppressWarnings("DuplicatedCode")
public class Solution {

    public int trap(int[] height) {
        if (height.length == 0) return 0;

        int[] left = new int[height.length];
        left[0] = height[0];
        for (int i = 1; i < left.length; i++) {
            left[i] = Math.max(left[i - 1], height[i]);
        }

        int[] right = new int[height.length];
        right[right.length - 1] = height[height.length - 1];
        for (int i = height.length - 2; i >= 0; i--) {
            right[i] = Math.max(right[i + 1], height[i]);
        }

        int ans = 0;
        for (int i = 0; i < height.length; i++) {
            ans += Math.max(Math.min(left[i], right[i]) - height[i], 0);
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().trap(new int[]{0,1,0,2,1,0,1,3,2,1,2,1}) == 6;
    }

}
