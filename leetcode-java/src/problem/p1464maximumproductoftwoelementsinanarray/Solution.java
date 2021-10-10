package problem.p1464maximumproductoftwoelementsinanarray;

/**
 * 1464. Maximum Product of Two Elements in an Array
 *
 * https://leetcode-cn.com/problems/maximum-product-of-two-elements-in-an-array/
 *
 * Given the array of integers nums, you will choose two different indices i and j of that array.
 *
 * Return the maximum value of (nums[i]-1)*(nums[j]-1).
 */

public class Solution {

    public int maxProduct(int[] nums) {
        int a = 0, b = 0;
        for (var n : nums) {
            if (n >= a) {
                b = a;
                a = n;
            } else if (n >= b) {
                b = n;
            }
        }
        return (a - 1) * (b - 1);
    }

    public static void main(String[] args) {
        assert new Solution().maxProduct(new int[]{10,2,5,2}) == 36;

        assert new Solution().maxProduct(new int[]{3,4,5,2}) == 12;
        assert new Solution().maxProduct(new int[]{1,5,4,5}) == 16;
        assert new Solution().maxProduct(new int[]{3,7}) == 12;
    }

}
