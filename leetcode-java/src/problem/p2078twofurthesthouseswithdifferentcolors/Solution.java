package problem.p2078twofurthesthouseswithdifferentcolors;

/**
 * 2078. Two Furthest Houses With Different Colors
 *
 * https://leetcode-cn.com/problems/two-furthest-houses-with-different-colors/
 *
 * There are n houses evenly lined up on the street, and each house is beautifully painted.
 *
 * You are given a 0-indexed integer array colors of length n, where colors[i] represents the color of the ith house.
 *
 * Return the maximum distance between two houses with different colors.
 *
 * The distance between the ith and jth houses is abs(i - j), where abs(x) is the absolute value of x.
 */

public class Solution {

    public int maxDistance(int[] colors) {
        int ans = 0, l = colors.length;
        for (int i = 0; i < l - ans; i++) {
            for (int j = l - 1; j >= i; j--) {
                if (colors[i] != colors[j]) {
                    ans = Math.max(ans, j - i);
                    break;
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().maxDistance(new int[]{6,6,6,6,6,6,6,6,6,19,19,6,6}) == 10;

        assert new Solution().maxDistance(new int[]{1,1,1,6,1,1,1}) == 3;
        assert new Solution().maxDistance(new int[]{1,8,3,8,3}) == 4;
        assert new Solution().maxDistance(new int[]{0,1}) == 1;
    }

}
