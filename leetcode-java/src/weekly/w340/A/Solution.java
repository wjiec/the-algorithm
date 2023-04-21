package weekly.w340.A;

/**
 * 2614. Prime In Diagonal
 *
 * https://leetcode.cn/contest/weekly-contest-340/problems/prime-in-diagonal/
 *
 * You are given a 0-indexed two-dimensional integer array nums.
 *
 * Return the largest prime number that lies on at least one of the diagonals of nums.
 * In case, no prime is present on any of the diagonals, return 0.
 *
 * Note that:
 *
 * An integer is prime if it is greater than 1 and has no positive integer
 * divisors other than 1 and itself.
 *
 * An integer val is on one of the diagonals of nums if there exists an integer i
 * for which nums[i][i] = val or an i for which nums[i][nums.length - i - 1] = val.
 */

@SuppressWarnings("DuplicatedCode")
public class Solution {

    public int diagonalPrime(int[][] nums) {
        int ans = 0, n = nums.length;
        for (int i = 0; i < n; i++) {
            if (isPrime(nums[i][i])) ans = Math.max(ans, nums[i][i]);
            if (isPrime(nums[i][n - i - 1])) ans = Math.max(ans, nums[i][n - i - 1]);
        }
        return ans;
    }

    private boolean isPrime(int n) {
        if (n < 3 || n % 2 == 0) return n == 2;
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) return false;
        }
        return true;
    }

    public static void main(String[] args) {
    }

}
