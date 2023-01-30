package weekly.w329.C;

/**
 * 2546. Apply Bitwise Operations to Make Strings Equal
 *
 * https://leetcode.cn/problems/apply-bitwise-operations-to-make-strings-equal/
 *
 * You are given two 0-indexed binary strings s and target of the same length n.
 *
 * You can do the following operation on s any number of times:
 *
 * Choose two different indices i and j where 0 <= i, j < n.
 * Simultaneously, replace s[i] with (s[i] OR s[j]) and s[j] with (s[i] XOR s[j]).
 *
 * For example, if s = "0110", you can choose i = 0 and j = 2, then simultaneously
 * replace s[0] with (s[0] OR s[2] = 0 OR 1 = 1), and s[2] with (s[0] XOR s[2] = 0 XOR 1 = 1),
 * so we will have s = "1110".
 *
 * Return true if you can make the string s equal to target, or false otherwise.
 */

public class Solution {

    public boolean makeStringsEqual(String s, String target) {
        char[] ss = s.toCharArray();
        char[] ts = target.toCharArray();

        // swaps 表示想从 1 变成 0 的数量
        int f01 = 0, f10 = 0, ones = 0;
        for (int i = 0; i < ss.length; i++) {
            // 如果 s1 想从 0 变成 1 就需要找一个 s2 它需要从 1 变成 0
            if (ss[i] == '0' && ts[i] == '1') f01++;
            // 计数 s 想从 1 变成 0
            else if (ss[i] == '1' && ts[i] == '0') f10++;
            else if (ts[i] == '1') ones++;
        }

        // 没有需要交换的
        if (f01 == 0 && f10 == 0) return true;
        if (f01 != 0 && (f10 != 0 || ones != 0)) {
            f10 += f01; ones += f01;
        }

        // 如果 swaps 为负数, 则说明有很多 0 想变成 1, 需要找个 1 操作一下
        if (f10 < 0 && ones > 0) return true;
        // 如果 swaps 为正数, 则说明有很多 1 想变成 0, 也需要找个 1 操作一下
        if (f10 > 0 && ones > 0) return true;

        return false;
    }

    public static void main(String[] args) {
        assert new Solution().makeStringsEqual("0100", "1100");
        assert new Solution().makeStringsEqual("00000100000", "11011011011");
        assert !new Solution().makeStringsEqual("00", "10");

        assert new Solution().makeStringsEqual("101110100", "110011000");
    }

}
