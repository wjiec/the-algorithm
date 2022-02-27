package problem.p1134armstrongnumber;

import java.util.ArrayList;
import java.util.List;

/**
 * 1134. Armstrong Number
 *
 * https://leetcode-cn.com/problems/armstrong-number/
 *
 * Given an integer n, return true if and only if it is an Armstrong number.
 *
 * The k-digit number n is an Armstrong number if and only if the kth power of each digit sums to n.
 */

public class Solution {

    public boolean isArmstrong(int n) {
        List<Integer> digits = new ArrayList<>();
        for (int v = n; v != 0; v /= 10) {
            digits.add(v % 10);
        }

        int v = 0;
        for (var digit : digits) {
            v += (int) Math.pow(digit, digits.size());
        }
        return v == n;
    }

    public static void main(String[] args) {
        assert new Solution().isArmstrong(153);
        assert !new Solution().isArmstrong(123);
    }

}
