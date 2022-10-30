package weekly.w317.C;

/**
 * 6222. Minimum Addition to Make Integer Beautiful
 *
 * https://leetcode.cn/contest/weekly-contest-317/problems/minimum-addition-to-make-integer-beautiful/
 *
 * You are given two positive integers n and target.
 *
 * An integer is considered beautiful if the sum of its digits is less than or equal to target.
 *
 * Return the minimum non-negative integer x such that n + x is beautiful.
 * The input will be generated such that it is always possible to make n beautiful.
 */

public class Solution {

    public long makeIntegerBeautiful(long n, int target) {
        int[] digits = getDigits(n);

        int sum = sumDigits(digits);
        if (sum <= target) return 0;

        int len = digits.length;
        for (int i = len - 2; i >= 0; i--) {
            int[] nd = copyDigits(digits);
            nd[i] = digits[i] + 1;
            for (int j = i + 1; j < len; j++) nd[j] = 0;
            if (sumDigits(nd) <= target) {
                return d2long(nd) - n;
            }
        }

        long curr = 1;
        while (curr < n) curr *= 10;
        return curr - n;
    }

    private int[] copyDigits(int[] digits) {
        int[] newDigits = new int[digits.length];
        System.arraycopy(digits, 0, newDigits, 0, digits.length);
        return newDigits;
    }

    private int[] getDigits(long n) {
        int len = 0;
        for (long v = n; v != 0; v /= 10) len++;
        int[] digits = new int[len];

        for (int i = len - 1; n != 0; n /= 10, i--) digits[i] = (int) (n % 10);
        return digits;
    }

    private int sumDigits(int[] digits) {
        int sum = 0;
        for (var v : digits) sum += v;
        return sum;
    }

    private long d2long(int[] digits) {
        long ans = 0;
        for (var v : digits) ans = ans * 10 + v;
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().makeIntegerBeautiful(165, 10) == 5;
        assert new Solution().makeIntegerBeautiful(8, 2) == 2;

        assert new Solution().makeIntegerBeautiful(16, 6) == 4;
        assert new Solution().makeIntegerBeautiful(467, 6) == 33;
        assert new Solution().makeIntegerBeautiful(1, 1) == 0;
    }

}
