package daily.d210514p12integertoroman;

import java.util.Map;

/**
 * 12. Integer to Roman
 *
 * https://leetcode-cn.com/problems/integer-to-roman/
 *
 * Roman numerals are represented by seven different symbols: I, V, X, L, C, D and M.
 *
 * For example, 2 is written as II in Roman numeral, just two one's added together.
 * 12 is written as XII, which is simply X + II. The number 27 is written as XXVII, which is XX + V + II.
 *
 * Roman numerals are usually written largest to smallest from left to right.
 * However, the numeral for four is not IIII. Instead, the number four is written as IV.
 * Because the one is before the five we subtract it making four.
 * The same principle applies to the number nine, which is written as IX.
 * There are six instances where subtraction is used:
 *
 * I can be placed before V (5) and X (10) to make 4 and 9. 
 * X can be placed before L (50) and C (100) to make 40 and 90. 
 * C can be placed before D (500) and M (1000) to make 400 and 900.
 *
 * Given an integer, convert it to a roman numeral.
 */

public class Solution {

    public String intToRoman(int num) {
        Object[] mapping = new Object[]{
            1, "I", 4, "IV", 5, "V", 9, "IX",
            10, "X", 40, "XL", 50, "L", 90, "XC",
            100, "C", 400, "CD", 500, "D", 900, "CM", 1000, "M",
        };
        StringBuilder sb = new StringBuilder();
        while (num > 0) {
            int index = 24; // 1000
            for (int i = 0; i < mapping.length; i += 2) {
                if ((int) mapping[i] == num) {
                    index = i;
                    break;
                } else if ((int) mapping[i] > num) {
                    index = i - 2;
                    break;
                }
            }

            sb.append(mapping[index + 1]);
            num -= (int) mapping[index];
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        assert new Solution().intToRoman(1).equals("I");
        assert new Solution().intToRoman(2).equals("II");
        assert new Solution().intToRoman(3).equals("III");
        assert new Solution().intToRoman(4).equals("IV");
        assert new Solution().intToRoman(5).equals("V");
        assert new Solution().intToRoman(9).equals("IX");
        assert new Solution().intToRoman(58).equals("LVIII");
        assert new Solution().intToRoman(1994).equals("MCMXCIV");
    }

}
