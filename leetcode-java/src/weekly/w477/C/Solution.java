package weekly.w477.C;

import common.Checker;

import java.util.stream.IntStream;

/**
 * Q3. Concatenate Non-Zero Digits and Multiply by Sum II
 *
 * https://leetcode.cn/contest/weekly-contest-477/problems/concatenate-non-zero-digits-and-multiply-by-sum-ii/
 *
 * You are given a string s of length m consisting of digits. You are also given a 2D integer
 * array queries, where queries[i] = [li, ri].
 *
 * For each queries[i], extract the substring s[li..ri]. Then, perform the following:
 *
 * Form a new integer x by concatenating all the non-zero digits from the substring in their
 * original order. If there are no non-zero digits, x = 0.
 *
 * Let sum be the sum of digits in x. The answer is x * sum.
 *
 * Return an array of integers answer where answer[i] is the answer to the ith query.
 *
 * Since the answers may be very large, return them modulo 1e9 + 7.
 */

public class Solution {

    private static final int MOD = 1_000_000_007;
    private static final int MAX_N = 100_001;
    private static final int[] pow10 = new int[MAX_N];
    static { pow10[0] = 1; }
    static { for (int i = 1; i < MAX_N; i++) pow10[i] = (int) ((pow10[i - 1] * 10L) % MOD); }

    public int[] sumAndMultiply(String s, int[][] queries) {
        int n = s.length();
        // 对于 1234... 这个数字, 我们我们已知从 [0, i] 的数字是 v_i
        //  - 那么对于 [l, i] 的数字我们可以使用 v_i - v_l * 10 ^ (i - l + 1) 的方式得到
        //      - 也就是例如对于 i = 3, v_i = 1234, 如果我们只需要知道 [2, 3] 的数字的话, 此时 v_l = 12
        //      - v_i - v_l * 10 ^ (i - l + 1) = 1234 - 12 * 10 ^ 2 = 34
        //
        // 如果出现 0 的话, 我们需要将其扣除. 剩下就是用前缀和计算所有的值即可
        int[] digitSum = new int[n + 1]; // 数字的前缀和
        int[] preSum = new int[n + 1]; // 数的前缀和
        int[] nonZeroSum = new int[n + 1]; // 非 0 数字个数前缀和
        for (int i = 0; i < n; i++) {
            int digit = s.charAt(i) - '0';

            digitSum[i + 1] = digitSum[i] + digit;
            preSum[i + 1] = digit == 0 ? preSum[i] : (int) ((preSum[i] * 10L + digit) % MOD);
            nonZeroSum[i + 1] = nonZeroSum[i] + (digit != 0 ? 1 : 0);
        }

        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int l = queries[i][0], r = queries[i][1] + 1;
            // 计算从 [l, r) 有多少个非零的字符, 也就是计算指数是多少
            int digits = nonZeroSum[r] - nonZeroSum[l];
            // 计算 [l, r) 的数是多少, 也就是 v_r - v_l * 10 ^ digits, 且需要保证结果非负
            long base = preSum[r] - (((long) preSum[l] * pow10[digits]) % MOD) + MOD;
            // 保存答案
            ans[i] = (int) ((base * (digitSum[r] - digitSum[l])) % MOD);
        }
        return ans;
    }

    public static void main(String[] args) {
        long v = 57569977386369791L;
        assert Checker.check(new Solution().sumAndMultiply(String.valueOf(v), new int[][]{
            {0,0},{0,1},{0,2},{0,3},{0,4},{0,5},{0,6},{0,7},{0,8},{0,9},{0,10},{0,11},{0,12},{0,13},{0,14},{0,15},{0,16}
        }), IntStream.range(0, 17).map((i) -> {
            char[] chars = String.valueOf(v).substring(0, i + 1).toCharArray();
            int sum = 0; for (var c : chars) sum += c - '0';
            return (int) ((Long.parseLong(new String(chars)) * sum) % 1_000_000_007);
        }).toArray());

        assert Checker.check(new Solution().sumAndMultiply("10203004", new int[][]{{0,7},{1,3},{4,6}}), new int[]{12340, 4, 9});
        assert Checker.check(new Solution().sumAndMultiply("1000", new int[][]{{0,3},{1,1}}), new int[]{1,0});
        assert Checker.check(new Solution().sumAndMultiply("9876543210", new int[][]{{0,9}}), new int[]{444444137});
    }

}
