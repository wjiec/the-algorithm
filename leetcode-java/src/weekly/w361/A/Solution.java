package weekly.w361.A;

/**
 * 2843. Count Symmetric Integers
 *
 * https://leetcode.cn/contest/weekly-contest-361/problems/count-symmetric-integers/
 *
 * You are given two positive integers low and high.
 *
 * An integer x consisting of 2 * n digits is symmetric if the sum of the
 * first n digits of x is equal to the sum of the last n digits of x.
 *
 * Numbers with an odd number of digits are never symmetric.
 *
 * Return the number of symmetric integers in the range [low, high].
 */

public class Solution {

    public int countSymmetricIntegers(int low, int high) {
        int ans = 0;
        for (int i = low; i <= high; i++) {
            if (isSymmetric(i)) ans++;
        }
        return ans;
    }

    private boolean isSymmetric(int v) {
        String s = String.valueOf(v);
        if (s.length() % 2 != 0) return false;

        int sum = 0, n = s.length() / 2;
        for (int i = 0; i < s.length(); i++) {
            if (i < n) sum += s.charAt(i) - '0';
            else sum -= s.charAt(i) - '0';
        }
        return sum == 0;
    }

    public static void main(String[] args) {
    }

}
