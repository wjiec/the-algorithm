package problem.p640solvetheequation;

/**
 * 640. Solve the Equation
 *
 * https://leetcode-cn.com/problems/solve-the-equation/
 *
 * Solve a given equation and return the value of 'x' in the form of a string "x=#value".
 * The equation contains only '+', '-' operation, the variable 'x' and its coefficient.
 * You should return "No solution" if there is no solution for the equation,
 * or "Infinite solutions" if there are infinite solutions for the equation.
 *
 * If there is exactly one solution for the equation, we ensure that the value of 'x' is an integer.
 */

public class Solution {

    public String solveEquation(String equation) {
        int index = equation.indexOf('=');

        int[] left = parse(equation.substring(0, index));
        int[] right = parse(equation.substring(index + 1));

        int factor = left[1] - right[1], number = right[0] - left[0];
        if (factor == 0) return number == 0 ? "Infinite solutions" : "No solution";
        return String.format("x=%d", number / factor);
    }

    // [number, factor]
    private int[] parse(String expression) {
        boolean isFactor = false, hasNumber = false;
        int number = 0, factor = 0, curr = 0, base = 1;
        for (int i = 0, n = expression.length(); i <= n; i++) {
            char c = i == n ? '+' : expression.charAt(i);

            if ('0' <= c && c <= '9') {
                hasNumber = true;
                curr = curr * 10 + (c - '0');
            } else if (c == 'x') {
                if (!hasNumber && curr == 0) curr =1;
                isFactor = true; hasNumber = false;
            } else if (c == '-' || c == '+') {
                if (isFactor) factor += curr * base;
                else number += curr * base;

                hasNumber = false; isFactor = false;
                curr = 0; base = c == '-' ? -1 : 1;
            }
        }
        return new int[]{number, factor};
    }

    public static void main(String[] args) {
        assert new Solution().solveEquation("0x=0").equals("Infinite solutions");
        assert new Solution().solveEquation("2+2-x+x+3x=x+2x-x+x+4").equals("Infinite solutions");
        assert new Solution().solveEquation("1+1=x").equals("x=2");
        assert new Solution().solveEquation("x=x+2").equals("No solution");
        assert new Solution().solveEquation("2x+3x-6x=x+2").equals("x=-1");

        assert new Solution().solveEquation("x+5-3+x=6+x-2").equals("x=2");
        assert new Solution().solveEquation("x=x").equals("Infinite solutions");
        assert new Solution().solveEquation("2x=x").equals("x=0");
    }

}
