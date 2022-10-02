package weekly.w313.A;

/**
 * 6192. Number of Common Factors
 *
 * https://leetcode.cn/problems/number-of-common-factors/
 *
 * Given two positive integers a and b, return the number of common factors of a and b.
 *
 * An integer x is a common factor of a and b if x divides both a and b.
 */

public class Solution {

    public int commonFactors(int a, int b) {
        int ans = 0;
        for (int i = 1; i <= Math.min(a, b); i++) {
            if (a % i == 0 && b % i == 0) ans++;
        }
        return ans;
    }

    public static void main(String[] args) {
    }

}
