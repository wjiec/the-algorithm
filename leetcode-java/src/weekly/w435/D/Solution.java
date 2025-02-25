package weekly.w435.D;

/**
 * 3445. Maximum Difference Between Even and Odd Frequency II
 *
 * https://leetcode.cn/contest/weekly-contest-435/problems/maximum-difference-between-even-and-odd-frequency-ii/
 *
 * You are given a string s and an integer k. Your task is to find the maximum difference
 * between the frequency of two characters, freq[a] - freq[b], in a substring subs of s, such that:
 *
 * subs has a size of at least k.
 * Character a has an odd frequency in subs.
 * Character b has an even frequency in subs.
 * Return the maximum difference.
 *
 * Note that subs can contain more than 2 distinct characters.
 */

public class Solution {

    // s 中只有 0, 1, 2, 3, 4
    public int maxDifference(String s, int k) {
        char[] chars = s.toCharArray();
        boolean[] seen = new boolean[128];
        for (var c : chars) seen[c] = true;

        // 可以枚举奇数数字是 a, 偶数数字是 b, 求长度至少为 k 的子字符串中 a - b 的最大值
        int ans = Integer.MIN_VALUE;
        for (char a = '0'; a <= '4'; a++) {
            if (!seen[a]) continue;
            for (char b = '0'; b <= '4'; b++) {
                if (a == b || !seen[b]) continue;
                ans = Math.max(ans, maxDifference(chars, k, a, b));
            }
        }
        return ans;
    }

    // 求 chars 中长度至少为 k 的子序列中, a 出现奇数次且 b 出现偶数次时 freq[a] - freq[b] 的最大值
    private int maxDifference(char[] chars, int k, char a, char b) {
        // 要想 freq[a] - freq[b] 则 freq[a] 需要尽可能大, 同时 freq[b] 尽可能小
        //  - 可以在序列中将 a 变为 1, b 变为 -1, 求子数组和最大, 同时
        return 1;
    }

    public static void main(String[] args) {
    }

}
