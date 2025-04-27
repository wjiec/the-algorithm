package weekly.w445.D;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

/**
 * 3519. Count Numbers with Non-Decreasing Digits
 *
 * https://leetcode.cn/contest/weekly-contest-445/problems/count-numbers-with-non-decreasing-digits/
 *
 * You are given two integers, l and r, represented as strings, and an integer b.
 *
 * Return the count of integers in the inclusive range [l, r] whose
 * digits are in non-decreasing order when represented in base b.
 *
 * An integer is considered to have non-decreasing digits if, when read from
 * left to right (from the most significant digit to the least significant digit),
 * each digit is greater than or equal to the previous one.
 *
 * Since the answer may be too large, return it modulo 1e9 + 7.
 */

public class Solution {

    public int countNumbers(String l, String r, int b) {
        return (int) ((count(r, b, true) - count(l, b, false) + MOD) % MOD);
    }

    private final long MOD = 1_000_000_007;

    // 计算在不超过 chars 的数字中以 b 进制非递减的整数个数
    private long count(String s, int b, boolean add) {
        memo.clear();
        // 将一个数从十进制转换为 b 进制的方法
        var bi = new BigInteger(s);
        if (add) bi = bi.add(BigInteger.ONE);
        char[] chars = bi.toString(b).toCharArray();

        // 如果 r + 1 是非递减的话, 我们需要使得 r + 1 变得有实际意义, 也就是让 r + 1 能真正 > r
        return (dfs(chars, 0, '\0', false, true, b) + (isSortedNumbers(chars) ? 0 : 1)) % MOD;
    }

    private final Map<Integer, Long> memo = new HashMap<>();

    private long dfs(char[] chars, int i, char prev, boolean valid, boolean limited, int b) {
        if (i == chars.length) return valid ? 1 : 0;
        int key = (i << 16) | prev;
        if (!limited && memo.containsKey(key)) return memo.get(key);

        long ans = 0;
        if (!valid) ans += dfs(chars, i + 1, prev, false, false, b);

        char lower = valid ? (char) Math.max(prev, '0') : '1';
        char upper = limited ? chars[i] : (char) ('0' + b - 1);
        for (char c = lower; c <= upper; c++) {
            ans = (ans + dfs(chars, i + 1, c, true, limited && c == chars[i], b)) % MOD;
        }

        memo.put(key, ans % MOD);
        return ans % MOD;
    }

    private boolean isSortedNumbers(char[] c) {
        for (int i = 1; i < c.length; i++) {
            if (c[i] < c[i - 1]) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        assert new Solution().countNumbers("1", "1", 2) == 1;

        assert new Solution().countNumbers("23", "28", 8) == 3;
        assert new Solution().countNumbers("2", "7", 2) == 2;
    }

}
