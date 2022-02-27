package daily.d220225p537complexnumbermultiplication;

/**
 * 537. Complex Number Multiplication
 *
 * https://leetcode-cn.com/problems/complex-number-multiplication/
 *
 * A complex number can be represented as a string on the form "real+imaginaryi" where:
 *
 * real is the real part and is an integer in the range [-100, 100].
 * imaginary is the imaginary part and is an integer in the range [-100, 100].
 *
 * i^2 == -1.
 *
 * Given two complex numbers num1 and num2 as strings,
 * return a string of the complex number that represents their multiplications.
 */

public class Solution {

    private static class Complex {
        private final int real, imaginary;
        public Complex(int real, int imaginary) { this.real = real; this.imaginary = imaginary; }
        public String toString() { return String.format("%d+%di", real, imaginary); }
        public Complex multiply(Complex o) {
            int a = real * o.real, b = real * o.imaginary;
            int c = imaginary * o.real, d = imaginary * o.imaginary;
            return new Complex(a - d, b + c);
        }
        public static Complex valueOf(String s) {
            int real = 0, imaginary = 0, i = 0, rFactor = 1, rImaginary = 1;
            for (; i < s.length(); i++) {
                if (s.charAt(i) == '-') rFactor = -1;
                else if (s.charAt(i) == '+' && i != 0) break;
                else real = real * 10 + (s.charAt(i) - '0');
            }

            for (i += 1; i < s.length(); i++) {
                if (s.charAt(i) == '-') rImaginary = -1;
                else if (s.charAt(i) != 'i') imaginary = imaginary * 10 + (s.charAt(i) - '0');
            }

            return new Complex(real * rFactor, imaginary * rImaginary);
        }
    }

    public String complexNumberMultiply(String num1, String num2) {
        return Complex.valueOf(num1).multiply(Complex.valueOf(num2)).toString();
    }

    public static void main(String[] args) {
        assert new Solution().complexNumberMultiply("1+1i", "1+1i").equals("0+2i");
        assert new Solution().complexNumberMultiply("1+-1i", "1+-1i").equals("0+-2i");
    }

}
