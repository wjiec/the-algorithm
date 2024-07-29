package weekly.w408.B;

import ability.Prime;

/**
 * 3233. Find the Count of Numbers Which Are Not Special
 *
 * https://leetcode.cn/contest/weekly-contest-408/problems/find-the-count-of-numbers-which-are-not-special/
 *
 * You are given 2 positive integers l and r. For any number x, all positive
 * divisors of x except x are called the proper divisors of x.
 *
 * A number is called special if it has exactly 2 proper divisors. For example:
 *
 * The number 4 is special because it has proper divisors 1 and 2.
 * The number 6 is not special because it has proper divisors 1, 2, and 3.
 *
 * Return the count of numbers in the range [l, r] that are not special.
 */

@SuppressWarnings("DuplicatedCode")
public class Solution {

    public int nonSpecialCount(int l, int r) {
        int ans = r - l + 1;
        for (int i = 2; i * i <= r; i++) {
            if (Prime.isPrime(i)) ans += (i * i < l ? 0 : -1);
        }
        return ans;
    }

    public static void main(String[] args) {
        assert  new Solution().nonSpecialCount(182, 18677) == 18470;

        assert  new Solution().nonSpecialCount(5,7) == 3;
        assert new Solution().nonSpecialCount(4,16) == 11;
    }

}
