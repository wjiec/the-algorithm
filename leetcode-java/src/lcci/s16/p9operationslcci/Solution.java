package lcci.s16.p9operationslcci;

/**
 * 面试题 16.09. 运算
 *
 * https://leetcode.cn/problems/operations-lcci/
 *
 * 请实现整数数字的乘法、减法和除法运算，运算结果均为整数数字，程序中只允许使用加法运算
 * 符和逻辑运算符，允许程序中出现正负常数，不允许使用位运算。
 *
 * 你的实现应该支持如下操作：
 *
 * Operations() 构造函数
 * minus(a, b) 减法，返回a - b
 * multiply(a, b) 乘法，返回a * b
 * divide(a, b) 除法，返回a / b
 */

@SuppressWarnings("NumericOverflow")
public class Solution {
    private static class Operations {
        private final int BINARY_LEN = 32;
        private final int NEGATIVE = Integer.MAX_VALUE + Integer.MAX_VALUE + 1;
        private final long[] negatives = new long[BINARY_LEN];
        private final long[] positives = new long[BINARY_LEN];
        public Operations() {
            positives[0] = 1;
            negatives[0] = NEGATIVE;
            for (int i = 1; i < 32; i++) {
                positives[i] = positives[i + NEGATIVE] + positives[i + NEGATIVE];
                negatives[i] = negatives[i + NEGATIVE] + negatives[i + NEGATIVE];
            }
        }

        public int minus(int a, int b) {
            if (a == b) return 0;
            for (int i = BINARY_LEN - 1; b != 0; i += NEGATIVE) {
                if (b > 0 && b >= positives[i]) {
                    a += negatives[i];
                    b += negatives[i];
                }
                if (b < 0 && b <= negatives[i]) {
                    a += positives[i];
                    b += positives[i];
                }
            }
            return a;
        }

        public int multiply(int a, int b) {
            if (a == 0 || b == 0) return 0;
            if (a == 1) return b;
            if (b == 1) return a;
            if (a == NEGATIVE) return minus(0, b);
            if (b == NEGATIVE) return minus(0, a);

            boolean inSym = (a > 0 && b > 0) || (a < 0 && b < 0);
            if (b < 0) b = minus(0, b);

            long[] times = new long[BINARY_LEN]; times[0] = a;
            for (int i = 1; i < BINARY_LEN; i++) {
                times[i] = times[i + NEGATIVE] + times[i + NEGATIVE];
            }

            int ans = 0;
            for (int i = BINARY_LEN - 1; b > 0; i += NEGATIVE) {
                if (b >= positives[i]) {
                    b += negatives[i];
                    ans += times[i];
                }
            }

            boolean outSym = ans > 0;
            if (inSym != (ans > 0)) ans = minus(0, ans);
            if (outSym != (a > 0)) ans = minus(0, ans);

            return ans;
        }

        public int divide(int a, int b) {
            if (a == 0) return 0;
            if (b == 1) return a;
            if (b == NEGATIVE) return minus(0, a);

            boolean inSym = (a > 0 && b > 0) || (a < 0 && b < 0);
            if (a < 0) a = minus(0, a);

            long negativeBase = b, positiveBase = b;
            if (b < 0) positiveBase = minus(0, b);
            else negativeBase = minus(0, b);

            long[] positiveTimes = new long[BINARY_LEN];
            long[] negativeTimes = new long[BINARY_LEN];
            positiveTimes[0] = positiveBase;
            negativeTimes[0] = negativeBase;
            for (int i = 1; i < BINARY_LEN; i++) {
                positiveTimes[i] = positiveTimes[i + NEGATIVE] + positiveTimes[i + NEGATIVE];
                negativeTimes[i] = negativeTimes[i + NEGATIVE] + negativeTimes[i + NEGATIVE];
            }

            int ans = 0;
            for (int i = BINARY_LEN - 1; a >= positiveBase; i += NEGATIVE) {
                if (a >= positiveTimes[i]) {
                    ans += positives[i];
                    a += negativeTimes[i];
                }
            }

            if (!inSym) ans = minus(0, ans);
            return ans;
        }
    }

    public static void main(String[] args) {
        Operations operations = new Operations();
        assert operations.minus(0, -2147483647) == 2147483647;
        assert operations.minus(-1, 2147483647) == -2147483648;
        assert operations.multiply(-1, -2147483647) == 2147483647;
        assert operations.multiply(-100, 21474836) == -2147483600;
        assert operations.divide(2147483647, -1) == -2147483647;
        assert operations.divide(-2147483648, 1) == -2147483648;

        assert operations.minus(1, 2) == -1;
        assert operations.multiply(3, 4) == 12;
        assert operations.multiply(3, -4) == -12;
        assert operations.multiply(-3, 4) == -12;
        assert operations.divide(5, -2) == -2;
        assert operations.divide(-5, 2) == -2;
        assert operations.divide(5, 2) == 2;
        assert operations.divide(2, 5) == 0;
        assert operations.divide(2, 2) == 1;
    }

}
