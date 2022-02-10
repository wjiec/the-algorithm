package problem.p2160minimumsumoffourdigitnumberaftersplittingdigits;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 2160. Minimum Sum of Four Digit Number After Splitting Digits
 *
 * https://leetcode-cn.com/problems/minimum-sum-of-four-digit-number-after-splitting-digits/
 *
 * You are given a positive integer num consisting of exactly four digits.
 *
 * Split num into two new integers new1 and new2 by using the digits found in num.
 *
 * Leading zeros are allowed in new1 and new2, and all the digits found in num must be used.
 *
 * For example, given num = 2932, you have the following digits:
 *      two 2's, one 9 and one 3.
 * Some possible pairs [new1, new2] are [22, 93], [23, 92], [223, 9] and [2, 329].
 *
 * Return the minimum possible sum of new1 and new2.
 */

public class Solution {

    public int minimumSum(int num) {
        List<Integer> digits = new ArrayList<>();
        for (; num != 0; num /= 10) digits.add(num % 10);
        Collections.sort(digits);

        return 10 * (digits.get(0) + digits.get(1)) + digits.get(2) + digits.get(3);
    }

    public static void main(String[] args) {
        assert new Solution().minimumSum(2932) == 52;
        assert new Solution().minimumSum(4009) == 13;
        assert new Solution().minimumSum(1000) == 1;
    }

}
