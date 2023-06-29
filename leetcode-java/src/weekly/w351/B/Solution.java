package weekly.w351.B;

/**
 * 2749. Minimum Operations to Make the Integer Zero
 *
 * https://leetcode.cn/contest/weekly-contest-351/problems/minimum-operations-to-make-the-integer-zero/
 *
 * You are given two integers num1 and num2.
 *
 * In one operation, you can choose integer i in the range [0, 60] and subtract 2i + num2 from num1.
 *
 * Return the integer denoting the minimum number of operations needed to make num1 equal to 0.
 *
 * If it is impossible to make num1 equal to 0, return -1.
 */

public class Solution {

    public int makeTheIntegerZero(int num1, int num2) {
        for (long k = 1; k <= num1 - num2 * k; k++) {
            if (k >= Long.bitCount(num1 - num2 * k)) {
                return (int) k;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
    }

}
