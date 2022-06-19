package weekly.w298.B;

import java.util.Arrays;
import java.util.TreeSet;

/**
 * 5218. Sum of Numbers With Units Digit K
 *
 * https://leetcode.cn/contest/weekly-contest-298/problems/sum-of-numbers-with-units-digit-k/
 *
 * Given two integers num and k, consider a set of positive integers with the following properties:
 *
 * The units digit of each integer is k.
 * The sum of the integers is num.
 * Return the minimum possible size of such a set, or -1 if no such set exists.
 *
 * Note:
 *
 * The set can contain multiple instances of the same integer, and the sum of an empty set is considered 0.
 * The units digit of a number is the rightmost digit of the number.
 */

public class Solution {

    public int minimumNumbers(int num, int k) {
        if (num == 0) return 0;
        if (k > num) return -1;
        if (k == num) return 1;

        int[] dp = new int[num + 1];
        Arrays.fill(dp, -1); dp[0] = 0;
        TreeSet<Integer> possibles = allPossible(num, k);
        for (var n : possibles) dp[n] = 1;
        for (int i = 1; i <= num; i++) {
            if (dp[i] == -1) {
                for (var n : possibles) {
                    if (i - n >= 0 && dp[i - n] != -1) {
                        if (dp[i] == -1) dp[i] = dp[i - n] + 1;
                        else dp[i] = Math.min(dp[i], dp[i - n] + 1);
                    } else break;
                }
            }
        }
        return dp[num];
    }

    private TreeSet<Integer> allPossible(int num, int k) {
        TreeSet<Integer> set = new TreeSet<>();
        for (int i = 0; i < 300; i++) {
            int m10 = i * 10 + k, m100 = i * 100 + k, m1000 = i * 1000 + k;
            if (m10 <= num) set.add(m10);
            if (m100 <= num) set.add(m100);
            if (m1000 <= num) set.add(m1000);
        }
        return set;
    }

    public static void main(String[] args) {
        assert new Solution().minimumNumbers(2, 8) == -1;
        assert new Solution().minimumNumbers(37, 2) == -1;
    }

}
