package weekly.w445.C;

import common.Tag;

/**
 * 3518. Smallest Palindromic Rearrangement II
 *
 * https://leetcode.cn/contest/weekly-contest-445/problems/smallest-palindromic-rearrangement-ii/
 *
 * You are given a palindromic string s and an integer k.
 *
 * Return the k-th lexicographically smallest palindromic permutation of s.
 *
 * If there are fewer than k distinct palindromic permutations, return an empty string.
 *
 * Note: Different rearrangements that yield the same palindromic
 * string are considered identical and are counted once.
 */

public class Solution {

    @Tag({"阶乘转组合数", "试填法"})
    public String smallestPalindrome(String s, int k) {
        // 我们要找到第 k 小的回文排列
        //  - 如果长度为奇数, 中间的字母是不可变的, 剩下的情况和偶数长度的一致
        //  - 如果长度是偶数, 由于前一半和后一半必须一样, 实际上就是找前一半的最小第 k 个排列
        //      - 已知条件是我们有 n1 个 a, n2 个 b, ..., n26 个 z
        //
        // 如果第一个位置选择填 a, 那么剩下的 n - 1 个位置有多少种填法?
        //  - 每个位置可以填一个 char, 但是不能填重复的, 也就是 n! / a! * b! * ... * z!
        //
        // 我们只需要枚举前一半的第 k 排列
        int[] cnt = new int[26]; int n = s.length() / 2;
        for (int i = 0; i < n; i++) cnt[s.charAt(i) - 'a']++;

        // 对于计算 n! / a! * b! * ... * z!
        //  - 我们可以将其转换为组合数的计算方式
        //  - 也就是有 n 个空位, 先填 a 的 c1 个, 即 C^n_c1
        //  - 后续我们剩下 n - c1 - ... - c_n 个空位, 需要填入 c_n+1 个元素
        //  - 也就是计算 C^n1_c1 * C^n2_c2 * ... * C^nk_ck
        //      - 这个计算可以使用 n / 1 * (n - 1) / 2 * (n - 3) / 3 * (n - 4) / 4 来避免大数问题
        //      - 从 [n, n - k] 共有 k 个数, 肯定是可以被 k 整除的
        if (perm(cnt, n, k) < k) return "";

        // 接下来枚举每一位应该填什么
        char[] left = new char[n];
        for (int i = 0; i < n; i++) {
            // 尝试填入字符 c - 'a'
            for (char c = 0; c < 26; c++) {
                if (cnt[c] == 0) continue;

                cnt[c]--; // 假设使用当前字符
                // 还剩下 n - i - 1 个位置需要填入剩下的字符
                int p = perm(cnt, n - i - 1, k);
                // 如果填入当前字符, 剩下的排列数可以覆盖 k, 则说明可以这么填
                if (p >= k) {
                    left[i] = (char) ('a' + c);
                    break;
                }

                // 否则我们无法凑到足够的 k 个数量, 得填下一个字母
                k -= p; cnt[c]++;
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(left);
        // 检查是否需要加入中间位置
        if ((s.length() & 1) == 1) sb.append(s.charAt(n));
        // 补充右边的位置
        for (int i = n - 1; i >= 0; i--) sb.append(left[i]);

        return sb.toString();
    }

    private int perm(int[] cnt, int n, int k) {
        long ans = 1;
        for (var c : cnt) {
            if (c == 0) continue;

            // 从 n 里选择 c 个位置填充
            ans *= comb(n, c, k);
            if (ans >= k) return k;

            // 移除已填的位置
            n -= c;
        }
        return (int) ans;
    }

    // 使用 c 个字符填充 n 个位置不同的方案数, 如果超过 k 则返回 k
    private int comb(int n, int c, int k) {
        long ans = 1; c = Math.min(c, n - c);
        for (int i = 1; i <= c; i++) {
            ans = ans * (n - i + 1) / i;
            if (ans >= k) return k;
        }

        return (int) ans;
    }

    public static void main(String[] args) {
        assert new Solution().smallestPalindrome("dmtmd", 3).equals("");
        assert new Solution().smallestPalindrome("abba", 2).equals("baab");
        assert new Solution().smallestPalindrome("aa", 2).equals("");
        assert new Solution().smallestPalindrome("bacab", 1).equals("abcba");
    }

}
