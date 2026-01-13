package weekly.bw168.B;

/**
 * Q2. Maximize Sum of Squares of Digits
 *
 * https://leetcode.cn/contest/biweekly-contest-168/problems/maximize-sum-of-squares-of-digits/
 *
 * You are given two positive integers num and sum.
 *
 * A positive integer n is good if it satisfies both of the following:
 *
 * The number of digits in n is exactly num.
 * The sum of digits in n is exactly sum.
 * The score of a good integer n is the sum of the squares of digits in n.
 *
 * Return a string denoting the good integer n that achieves the maximum score.
 * If there are multiple possible integers, return the maximum one.
 * If no such integer exists, return an empty string.
 */

public class Solution {

    public String maxSumOfSquares(int num, int sum) {
        // 数位数量为 num, 数位之和为 sum, 要求是数位的平方和最大
        //  - 每个数位最大可以填 9, 如果 sum > 9 * num 则无法填入
        if (sum > 9 * num) return "";

        // 剩下的就是填能填的最大的
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < num; i++) {
            int digit = Math.min(9, sum);
            sb.append(digit); sum -= digit;
        }
        return sb.toString();
    }

    private static class Optimization {
        public String maxSumOfSquares(int num, int sum) {
            if (sum > 9 * num) return "";
            int div = sum / 9, mod = sum % 9, rest = num - div - (mod == 0 ? 0 : 1);
            return "9".repeat(div) + (mod == 0 ? "" : mod) + "0".repeat(rest);
        }
    }

    public static void main(String[] args) {
        long[] max = new long[1000];
        String[] maxNumber = new String[1000];

        for (int a = 1; a < 10; a++) {
            for (int b = 0; b < 10; b++) {
                for (int c = 0; c < 10; c++) {
                    int s = a + b + c, score = a * a + b * b + c * c;
                    if (score > max[s]) {
                        max[s] = score; maxNumber[s] = String.format("%d-%d-%d", a, b, c);
                    }
                }
            }
        }

        for (int i = 0; i < max.length; i++) {
            if (max[i] != 0) {
                System.out.printf("%d = %d -> %s\n", i, max[i], maxNumber[i]);
            }
        }
    }

}
