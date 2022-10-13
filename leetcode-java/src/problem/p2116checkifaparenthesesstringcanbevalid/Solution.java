package problem.p2116checkifaparenthesesstringcanbevalid;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 2116. Check if a Parentheses String Can Be Valid
 *
 * https://leetcode.cn/problems/check-if-a-parentheses-string-can-be-valid/
 *
 * A parentheses string is a non-empty string consisting only of '(' and ')'.
 * It is valid if any of the following conditions is true:
 *
 * It is ().
 * It can be written as AB (A concatenated with B), where A and B are valid parentheses strings.
 * It can be written as (A), where A is a valid parentheses string.
 * You are given a parentheses string s and a string locked, both of length n.
 * locked is a binary string consisting only of '0's and '1's. For each index i of locked,
 *
 * If locked[i] is '1', you cannot change s[i].
 * But if locked[i] is '0', you can change s[i] to either '(' or ')'.
 * Return true if you can make s a valid parentheses string. Otherwise, return false.
 */

public class Solution {

    public boolean canBeValid(String s, String locked) {
        int n = s.length();
        if (n % 2 == 1) return false;

        int opens = 0;
        char[] chars = s.toCharArray();
        char[] locks = locked.toCharArray();
        Deque<Integer> blank = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            if (locks[i] == '0') {
                blank.add(i);
                chars[i] = '.';
            } else opens += chars[i] == '(' ? 1 : -1;
        }

        if (opens < 0) {
            for (int i = n - 1; !blank.isEmpty() && i > blank.peekFirst(); i--) {
                if (chars[i] == ')') chars[blank.removeFirst()] = '(';
            }
        }

        int open = 0, close = 0;
        for (int i = 0; i < n; i++) {
            if (chars[i] == '(') open++;
            else if (chars[i] == ')') close++;
        }

        for (int i = 0; i < n && open < close; i++) {
            if (chars[i] == '.') {
                chars[i] = '('; open++;
            }
        }
        for (int i = n - 1; i >= 0 && open > close; i--) {
            if (chars[i] == '.') {
                chars[i] = ')'; close++;
            }
        }

        for (int l = 0, r = n - 1; l < r; ) {
            if (chars[l] == '.' && chars[r] == '.') {
                chars[l++] = '('; chars[r--] = ')';
                continue;
            }

            if (chars[l] != '.') l++;
            if (chars[r] != '.') r--;
        }

        opens = 0;
        for (int i = 0; i < n; i++) {
            opens += chars[i] == '(' ? 1 : -1;
            if (opens < 0) return false;
        }
        return opens == 0;
    }

    public static void main(String[] args) {
        assert new Solution().canBeValid("(())()", "110110");

        assert new Solution().canBeValid(
            "()()))()())(((()((()((()((()()()))(()()((()((()()(((()())))))()((()(()(())((()()((())))))))(())(())))()()()((()())())(()()))((((((())()())())))))())((((()())(())(())()()()(()(()((()))((((()((()((()())(())((((())(())))(()((((())))((()(((((()()))))((((()))))())()))))())",
            "0111000100000011110100010110101001110111010111110111111011101000100000011101010000110110001100100100100011000001110101101110011000000011111000111111111001011101100000100111010111010000001100011101000110101011001000100100111110110110100101010111111001000010000010010010"
        );
        assert new Solution().canBeValid("()", "11");

        assert new Solution().canBeValid("))()))", "010100");
        assert new Solution().canBeValid("()()", "0000");
        assert !new Solution().canBeValid(")", "0");
    }

}
