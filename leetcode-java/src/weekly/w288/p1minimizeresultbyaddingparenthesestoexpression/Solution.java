package weekly.w288.p1minimizeresultbyaddingparenthesestoexpression;

/**
 * 6038. Minimize Result by Adding Parentheses to Expression
 *
 * https://leetcode-cn.com/contest/weekly-contest-288/problems/minimize-result-by-adding-parentheses-to-expression/
 *
 * You are given a 0-indexed string expression of the form "<num1>+<num2>"
 * where <num1> and <num2> represent positive integers.
 *
 * Add a pair of parentheses to expression such that after the addition of parentheses,
 * expression is a valid mathematical expression and evaluates to the smallest possible value.
 *
 * The left parenthesis must be added to the left of '+' and the right parenthesis must be added to the right of '+'.
 *
 * Return expression after adding a pair of parentheses such that expression evaluates to the smallest possible value.
 * If there are multiple answers that yield the same result, return any of them.
 *
 * The input has been generated such that the original value of expression, and the value of expression
 * after adding any pair of parentheses that meets the requirements fits within a signed 32-bit integer.
 */

public class Solution {

    public String minimizeResult(String expression) {
        int plus = 0;
        while (expression.charAt(plus) != '+') plus++;

        String ans = "";
        int min = Integer.MAX_VALUE;
        for (String a = "", b = expression.substring(0, plus); !b.isEmpty(); ) {
            for (String c = expression.substring(plus + 1), d = ""; !c.isEmpty(); ) {
                int v = calc(a, b, c, d);
                if (v < min) {
                    min = v;
                    ans = a + "(" + b + "+" + c + ")" + d;
                }

                d = c.charAt(c.length() - 1) + d;
                c = c.substring(0, c.length() - 1);
            }

            a = a + b.charAt(0);
            b = b.substring(1);
        }
        return ans;
    }

    private int calc(String a, String b, String c, String d) {
        int v1 = a.isEmpty() ? 1 : Integer.valueOf(a, 10);
        int v2 = Integer.valueOf(b, 10);
        int v3 = Integer.valueOf(c, 10);
        int v4 = d.isEmpty() ? 1 : Integer.valueOf(d, 10);

        return v1 * (v2 + v3) * v4;
    }

    public static void main(String[] args) {
        assert new Solution().minimizeResult("247+38").equals("2(47+38)");
        assert new Solution().minimizeResult("12+34").equals("1(2+3)4");
        assert new Solution().minimizeResult("999+999").equals("(999+999)");
        assert new Solution().minimizeResult("1+1").equals("(1+1)");
    }

}
