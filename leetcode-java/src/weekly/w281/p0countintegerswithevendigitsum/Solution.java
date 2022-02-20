package weekly.w281.p0countintegerswithevendigitsum;

/**
 * 6012. Count Integers With Even Digit Sum
 *
 * https://leetcode-cn.com/contest/weekly-contest-281/problems/count-integers-with-even-digit-sum/
 *
 * Given a positive integer num, return the number of positive integers less than
 * or equal to num whose digit sums are even.
 *
 * The digit sum of a positive integer is the sum of all its digits.
 */

public class Solution {

    public int countEven(int num) {
        int ans = 0;
        for (int i = 1; i <= num; i++) {
            if (isEven(i)) ans++;
        }
        return ans;
    }

    public boolean isEven(int v) {
        int n = 0;
        for (; v != 0; v /= 10) n += v % 10;
        return n % 2 == 0;
    }

    public static void main(String[] args) {
        assert new Solution().countEven(4) == 2;
        assert new Solution().countEven(30) == 14;
        assert new Solution().countEven(100) == 49;
        assert new Solution().countEven(99) == 49;
    }

}
