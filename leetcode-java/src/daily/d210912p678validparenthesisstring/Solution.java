package daily.d210912p678validparenthesisstring;

/**
 * 678. Valid Parenthesis String
 *
 * https://leetcode-cn.com/problems/valid-parenthesis-string/
 *
 * Given a string s containing only three types of characters: '(', ')' and '*', return true if s is valid.
 *
 * The following rules define a valid string:
 *
 * Any left parenthesis '(' must have a corresponding right parenthesis ')'.
 * Any right parenthesis ')' must have a corresponding left parenthesis '('.
 * Left parenthesis '(' must go before the corresponding right parenthesis ')'.
 * '*' could be treated as a single right parenthesis ')' or a single left parenthesis '(' or an empty string "".
 */

public class Solution {

    public boolean checkValidString(String s) {
        int min = 0, max = 0;
        for (var c : s.toCharArray()) {
            if (c == '(') {
                min++; max++;
            } else if (c == ')') {
                min = Math.max(0, min - 1);
                if (--max < 0) return false;
            } else {
                min = Math.max(min - 1, 0);
                max++;
            }
        }
        return min == 0;
    }

    public static void main(String[] args) {
        assert !new Solution().checkValidString("(");

        assert new Solution().checkValidString("()");
        assert new Solution().checkValidString("(*)");
        assert new Solution().checkValidString("(*))");
    }

}
