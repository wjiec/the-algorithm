package problem.p1725numberofrectanglesthatcanformthelargestsquare;

import java.util.HashMap;
import java.util.Map;

/**
 * 1725. Number Of Rectangles That Can Form The Largest Square
 *
 * https://leetcode-cn.com/problems/number-of-rectangles-that-can-form-the-largest-square/
 *
 * You are given an array rectangles where rectangles[i] = [li, wi]
 * represents the ith rectangle of length li and width wi.
 *
 * You can cut the ith rectangle to form a square with a side length of k
 * if both k <= li and k <= wi.
 *
 * For example, if you have a rectangle [4,6], you can cut it to get a square with a side length of at most 4.
 *
 * Let maxLen be the side length of the largest square you can obtain from any of the given rectangles.
 *
 * Return the number of rectangles that can make a square with a side length of maxLen.
 */

public class Solution {

    public int countGoodRectangles(int[][] rectangles) {
        int max = 0, ans = 0;
        for (var rectangle : rectangles) {
            int val = Math.min(rectangle[0], rectangle[1]);
            if (val > max) {
                ans = 1;
                max = val;
            } else if (val == max) ans++;
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().countGoodRectangles(new int[][]{
            {5,8},
            {3,9},
            {5,12},
            {16,5}
        }) == 3;

        assert new Solution().countGoodRectangles(new int[][]{
            {2,3},
            {3,7},
            {4,3},
            {3,7}
        }) == 3;
    }

}
