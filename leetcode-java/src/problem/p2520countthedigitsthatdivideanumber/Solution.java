package problem.p2520countthedigitsthatdivideanumber;

/**
 * 2520. Count the Digits That Divide a Number
 *
 * https://leetcode.cn/problems/count-the-digits-that-divide-a-number/
 *
 * Given an integer num, return the number of digits in num that divide num.
 *
 * An integer val divides nums if nums % val == 0.
 */

public class Solution {

    public int countDigits(int num) {
        int[] digits = new int[10];
        for (int v = num; v != 0; v /= 10) {
            digits[v % 10]++;
        }

        int ans = 0;
        for (int i = 1; i < 10; i++) {
            if (num % i == 0) ans += digits[i];
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().countDigits(7) == 1;
        assert new Solution().countDigits(121) == 2;
        assert new Solution().countDigits(1248) == 4;
    }

}
