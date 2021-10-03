package daily.d211003p166fractiontorecurringdecimal;

import java.util.HashMap;
import java.util.Map;

/**
 * 166. Fraction to Recurring Decimal
 *
 * https://leetcode-cn.com/problems/fraction-to-recurring-decimal/
 *
 * Given two integers representing the numerator and denominator of a fraction, return the fraction in string format.
 *
 * If the fractional part is repeating, enclose the repeating part in parentheses.
 *
 * If multiple answers are possible, return any of them.
 *
 * It is guaranteed that the length of the answer string is less than 104 for all the given inputs.
 */

public class Solution {

    public String fractionToDecimal(int numerator, int denominator) {
        long numeratorLong = numerator;
        long denominatorLong = denominator;
        if (numeratorLong % denominatorLong == 0) {
            return String.valueOf(numeratorLong / denominatorLong);
        }

        StringBuilder sb = new StringBuilder();
        if (numeratorLong < 0 ^ denominatorLong < 0) {
            sb.append('-');
        }

        // 整数部分
        numeratorLong = Math.abs(numeratorLong);
        denominatorLong = Math.abs(denominatorLong);
        long integerPart = numeratorLong / denominatorLong;
        sb.append(integerPart);
        sb.append('.');

        // 小数部分
        StringBuilder fractionPart = new StringBuilder();
        Map<Long, Integer> remainderIndexMap = new HashMap<>();
        long remainder = numeratorLong % denominatorLong;
        int index = 0;
        while (remainder != 0 && !remainderIndexMap.containsKey(remainder)) {
            remainderIndexMap.put(remainder, index);
            remainder *= 10;
            fractionPart.append(remainder / denominatorLong);
            remainder %= denominatorLong;
            index++;
        }
        if (remainder != 0) { // 有循环节
            int insertIndex = remainderIndexMap.get(remainder);
            fractionPart.insert(insertIndex, '(');
            fractionPart.append(')');
        }
        sb.append(fractionPart);

        return sb.toString();
    }

    public static void main(String[] args) {
        assert new Solution().fractionToDecimal(1, 2).equals("0.5");
        assert new Solution().fractionToDecimal(2, 1).equals("2");
        assert new Solution().fractionToDecimal(2, 3).equals("0.(6)");
        assert new Solution().fractionToDecimal(4, 333).equals("0.(012)");
        assert new Solution().fractionToDecimal(1, 5).equals("0.2");
    }

}
