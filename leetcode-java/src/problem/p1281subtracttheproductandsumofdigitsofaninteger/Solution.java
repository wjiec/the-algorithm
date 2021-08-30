package problem.p1281subtracttheproductandsumofdigitsofaninteger;

import com.sun.jdi.PathSearchingVirtualMachine;

/**
 * 1281. Subtract the Product and Sum of Digits of an Integer
 *
 * https://leetcode-cn.com/problems/subtract-the-product-and-sum-of-digits-of-an-integer/
 *
 * Given an integer number n, return the difference between the product of its digits and the sum of its digits.
 */

public class Solution {

    public int subtractProductAndSum(int n) {
        int mul = 1, sum = 0;
        for (; n != 0; n /= 10) {
            mul *= n % 10;
            sum += n % 10;
        }
        return mul - sum;
    }

    public static void main(String[] args) {
        assert new Solution().subtractProductAndSum(234) == 15;
        assert new Solution().subtractProductAndSum(4421) == 21;
    }

}
