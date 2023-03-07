package weekly.bw99.A;

import java.util.Arrays;

/**
 * 2578. Split With Minimum Sum
 *
 * https://leetcode.cn/contest/biweekly-contest-99/problems/split-with-minimum-sum/
 *
 * Given a positive integer num, split it into two non-negative integers num1 and num2 such that:
 *
 * The concatenation of num1 and num2 is a permutation of num.
 * In other words, the sum of the number of occurrences of each digit in num1 and num2 is equal
 * to the number of occurrences of that digit in num.
 *
 * num1 and num2 can contain leading zeros.
 * Return the minimum possible sum of num1 and num2.
 *
 * Notes:
 *
 * It is guaranteed that num does not contain any leading zeros.
 * The order of occurrence of the digits in num1 and num2 may differ from the order of occurrence of num.
 */

public class Solution {

    private int ans = Integer.MAX_VALUE;

    public int splitNum(int num) {
        char[] chars = String.valueOf(num).toCharArray();
        Arrays.sort(chars);
        dfs(chars, 0, 0, 0);
        return ans;
    }

    private void dfs(char[] chars, int i, int a, int b) {
        if (i == chars.length) {
            ans = Math.min(ans, a + b);
            return;
        }

        dfs(chars, i + 1, a * 10 + chars[i] - '0', b);
        dfs(chars, i + 1, a, b * 10 + chars[i] - '0');
    }

    private static class Optimize {
        public int splitNum(int num) {
            char[] chars = String.valueOf(num).toCharArray();
            Arrays.sort(chars);

            int a = 0, b = 0, n = chars.length;
            for (int i = 0; i < n; i += 2) a = a * 10 + chars[i] - '0';
            for (int i = 1; i < n; i += 2) b = b * 10 + chars[i] - '0';
            return a + b;
        }
    }

    public static void main(String[] args) {
    }

}
