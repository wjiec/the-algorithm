package problem.p592fractionadditionandsubtraction;

import java.util.ArrayList;
import java.util.List;

/**
 * 592. Fraction Addition and Subtraction
 *
 * https://leetcode-cn.com/problems/fraction-addition-and-subtraction/
 *
 * Given a string expression representing an expression of fraction addition and subtraction,
 * return the calculation result in string format.
 *
 * The final result should be an irreducible fraction. If your final result is an integer,
 * change it to the format of a fraction that has a denominator 1.
 *
 * So in this case, 2 should be converted to 2/1.
 */

public class Solution {

    private static class Fraction {
        private int numerator, denominator;
        private Fraction(int numerator, int denominator) {
            this.numerator = numerator;
            this.denominator = denominator;
            if (this.numerator != 0) {
                while (true) {
                    int div = Math.abs(gcd(this.numerator, this.denominator));
                    if (div <= 1) break;

                    this.numerator /= div;
                    this.denominator /= div;
                }
            } else this.denominator = 1;
        }
        public Fraction(String s) {
            boolean isNumerator = true;
            boolean isNegative = false;
            numerator = 0; denominator = 0;
            for (var c : s.toCharArray()) {
                if (c == '-') isNegative = true;
                else if (c == '+') isNegative = false;
                else if (c == '/') isNumerator = false;
                else if (isNumerator) numerator = numerator * 10 + (c - '0');
                else denominator = denominator * 10 + (c - '0');
            }
            if (isNegative) numerator *= -1;
        }
        public Fraction add(Fraction f) {
            int a = numerator , b = denominator;
            int c = f.numerator, d = f.denominator;
            if (b != d) { a *= d; c *= b; b *= d; }

            return new Fraction(a + c, b);
        }
        public String toString() { return numerator + "/" + denominator; }
        private int gcd(int a, int b) { return a % b == 0 ? b : gcd(b, a % b); }
    }

    public String fractionAddition(String expression) {
        int n = expression.length();
        List<Fraction> fractions = new ArrayList<>();
        for (int l = 0, r = 0; r <= n; r++) {
            if (r == n || (r != 0 && (expression.charAt(r) == '+' || expression.charAt(r) == '-'))) {
                fractions.add(new Fraction(expression.substring(l, r)));
                l = r;
            }
        }

        Fraction ans = fractions.get(0);
        for (int i = 1; i < fractions.size(); i++) {
            ans = ans.add(fractions.get(i));
        }
        return ans.toString();
    }

    public static void main(String[] args) {
        assert new Solution().fractionAddition("1/3-5/4+3/10").equals("-37/60");

        assert new Solution().fractionAddition("-1/2+1/2").equals("0/1");
        assert new Solution().fractionAddition("-1/2+1/2+1/3").equals("1/3");
        assert new Solution().fractionAddition("1/3-1/2").equals("-1/6");
    }

}
