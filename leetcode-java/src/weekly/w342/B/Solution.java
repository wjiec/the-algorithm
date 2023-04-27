package weekly.w342.B;

/**
 * 2652. Sum Multiples
 *
 * https://leetcode.cn/contest/weekly-contest-342/problems/sum-multiples/
 *
 * Given a positive integer n, find the sum of all integers in the
 * range [1, n] inclusive that are divisible by 3, 5, or 7.
 *
 * Return an integer denoting the sum of all numbers in the given range satisfying the constraint.
 */

public class Solution {

    public int sumOfMultiples(int n) {
        int ans = 0;
        for (int i = 1; i <= n; i++) {
            if (i % 3 == 0 || i % 5 == 0 || i % 7 == 0) {
                ans += i;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
    }

}
