package problem.p2443sumofnumberanditsreverse;

/**
 * 2443. Sum of Number and Its Reverse
 *
 * https://leetcode.cn/problems/sum-of-number-and-its-reverse/
 *
 * Given a non-negative integer num, return true if num can be expressed as
 * the sum of any non-negative integer and its reverse, or false otherwise.
 */

@SuppressWarnings("DuplicatedCode")
public class Solution {

    public boolean sumOfNumberAndReverse(int num) {
        for (int i = 0; i <= num; i++) {
            if (i + reverse(i) == num) return true;
        }
        return false;
    }

    private int reverse(int v) {
        int len = 0;
        int[] digits = new int[32];
        for (; v != 0; v /= 10) digits[len++] = v % 10;

        int ans = 0;
        for (int i = 0; i < len; i++) ans = ans * 10 + digits[i];
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().sumOfNumberAndReverse(443);
        assert !new Solution().sumOfNumberAndReverse(63);
        assert new Solution().sumOfNumberAndReverse(181);
    }

}
