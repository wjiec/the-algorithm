package offer.p20biaoshishuzhidezifuchuanlcof;

import java.util.HashMap;
import java.util.Map;

/**
 * 剑指 Offer 20. 表示数值的字符串
 *
 * https://leetcode-cn.com/problems/biao-shi-shu-zhi-de-zi-fu-chuan-lcof/
 *
 * 请实现一个函数用来判断字符串是否表示数值（包括整数和小数）。
 *
 * 数值（按顺序）可以分成以下几个部分：
 *
 * 若干空格
 * 一个 小数 或者 整数
 * （可选）一个 'e' 或 'E' ，后面跟着一个 整数
 * 若干空格
 * 小数（按顺序）可以分成以下几个部分：
 *
 * （可选）一个符号字符（'+' 或 '-'）
 * 下述格式之一：
 * 至少一位数字，后面跟着一个点 '.'
 * 至少一位数字，后面跟着一个点 '.' ，后面再跟着至少一位数字
 * 一个点 '.' ，后面跟着至少一位数字
 * 整数（按顺序）可以分成以下几个部分：
 *
 * （可选）一个符号字符（'+' 或 '-'）
 * 至少一位数字
 * 部分数值列举如下：
 *
 * ["+100", "5e2", "-123", "3.1416", "-1E-16", "0123"]
 * 部分非数值列举如下：
 *
 * ["12e", "1a3.14", "1.2.3", "+-5", "12e+5.4"]
 */

public class Solution {

    public boolean isNumber(String s) {
        Map<State, Map<CharType, State>> machine = new HashMap<>() {{
            put(State.STATE_INITIAL, new HashMap<>() {{
                put(CharType.CHAR_SPACE, State.STATE_INITIAL);
                put(CharType.CHAR_SIGN, State.STATE_INTEGER_WITH_SIGN);
                put(CharType.CHAR_NUMBER, State.STATE_INTEGER);
                put(CharType.CHAR_POINT, State.STATE_FRACTION_WITHOUT_INTEGER);
            }});

            put(State.STATE_INTEGER_WITH_SIGN, new HashMap<>() {{
                put(CharType.CHAR_NUMBER, State.STATE_INTEGER);
                put(CharType.CHAR_POINT, State.STATE_FRACTION_WITHOUT_INTEGER);
            }});

            put(State.STATE_INTEGER, new HashMap<>() {{
                put(CharType.CHAR_NUMBER, State.STATE_INTEGER);
                put(CharType.CHAR_POINT, State.STATE_FRACTION);
                put(CharType.CHAR_EXPONENT, State.STATE_EXPONENT);
                put(CharType.CHAR_SPACE, State.STATE_END);
            }});

            put(State.STATE_FRACTION_WITHOUT_INTEGER, new HashMap<>() {{
                put(CharType.CHAR_NUMBER, State.STATE_FRACTION);
            }});

            put(State.STATE_FRACTION, new HashMap<>() {{
                put(CharType.CHAR_NUMBER, State.STATE_FRACTION);
                put(CharType.CHAR_EXPONENT, State.STATE_EXPONENT);
                put(CharType.CHAR_SPACE, State.STATE_END);
            }});

            put(State.STATE_EXPONENT, new HashMap<>() {{
                put(CharType.CHAR_SIGN, State.STATE_EXPONENT_SIGN);
                put(CharType.CHAR_NUMBER, State.STATE_EXPONENT_INTEGER);
            }});

            put(State.STATE_EXPONENT_SIGN, new HashMap<>() {{
                put(CharType.CHAR_NUMBER, State.STATE_EXPONENT_INTEGER);
            }});

            put(State.STATE_EXPONENT_INTEGER, new HashMap<>() {{
                put(CharType.CHAR_NUMBER, State.STATE_EXPONENT_INTEGER);
                put(CharType.CHAR_SPACE, State.STATE_END);
            }});

            put(State.STATE_END, new HashMap<>() {{
                put(CharType.CHAR_SPACE, State.STATE_END);
            }});
        }};

        State state = State.STATE_INITIAL;
        for (var c : s.toCharArray()) {
            state = machine.get(state).getOrDefault(toCharType(c), State.STATE_ILLEGAL);
            if (state == State.STATE_ILLEGAL) return false;
        }
        return state == State.STATE_END || state == State.STATE_INTEGER || state == State.STATE_FRACTION
            || state == State.STATE_EXPONENT_INTEGER;
    }

    private CharType toCharType(char c) {
        if (c == ' ') return CharType.CHAR_SPACE;
        if (c == '.') return CharType.CHAR_POINT;
        if (c == '+' || c == '-') return CharType.CHAR_SIGN;
        if (c >= '0' && c <= '9') return CharType.CHAR_NUMBER;
        if (c == 'e' || c == 'E') return CharType.CHAR_EXPONENT;
        return CharType.CHAR_ILLEGAL;
    }

    private enum State {
        STATE_INITIAL,
        STATE_INTEGER_WITH_SIGN,
        STATE_INTEGER,
        STATE_FRACTION_WITHOUT_INTEGER,
        STATE_FRACTION,
        STATE_EXPONENT,
        STATE_EXPONENT_SIGN,
        STATE_EXPONENT_INTEGER,
        STATE_ILLEGAL,
        STATE_END
    }

    private enum CharType {
        CHAR_SPACE,
        CHAR_SIGN,
        CHAR_NUMBER,
        CHAR_POINT,
        CHAR_EXPONENT,
        CHAR_ILLEGAL
    }

    public static void main(String[] args) {
        assert new Solution().isNumber("0");
        assert !new Solution().isNumber("e");
        assert !new Solution().isNumber(".");
        assert new Solution().isNumber("    .1  ");

        assert !new Solution().isNumber("7e69e");
        assert new Solution().isNumber("2");
        assert new Solution().isNumber("0089");
        assert new Solution().isNumber("-0.1");
        assert new Solution().isNumber("+3.14");
        assert new Solution().isNumber("4.");
        assert new Solution().isNumber("-.9");
        assert new Solution().isNumber("2e10");
        assert new Solution().isNumber("-90E3");
        assert new Solution().isNumber("3e+7");
        assert new Solution().isNumber("+6e-1");
        assert new Solution().isNumber("53.5e93");
        assert new Solution().isNumber("-123.456e789");
        assert !new Solution().isNumber("abc");
        assert !new Solution().isNumber("1a");
        assert !new Solution().isNumber("1e");
        assert !new Solution().isNumber("e3");
        assert !new Solution().isNumber("99e2.5");
        assert !new Solution().isNumber("--6");
        assert !new Solution().isNumber("-+3");
        assert !new Solution().isNumber("95a54e53");
    }

}
