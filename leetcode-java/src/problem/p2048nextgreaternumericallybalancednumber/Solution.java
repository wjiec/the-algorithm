package problem.p2048nextgreaternumericallybalancednumber;

/**
 * 2048. Next Greater Numerically Balanced Number
 *
 * https://leetcode.cn/problems/next-greater-numerically-balanced-number/
 *
 * An integer x is numerically balanced if for every digit d in the number x, there are
 * exactly d occurrences of that digit in x.
 *
 * Given an integer n, return the smallest numerically balanced number strictly
 * greater than n.
 */

public class Solution {

    public int nextBeautifulNumber(int n) {
        while (!isBalanced(++n)) {}
        return n;
    }

    private boolean isBalanced(int n) {
        int[] bits = new int[10];
        for (; n != 0; n /= 10) bits[n % 10]++;
        for (int i = 0; i < 10; i++) {
            if (bits[i] != 0 && bits[i] != i) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        assert new Solution().nextBeautifulNumber(1) == 22;
        assert new Solution().nextBeautifulNumber(1000) == 1333;
        assert new Solution().nextBeautifulNumber(3000) == 3133;
    }

}
