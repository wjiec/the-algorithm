package daily.d210808pnthtribonaccinumber;

/**
 * 1137. N-th Tribonacci Number
 *
 * https://leetcode-cn.com/problems/n-th-tribonacci-number/
 *
 * The Tribonacci sequence Tn is defined as follows: 
 *
 * T0 = 0, T1 = 1, T2 = 1, and Tn+3 = Tn + Tn+1 + Tn+2 for n >= 0.
 *
 * Given n, return the value of Tn.
 */

public class Solution {

    public int tribonacci(int n) {
        if (n == 0) return 0;

        int a = 0, b = 1, c = 1;
        for (int i = 2; i < n; i++) {
            int s = a + b + c;
            a = b; b = c; c = s;
        }
        return c;
    }

    public static void main(String[] args) {
        assert new Solution().tribonacci(4) == 4;
        assert new Solution().tribonacci(25) == 1389537;
    }

}
