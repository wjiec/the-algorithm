package daily.d211023p492constructtherectangle;

import common.Checker;

/**
 * 492. Construct the Rectangle
 *
 * https://leetcode-cn.com/problems/construct-the-rectangle/
 *
 * A web developer needs to know how to design a web page's size. So, given a specific rectangular web page’s area,
 * your job by now is to design a rectangular web page, whose length L and width W satisfy the following requirements:
 *
 * The area of the rectangular web page you designed must equal to the given target area.
 * The width W should not be larger than the length L, which means L >= W.
 * The difference between length L and width W should be as small as possible.
 *
 * Return an array [L, W] where L and W are the length and width of the web page you designed in sequence.
 */

public class Solution {

    public int[] constructRectangle(int area) {
        int mid = (int) (Math.sqrt(area));
        while (area % mid != 0) mid--;
        return new int[]{area / mid, mid};
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().constructRectangle(4), new int[]{2,2});
        assert Checker.check(new Solution().constructRectangle(37), new int[]{37,1});
        assert Checker.check(new Solution().constructRectangle(122122), new int[]{427,286});
    }

}
