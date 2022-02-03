package daily.d220203pfindtheminimumnumberoffibonaccinumberswhosesumisk;

import java.util.ArrayList;
import java.util.List;

/**
 * 1414. Find the Minimum Number of Fibonacci Numbers Whose Sum Is K
 *
 * https://leetcode-cn.com/problems/find-the-minimum-number-of-fibonacci-numbers-whose-sum-is-k/
 *
 * Given an integer k, return the minimum number of Fibonacci numbers whose sum is equal to k.
 *
 * The same Fibonacci number can be used multiple times.
 *
 * The Fibonacci numbers are defined as:
 *
 * F1 = 1
 * F2 = 1
 * Fn = Fn-1 + Fn-2 for n > 2.
 *
 * It is guaranteed that for the given constraints we can always find such Fibonacci numbers that sum up to k.
 */

public class Solution {

    public int findMinFibonacciNumbers(int k) {
        List<Integer> fibs = new ArrayList<>(); fibs.add(1);
        for (int a = 1, b = 1, c; a + b <= k; a = b, b = c) {
            c = a + b;
            fibs.add(c);
        }

        int ans = 0;
        for (int i = fibs.size() - 1; i >= 0 && k > 0; i--) {
            int v = fibs.get(i);
            if (k >= v) {
                k -= v;
                ans++;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().findMinFibonacciNumbers(5) == 1;

        assert new Solution().findMinFibonacciNumbers(7) == 2;
        assert new Solution().findMinFibonacciNumbers(10) == 2;
        assert new Solution().findMinFibonacciNumbers(19) == 3;
    }

}
