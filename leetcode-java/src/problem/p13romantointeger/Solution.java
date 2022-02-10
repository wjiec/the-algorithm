package problem.p13romantointeger;

/**
 * 13. Roman to Integer
 *
 * https://leetcode-cn.com/problems/roman-to-integer/
 *
 * Roman numerals are represented by seven different symbols: I, V, X, L, C, D and M.
 *
 * Symbol       Value
 * I             1
 * V             5
 * X             10
 * L             50
 * C             100
 * D             500
 * M             1000
 * For example, 2 is written as II in Roman numeral, just two one's added together. 12 is written as XII,
 * which is simply X + II. The number 27 is written as XXVII, which is XX + V + II.
 *
 * Roman numerals are usually written largest to smallest from left to right. However,
 * the numeral for four is not IIII. Instead, the number four is written as IV.
 * Because the one is before the five we subtract it making four. The same principle applies to the number nine,
 * which is written as IX. There are six instances where subtraction is used:
 *
 * I can be placed before V (5) and X (10) to make 4 and 9. 
 * X can be placed before L (50) and C (100) to make 40 and 90. 
 * C can be placed before D (500) and M (1000) to make 400 and 900.
 *
 * Given a roman numeral, convert it to an integer.
 */

public class Solution {

    public int romanToInt(String s) {
        int rs = 0;
        for (int i = 0; i < s.length(); i++) {
            switch (s.charAt(i)) {
                case 'I':
                    rs += 1;
                    break;
                case 'V':
                    rs += 5;
                    if (i != 0 && s.charAt(i - 1) == 'I') {
                        rs -= 2;
                    }
                    break;
                case 'X':
                    rs += 10;
                    if (i != 0 && s.charAt(i - 1) == 'I') {
                        rs -= 2;
                    }
                    break;
                case 'L':
                    rs += 50;
                    if (i != 0 && s.charAt(i - 1) == 'X') {
                        rs -= 20;
                    }
                    break;
                case 'C':
                    rs += 100;
                    if (i != 0 && s.charAt(i - 1) == 'X') {
                        rs -= 20;
                    }
                    break;
                case 'D':
                    rs += 500;
                    if (i != 0 && s.charAt(i - 1) == 'C') {
                        rs -= 200;
                    }
                    break;
                case 'M':
                    rs += 1000;
                    if (i != 0 && s.charAt(i - 1) == 'C') {
                        rs -= 200;
                    }
                    break;
            }
        }

        return rs;
    }

    public static void main(String[] args) {
        assert new Solution().romanToInt("III") == 3;
        assert new Solution().romanToInt("IV") == 4;
        assert new Solution().romanToInt("IX") == 9;
        assert new Solution().romanToInt("VII") == 7;
        assert new Solution().romanToInt("LVIII") == 58;
        assert new Solution().romanToInt("MCMXCIV") == 1994;
        assert new Solution().romanToInt("DCXXI") == 621;
    }

}
