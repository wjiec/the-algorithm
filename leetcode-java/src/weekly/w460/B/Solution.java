package weekly.w460.B;

/**
 * Q2. Maximum Number of Subsequences After One Inserting
 *
 * https://leetcode.cn/contest/weekly-contest-460/problems/maximum-number-of-subsequences-after-one-inserting/
 *
 * You are given a string s consisting of uppercase English letters.
 *
 * You are allowed to insert at most one uppercase English letter
 * at any position (including the beginning or end) of the string.
 *
 * Return the maximum number of "LCT" subsequences that can be formed
 * in the resulting string after at most one insertion.
 */

public class Solution {

    public long numOfSubsequences(String s) {
        char[] chars = s.toCharArray();
        // 枚举 C, 找到左侧 L 的数量 ln, 找到右侧 T 的数量 tn, 累加所有的 ln * tn
        //  - 选择在最左侧插入一个 L, 选择在最右侧插入一个 T
        //  - 中间某个位置插入一个 C
        //      - 也就是原本的序列中某个 ln * tn 值出现 2 次
        long tn = 0, ln = 0;
        for (var c : chars) tn += c == 'T' ? 1 : 0;

        long ans = 0, addL = 0, addT = 0, maxC = 0;
        for (var c : chars) {
            switch (c) {
                case 'L' -> maxC = Math.max(maxC, ++ln * tn);
                case 'C' -> {
                    ans += ln * tn;
                    maxC = Math.max(maxC, ln * tn);
                    addL += (ln + 1) * tn;
                    addT += ln * (tn + 1);
                }
                case 'T' -> maxC = Math.max(maxC, ln * --tn);
            }
        }
        return Math.max(ans + maxC, Math.max(addL, addT));
    }

    public static void main(String[] args) {
        assert new Solution().numOfSubsequences("LT") == 1;
    }

}
