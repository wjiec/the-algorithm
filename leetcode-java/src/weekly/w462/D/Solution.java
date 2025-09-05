package weekly.w462.D;

import java.util.ArrayList;
import java.util.List;

/**
 * Q4. Next Special Palindrome Number
 *
 * https://leetcode.cn/contest/weekly-contest-462/problems/next-special-palindrome-number/
 *
 * You are given an integer n.
 *
 * A number is called special if:
 *
 * It is a palindrome.
 * Every digit k in the number appears exactly k times.
 *
 * Return the smallest special number strictly greater than n.
 */

public class Solution {

    // 1 只能出现 1 次, 2 只能出现 2 次, ...
    public long specialPalindrome(long n) {
        int nc = String.valueOf(n).length();
        // 我们最多只需要使用 len(c) 或者 len(c) + 1 个数字就行
        //  - 也就是从 1 2 3 4 5 6 7 8 9 中挑出几个数, 使得和为 len(c) 或者 len(c) + 1
        //      - 凑硬币的方式
        //  - 再使用这些数字构建出大于 n 的最小整数
        record Plan(int sum, List<Integer> coins, boolean single) {}
        List<Plan> plans = new ArrayList<>();
        for (int i = 1; i < 10; i++) {
            int currSize = plans.size();
            for (int j = 0; j < currSize; j++) {
                var curr = plans.get(j);
                if (i % 2 == 0 || !curr.single) {
                    var newList = new ArrayList<>(curr.coins); newList.add(i);
                    plans.add(new Plan(curr.sum + i, newList, curr.single || (i % 2 == 1)));
                }
            }
            plans.add(new Plan(i, List.of(i), i % 2 == 1));

            for (int j = currSize; j < plans.size(); j++) {
                var curr = plans.get(j);
                if (curr.sum - nc >= 0 && curr.sum - nc <= 1) {
                    palindrome(n, curr.coins, curr.sum);
                }
            }
        }

        return ans;
    }

    private long ans = Long.MAX_VALUE;

    // 使用 mask 组装出所有可能的数
    public void palindrome(long n, List<Integer> digits, int len) {
        if (digits.size() == 1) {
            long curr = Long.parseLong(String.valueOf(digits.get(0)).repeat(digits.get(0)));;
            if (curr > n) ans = Math.min(ans, curr);
            return;
        }

        // 我们只需要组装一半就可以, 剩下的一半直接镜像出来
        char[] chars = new char[len]; int[] ds = new int[10];
        for (var d : digits) {
            ds[d] = d / 2;
            if (d % 2 == 1) chars[len / 2] = (char) ('0' + d);
        }
        dfs(n, ds, chars, 0);
    }

    private void dfs(long n, int[] digits, char[] chars, int i) {
        if (i == chars.length / 2) {
            for (int j = 0; j < i; j++) chars[chars.length - j - 1] = chars[j];
            long curr = Long.parseLong(new String(chars));
            if (curr > n) ans = Math.min(ans, curr);
            return;
        }

        for (int j = 1; j < 10; j++) {
            if (digits[j] != 0) {
                digits[j]--; chars[i] = (char) ('0' + j);
                dfs(n, digits, chars, i + 1);
                digits[j]++;
            }
        }
    }

    public static void main(String[] args) {
        assert new Solution().specialPalindrome(29388578661L) == 29999999992L;
        assert new Solution().specialPalindrome(916681587L) == 999999999L;
        assert new Solution().specialPalindrome(18273616235612L) == 24488888888442L;

        assert new Solution().specialPalindrome(2) == 22;
        assert new Solution().specialPalindrome(33) == 212;
    }

}
