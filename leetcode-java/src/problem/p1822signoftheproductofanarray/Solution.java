package problem.p1822signoftheproductofanarray;

import java.awt.*;

/**
 * 1822. Sign of the Product of an Array
 *
 * https://leetcode-cn.com/problems/sign-of-the-product-of-an-array/
 *
 * There is a function signFunc(x) that returns:
 *
 * 1 if x is positive.
 * -1 if x is negative.
 * 0 if x is equal to 0.
 * You are given an integer array nums. Let product be the product of all values in the array nums.
 *
 * Return signFunc(product).
 */

public class Solution {

    public int arraySign(int[] nums) {
        boolean positive = true;
        for (int num : nums) {
            if (num == 0) {
                return 0;
            } else if (num < 0) {
                positive = !positive;
            }
        }
        return positive ? 1 : -1;
    }

    public static void main(String[] args) {
        assert new Solution().arraySign(new int[]{-1,-2,-3,-4,3,2,1}) == 1;
        assert new Solution().arraySign(new int[]{1,5,0,2,-3}) == 0;
        assert new Solution().arraySign(new int[]{-1,1,-1,1,-1}) == -1;
        assert new Solution().arraySign(new int[]{1,1,1,1,1}) == 1;
    }

}
